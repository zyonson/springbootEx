package com.example.eg_sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eg_sns.core.AppNotFoundException;
import com.example.eg_sns.dto.RequestPostComment;
import com.example.eg_sns.entity.PostComments;
import com.example.eg_sns.repository.PostCommentsRepository;

import lombok.extern.log4j.Log4j2;

//関連サービスクラス。
@Log4j2
@Service
public class PostCommentsService {

	@Autowired
	private PostCommentsRepository repository;
    //コメント登録処理
	public PostComments save(RequestPostComment requestPostComment, Long usersId) {
		PostComments comment = new PostComments();
		comment.setUsersId(usersId);
		comment.setComment(requestPostComment.getComment());
		comment.setPostsId(requestPostComment.getPostsId());
		return repository.save(comment);
	}
    //投稿に紐付いているコメントを全て削除する処理
	public void delete(List<PostComments> postCommentsList) {
		repository.deleteAll(postCommentsList);
	}
    //ログインしているユーザ自身がしたコメントの場合指定したコメント削除処理
	public void delete(Long id, Long usersId) {
		log.info("トピックを削除します。:id={}, usersId={}", id, usersId);

		PostComments postComments = repository.findByIdAndUsersId(id, usersId).orElse(null);
		if(postComments == null) {
			throw new AppNotFoundException();
		}

		repository.delete(postComments);
	}
}
