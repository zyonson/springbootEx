package com.example.eg_sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eg_sns.dto.RequestFriend;
import com.example.eg_sns.entity.Friends;
import com.example.eg_sns.repository.FriendsRepository;

import lombok.extern.log4j.Log4j2;

//フレンド関連サービスクラス。
@Log4j2
@Service
public class FriendsService {

	@Autowired
	private FriendsRepository repository;
    //ログインしているユーザーのフレンド申請が申請中または承認ステータスのレコード取得
	public List<Friends> sendFriends(Long usersId, List<Long> approvalStatusList){
		return (List<Friends>) repository.findByUsersIdAndApprovalStatusIn(usersId, approvalStatusList);
	}

	public List<Friends> findFriends(Long friendUsersId){
		return (List<Friends>) repository.findByFriendUsersIdOrderById(friendUsersId);
	}
	//フレンド申請処理
	public Friends save(RequestFriend requestFriend, Long users) {
		//すでに申請中、または申請されている場合やフレンド登録されている場合、申請処理中断
        if (repository.findByUsersIdAndFriendUsersId(users, requestFriend.getFriendUsersId()) != null) {
        	return null;
        }else {
        //申請中のレコード
		Friends sendRequest = new Friends();
		sendRequest.setUsersId(users);
		sendRequest.setFriendUsersId(requestFriend.getFriendUsersId());
		sendRequest.setApprovalStatus(1L);
		repository.save(sendRequest);
        //承認待ちのレコード
		Friends receiverRequest = new Friends();
		receiverRequest.setUsersId(requestFriend.getFriendUsersId());
		receiverRequest.setFriendUsersId(users);
		receiverRequest.setApprovalStatus(2L);
		repository.save(receiverRequest);
		return null;
        }
	}
}
