package com.example.eg_sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eg_sns.core.AppNotFoundException;
import com.example.eg_sns.dto.RequestPost;
import com.example.eg_sns.entity.Posts;
import com.example.eg_sns.repository.PostsRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class PostsService {

	@Autowired
	private PostsRepository repository;

	@Autowired
	//private CommentsService commentsService;

	public List<Posts> findAllPosts(){
		return (List<Posts>) repository.findByOrderByIdDesc();
	}

	public Posts findPost(Long id) {
		log.info("トピックを検索します。:id={}", id);

		Posts posts = repository.findById(id).orElse(null);
		log.info("ユーザー検索結果。:id={}, posts={}", id, posts);

		return posts;
	}

	public Posts save(RequestPost requestPost, Long usersId) {
		Posts posts = new Posts();
		posts.setUsersId(usersId);
		posts.setTitle(requestPost.getTitle());
		posts.setBody(requestPost.getBody());
		return repository.save(posts);
	}

	public void delete(Long postsId, Long usersId) {
		log.info("トピックを削除します。:topicsId={}, usersId={}", postsId, usersId);

		Posts posts = repository.findByIdAndUsersId(postsId, usersId).orElse(null);
		if(posts == null) {
			throw new AppNotFoundException();
		}

		//List<Comments> commentsList = topics.getCommentsList();
		//if (CollectionUtil.isNotEmpty(commentsList)) {
		//	commentsService.delete(commentsList);
		//}

		repository.delete(posts);
	}
}
