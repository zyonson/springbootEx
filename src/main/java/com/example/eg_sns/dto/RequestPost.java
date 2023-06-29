package com.example.eg_sns.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

//投稿DTOクラス。
@Data
@EqualsAndHashCode(callSuper = true)
public class RequestPost  extends DtoBase {

	/** 投稿のタイトル */
	@NotBlank(message = "タイトルを入力してください。")
	@Size(max = 128, message =  "タイトルは最大128文字です。")
	private String title;

	/** 投稿の本文 */
	@NotBlank(message = "本文を入力してください。")
	@Size(max = 2000, message = "本文は最大2000文字です。")
	private String body;
}
