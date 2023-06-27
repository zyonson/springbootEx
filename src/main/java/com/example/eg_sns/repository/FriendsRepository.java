package com.example.eg_sns.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.eg_sns.entity.Friends;

//フレンド関連リポジトリインターフェース。
public interface FriendsRepository extends PagingAndSortingRepository<Friends, Long>, CrudRepository<Friends, Long> {

	List<Friends> findByUsersIdAndApprovalStatusIn(Long usersId, List<Long> approvalStatusList);
	List<Friends> findByFriendUsersIdOrderById(Long friendUsersId);
	Friends findByUsersIdAndFriendUsersId(Long usersId, Long friendUsersId);
}
