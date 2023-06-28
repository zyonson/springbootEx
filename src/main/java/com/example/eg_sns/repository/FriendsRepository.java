package com.example.eg_sns.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.eg_sns.entity.Friends;

//フレンド関連リポジトリインターフェース。
public interface FriendsRepository extends PagingAndSortingRepository<Friends, Long>, CrudRepository<Friends, Long> {

	/**
	 * フレンド一覧を取得
	 * ユーザーID、approvalStatusを指定し、条件に合致するフレンドを習得する。
	 *
	 * @param usersId ユーザーID
	 * @param approvalStatusList　承認状態
	 * @return フレンド一覧を返す。
	 */
	List<Friends> findByUsersIdAndApprovalStatusIn(Long usersId, List<Long> approvalStatusList);

	/**
	 * フレンドを取得
	 * ユーザーID、フレンドuserIdを指定し、条件に合致するフレンドを習得する。
	 *
	 * @param usersId ユーザーID
	 * @param friendUsersId フレンドのuserId
	 * @return フレンドを返す。
	 */
	Friends findByUsersIdAndFriendUsersId(Long usersId, Long friendUsersId);
}
