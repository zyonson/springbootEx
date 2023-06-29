package com.example.eg_sns.controller;

import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.eg_sns.entity.Posts;
import com.example.eg_sns.service.PostsService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/home")
public class HomeController  extends AppController{

	@Autowired
	private PostsService postsService;

	/**
	 * [GET]ホーム画面のアクション。
	 *
	 * @param model ページに表示されるユーザー情報や投稿内容のオブジェクト
	 * @param isSuccess フラッシュメッセージ
	 * @return　ホーム画面を表示
	 */
	@GetMapping(path = {"", "/"})
	public String home(Model model, @ModelAttribute("isSuccess") String isSuccess) {
		log.info("ホーム画面のアクションが呼ばれました。");

		// 全ての投稿を取得
	    List<Posts> postsList = postsService.findAllPosts();

		Long usersId = getUsersId();

		model.addAttribute("postsList", postsList);
		model.addAttribute("isSuccess", BooleanUtils.toBoolean(isSuccess));
		model.addAttribute("usersId", usersId);

		return "/home/index";
	}
}
