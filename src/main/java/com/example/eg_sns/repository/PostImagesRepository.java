package com.example.eg_sns.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.eg_sns.entity.PostImages;

public interface PostImagesRepository extends PagingAndSortingRepository<PostImages, Long>,CrudRepository<PostImages, Long>{
	
	Optional<PostImages> findById(Long id);

	Optional<PostImages> findByIdAndUsersId(Long id, Long usersId);

	List<PostImages> findByOrderByIdDesc();
}
