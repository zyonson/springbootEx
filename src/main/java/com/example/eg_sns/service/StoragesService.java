package com.example.eg_sns.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

//プロフィール関連サービスクラス。
@Log4j2
@Service
public class StoragesService {

	/**
	 * ファイルアップロード処理。
	 *
	 * @param multipartFile マルチパートで受信したファイル。
	 * @return ファイルアップロード先の、Webサイトで見たときのドキュメントルートからの相対パスが返却される。
	 */
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

	/**
	 * ファイルチェック処理。
	 * 画像ファイルであるか、否かのチェックを行う。
	 *
	 * @param multipartFile マルチパートで受信したファイル。
	 * @return true.画像ファイルである。false.画像ファイルではない。（または、処理中にエラーが発生した場合。）
	 */
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