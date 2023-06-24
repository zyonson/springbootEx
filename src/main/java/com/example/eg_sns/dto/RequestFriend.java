package com.example.eg_sns.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RequestFriend extends DtoBase{

	private Long friendUsersId;

	private Long approvalStatus;
}
