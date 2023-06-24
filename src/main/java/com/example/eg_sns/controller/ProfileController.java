package com.example.eg_sns.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.eg_sns.dto.RequestPassword;
import com.example.eg_sns.dto.RequestProfile;
import com.example.eg_sns.entity.Posts;
import com.example.eg_sns.entity.Users;
import com.example.eg_sns.service.PostImagesService;
import com.example.eg_sns.service.PostsService;
import com.example.eg_sns.service.ProfilesService;
import com.example.eg_sns.service.UsersService;
import com.example.eg_sns.util.StringUtil;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/profile")
public class ProfileController extends AppController{	

	@Autowired
	private PostsService postsService;

	@Autowired
	private PostImagesService postImagesService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private ProfilesService profilesService;

  @GetMapping("{usersid}")
    public String profile(Model model, @PathVariable("usersid") Long usersId){

	    List<Posts> postsList = postsService.findPost(usersId);

	    Users user = usersService.search(usersId);

	    Long loginUser = getUsersId();

	    model.addAttribute("postsList", postsList);
	    model.addAttribute("user", user);
	    model.addAttribute("usersId", loginUser);

	    return "profile/index";
    }

  @PostMapping("/edit")
    public String edit(@Validated @ModelAttribute RequestProfile requestProfile,
	  @RequestParam("profileFile")MultipartFile profileFile,
	  BindingResult result,
	  RedirectAttributes redirectAttributes) {
	
	  log.info("プロフィール編集処理のアクションが呼ばれました。");

	  if(result.hasErrors()) {
		log.warn("バリデーションエラーが発生しました。:requestProfile={}, result={}",requestProfile, result);

		redirectAttributes.addFlashAttribute("validationErrors",result);
		redirectAttributes.addFlashAttribute("requestProfile",requestProfile);
		
		return "redirect:/profile";
	  }
	
	  if(!profilesService.isImageFile(profileFile)) {
		log.warn("指定されたファイルは、画像ファイルではありません。:requestProfile={}",requestProfile);

		result.rejectValue("profileFileHidden",StringUtil.BLANK,"画像ファイルを指定してください。");

		redirectAttributes.addFlashAttribute("validationErrors",result);
		redirectAttributes.addFlashAttribute("requestProfile",requestProfile);

		return "redirect:/profile";
	  }

	  Users users = getUsers();

	  String fileUri = profilesService.store(profileFile);

	  users.setName(requestProfile.getName());
	  users.setProfile(requestProfile.getProfile());
	  users.setEmail(requestProfile.getEmail());
  	  users.setIconUri(fileUri);
	  usersService.save(users);

	  return "redirect:/home";
  }

  @PostMapping("/editpassword")
    public String editpassword(@Validated @ModelAttribute RequestPassword requestpassword,Model model,
		  BindingResult result,
		  RedirectAttributes redirectAttributes) {
	  Users user = getUsers();
	  Long usersid = user.getId();
	  if(!(user.getPassword().equals(requestpassword.getPassword()))) {
		model.addAttribute("usersid", usersid);

		redirectAttributes.addFlashAttribute("validationErrors", result);
        redirectAttributes.addFlashAttribute("requestProfile", requestpassword);

		return "redirect:/profile/" + usersid;
	  }else if(!(requestpassword.getNewpassword().equals(requestpassword.getRenewpassword()))) {
		model.addAttribute("usersid", usersid);
		return "redirect:/profile/" + usersid;
	  }else {
	  user.setPassword(requestpassword.getNewpassword());
	  usersService.save(user);

	  return "redirect:/home";
	}
  }
}
