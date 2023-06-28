package com.example.eg_sns.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.eg_sns.entity.PostImages;

//投稿画像関連リポジトリインターフェース。
public interface PostImagesRepository extends PagingAndSortingRepository<PostImages, Long>,CrudRepository<PostImages, Long>{

	/**
	 * ユーザーIDに紐付いた投稿画像一覧を取得する。
	 * @param usersId ユーザーID
	 * @return ユーザーIDに紐付いた投稿画像一覧を返す。
	 */
	List<PostImages> findByUsersIdOrderById(Long usersId);

	/**
	 * 投稿画像IDとユーザーIDに紐付いた投稿画像一覧を取得する。
	 * *@param id 投稿画像ID
	 * @param usersId ユーザーID
	 * @return ユーザーIDに紐付いた投稿画像一覧を返す。
	 */
	Optional<PostImages> findByIdAndUsersId(Long id, Long usersId);

	List<PostImages> findByOrderByIdDesc();
}
