<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <script src="https://unpkg.com/ag-grid-community/dist/ag-grid-community.min.noStyle.js"></script>
    <link rel="stylesheet" href="https://unpkg.com/ag-grid-community/dist/styles/ag-grid.css">
    <link rel="stylesheet" href="https://unpkg.com/ag-grid-community/dist/styles/ag-theme-balham.css">
    <script src="${pageContext.request.contextPath}/js/modal.js?v=<%=System.currentTimeMillis()%>" defer></script>
    <script src="${pageContext.request.contextPath}/js/datepicker.js" defer></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker.css">
    <script>
        // O setting datapicker
        $(function() {
            // o set searchDate
            $('[data-toggle="datepicker"]').datepicker({
                autoHide: true,
                zIndex: 2048,
                startDate: new Date(),
                endDate: '0d',
                todayHiglght: true,
                autoaShow: true,
            });
        });
    </script>
    <style>
        button {
            background-color: #506FA9;
            border: none;
            color: white;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
            border-radius: 3px;
            margin-bottom: 10px;
        }

        .ag-header-cell-label {
            justify-content: center;
        }

        .ag-cell-value {
            padding-left: 12px;
        }

        .form-control {
            display: inline;
        !important;
        }

        #orderModal {
            position: absolute !important;
            z-index: 3000;
        }

        @media (min-width: 768px) {
            .modal-xl {
                width: 90%;
                max-width: 1700px;
            }
        }
    </style>
</head>
<body>
<article class="workOrder">
    <div class="workOrder__Title" style="color: black">
        <h5>🏭 작업지시</h5>
        <b>필요목록 조회 / 작업지시(BY MRP)</b></br>
        <button id="workOrderListButton">조회</button>
        <button id="showWorkOrderSimulationByMrpButton">모의작업지시</button>
    </div>
</article>
<article class="myGrid">
    <div align="center">
        <div id="myGrid" class="ag-theme-balham" style="height:30vh; width:auto; text-align: center;"></div>
    </div>
</article>
<article class="workOrderInfo">
    <div class="workOrderInfo" style="color: black">
        <h5>📊 작업지시현황</h5>
        <b>작업지시 조회 / 등록</b><br/>
        <button id="workOrderInfoListButton">작업지시 조회</button>
        <button id="workOrderCompletionButton">작업완료 등록</button>
    </div>
</article>
<article class="myGrid2">
    <div align="center">
        <div id="myGrid2" class="ag-theme-balham" style="height:35vh; width:auto; text-align: center;"></div>
    </div>
