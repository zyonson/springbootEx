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

	public List<Friends> sendFriends(Long usersId, List<Long> approvalStatusList){
		return (List<Friends>) repository.findByUsersIdAndApprovalStatusIn(usersId, approvalStatusList);
	}

	public List<Friends> findFriends(Long friendUsersId){
		return (List<Friends>) repository.findByFriendUsersIdOrderById(friendUsersId);
	}
	
	public Friends save(RequestFriend requestFriend, Long users) {
        if (repository.findByUsersIdAndFriendUsersId(users, requestFriend.getFriendUsersId()) != null) {
        	return null;
        }else {
		Friends sendRequest = new Friends();
		sendRequest.setUsersId(users);
		sendRequest.setFriendUsersId(requestFriend.getFriendUsersId());
		sendRequest.setApprovalStatus(1L);
		repository.save(sendRequest);

		Friends receiverRequest = new Friends();
		receiverRequest.setUsersId(requestFriend.getFriendUsersId());
		receiverRequest.setFriendUsersId(users);
		receiverRequest.setApprovalStatus(2L);
		repository.save(receiverRequest);
		return null;
        }
	}
}
