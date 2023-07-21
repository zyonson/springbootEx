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
import com.example.eg_sns.service.StoragesService;

import lombok.extern.log4j.Log4j2;
import software.amazon.awssdk.services.s3.S3Client;

@Log4j2
@Controller
@RequestMapping("/post")
public class PostController extends AppController {

	@Autowired
	private PostsService postsService;

	@Autowired
	private PostImagesService postImagesService;

	@Autowired
	private StoragesService storagesService;

	private final S3Client s3Client;
	
	public PostController(S3Client s3Client) {
		this.s3Client = s3Client;
	}

	/**
	 * [POST]投稿作成アクション。
	 *
	 * @param RequestPassword 入力フォームの内容
	 * @param model usersIdを格納
	 * @param result バリデーション結果
	 * @param redirectAttributes リダイレクト時に使用するオブジェクト
	 * @return ホーム画面を表示
	 */
	@PostMapping("regist")
	public String regist(@Validated @ModelAttribute RequestPost requestPost,
			@RequestParam("profileFile") MultipartFile profileFile,
			BindingResult result,
			RedirectAttributes redirectAttributes) {
	    log.info("トピック作成処理のアクションが呼ばれました :requestPost={}", requestPost);

		if (result.hasErrors()) {
			log.warn("バリデーションエラーが発生しました。:requestPost={}, result={}", requestPost, result);

			redirectAttributes.addFlashAttribute("validationErrorsPost", result);
			redirectAttributes.addFlashAttribute("requestPost", requestPost);

			return "redirect:/home";
		}

		// アップロードしたファイルが画像ファイル出ない場合、エラーメッセージを格納してhome画面へ
		// if (!storagesService.isImageFile(profileFile)) {
		//	log.warn("指定されたファイルは、画像ファイルではありません。:profileFile={}", profileFile);

//		    result.rejectValue("profileFile", StringUtil.BLANK, "画像ファイルを指定してください。");
//
//		    redirectAttributes.addFlashAttribute("validationErrorsPost", result);
//		    redirectAttributes.addFlashAttribute("requestPost", requestPost);
//
//		    return "redirect:/home";
//		}

		Long usersId = getUsersId();

		String fileUri = storagesService.store(profileFile);

		Posts posts = postsService.save(requestPost, usersId);

		Long postsId = posts.getId();

		//投稿画像を保存
		postImagesService.save(fileUri,usersId,postsId);

		redirectAttributes.addFlashAttribute("isSuccess", "true");

		return "redirect:/home";
	}

	/**
	 * [GET]投稿削除アクション
	 *
	 * @param postsId 投稿ID
	 * @return ホーム画面を表示
	 */
	@GetMapping("/delete/{postsId}")
	public String delete(@PathVariable Long postsId) {
		log.info("トピック削除処理のアクションが呼ばれました。 :postsId={}", postsId);

		Long usersId = getUsersId();

		//ログインしているユーザーの投稿を削除
		postsService.delete(postsId, usersId);

		return "redirect:/home";
	}
}
