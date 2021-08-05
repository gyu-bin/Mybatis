package com.example.base.to;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CodeTO extends BaseTO {
	
	private String divisionCodeNo;
	private String codeType;
	private String divisionCodeName;
	private String codeChangeAvailable;
	private String description;
	private ArrayList<CodeDetailTO> codeDetailTOList;
	
}