package com.example.eg_sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eg_sns.dto.RequestFriend;
import com.example.eg_sns.entity.Friends;
import com.example.eg_sns.repository.FriendsRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class FriendsService {

	@Autowired
	private FriendsRepository repository;

	public List<Friends> sendFriends(Long usersId){
		return (List<Friends>) repository.findByUsersIdOrderById(usersId);
	}

	public List<Friends> findFriends(Long friendUsersId){
		return (List<Friends>) repository.findByFriendUsersIdOrderById(friendUsersId);
	}
	
	public Friends save(RequestFriend requestFriend, Long users) {
		Friends friends = new Friends();
		friends.setUsersId(users);
		friends.setFriendUsersId(requestFriend.getFriendUsersId());
		friends.setApprovalStatus(requestFriend.getApprovalStatus());
		return repository.save(friends);
	}
}
