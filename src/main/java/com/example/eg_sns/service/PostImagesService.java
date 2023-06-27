package com.example.eg_sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eg_sns.entity.PostImages;
import com.example.eg_sns.repository.PostImagesRepository;

import lombok.extern.log4j.Log4j2;

//投稿画像関連サービスクラス。
@Log4j2
@Service
public class PostImagesService {

	@Autowired
	private PostImagesRepository repository;
    //新しい順に全ての投稿画像を取得
	public List<PostImages> findAllPostImages(){
		return (List<PostImages>) repository.findByOrderByIdDesc();
	}
    //プロフィール画面に基づくユーザーの投稿画像を全て取得
	public List<PostImages> findPostImage(Long usersId){
		return (List<PostImages>) repository.findByUsersIdOrderById(usersId);
	}
    //投稿画像保存処理
	public PostImages save(String fileUri, Long usersId,Long postsId) {
		PostImages postImages= new PostImages();
		postImages.setUsersId(usersId);
		postImages.setPostsId(postsId);
		postImages.setImageUri(fileUri);
		return repository.save(postImages);
	}
}
