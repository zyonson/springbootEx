package com.example.eg_sns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.eg_sns.dto.RequestPostComment;
import com.example.eg_sns.service.PostCommentsService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/comment")
public class PostCommentController extends AppController{

	@Autowired
	private PostCommentsService postcommentsService;

	@PostMapping("/create")
	public String create(@Validated @ModelAttribute RequestPostComment requestPostComment,
			BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			log.warn("バリデーションエラーが発生しました。：requestPostComment={}, result={}", requestPostComment, result);
		    redirectAttributes.addFlashAttribute("validationErrors", result);
		    redirectAttributes.addFlashAttribute("requestPostComment", requestPostComment);

		    return "redirect:/home";
		}
		Long usersId = getUsersId();

		//コメントを登録
		postcommentsService.save(requestPostComment,usersId);
		return "redirect:/home";
	}

	@GetMapping("/delete/{postcommentId}")
	public String delete(@PathVariable Long postcommentId) {
		log.info("トピック削除処理のアクションが呼ばれました。 :postcommentId={}", postcommentId);

		Long usersId = getUsersId();

		//ログインしているユーザーの指定したコメントを削除
		postcommentsService.delete(postcommentId, usersId);

		return "redirect:/home";
	}
}
