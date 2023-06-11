package com.example.eg_sns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.eg_sns.core.AppConst;
import com.example.eg_sns.dto.RequestLogin;
import com.example.eg_sns.entity.Users;
import com.example.eg_sns.service.UsersService;
import com.example.eg_sns.util.StringUtil;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/")

public class LoginController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping("/login")
	public String login(Model model) {
		
		log.info("ログイン画面のアクションが呼ばれました。");
		
		if(!model.containsAttribute("requestLogin")) {
			model.addAttribute("requestLogin", new RequestLogin());
		}
		
		Users users = (Users) session.getAttribute(AppConst.SESSION_KEY_LOGIN_INFO);
		if(users != null) {
			return "redirect:/home";
		}
		return "login/index";
	}

	@PostMapping("/login")
	public String login(@Validated @ModelAttribute RequestLogin requestLogin,
		BindingResult result,
		RedirectAttributes redirectAttributes) {

		log.info("ログイン処理のアクションが呼ばれました。");

		// バリデーション。
		if (result.hasErrors()) {
			// javascriptのバリデーションを改ざんしてリクエストした場合に通る処理。
			log.warn("バリデーションエラーが発生しました。：requestLogin={}, result={}", requestLogin, result);

			redirectAttributes.addFlashAttribute("validationErrors", result);
			redirectAttributes.addFlashAttribute("requestLogin", requestLogin);

			// ログイン画面へリダイレクト。
			return "redirect:/login";
		}


		// ログインIDとパスワードを取得。
		String loginId = requestLogin.getLoginId();
		String password = requestLogin.getPassword();

		// ユーザー検索を行う。
		Users users = usersService.findUsers(loginId, password);

		// ユーザーが取得できなかったら、ログインエラー。
		if (users == null) {
			log.warn("ログインに失敗しました。：requestLogin={}", requestLogin);

			// エラーメッセージをセット。
			result.rejectValue("loginId", StringUtil.BLANK, "ログインに失敗しました。入力内容をご確認の上、再度ログインしてください。");

			redirectAttributes.addFlashAttribute("validationErrors", result);
			redirectAttributes.addFlashAttribute("requestLogin", requestLogin);

			// ログイン画面へリダイレクト。
			return "redirect:/login";
		}

		// ログインに成功したら、ログイン情報をセッションに保持。
		 session.setAttribute(AppConst.SESSION_KEY_LOGIN_INFO, users);

		// ホーム画面へリダイレクト。
		return "redirect:/home";
	}
	
	@GetMapping("logout")
	public String logout() {
		session.removeAttribute(AppConst.SESSION_KEY_LOGIN_INFO);
		
		return "redirect:/login";
	}
}
