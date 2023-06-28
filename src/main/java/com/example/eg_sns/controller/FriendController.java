package com.example.eg_sns.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.eg_sns.dto.RequestFriend;
import com.example.eg_sns.entity.Friends;
import com.example.eg_sns.service.FriendsService;

/**
 * ※TODO 適宜実装を入れてください。
 */
@Controller
@RequestMapping("/friend")
public class FriendController extends AppController{

	@Autowired
	private FriendsService friendsService;

	@GetMapping("/list")
	public String list(Model model) {
		Long usersId = getUsersId();

		// 申請中、承認ステータスのfriendを習得
		List<Friends> friendsList = friendsService.sendFriends(usersId, Arrays.asList(1L, 3L));
		List<Friends> friendsList2 = friendsService.sendFriends(usersId, Arrays.asList(2L, 4L));
		model.addAttribute("usersId", usersId);
		model.addAttribute("friendsList", friendsList);
		model.addAttribute("friendsList2", friendsList2);

		return "/friend/list";
	}

    // friend申請機能
	@PostMapping("/offer")
	public String offer(RequestFriend requestFriend) {
		Long usersId = getUsersId();
		friendsService.save(requestFriend,usersId);
		return "redirect:/friend/list";
	}

	// friend申請承認機能
	@PostMapping("/update")
	public String update(RequestFriend requestFriend) {
		friendsService.update(requestFriend);
		return "redirect:/friend/list";
	}

	// friend申請取り消し、却下機能
	@GetMapping("/delete/{friendUsersId}")
	public String delete(@PathVariable("friendUsersId") Long friendUsersId){
		Long usersId = getUsersId();
		friendsService.delete(usersId,friendUsersId);
	    return "redirect:/friend/list";
	}
}
