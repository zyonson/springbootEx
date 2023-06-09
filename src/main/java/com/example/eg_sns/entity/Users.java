package com.example.eg_sns.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ユーザーEntityクラス。
 *
 * @author tomo-sato
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
public class Users extends EntityBase {

	/** ID */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** ログインID */
	@Column(name = "login_id", nullable = false)
	private String loginId;

	/** パスワード */
	@Column(name = "password", nullable = false)
	private String password;

	/** 名前 */
	@Column(name = "name", nullable = false)
	private String name;

	/** メールアドレス */
	@Column(name = "email_address", nullable = false)
	private String email;

	/** プロフィール */
	@Column(name = "profile", nullable = true)
	private String profile;

	/** プロフィールアイコンURI */
	@Column(name = "icon_uri", nullable = true)
	private String iconUri;
}
