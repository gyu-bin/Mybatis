package com.example.base.to;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CodeDetailTO extends BaseTO {
	
	private String divisionCodeNo;
	private String detailCode;
	private String detailCodeName;
	private String codeUseCheck;
	private String description;
	
}