</article>
<%--O WORKORDER MODAL--%>
<div class="modal fade" id="workOrderModal" role="dialog">
    <div class="modal-dialog modal-xl">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title">
                    <h5>WORKORDER</h5>
                    <input type="text" data-toggle="datepicker" id="workOderDate" placeholder="작업일자 📅" size="10"
                           autocomplete="off" style="text-align: center">&nbsp&nbsp
                    <input type="text" placeholder="사업장코드" id="workPlaceName" value="${sessionScope.workplaceName}"
                           size="12">
                    <select type="text" placeholder="생산공정코드" id="productionProcess" style="width: 130px; height: 26px">
                    </select>
                    <button id="workOrderButton">모의전개된 결과 작업지시</button>
                </div>
                <button type="button" class="close" data-dismiss="modal" style="padding-top: 0.5px; height: 35px">
                    &times;
                </button>
            </div>
            <div class="modal-body">
                <div id="workOrderSimulationGrid" class="ag-theme-balham" style="height: 40vh;width:auto;">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script>
    const myGrid = document.querySelector("#myGrid");
    const myGrid2 = document.querySelector("#myGrid2");
    const workOrderListBtn = document.querySelector("#workOrderListButton");                            // 조회
    const showWorkOrderSimulationByMrpBtn = document.querySelector("#showWorkOrderSimulationByMrpButton");  // 모의작업지시
    const workOderDate = document.querySelector("#workOderDate");                                             // 작업일자
    const workOrderBtn = document.querySelector("#workOrderButton");         // 작업지시
    const productionProcess = document.querySelector("#productionProcess"); // 생산공정코드
    // 작업지시 현황
    const workOrderInfoListBtn = document.querySelector("#workOrderInfoListButton");
    const workOrderCompletionBtn = document.querySelector("#workOrderCompletionButton");

    // O workOrderList
    const workMrpColumn = [
        /*  {
           headerName: "소요량전개번호", field: "mrpNo", suppressSizeToFit: true, headerCheckboxSelection: false,
           headerCheckboxSelectionFilteredOnly: true, suppressRowClickSelection: true,
           checkboxSelection: true
         }, */
        //{headerName: "주생산계획번호", field: "mpsNo",},
        {headerName: '소요량취합번호', field: "mrpGatheringNo",
            suppressSizeToFit: true, headerCheckboxSelection: false,
            headerCheckboxSelectionFilteredOnly: true,checkboxSelection: true},
        {headerName: '품목분류', field: 'itemName', cellRenderer:(params) => {
                if (params.value. indexOf('본체')>0) {
                    return params.value = "반제품";
                }
                return '완제품';//프로시저 상에서 생산,구매로 넘어오는 것들은 품목분류에 속하지 않는놈인데 쓸데없이 넘어와서
                // 제품별로 반제품인지 완제품인지 분류하기 위해 위의 기능을 구현함
            }},

        {headerName: '품목코드', field: 'itemCode',},
        {headerName: '품목명', field: 'itemName',},
        {headerName: '단위', field: 'unitOfMrp',},
        {headerName: '필요수량', field: 'requiredAmount',},
        {headerName: '작업지시기한', field: 'orderDate', cellRenderer: function (params) {
                return '📅 ' + params.value;
            }},
        {headerName: '작업완료기한', field: 'requiredDate', cellRenderer: function (params) {
                return '📅 ' + params.value;
            }},
    ];
    let workMrpRowData = [];
    let workMrpRowNode;
    const workMrpGridOptions = {
        defaultColDef: {
            flex: 1,
            minWidth: 100,
            resizable: true,
        },
        columnDefs: workMrpColumn,
        rowSelection: 'single',
        rowData: workMrpRowData,
        defaultColDef: {editable: false},
        overlayNoRowsTemplate: "작업지시 리스트가 없습니다.",
        onGridReady: function(event) {// onload 이벤트와 유사 ready 이후 필요한 이벤트 삽입한다.
            event.api.sizeColumnsToFit();
        },
        onRowSelected: function(event) { // checkbox
            workMrpRowNode = event;
            console.log(workMrpRowNode);
        },
        onGridSizeChanged: function(event) {
            event.api.sizeColumnsToFit();
        },
        getRowStyle: function(param) {
            return {'text-align': 'center'};
        },
    }
    // o get work orderable Mrp List
    const workOrderList = () => {
        workMrpGridOptions.api.setRowData([]);
        let xhr = new XMLHttpRequest();
        xhr.open('POST', '${pageContext.request.contextPath}/production/getWorkOrderableMrpList.do' +
            "?method=getWorkOrderableMrpList",
            true)
        xhr.setRequestHeader('Accept', 'application/json');
        xhr.send();
        xhr.onreadystatechange = () => {
            if (xhr.readyState === 4 && xhr.status === 200) {
                let txt = xhr.responseText;
                txt = JSON.parse(txt);
                if (txt.errorCode < 0) {
                    swal.fire("오류", txt.errorMsg, "error");
                    return;
                }
                console.log(txt);
                workMrpGridOptions.api.setRowData(txt.gridRowJson);
            }
        }
    }
    workOrderListBtn.addEventListener('click', () => {
        workOrderList();
    });
    // O warehousing modal
    let _setWorkOrderSimulationModal = (function() {
        let executed = false;
        return function() {
            if (!executed) {
                executed = true;
                setWorkOrderSimulationModal();
            }
        };
    })();
    let _getDetailCodeList = (function() {
        let executed = false;
        return function() {
            if (!executed) {
                executed = true;
                getDetailCodeList();
            }
        };
    })();
    // o 생산조립과정 select tage에 넣기
    const getDetailCodeList= () => {
        let data = transferVar();
        let target = document.querySelector("#productionProcess");
        for (let index of data.detailCodeList) {
            let node = document.createElement("option");
            if (index.codeUseCheck != "N") { //CodeUseCheck가 N인것만 가능하다.
                node.value = index.detailCode;
                let textNode = document.createTextNode(index.detailCodeName);
                node.appendChild(textNode);
                target.appendChild(node);
            }
        }
    }
    // O WORKORDER
    showWorkOrderSimulationByMrpBtn.addEventListener('click', () => {
        let selectedRows = workMrpGridOptions.api.getSelectedRows();
        if (selectedRows == "") {
            Swal.fire("알림", "선택한 행이 없습니다.", "info");
            return;
        }
        getCustomerData('PP');
        let mrpGatheringNo = selectedRows[0].mrpGatheringNo;
        _setWorkOrderSimulationModal();
        getWorkOrderSimulationModal(mrpGatheringNo);
        $("#workOrderModal").modal('show');
        setTimeout(() => {
            _getDetailCodeList();
        }, 100)

    });
    // o order work
    workOrderBtn.addEventListener('click', () => {
        // o 확인
        if (workOderDate.value == "") {
            Swal.fire("알림", "작업일자를 입력하십시오.", "info");
            return;
        }
        if (productionProcess.value == "") {
            Swal.fire("알림", "생산공정코드를 입력하십시오.", "info");
            return
        }
        let displayModel = workOrderSimulationGridOptions.api.getModel();
        let modalData = displayModel.gridApi.getRenderedNodes();
        let workOrderList = [];
        let workItem;
        let status;
        let mrpGatheringNo;
        modalData.forEach(function(rowNode, index) {
            if (rowNode.data.stockAfterWork == "재고부족") {
                Swal.fire("알림", "재고가 부족합니다.", "info");
                return status = true;
            }
            if (rowNode.data.itemClassification == "생산") {
                workItem = rowNode.data.itemName;
                mrpGatheringNo = rowNode.data.mrpGatheringNo;
            }
            workOrderList.push(rowNode.data);
        });
        if (status) {return;}
        console.log(workOrderList);
        // o 데이터 전송
        Swal.fire({
            title: '작업을 지시하시겠습니까?',
            html: '작업지시품목</br>' + '<b>' + workItem + '</b>' + '</br>를 작업합니다.',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            cancelButtonText: '취소',
            confirmButtonText: '확인',
        }).then((result) => {
            let productionProcessCode = productionProcess.value;
            if (result.isConfirmed) {
                let xhr = new XMLHttpRequest();
                xhr.open('POST', '${pageContext.request.contextPath}/production/workOrder.do' +
                    "?method=workOrder"
                    + "&mrpGatheringNo=" + mrpGatheringNo
                    + "&workPlaceCode=" + "${sessionScope.workplaceCode}"
                    + "&productionProcessCode=" + productionProcessCode,
                    true)
                xhr.setRequestHeader('Accept', 'application/json');
                xhr.send();
                xhr.onreadystatechange = () => {
                    if (xhr.readyState == 4 && xhr.status == 200) {
                        let txt = xhr.responseText;
                        txt = JSON.parse(txt);
                        console.log(txt);
                        if (txt.errorCode < 0) {
                            swal.fire("오류", txt.errorMsg, "error");
                            return;
                        }
                        Swal.fire(
                            '성공!',
                            '작업지시가 완료되었습니다. 작업장에서 검사 및 제작 상황을 볼 수 있습니다.',
                            'success'
                        )
                        workMrpGridOptions.api.updateRowData({remove: [workMrpRowNode.data]});
                        workOrderSimulationGridOptions.api.setRowData([]);
                        console.log(txt);
                    }
                }
            }
        })
    });
    // O workOrderInfo
    const workOrderInfoColumn = [
        {headerName: "작업지시일련번호", field: "workOrderNo", suppressSizeToFit: true, headerCheckboxSelection: false,
            headerCheckboxSelectionFilteredOnly: true, suppressRowClickSelection: true,
            checkboxSelection: true},
        //{headerName: "소요량전개번호", field: "mrpNo", width: 300},
        //{headerName: '주생산계획번호', field: "mpsNo",width: 300},
        {headerName: '소요량취합번호', field: 'mrpGatheringNo', width: 300},
        {headerName: '품목분류', field: 'itemClassification',},
        {headerName: '품목코드', field: 'itemCode'},
        {headerName: '품목명', field: 'itemName', width: 300},
        {headerName: '단위', field: 'unitOfMrp'},
        {headerName: '지시수량', field: 'requiredAmount'},
        {headerName: '생산공정코드', field: 'productionProcessCode'},
        {headerName: '생산공정명', field: 'productionProcessName'},
        {headerName: '작업장코드', field: 'workSiteCode'},
        {headerName: '작업장명', field: 'workStieName'},
        {headerName: '완료상태', field: 'completionStatus', cellRenderer: function (params) {
                if (params.value == "Y") {
                    return params.value = "🟢";
                }
                return '❌';
            },},
        {headerName: '작업완료된수량', field: 'actualCompletionAmount', editable: true, cellRenderer: function (params) {
                if (params.value == null) {
                    return '📷';
                }
                return '📷' + params.value;
            },}
    ];
    let workOrderInfoRowData = [];
    const workOrderInfoGridOptions = {
        defaultColDef: {
            flex: 1,
            minWidth: 100,
            resizable: true,
        },
        columnDefs: workOrderInfoColumn,
        rowSelection: 'single',
        rowData: workOrderInfoRowData,
        defaultColDef: {editable: false},
        overlayNoRowsTemplate: "조회된 작업지시현황 리스트가 없습니다.",
        onGridReady: function(event) {// onload 이벤트와 유사 ready 이후 필요한 이벤트 삽입한다.
            event.api.sizeColumnsToFit();
        },
        onRowSelected: function(event) { // checkbox
        },
        onGridSizeChanged: function(event) {
            event.api.sizeColumnsToFit();
        },
        getRowStyle: function(param) {
            return {'text-align': 'center'};
        },
    }
    // o get workSiteLog
    const WorkOrderInfoList = () => {
        let xhr = new XMLHttpRequest();
        xhr.open('POST', '${pageContext.request.contextPath}/production/showWorkOrderInfoList.do' +
            "?method=showWorkOrderInfoList",
            true)
        xhr.setRequestHeader('Accept', 'application/json');
        xhr.send();
        xhr.onreadystatechange = () => {
            if (xhr.readyState === 4 && xhr.status === 200) {
                let txt = xhr.responseText;
                txt = JSON.parse(txt);
                if (txt.errorCode < 0) {
                    swal.fire("오류", txt.errorMsg, "error");
                    return;
                } else if (txt.gridRowJson === "") {
                    swal.fire("알림", "조회된 작업장리스트가 없습니다.", "info");
                    return;
                }
                console.log(txt);
                workOrderInfoGridOptions.api.setRowData(txt.gridRowJson);
            }
        }
    }
    // O Select workOrderInfoList
    workOrderInfoListBtn.addEventListener('click', () => {
        WorkOrderInfoList();
    });
    // O workOrderCompletion
    const workOrderCompletion = () => {
        workOrderInfoGridOptions.api.stopEditing();
        let selectedRows = workOrderInfoGridOptions.api.getSelectedRows();
        let selectedRow = selectedRows[0];
        console.log(selectedRow);
        if (selectedRows == "") {
            Swal.fire("알림", "선택한 행이 없습니다.", "info");
            return;
        }
        if (selectedRow.completionStatus != "Y")  {
            Swal.fire("알림", "작업공정이 다 끝나지 않았습니다. 작업장을 방문하십시오", "info");
            return;
        }
        if (selectedRow.actualCompletionAmount == undefined || selectedRow.actualCompletionAmount == "")  {
            Swal.fire("알림", "작업완료수량을 입력하십시오.", "info");
            return;
        }
        let confirmMsg = "작업을 완료합니다.</br>"
            + "작업일련번호 : " + selectedRow.workOrderNo  + "</br>"
            + "<b>작업완료된수량 : " + selectedRow.actualCompletionAmount + "</b></br>";

        // o 데이터 전송
        Swal.fire({
            title: '작업을 완료하시겠습니까?',
            html: confirmMsg,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            cancelButtonText: '취소',
            confirmButtonText: '확인',
        }).then((result) => {
            if (result.isConfirmed) {
                let xhr = new XMLHttpRequest();
                xhr.open('POST', '${pageContext.request.contextPath}/production/workOrderCompletion.do' +
                    "?method=workOrderCompletion"
                    + "&workOrderNo=" + selectedRow.workOrderNo
                    + "&actualCompletionAmount=" + selectedRow.actualCompletionAmount,
                    true)
                xhr.setRequestHeader('Accept', 'application/json');
                xhr.send();
                xhr.onreadystatechange = () => {
                    if (xhr.readyState == 4 && xhr.status == 200) {
                        let txt = xhr.responseText;
                        txt = JSON.parse(txt);
                        console.log(txt);
                        if (txt.errorCode < 0) {
                            swal.fire("오류", txt.errorMsg, "error");
                            return;
                        }
                        Swal.fire(
                            '성공!',
                            '작업등록이 완료되었습니다.',
                            'success'
                        )
                        workOrderInfoGridOptions.api.updateRowData({remove: [selectedRow]});
                        console.log(txt);
                    }
                }
            }
        });
    }

    // O Register workOrderCompletion
    workOrderCompletionBtn.addEventListener('click', () => {
        workOrderCompletion();
    })

    document.addEventListener('DOMContentLoaded', () => {
        new agGrid.Grid(myGrid, workMrpGridOptions);
        new agGrid.Grid(myGrid2, workOrderInfoGridOptions);
    });
</script>
</body>
</html>