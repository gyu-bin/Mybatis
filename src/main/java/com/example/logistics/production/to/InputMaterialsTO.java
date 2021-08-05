package com.example.logistics.production.to;

import com.example.base.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InputMaterialsTO extends BaseTO {
	 private String inputItemNo;
	 private String productionResultNo;
	 private String description;
	 private String itemCode;
	 private String unitOfInputMaterials;
	 private String warehouseCode;
	 private String itemName;
	 private int inputAmount;
	 private String inputDate;

}