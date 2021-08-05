package com.example.logistics.production.to;

import com.example.base.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
public class MrpGatheringTO extends BaseTO {

	private String mrpGatheringNo;
	private String orderOrProductionStatus;
	private String itemCode;
	private String itemName;
	private String unitOfMrpGathering;
	private String claimDate;
	private String dueDate;
	private int necessaryAmount;
	private ArrayList<MrpTO> mrpTOList;
	private int mrpGatheringSeq;

}