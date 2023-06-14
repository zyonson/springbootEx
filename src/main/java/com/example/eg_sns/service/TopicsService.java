package com.example.eg_sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eg_sns.core.AppNotFoundException;
import com.example.eg_sns.dto.RequestTopic;
import com.example.eg_sns.entity.Topics;
import com.example.eg_sns.repository.TopicsRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class TopicsService {

	@Autowired
	private TopicsRepository repository;

	@Autowired
	//private CommentsService commentsService;

	public List<Topics> findAllTopics(){
		return (List<Topics>) repository.findByOrderByIdDesc();
	}

	public Topics findTopics(Long id) {
		log.info("トピックを検索します。:id={}", id);

		Topics topics = repository.findById(id).orElse(null);
		log.info("ユーザー検索結果。:id={}, topics={}", id, topics);

		return topics;
	}

	public Topics save(RequestTopic requestTopic, Long usersId) {
		Topics topics = new Topics();
		topics.setUsersId(usersId);
		topics.setTitle(requestTopic.getTitle());
		topics.setBody(requestTopic.getBody());
		return repository.save(topics);
	}

	public void delete(Long topicsId, Long usersId) {
		log.info("トピックを削除します。:topicsId={}, usersId={}", topicsId, usersId);

		Topics topics = repository.findByIdAndUsersId(topicsId, usersId).orElse(null);
		if(topics == null) {
			throw new AppNotFoundException();
		}

		//List<Comments> commentsList = topics.getCommentsList();
		//if (CollectionUtil.isNotEmpty(commentsList)) {
		//	commentsService.delete(commentsList);
		//}

		repository.delete(topics);
	}
}
