package com.example.eg_sns.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RequestPostComment extends DtoBase{

	@NotBlank(message = "コメントを入力してください")
	@Size(max = 256, message = "コメントは最大256文字です。")
	private String comment;

	private Long postsId;
}
