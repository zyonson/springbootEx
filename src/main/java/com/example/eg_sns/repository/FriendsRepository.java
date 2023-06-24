package com.example.eg_sns.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.eg_sns.entity.Friends;

public interface FriendsRepository extends PagingAndSortingRepository<Friends, Long>, CrudRepository<Friends, Long> {

	List<Friends> findByUsersIdOrderById(Long usersId);
	List<Friends> findByFriendUsersIdOrderById(Long friendUsersId);
}

