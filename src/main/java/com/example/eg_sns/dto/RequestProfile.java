package com.example.eg_sns.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RequestProfile extends DtoBase{

	@NotBlank(message = "お名前を入力してください。")
	@Size(max = 32, message = "お名前は最大32文字です。")
	private String name;

	@NotBlank(message = "メールアドレスを入力してください")
	@Size(max = 256, message = "メールアドレスは最大256文字です。")
	private String email;

	private String profile;

	private String iconUri;

	private String profileFileHidden;
}
