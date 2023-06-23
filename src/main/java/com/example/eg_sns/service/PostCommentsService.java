package com.example.eg_sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eg_sns.dto.RequestPostComment;
import com.example.eg_sns.entity.PostComments;
import com.example.eg_sns.repository.PostCommentsRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class PostCommentsService {

	@Autowired
	private PostCommentsRepository repository;

	public PostComments save(RequestPostComment requestPostComment, Long usersId) {
		PostComments comment = new PostComments();
		comment.setUsersId(usersId);
		comment.setComment(requestPostComment.getComment());
		comment.setPostsId(requestPostComment.getPostsId());
		return repository.save(comment);
	}
	
	public void delete(List<PostComments> postCommentsList) {
		repository.deleteAll(postCommentsList);
	}
}
