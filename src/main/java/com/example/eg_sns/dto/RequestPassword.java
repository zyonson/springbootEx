package com.example.eg_sns.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

//パスワードDTOクラス。
@Data
@EqualsAndHashCode(callSuper = true)
public class RequestPassword extends DtoBase{

	/** 前のパスワード */
	private String password; 

	/** 新しいパスワード */
	@NotBlank(message = "パスワードを入力してください。")
	@Size(max = 32, message = "パスワードは最大32文字です。")
	private String newpassword;

	/** 新しいパスワードの確認 */
	private String renewpassword;
}
