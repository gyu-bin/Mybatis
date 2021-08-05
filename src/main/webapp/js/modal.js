'use strict';
// O Customer, Item, to 변수
let jsonData;
let customerGrid = document.querySelector('#customerGrid');
let itemGrid = document.querySelector('#itemGrid');
let unitGrid = document.querySelector('#unitGrid');
let workplaceGrid = document.querySelector('#workplaceCodeGrid');
let departmentGrid = document.querySelector('#departmentCodeGrid');
let positionGrid = document.querySelector('#positionCodeGrid');
let contractTypeGrid = document.querySelector('#contractGrid');
let mpsGrid = document.querySelector('#mpsGrid');
let mrpGrid = document.querySelector("#mrpGrid");
let mrpGatheringGrid = document.querySelector("#mrpGatheringGrid");
let orderGrid = document.querySelector("#orderGrid");
let warehousingGrid = document.querySelector("#warehousingGrid");
let workOrderSimulationGrid = document.querySelector("#workOrderSimulationGrid");
let workSiteSituationGrid = document.querySelector("#workSiteSituationGrid");
let to;                      // 전달 변수
let transferVar = () => to;  // 전달 함수
let isElement = [];

// O Common GridOptions
const gridOptions = {
    defaultColDef: { editable: false },
    onGridReady: function (event) {
        event.api.sizeColumnsToFit();
    },
    onGridSizeChanged: function (event) {
        event.api.sizeColumnsToFit();
    },
    rowSelection: 'multiple',
};

