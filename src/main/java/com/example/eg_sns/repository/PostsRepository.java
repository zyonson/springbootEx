package com.example.eg_sns.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.eg_sns.entity.Posts;

//投稿関連リポジトリインターフェース。
public interface PostsRepository extends PagingAndSortingRepository<Posts, Long>, CrudRepository<Posts, Long>{

	Optional<Posts> findById(Long usersid);

	Optional<Posts> findByIdAndUsersId(Long id, Long usersId);

	List<Posts> findByOrderByIdDesc();

	List<Posts> findByUsersIdOrderByIdDesc(Long usersId);
}
