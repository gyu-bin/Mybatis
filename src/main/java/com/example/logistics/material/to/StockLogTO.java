package com.example.logistics.material.to;

import lombok.Data;

@Data
public class StockLogTO {
	
	private String logDate;
	private String itemCode;
	private String itemName;
	private String amount;
	private String reason;
	private String cause;
	private String effect;
	
}
