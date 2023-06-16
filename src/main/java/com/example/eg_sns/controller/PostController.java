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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.eg_sns.dto.RequestPost;
import com.example.eg_sns.entity.Posts;
import com.example.eg_sns.service.PostImagesService;
import com.example.eg_sns.service.PostsService;
import com.example.eg_sns.util.StringUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/post")
public class PostController extends AppController {

	@Autowired
	private PostsService postsService;
	
	@Autowired
	private PostImagesService postImagesService;

	@PostMapping("regist")
	public String regist(@Validated @ModelAttribute RequestPost requestPost,
			@RequestParam("profileFile") MultipartFile profileFile,
			BindingResult result,
			RedirectAttributes redirectAttributes) {
		log.info("トピック作成処理のアクションが呼ばれました :requestPost={}", requestPost);

		if (result.hasErrors()) {
			log.warn("バリデーションエラーが発生しました。:requestPost={}, result={}", requestPost, result);

			redirectAttributes.addFlashAttribute("validationErrors", result);
			redirectAttributes.addFlashAttribute("requestPost", requestPost);

			return "redirect:/home";
		}

		if (!PostImagesService.isImageFile(profileFile)) {
			log.warn("指定されたファイルは、画像ファイルではありません。:profileFile={}", profileFile);

		    result.rejectValue("profileFile", StringUtil.BLANK, "画像ファイルを指定してください。");

		    redirectAttributes.addFlashAttribute("validationErrors", result);
		    redirectAttributes.addFlashAttribute("requestTopic", requestPost);

		    return "redirect:/home";
		}

		Long usersId = getUsersId();

		String fileUri = postImagesService.store(profileFile);

		Posts posts = postsService.save(requestPost, usersId);
		
		Long postsId = posts.getId();
		
		postImagesService.save(fileUri,usersId,postsId);
 
		redirectAttributes.addFlashAttribute("isSuccess", "true");

		return "redirect:/home";
	}

	@GetMapping("/delete/{postsId}")
	public String delete(@PathVariable Long postsId) {
		log.info("トピック削除処理のアクションが呼ばれました。 :postsId={}", postsId);

		Long usersId = getUsersId();

		postsService.delete(postsId, usersId);

		return "redirect:/home";
	}
}
