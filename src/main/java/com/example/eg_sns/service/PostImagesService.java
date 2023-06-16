package com.example.eg_sns.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.eg_sns.entity.PostImages;
import com.example.eg_sns.repository.PostImagesRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class PostImagesService {

	@Autowired
	private PostImagesRepository repository;
	
	//public List<PostImages> findAllTopics(){
	//	return (List<PostImages>) repository.findByOrderByIdDesc();
	//}
	
	public List<PostImages> findAllPostImages(){
		return (List<PostImages>) repository.findByOrderByIdDesc();
	}

	public PostImages findPostImage(Long id) {
		log.info("トピックを検索します。:id={}", id);

		PostImages postImages = repository.findById(id).orElse(null);
		log.info("ユーザー検索結果。:id={}, postImages={}", id, postImages);

		return postImages;
	}

	public PostImages save(String fileUri, Long usersId,Long postsId) {
		PostImages postImages= new PostImages();
		postImages.setUsersId(usersId);
		postImages.setPostsId(postsId);
		postImages.setImageUri(fileUri);
		return repository.save(postImages);
	}

	public String store(MultipartFile multipartFile) {

		String fileName = multipartFile.getOriginalFilename();

		if (StringUtils.isEmpty(fileName)) {
			return null;
		}

		Path filePath = Paths.get("src/main/resources/static/assets/profileimg/" + fileName);

		OutputStream stream = null;
		try {
			byte[] bytes = multipartFile.getBytes();

			stream = Files.newOutputStream(filePath);

			stream = Files.newOutputStream(filePath);

			stream.write(bytes);
		} catch (IOException e) {
			log.error("ファイルアップロード中にエラーが発生しました。", e);
			return null;
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					log.error("ファイルアップロードのクローズ処理でエラーが発生しました。", e);
				}
			}
		}

		return "/assets/profileimg/" + fileName;
	}

	public static boolean isImageFile(MultipartFile multipartFile) {

		String fileName = multipartFile.getOriginalFilename();

		if (StringUtils.isEmpty(fileName)) {
			return true;
		}

		FileOutputStream fos = null;
		try {
			File file = new File(fileName);
			file.createNewFile();
			fos = new FileOutputStream(file);
			fos.write(multipartFile.getBytes());
			fos.close();
			
			if(file != null && file.isFile()) {
				BufferedImage bi = ImageIO.read(file);
				if (bi != null) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			log.error("ファイルチェック中にエラーが発生しました。", e);
			return false;
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					log.error("ファイルチェック処理中のクローズ処理でエラーが発生しました。", e);
				}
			}
		}
	}
}
