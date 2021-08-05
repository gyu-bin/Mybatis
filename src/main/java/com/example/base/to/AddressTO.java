package com.example.base.to;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddressTO extends BaseTO {

	private String addressNo;
	private int cnt;
	private String zipCode;
	private String addressType;
	private String address;

}