// O Get ajax Data  FROM (TABLE) DETAIL_CODE
const getCustomerData = (divisionCodeNo) => {
    let xhr = new XMLHttpRequest();
    xhr.open('POST',
        `/base/codeList.do?method=findDetailCodeList&divisionCodeNo=${divisionCodeNo}`,
        true);
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.send();
    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let txt = xhr.responseText;
            jsonData = JSON.parse(txt);
            console.log(jsonData);
            to = jsonData;
            console.log(jsonData);
            if (jsonData.errorCode !== 1) {
                swal({
                    text: '데이터를 불러드리는데 오류가 발생했습니다.',
                    icon: 'error',
                });
                return;
            }
        }
    };
};
// O Setting Customer MODAL Grid FROM ajax data
const setCutomerModal = (section) => {    // section: CUSTOMER, ITEM, UNIT ...
    if (!isElement.includes(section)) {
          console.log(section);
        isElement.push(section);
    } else {
        return;
    } // 요소가 있으면 나오기!!
    let cusGridOptions = gridOptions;
    cusGridOptions.columnDefs = [
        { headerName: '상세코드번호', field: 'detailCode', width: 80, cellStyle: { 'textAlign': 'center' }, },
        { headerName: '상세코드이름', field: 'detailCodeName', width: 80, cellStyle: { 'textAlign': 'center' }, },
        { headerName: '사용여부', field: 'codeUseCheck', width: 80, cellStyle: { 'textAlign': 'center' } }];
    cusGridOptions.onRowDoubleClicked = (event) => {
        console.log(`event.data.detailCodeName:${event.data}`);
        to = event.data;
        let closeList = document.querySelectorAll('.close');
        for (let value of closeList) {
            value.click();
        }
    };
    cusGridOptions.onGridReady = function () {

        cusGridOptions.api.setRowData(jsonData.detailCodeList);
    };


    switch (section) {
        case 'CUSTOMER':
            console.log('항목: CUSTOMER');
            new agGrid.Grid(customerGrid, cusGridOptions);
            break;
        case 'ITEM':
            console.log('항목: ITEM');
            new agGrid.Grid(itemGrid, cusGridOptions);
            break;
        case 'UNIT':
            console.log('항목: UNIT');
            new agGrid.Grid(unitGrid, cusGridOptions);
            break;
        case "CONTRACT":
            console.log('항목: CONTRACT');
            new agGrid.Grid(contractTypeGrid, cusGridOptions);
            break;
        case "WORKPLACE":
            console.log('항목: WORKPLACE');
            new agGrid.Grid(workplaceGrid, cusGridOptions);
            break;
        case "DEPARTMENT":
            console.log('항목: DEPARTMENT');
            new agGrid.Grid(departmentGrid, cusGridOptions);
            break;
        case "POSITION":
            console.log('항목: POSITION');
            new agGrid.Grid(positionGrid, cusGridOptions);
            break;
        default:
            console.log(`해당하는 항목이 존재하지 않음: ${section}`);
    }
};
// O Get StandardUnitPrice
const getStandardUnitPrice = (itemCode, unit) => {
    console.log(itemCode);
    let xhr = new XMLHttpRequest();
    // BOX
    xhr.open('POST',
        `/logisticsInfo/getStandardUnitPrice.do?method=getStandardUnitPrice&itemCode=${itemCode}`,
        true);
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.send();
    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let txt = xhr.responseText
            jsonData = JSON.parse(txt);
            console.log(jsonData);
            document.querySelector("#unitPriceOfEstimateBox").value = jsonData.gridRowJson;
            if (jsonData.errorCode !== 1) {
                swal({
                    text: '데이터를 불러드리는데 오류가 발생했습니다.',
                    icon: 'error',
                });
                return;
            }
        }
    };
};
// jsonData.gridRowJson;
const setMpsModal = () => {
    let mpsGridOptions = gridOptions;
    mpsGridOptions.columnDefs = [
        { headerName: '주생산계획번호', field: 'mpsNo', width: 450, cellStyle: { 'textAlign': 'center' } },
        { headerName: '계획구분', field: 'mpsPlanClassification', width: 250, cellStyle: { 'textAlign': 'center' }, },
        { headerName: '일련번호(수주/판매)', field: 'no', width: 500, cellStyle: { 'textAlign': 'center' } },
        { headerName: '수주상세일련번호', field: 'contractDetailNo', cellStyle: { 'textAlign': 'center' }, hide: true },
        { headerName: '판매계획일련번호', field: 'salesPlanNo', cellStyle: { 'textAlign': 'center' }, hide: true },
        { headerName: '품목코드', field: 'itemCode', width: 350, cellStyle: { 'textAlign': 'center' } },
        { headerName: '품목명', field: 'itemName', width: 450, cellStyle: { 'textAlign': 'center' } },
        { headerName: '단위', field: 'unitOfMps', cellStyle: { 'textAlign': 'center' } },
        { headerName: '계획일자', field: 'mpsPlanDate', width: 350, cellStyle: { 'textAlign': 'center' } },
        { headerName: '계획수량', field: 'mpsPlanAmount', cellStyle: { 'textAlign': 'center' } },
        { headerName: '납기일', field: 'dueDateOfMps', width: 350, cellStyle: { 'textAlign': 'center' } },
        { headerName: '예정마감일자', field: 'scheduledEndDate', width: 350, cellStyle: { 'textAlign': 'center' } },
        { headerName: 'MRP 적용상태', field: 'mrpApplyStatus', width: 350, cellStyle: { 'textAlign': 'center' } },
        { headerName: '비고', field: 'description', cellStyle: { 'textAlign': 'center' } },
    ];
    mpsGridOptions.onGridReady = function () {
        for (let i = 0; i < jsonData.gridRowJson.length; i++) {
            jsonData.gridRowJson[i].no = jsonData.gridRowJson[i].mpsPlanClassification == "수주상세"
                ? jsonData.gridRowJson[i].contractDetailNo : jsonData.gridRowJson[i].salesPlanNo;
        }
        mpsGridOptions.api.setRowData(jsonData.gridRowJson);
    };

    new agGrid.Grid(mpsGrid, mpsGridOptions);
}
// O mps modal
const getMpsList = () => {
    let fromDate = document.querySelector("#fromDate");
    let toDate = document.querySelector("#toDate");
    let xhr = new XMLHttpRequest();
    xhr.open('POST', '/production/searchMpsInfo.do' +
        "?method=searchMpsInfo"
        + "&startDate=" + fromDate.value
        + "&endDate=" + toDate.value,
        true)
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.send();
    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let txt = xhr.responseText;
            console.log(txt);
            jsonData = JSON.parse(txt);
            console.log(jsonData);
            if (jsonData.errorCode !== 1) {
                swal({
                    text: '데이터를 불러드리는데 오류가 발생했습니다.',
                    icon: 'error',
                });
                return;
            }
        }
    }
}
// O MRP SIMULATION
// o set Mrp modal
let mrpGridOptions;
const setMrpModal = () => {
    mrpGridOptions = gridOptions;
    mrpGridOptions.columnDefs = [
        { headerName: '주생산계획번호', field: 'mpsNo', width: 450, cellStyle: { 'textAlign': 'center' } },
        { headerName: 'BOM 번호', field: 'bomNo', width: 350, cellStyle: { 'textAlign': 'center' }, },
        { headerName: '품목구분', field: 'itemClassification', width: 300, cellStyle: { 'textAlign': 'center' } },
        { headerName: '품목코드', field: 'itemCode', width: 300, cellStyle: { 'textAlign': 'center' } },
        { headerName: '품목명', field: 'itemName', width: 500, cellStyle: { 'textAlign': 'center' } },
        { headerName: '발주/작업지시기한', field: 'orderDate', width: 450, cellStyle: { 'textAlign': 'center' }, editable: true },
        {
            headerName: '발주/작업지시완료기한',
            field: 'requiredDate',
            width: 450,
            cellStyle: { 'textAlign': 'center' },
            editable: true
        },
        { headerName: '계획수량', field: 'planAmount', width: 350, cellStyle: { 'textAlign': 'center' } },
        { headerName: '누적손실율', field: 'totalLossRate', width: 350, cellStyle: { 'textAlign': 'center' } },
        { headerName: '계산수량', field: 'caculatedAmount', width: 350, cellStyle: { 'textAlign': 'center' } },
        { headerName: '필요수량', field: 'requiredAmount', width: 350, cellStyle: { 'textAlign': 'center' } },
        { headerName: '단위', field: 'unitOfMrp', width: 350, cellStyle: { 'textAlign': 'center' } },
    ];
    new agGrid.Grid(mrpGrid, mrpGridOptions);
}
// o get Mrp data
const getMrpList = (mpsRowNode) => {
    mrpGridOptions.api.setRowData([]);
    console.log(mpsRowNode.data.mpsNo);
    let xhr = new XMLHttpRequest();
    let mpsNoList = JSON.stringify([mpsRowNode.data.mpsNo]);
    console.log(mpsNoList);
    xhr.open('POST', '/production/openMrp.do' +
        "?method=openMrp"
        + "&mpsNoList=" + encodeURI(mpsNoList),
        true)
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.send();
    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let txt = xhr.responseText;
            jsonData = JSON.parse(txt);
            console.log(jsonData);
            mrpGridOptions.api.updateRowData({add: jsonData.gridRowJson});
            if (jsonData.errorCode !== 0) {
                Swal.fire({
                    text: '데이터를 불러드리는데 오류가 발생했습니다.',
                    icon: 'error',
                });
                return;
            }
        }
    }
}
// o do registerMrp
const registerMrp = (mrpDate) => {
    let xhr = new XMLHttpRequest();
    let batchList = JSON.stringify(jsonData.gridRowJson);
    xhr.open('POST', '/production/registerMrp.do' +
        "?method=registerMrp"
        + "&batchList=" + encodeURI(batchList)
        + "&mrpRegisterDate=" + mrpDate,
        true)
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.send();
    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let txt = xhr.responseText;
            txt = JSON.parse(txt);
            let resultMsg =
                `< 소요량전개  => 소요량취합 등록 내역 ><br>`
                + ((txt.result.INSERT.length !== 0) ?
                    `소요량취합 일련번호 : <br>`
                    + txt.result.firstMrpNo + ` 부터 ` + txt.result.lastMrpNo + ` 까지 <br>
                     총 ` + txt.result.INSERT.length + `건 등록 완료.<br>`
                    + `소요량전개 일련번호 : ` + txt.result.changeMrpApplyStatus + ` 의 <br>
                     소요량취합 적용상태  Y 로 변경 완료.` : `없음`) + `<br>
                     위와 같이 작업이 처리되었습니다.`;
            console.log(mpsRowNode);
            mpsRowNode.setDataValue("mrpApplyStatus", 'Y');
            if (jsonData.errorCode !== 0) {
                Swal.fire({
                    text: '데이터를 불러드리는데 오류가 발생했습니다.',
                    icon: 'error',
                });
                return;
            } else {
                Swal.fire("성공", resultMsg, "success");
            }
        }
    }
}
// O MRP GATHERING
// o set MrpGathering
let mrpGatheringGridOptions;
const setMrpGatheringModal = () => {
    mrpGatheringGridOptions = gridOptions;
    mrpGatheringGridOptions.columnDefs = [
        { headerName: "구매 및 생산여부", field: "orderOrProductionStatus", cellStyle: { 'textAlign': 'center' } },
        { headerName: "품목코드", field: "itemCode", cellStyle: { 'textAlign': 'center' } },
        { headerName: '품목명', field: 'itemName', cellStyle: { 'textAlign': 'center' } },
        { headerName: '단위', field: 'unitOfMrpGathering', cellStyle: { 'textAlign': 'center' } },
        {
            headerName: '발주/작업지시기한',
            field: 'claimDate',
            cellStyle: { 'textAlign': 'center' },
            cellRenderer: function (params) {
                if (params.value == null) {
                    params.value = "";
                }
                return '📅 ' + params.value;
            }
        },
        {
            headerName: '발주/작업지시완료기한',
            field: 'dueDate',
            cellStyle: { 'textAlign': 'center' },
            cellRenderer: function (params) {
                if (params.value == null) {
                    params.value = "";
                }
                return '📅 ' + params.value;
            }
        },
        { headerName: '필요수량', field: 'necessaryAmount', cellStyle: { 'textAlign': 'center' } }
    ];
    mrpGatheringGridOptions.getRowStyle = (param) => {
        if (param.data.orderOrProductionStatus != "구매") {
            return { 'font-weight': 'bold', background: '#b1b5cc' };
        }
    }
    new agGrid.Grid(mrpGatheringGrid, mrpGatheringGridOptions);
}
// o get MrpGathering Data
const getMrpGatheringModal = (mrpNoList) => {
    mrpGatheringGridOptions.api.setRowData([]);
    let xhr = new XMLHttpRequest();
    mrpNoList = JSON.stringify(mrpNoList);
    xhr.open('POST', '/production/getMrpGatheringList.do' +
        "?method=getMrpGatheringList"
        + "&mrpNoList=" + encodeURI(mrpNoList),
        true)
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.send();
    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let txt = xhr.responseText;
            jsonData = JSON.parse(txt);
            // todo ... 왜 mrpGatheringNo, mrpGatheringStatus, mrpTOList 넘어오는거지?
            for (let objcet of jsonData.gridRowJson) {
                delete objcet.mrpGatheringNo;
                delete objcet.mrpGatheringStatus;
                delete objcet.mrpTOList;
            }
            console.log(jsonData);
            mrpGatheringGridOptions.api.updateRowData({ add: jsonData.gridRowJson });
            if (jsonData.errorCode < 0) {
                Swal.fire({
                    text: '데이터를 불러드리는데 오류가 발생했습니다.',
                    icon: 'error',
                });
                return;
            }
        }
    }
}
const registerMrpGathering = (mrpGatheringDate, mrpNoList, mrpNoAndItemCodeList) => {
    let xhr = new XMLHttpRequest();
    let mrpGatheringList = JSON.stringify(jsonData.gridRowJson);
    mrpNoList = JSON.stringify(mrpNoList);
    mrpNoAndItemCodeList = JSON.stringify(mrpNoAndItemCodeList);
    console.log(mrpGatheringList);
    console.log(mrpGatheringDate);
    console.log(mrpNoList);
    console.log(mrpNoAndItemCodeList);
    xhr.open('POST', '/production/registerMrpGathering.do' +
        "?method=registerMrpGathering"
        + "&batchList=" + encodeURI(mrpGatheringList)
        + "&mrpGatheringRegisterDate=" + mrpGatheringDate
        + "&mrpNoList=" + encodeURI(mrpNoList)
        + "&mrpNoAndItemCodeList=" + encodeURI(mrpNoAndItemCodeList),
        true)
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.send();
    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let txt = xhr.responseText;
            txt = JSON.parse(txt);
            let resultMsg =
                "< 소요량전개 => 소요량취합 등록 내역 ><br/>"
                + ((txt.result.INSERT.length !== 0) ?
                    "소요량취합 일련번호 : " + txt.result.firstMrpGatheringNo + " 부터 </br>"
                    + txt.result.lastMrpGatheringNo + " 까지 </br>"
                    + txt.result.INSERT.length + " 건 등록 완료. </br>"
                    + "소요량전개 일련번호 : " + txt.result.changeMrpGatheringStatus
                    + "</br> 의 소요량취합 적용상태  \"Y\" 로 변경 완료." : "없음") + "</br>"
                + "위와 같이 작업이 처리되었습니다.";
            mrpGatheringGridOptions.api.setRowData([]);
            if (txt.errorCode < 0) {
                Swal.fire({
                    text: '데이터를 불러드리는데 오류가 발생했습니다.',
                    icon: 'error',
                });
                return;
            } else {
                Swal.fire("성공", resultMsg, "success");
            }
        }
    }
}
// O OrderDialog
let orderGridOptions;
const setOrderModal = () => {
    orderGridOptions = gridOptions;
    orderGridOptions.columnDefs = [
        //{ headerName: "선택된 취합번호", field: "mrpGatheringNo", cellStyle: { 'textAlign': 'center' } },
        { headerName: "품목코드", field: "itemCode", cellStyle: { 'textAlign': 'center' } },
        { headerName: '품목명', field: 'itemName', cellStyle: { 'textAlign': 'center' } },
        { headerName: '단위', field: 'unitOfMrp', cellStyle: { 'textAlign': 'center' } },
        { headerName: '총발주필요량', field: 'requiredAmount', cellStyle: { 'textAlign': 'center' } },
        { headerName: '사용가능재고량', field: 'stockAmount', cellStyle: { 'textAlign': 'center' }, },
        { headerName: '실제발주필요량', field: 'calculatedRequiredAmount', cellStyle: { 'textAlign': 'center' } },
        { headerName: '단가', field: 'standardUnitPrice', cellStyle: { 'textAlign': 'center' } },
        { headerName: '합계금액', field: 'sumPrice', cellStyle: { 'textAlign': 'center' } },
    ];
    new agGrid.Grid(orderGrid, orderGridOptions);
}
const getOrderModal = (mrpGatheringList) => {
    let xhr = new XMLHttpRequest();
    mrpGatheringList = JSON.stringify(mrpGatheringList);
    xhr.open('POST', '/material/showOrderDialog.do' +
        "?method=openOrderDialog"
        + "&mrpGatheringNoList=" + encodeURI(mrpGatheringList),
        true)
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.send();
    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let txt = xhr.responseText;
            txt = JSON.parse(txt);
            orderGridOptions.api.setRowData([]);
            if (txt.errorCode < 0) {
                Swal.fire({
                    text: '데이터를 불러드리는데 오류가 발생했습니다.',
                    icon: 'error',
                });
                return;
            }
            orderGridOptions.api.updateRowData({ add: txt.gridRowJson });
        }
    }
}


