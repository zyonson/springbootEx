package com.example.eg_sns.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.eg_sns.entity.PostImages;

public interface PostImagesRepository extends PagingAndSortingRepository<PostImages, Long>,CrudRepository<PostImages, Long>{
	
	//PostImages findByUsersIdAndPostsId(Long usersId, Long postId);
	
	//List<PostImages> findByOrderByIdDesc();
}
