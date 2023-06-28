package com.example.eg_sns.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

//フレンドDTOクラス。
@Data
@EqualsAndHashCode(callSuper = true)
public class RequestFriend extends DtoBase{

	private Long UsersId;
	/** 申請した相手のユーザーId */
	private Long friendUsersId;

	/** 申請ステータス */
	private Long approvalStatus;
}