// O Warehousing
let warehousingGridOptions;
const setWarehousingModal = () => {
    warehousingGridOptions = gridOptions;
    warehousingGridOptions.columnDefs = [
        {
            headerName: "발주번호", field: "orderNo", suppressSizeToFit: true, headerCheckboxSelection: true,
            headerCheckboxSelectionFilteredOnly: true,
            checkboxSelection: true
        },
        { headerName: "발주날짜", field: "orderDate", cellStyle: { 'textAlign': 'center' } },
        { headerName: "상태", field: "orderInfoStatus", cellStyle: { 'textAlign': 'center' } },
        { headerName: '발주구분', field: 'orderSort', cellStyle: { 'textAlign': 'center' } },
        { headerName: '품목코드', field: 'itemCode', cellStyle: { 'textAlign': 'center' } },
        { headerName: '품목명', field: 'itemName', cellStyle: { 'textAlign': 'center' } },
        { headerName: '수량', field: 'orderAmount', cellStyle: { 'textAlign': 'center' }, },
    ];
    warehousingGridOptions.getRowNodeId = (data) => {
        return data.orderNo;
    };
    new agGrid.Grid(warehousingGrid, warehousingGridOptions);
}
const getWarehousingModal = () => {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', '/material/searchOrderInfoListOnDelivery.do' +
        "?method=searchOrderInfoListOnDelivery",
        true);
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.send();
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4 && xhr.status == 200) {
            warehousingGridOptions.api.setRowData([]);
            let txt = xhr.responseText;
            txt = JSON.parse(txt);
            if (txt.errorCode < 0) {
                Swal.fire({
                    text: '데이터를 불러드리는데 오류가 발생했습니다.',
                    icon: 'error',
                });
                return;
            }
            warehousingGridOptions.api.updateRowData({ add: txt.gridRowJson });
        }
    }
}
// O WorkOrderSimulationModal
// o set WorkOrderSimulationModal
let workOrderSimulationGridOptions;
const setWorkOrderSimulationModal = () => {
    workOrderSimulationGridOptions = gridOptions;
    workOrderSimulationGridOptions.columnDefs = [
       // { headerName: "소요량전개번호", width: 500, field: "mrpNo", cellStyle: { 'textAlign': 'center' } },
        //{ headerName: "주생산계획번호", width: 500, field: "mpsNo", cellStyle: { 'textAlign': 'center' } },
        { headerName: "소요량취합번호", width: 500, field: "mrpGatheringNo", cellStyle: { 'textAlign': 'center' } },
        { headerName: '품목분류', field: 'itemClassification', width: 300, cellStyle: { 'textAlign': 'center' } },
        { headerName: '품목코드', field: 'itemCode', width: 400, cellStyle: { 'textAlign': 'center' } },
        { headerName: '품목명', field: 'itemName', width: 550, cellStyle: { 'textAlign': 'center' } },
        { headerName: '단위', field: 'unitOfMrp', width: 300, cellStyle: { 'textAlign': 'center' }, },
        { headerName: '재고량(투입예정재고)', field: 'inputAmount', width: 450, cellStyle: { 'textAlign': 'center' }, },
        { headerName: '재고소요/제작수량', field: 'requiredAmount', width: 400, cellStyle: { 'textAlign': 'center' }, },
        { headerName: '재고량(재고소요이후)', field: 'stockAfterWork', width: 500, cellStyle: { 'textAlign': 'center' }, },
        { headerName: '작업지시기한', field: 'orderDate', width: 400, cellStyle: { 'textAlign': 'center' }, },
        { headerName: '작업완료기한', field: 'requiredDate', width: 400, cellStyle: { 'textAlign': 'center' }, },
    ];
    workOrderSimulationGridOptions.getRowNodeId = (data) => {
        return data.requiredDate;
    };
    new agGrid.Grid(workOrderSimulationGrid, workOrderSimulationGridOptions);
}
// o get WorkOrderSimulationModal
const getWorkOrderSimulationModal = (mrpGatheringNo) => {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', '/production/showWorkOrderDialog.do'
        + "?method=showWorkOrderDialog"
        + "&mrpGatheringNo=" + mrpGatheringNo,
        true);
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.send();
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let txt = xhr.responseText;
            txt = JSON.parse(txt);
            workOrderSimulationGridOptions.api.setRowData([]);
            if (txt.errorCode < 0) {
                Swal.fire({
                    text: '데이터를 불러드리는데 오류가 발생했습니다.',
                    icon: 'error',
                });
                return;
            }
            workOrderSimulationGridOptions.api.updateRowData({ add: txt.gridRowJson });
        }
    }
}
// o set Worksite
let workSiteSimulationGridOptions;
const setWorkSiteModal = () => {
    workSiteSimulationGridOptions = gridOptions;
    workSiteSimulationGridOptions.columnDefs = [
        { headerName: "작업지시번호", field: "workOrderNo", cellStyle: { 'textAlign': 'center' } },
        { headerName: "소요량전개번호", field: "mrpNo", cellStyle: { 'textAlign': 'center' } },
        { headerName: "주생산계획번호", field: "mpsNo", cellStyle: { 'textAlign': 'center' } },
        { headerName: "작업장명", field: "workSieteName", cellStyle: { 'textAlign': 'center' } },
        { headerName: "제작품목분류", field: "wdItem", cellStyle: { 'textAlign': 'center' } },
        { headerName: "제작품목코드", field: "parentItemCode", cellStyle: { 'textAlign': 'center' } },
        { headerName: "제작품목명", field: "parentItemName", cellStyle: { 'textAlign': 'center' } },
        { headerName: "작업품목분류", field: "itemClassIfication", cellStyle: { 'textAlign': 'center' } },
        { headerName: "작업품목코드", field: "itemCode", cellStyle: { 'textAlign': 'center' } },
        { headerName: "작업품목명", field: "itemName", cellStyle: { 'textAlign': 'center' } },
        { headerName: "작업량", field: "requiredAmount", cellStyle: { 'textAlign': 'center' } },
    ];
    new agGrid.Grid(workSiteSituationGrid, workSiteSimulationGridOptions);
}
const getWorkSiteModal = (workSiteCourse, workOrderNo, itemClassIfication) => {
    console.log(workSiteCourse);
    console.log(workOrderNo);
    console.log(itemClassIfication);

    let xhr = new XMLHttpRequest();
    xhr.open('GET', '/production/showWorkSiteSituation.do'
        + "?method=showWorkSiteSituation"
        + "&workSiteCourse=" + workSiteCourse
        + "&workOrderNo=" + workOrderNo
        + "&itemClassIfication=" + encodeURI(itemClassIfication),
        true);
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.send();
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let txt = xhr.responseText;
            txt = JSON.parse(txt);
            console.log('test');
            console.log(txt.gridRowJson);
            workSiteSimulationGridOptions.api.setRowData([]);
            if (txt.errorCode < 0) {
                Swal.fire({
                    text: '데이터를 불러드리는데 오류가 발생했습니다.',
                    icon: 'error',
                });
                return;
            }
            workSiteSimulationGridOptions.api.setRowData(txt.gridRowJson);
        }
    }
}