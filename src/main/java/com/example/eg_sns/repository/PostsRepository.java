package com.example.eg_sns.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.eg_sns.entity.Posts;

//投稿関連リポジトリインターフェース。
public interface PostsRepository extends PagingAndSortingRepository<Posts, Long>, CrudRepository<Posts, Long>{

	/**
	 * 投稿を取得する。
	 * ユーザーIDを指定し、投稿を取得。
	 * @param usersId ユーザーID
	 * @return 投稿を返す。
	 */
	Optional<Posts> findById(Long usersid);

	/**
	 * 投稿を取得する。
	 * 投稿ID,ユーザーIDを指定し、投稿を取得。
	 * @param id 投稿ID
	 * @param usersId ユーザーID
	 * @return 投稿を返す。
	 */
	Optional<Posts> findByIdAndUsersId(Long id, Long usersId);

	/**
	 * 全ての投稿を取得する。
	 * 全ての投稿を最新のものから表示。
	 * @return 全ての投稿を返す。
	 */
	List<Posts> findByOrderByIdDesc();

	/**
	 * 投稿一覧を取得する。
	 * ユーザーIDを指定し、投稿一覧を取得し降順に。
	 * @param usersId ユーザーID
	 * @return 投稿一覧を返す。
	 */
	List<Posts> findByUsersIdOrderByIdDesc(Long usersId);
}
