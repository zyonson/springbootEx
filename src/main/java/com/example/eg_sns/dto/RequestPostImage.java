package com.example.eg_sns.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RequestPostImage extends DtoBase {

	@NotBlank(message = "ファイルを選択してください")
	private String imageUri;

	private String profileFileHidden;
}
