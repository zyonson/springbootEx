package com.example.eg_sns.controller;

import org.apache.commons.lang3.BooleanUtils;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.eg_sns.core.AppNotFoundException;
import com.example.eg_sns.dto.RequestTopic;
import com.example.eg_sns.entity.Topics;
import com.example.eg_sns.service.TopicsService;
import com.example.eg_sns.util.StringUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/topic")
public class TopicController extends AppController {
	
	@Autowired
	private TopicsService topicsService;
	
	@PostMapping("regist")
	public String regist(@Validated @ModelAttribute RequestTopic requestTopic,
			BindingResult result,
			RedirectAttributes redirectAttributes) {
		log.info("トピック作成処理のアクションが呼ばれました :requestTopic={}", requestTopic);
		
		if (result.hasErrors()) {
			log.warn("\"バリデーションエラーが発生しました。:requestTopic={}, result={}", requestTopic, result);
			
			redirectAttributes.addFlashAttribute("validationErrors", result);
			redirectAttributes.addFlashAttribute("requestTopic", requestTopic);
			
			return "redirect:/home";
		}
		
		Long usersId = getUsersId();
		
		Topics topics = topicsService.save(requestTopic, usersId);
		
		redirectAttributes.addFlashAttribute("isSuccess", "true");
		
		return "redirect:/topic/detail/" + StringUtil.toString(topics.getId(), StringUtil.BLANK);
		
	}
	
	@GetMapping("/detail/{topicsId}")
	public String detail(@PathVariable Long topicsId,
			@ModelAttribute("isSuccess") String isSuccess,
			Model model) {

		log.info("トピック詳細画面のアクションが呼ばれました。");

		//if (!model.containsAttribute("requestTopicComment")) {
		//	model.addAttribute("requestTopicComment", new RequestTopicComment());
		//}

		// トピック情報を取得する。
		Topics topics = topicsService.findTopics(topicsId);

		if (topics == null) {
			// トピックが取得できない場合は、Not Found。
			throw new AppNotFoundException();
		}

		model.addAttribute("topics", topics);
		model.addAttribute("isSuccess", BooleanUtils.toBoolean(isSuccess));

		return "topic/detail";
	}
	
	@GetMapping("/delete/{topicsId}")
	  public String delete(@PathVariable Long topicsId, RedirectAttributes redirectAttributes) {
		
		log.info("トピック削除処理のアクションが呼ばれました。 :topicsId={}", topicsId);
		
		Long usersId = getUsersId();
		
		topicsService.delete(topicsId, usersId);
		
		redirectAttributes.addFlashAttribute("isSuccess", "true");
		
		return "redirect:/home";
	}
}
