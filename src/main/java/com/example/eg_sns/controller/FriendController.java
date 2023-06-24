package com.example.eg_sns.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.eg_sns.dto.RequestFriend;
import com.example.eg_sns.entity.Friends;
import com.example.eg_sns.service.FriendsService;
import com.example.eg_sns.service.UsersService;

import lombok.extern.log4j.Log4j2;

/**
 * ※TODO 適宜実装を入れてください。
 */
@Log4j2
@Controller
@RequestMapping("/friend")
public class FriendController extends AppController{

	@Autowired
	private FriendsService friendsService;
	
	@Autowired
	private UsersService usersService;

	@GetMapping("/list")
	public String list(Model model) {
		Long usersId = getUsersId();
		List<Friends> friendsList = friendsService.sendFriends(usersId);
		model.addAttribute("usersId", usersId);
		model.addAttribute("friendsList", friendsList);

		log.info("friendsList: {}", friendsList);
		log.info("friendsList: {}", friendsList.get(0));
		return "/friend/list";
	}

	@PostMapping("/offer")
	public String offer(RequestFriend requestFriend) {
		Long usersId = getUsersId();
		friendsService.save(requestFriend,usersId);
		return "redirect:/friend/list";
	}
}
