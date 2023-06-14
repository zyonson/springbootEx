package com.example.eg_sns.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eg_sns.dto.RequestAccount;
import com.example.eg_sns.entity.Users;
import com.example.eg_sns.repository.UsersRepository;

import lombok.extern.log4j.Log4j2;

/**
 * ユーザー関連サービスクラス。
 *
 * @author tomo-sato
 */
@Log4j2
@Service
public class UsersService {

	/** リポジトリインターフェース。 */
	@Autowired
	private UsersRepository repository;

	/**
	 * ユーザー検索を行う。
	 * ログインIDを指定し、ユーザーを検索する。
	 *
	 * @param loginId ログインID
	 * @return ユーザー情報を返す。
	 */
	public Users findUsers(String loginId) {
		log.info("ユーザーを検索します。：loginId={}", loginId);

		Users users = repository.findByLoginId(loginId);
		log.info("ユーザー検索結果。：loginId={}, users={}", loginId, users);

		return users;
	}

	/**
	 * ユーザー検索を行う。
	 * ログインID、パスワードを指定し、ユーザーを検索する。
	 *
	 * @param loginId ログインID
	 * @param password パスワード
	 * @return ユーザー情報を返す。
	 */
	public Users findUsers(String loginId, String password) {
		log.info("ユーザーを検索します。：loginId={}, password={}", loginId, password);

		Users users = repository.findByLoginIdAndPassword(loginId, password);
		log.info("ユーザー検索結果。：loginId={}, password={}, users={}", loginId, password, users);

		return users;
	}

	/**
	 * ユーザー登録処理を行う。
	 *
	 * @param requestAccount ユーザーDTO
	 */
	public void save(RequestAccount requestAccount) {
		Users users = new Users();
		users.setLoginId(requestAccount.getLoginId());
		users.setPassword(requestAccount.getPassword());
		users.setEmail(requestAccount.getEmail());
		users.setName(requestAccount.getName());
		repository.save(users);
	}

	/**
	 * ユーザー登録処理を行う。
	 *
	 * @param requestAccount ユーザーDTO
	 */
	public void save(Users users) {
		repository.save(users);
	}
}
