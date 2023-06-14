package com.example.eg_sns.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.eg_sns.entity.Topics;

public interface TopicsRepository extends PagingAndSortingRepository<Topics, Long>, CrudRepository<Topics, Long>{

	Optional<Topics> findById(Long id);

	Optional<Topics> findByIdAndUsersId(Long id, Long usersId);

	List<Topics> findByOrderByIdDesc();
}
