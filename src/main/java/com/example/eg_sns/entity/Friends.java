package com.example.eg_sns.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

//フレンドEntityクラス。
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "friends")
public class Friends extends EntityBase {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	/** ユーザーID */
	@Column(name = "users_id", nullable = false)
	private Long usersId;

	/** フレンドID */
	@Column(name = "friend_users_id", nullable = false)
	private Long friendUsersId;

	/** 承認状態 */
	@Column(name = "approval_status", nullable = false)
	private Long approvalStatus;

	/** ユーザー情報とのJOIN */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "friend_users_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Users users;
}
