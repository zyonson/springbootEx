package com.example.eg_sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eg_sns.core.AppNotFoundException;
import com.example.eg_sns.dto.RequestPost;
import com.example.eg_sns.entity.PostComments;
import com.example.eg_sns.entity.Posts;
import com.example.eg_sns.repository.PostsRepository;
import com.example.eg_sns.util.CollectionUtil;

import lombok.extern.log4j.Log4j2;

//投稿関連サービスクラス。
@Log4j2
@Service
public class PostsService {

	@Autowired
	private PostsRepository repository;

	@Autowired
	private PostCommentsService postCommentsService;

	/**
	 * 投稿一覧を取得。
	 *
	 * @return 全ての投稿を降順に取得
	 */
	public List<Posts> findAllPosts(){
		return (List<Posts>) repository.findByOrderByIdDesc();
	}

	/**
	 * 投稿一覧を取得。
	 *
	 * @return 指定したusersIdに紐付いた投稿を降順に取得
	 */
	public List<Posts> findPost(Long usersId){
		return (List<Posts>) repository.findByUsersIdOrderByIdDesc(usersId);
	}

	/**
	 * 投稿登録処理を行う。
	 *
	 * @param requestPost 投稿DTO
	 * @param usersId ユーザーID
	 */
	public Posts save(RequestPost requestPost, Long usersId) {
		Posts posts = new Posts();
		posts.setUsersId(usersId);
		posts.setTitle(requestPost.getTitle());
		posts.setBody(requestPost.getBody());
		return repository.save(posts);
	}

	/**
	 * 投稿削除処理を行う。
	 *
	 * @param postsId 投稿ID
	 * @param usersId ユーザーID
	 */
	public void delete(Long postsId, Long usersId) {
		log.info("トピックを削除します。:postsId={}, usersId={}", postsId, usersId);

        //削除したい投稿がある場合取得
		Posts posts = repository.findByIdAndUsersId(postsId, usersId).orElse(null);
		if(posts == null) {
			throw new AppNotFoundException();
		}

        //削除したい投稿に紐付いたコメントを全て取得
		List<PostComments> postCommentsList = posts.getPostCommentsList();

		//コメントがあれば削除
		if (CollectionUtil.isNotEmpty(postCommentsList)) {
			postCommentsService.delete(postCommentsList);
		}

        //投稿を削除
		repository.delete(posts);
	}
}
