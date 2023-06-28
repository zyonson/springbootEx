package com.example.eg_sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eg_sns.dto.RequestFriend;
import com.example.eg_sns.entity.Friends;
import com.example.eg_sns.repository.FriendsRepository;

//フレンド関連サービスクラス。

@Service
public class FriendsService {

	@Autowired
	private FriendsRepository repository;
    //ログインしているユーザーのフレンド申請が申請中または承認ステータスのレコード取得
	public List<Friends> sendFriends(Long usersId, List<Long> approvalStatusList){
		return (List<Friends>) repository.findByUsersIdAndApprovalStatusIn(usersId, approvalStatusList);
	}

	//フレンド申請処理
	public Friends save(RequestFriend requestFriend, Long users) {
		//すでに申請中、または申請されている場合やフレンド登録されている場合、申請処理中断
        if (repository.findByUsersIdAndFriendUsersId(users, requestFriend.getFriendUsersId()) != null) {
        	return null;
        }else {

        //申請中のレコード作成
		Friends sendRequest = new Friends();
		sendRequest.setUsersId(users);
		sendRequest.setFriendUsersId(requestFriend.getFriendUsersId());
		sendRequest.setApprovalStatus(1L);
		repository.save(sendRequest);

        //承認待ちのレコード作成
		Friends receiverRequest = new Friends();
		receiverRequest.setUsersId(requestFriend.getFriendUsersId());
		receiverRequest.setFriendUsersId(users);
		receiverRequest.setApprovalStatus(2L);
		repository.save(receiverRequest);

		return null;
        }
	}

	//friend承認
	public Friends update(RequestFriend requestFriend) {
		Friends friend = repository.findByUsersIdAndFriendUsersId(requestFriend.getUsersId(), requestFriend.getFriendUsersId());
        Friends friends = repository.findByUsersIdAndFriendUsersId(requestFriend.getFriendUsersId(), requestFriend.getUsersId());

        //ステータスを承諾に変更
        friend.setUsersId(requestFriend.getUsersId());
		friend.setFriendUsersId(requestFriend.getFriendUsersId());
		friend.setApprovalStatus(4L);
		repository.save(friend);

        //ステータスを承認に変更
		friends.setUsersId(requestFriend.getFriendUsersId());
		friends.setFriendUsersId(requestFriend.getUsersId());
		friends.setApprovalStatus(3L);
		repository.save(friends);

		return null;
	}

	public Friends delete(Long friendUsersId, Long usersId) {
		Friends friend = repository.findByUsersIdAndFriendUsersId(usersId, friendUsersId);
		Friends friends = repository.findByUsersIdAndFriendUsersId(friendUsersId, usersId) ;
		repository.delete(friend);
		repository.delete(friends);
		return null;
	}
}
