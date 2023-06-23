package com.example.eg_sns.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.eg_sns.entity.PostComments;

public interface PostCommentsRepository extends PagingAndSortingRepository<PostComments, Long>, CrudRepository<PostComments, Long> {

	List<PostComments> findByPostsIdOrderById(Long postsId);

	Optional<PostComments> findByIdAndUsersId(Long id, Long usersId);

	@Transactional
	void deleteByIdAndUsersIdAndPostsId(Long id, Long usersId, Long postsId);
}
