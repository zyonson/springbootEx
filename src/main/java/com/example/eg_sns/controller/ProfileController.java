package com.example.eg_sns.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.eg_sns.entity.PostImages;
import com.example.eg_sns.entity.Users;
import com.example.eg_sns.service.PostImagesService;
import com.example.eg_sns.service.PostsService;

@Controller
@RequestMapping("/profile")
public class ProfileController extends AppController{
	

	@Autowired
	private PostsService postsService;

	@Autowired
	private PostImagesService postImagesService;

@GetMapping("{usersid}")
    public String profile(Model model, @PathVariable("usersid") Long usersId){

	    List<PostImages> postsList = postImagesService.findPostImage(usersId);

	    Users user = getUsers();

	    model.addAttribute("postsList", postsList);
	    model.addAttribute("user", user);

	    return "profile/index";
    }
}
