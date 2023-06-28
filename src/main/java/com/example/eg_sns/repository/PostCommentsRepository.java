package com.example.eg_sns.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.eg_sns.entity.PostComments;

//コメント関連リポジトリインターフェース。
public interface PostCommentsRepository extends PagingAndSortingRepository<PostComments, Long>, CrudRepository<PostComments, Long> {

	/**
	 * 投稿に紐付いたコメント一覧を取得する。
	 * 投稿IDを指定し、投稿に紐付いたコメント一覧を取得。
	 * @param　postsId 投稿ID
	 * @return 投稿に紐付いたコメント一覧を返す。
	 */
	List<PostComments> findByPostsIdOrderById(Long postsId);

	/**
	 * 投稿コメント検索を行う。
	 * 投稿コメントID、ユーザーIDを指定し、投稿コメントを検索する。
	 * @param id 投稿コメントID
	 * @param usersId ユーザーID
	 * @return 投稿コメントを返す。
	 */
	Optional<PostComments> findByIdAndUsersId(Long id, Long usersId);

	/**
	 * 投稿コメントを削除する。
	 * 投稿コメントID、ユーザーID、投稿IDを指定し、投稿コメントを削除する。
	 * @param id 投稿コメントID
	 * @param usersId ユーザーID
	 * @param postsId 投稿ID
	 * @return 投稿コメントを削除する
	 */
	@Transactional
	void deleteByIdAndUsersIdAndPostsId(Long id, Long usersId, Long postsId);
}
