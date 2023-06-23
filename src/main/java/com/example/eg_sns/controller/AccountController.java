package com.example.eg_sns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.eg_sns.dto.RequestAccount;
import com.example.eg_sns.entity.Users;
import com.example.eg_sns.service.UsersService;
import com.example.eg_sns.util.StringUtil;

import lombok.extern.log4j.Log4j2;

/**
 * ※TODO 適宜実装を入れてください。
 */
@Log4j2
@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private UsersService usersService;

	@GetMapping(path = {"", "/"})
	public String index() {
		return "/account/index";
	}

	@PostMapping("/regist")
	public String regist(@Validated @ModelAttribute RequestAccount requestAccount,
			BindingResult result,
			RedirectAttributes redirectAttributes) {

		log.info("アカウント作成処理のアクションが呼ばれました。：requestAccount={}", requestAccount);

		// バリデーション。
		if (result.hasErrors()) {
			// javascriptのバリデーションを改ざんしてリクエストした場合に通る処理。
			log.warn("バリデーションエラーが発生しました。：requestAccount={}, result={}", requestAccount, result);

			redirectAttributes.addFlashAttribute("validationErrors", result);
			redirectAttributes.addFlashAttribute("requestAccount", requestAccount);

			// 入力画面へリダイレクト。
			return "redirect:/account";
		}

		String loginId = requestAccount.getLoginId();

		// ユーザー検索を行う。（※「ログインID」で検索を行い、すでに登録済みの場合エラー。）
		Users users = usersService.findUsers(loginId);

		if (users != null) {
			log.warn("すでに登録済みのユーザーです。：requestAccount={}", requestAccount);

			// エラーメッセージをセット。
			result.rejectValue("loginId", StringUtil.BLANK, "指定のログインIDは、すでに登録されています。");

			redirectAttributes.addFlashAttribute("validationErrors", result);
			redirectAttributes.addFlashAttribute("requestAccount", requestAccount);

			// 入力画面へリダイレクト。
			return "redirect:/account";
		}

		// データ登録処理。
		usersService.save(requestAccount);

		redirectAttributes.addFlashAttribute("isSuccess", "true");

		// 完了画面へリダイレクト。
		return "redirect:/account/complete";
	}

	@GetMapping("/complete")
	public String complete() {
		return "/account/complete";
	}
}
