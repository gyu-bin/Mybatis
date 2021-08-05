<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>workSite</title>
    <script src="https://unpkg.com/ag-grid-community/dist/ag-grid-community.min.noStyle.js"></script>
    <link rel="stylesheet" href="https://unpkg.com/ag-grid-community/dist/styles/ag-grid.css">
    <link rel="stylesheet" href="https://unpkg.com/ag-grid-community/dist/styles/ag-theme-balham.css">
    <script src="${pageContext.request.contextPath}/js/modal.js?v=<%=System.currentTimeMillis()%>" defer></script>
    <script src="${pageContext.request.contextPath}/js/datepicker.js" defer></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker.css">
    <script>
      $(function () {
        $('[data-toggle="datepicker"]').datepicker({
          autoHide: true,
          zIndex: 2048,
        });
        $('#datepicker').datepicker({
          todayHiglght: true,
          autoHide: true,
          autoaShow: true,
        });
      })
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
            padding-left: 10px;
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
<article class="workSite">
    <div class="workSite__Title" style="color: black">
        <h5>🏭 작업장</h5>
        <b>작업장 조회 / 제품 작업장</b></br>
        <button id="workSiteList">조회</button>
        &nbsp&nbsp&nbsp&nbsp
        <button id="workSiteRawMaterials">원재료 검사</button>
        <button id="workSiteProduction">제품 제작</button>
        <button id="workSiteExamine">판매제품 검사</button>
    </div>
</article>
<article class="workSiteGrid">
    <div align="center">
        <div id="workSiteGrid" class="ag-theme-balham" style="height:30vh; width:auto; text-align: center;"></div>
    </div>
</article>
<article class="workSiteLog" style="color: black">
    <div class="workSiteLog__Ttile">
        <h5>🏭 작업장로그</h5>
        <b>작업장로그 조회</b><br/>
            <input type="text" data-toggle="datepicker" id="workSiteLogDate" placeholder="YYYY-MM-DD 📅" size="15" autocomplete="off" style="text-align: center">&nbsp&nbsp
            <button id="workSiteLogListButton">작업장 조회</button>
    </div>
</article>
<article class="workSiteLogGrid">
    <div align="center">
        <div id="workSiteLogGrid" class="ag-theme-balham" style="height:40vh; width:auto; text-align: center;"></div>
    </div>
</article>
<%--O WORK COMPLET MODAL--%>
<div class="modal fade" id="workSiteModal" role="dialog">
    <div class="modal-dialog modal-xl">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title">
                    <h5>WORK SITE</h5>
                    <button id="complete">검사 및 제작완료</button>
                </div>
                <button type="button" class="close" data-dismiss="modal" style="padding-top: 0.5px; height: 35px">
                    &times;
                </button>
            </div>
            <div class="modal-body">
                <div id="workSiteSituationGrid" class="ag-theme-balham" style="height: 40vh;width:auto;">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script>
  const workSiteGrid = document.querySelector("#workSiteGrid");
  const workSiteLogGrid = document.querySelector("#workSiteLogGrid");
  const workSiteListBtn = document.querySelector("#workSiteList");                   // 조회
  const workSiteRawMaterialsBtn = document.querySelector("#workSiteRawMaterials");  // 원재료검사
  const workSiteProductionBtn = document.querySelector("#workSiteProduction");      // 제품제작
  const workSiteExamineBtn = document.querySelector("#workSiteExamine");            // 판매제품검사
  const completeBtn = document.querySelector("#complete");                           // 제작완료
  // 작업장
  const workSiteLogListBtn = document.querySelector("#workSiteLogListButton");      // 작업장 로그
  const workSiteLogDate = document.querySelector("#workSiteLogDate");               // 작업장 로그일자

  // O workSiteList
  const workSiteColumn = [
    {
      headerName: "작업지시일련번호", field: "workOrderNo", suppressSizeToFit: true, headerCheckboxSelection: false,
      headerCheckboxSelectionFilteredOnly: true, suppressRowClickSelection: true,
      checkboxSelection: true
    },
    {headerName: "소요량전개번호", field: "mrpNo", width: 300},
    {headerName: '주생산계획번호', field: "mpsNo", width: 300},
    {headerName: '소요량취합번호', field: 'mrpGatheringNo', width: 300},
    {headerName: '품목분류', field: 'itemClassification', },
    {headerName: '품목코드', field: 'itemCode',width: 300},
    {headerName: '품목명', field: 'itemName', width: 300},
    {headerName: '단위', field: 'unitOfMrp',width: 250},
    {headerName: '생산공정코드', field: 'productionProcessCode'},
    {headerName: '생산공정명', field: 'productionProcessName'},
    {headerName: '작업장코드', field: 'workSiteCode'},
    {headerName: '작업장명', field: 'workStieName'},
    {headerName: '원재료검사', field: 'inspectionStatus', cellRenderer: function (params) {
        if (params.value == "Y") {
          return params.value = "🟢";
        }
        return '❌';
      },
    },
    {headerName: '제품제작', field: 'productionStatus',cellRenderer: function (params) {
        if (params.value == "Y") {
          return params.value = "🟢";
        }
        return '❌';
      },},
    {headerName: '제품검사', field: 'completionStatus',cellRenderer: function (params) {
        if (params.value == "Y") {
          return params.value = "🟢";
        }
        return '❌';
      },},
  ];
  let workSiteRowData = [];
  const workSiteGridOptions = {
    defaultColDef: {
      flex: 1,
      minWidth: 100,
      resizable: true,
    },
    columnDefs: workSiteColumn,
    rowSelection: 'single',
    rowData: workSiteRowData,
    defaultColDef: {editable: false},
    overlayNoRowsTemplate: "작업지시 리스트가 없습니다.",
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
  // o get work site List
  const getWorkSiteList = () => {
    workSiteGridOptions.api.setRowData([]);
    let xhr = new XMLHttpRequest();
    xhr.open('POST', '${pageContext.request.contextPath}/production/showWorkOrderInfoList.do' +
        "?method=showWorkOrderInfoList",
        true)
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.send();
    xhr.onreadystatechange = () => {
      if (xhr.readyState == 4 && xhr.status == 200) {
        let txt = xhr.responseText;
        txt = JSON.parse(txt);
        if (txt.errorCode < 0) {
          swal.fire("오류", txt.errorMsg, "error");
          return;
        }else if (txt.gridRowJson == "") {
            swal.fire("알림", "조회된 작업지시 리스트가 없습니다.", "info");
            return;
        }
        console.log(txt);
        workSiteGridOptions.api.setRowData(txt.gridRowJson);
      }
    }
  }
  // o Click worksiteList Button
  workSiteListBtn.addEventListener('click', () => {
    getWorkSiteList();
  });
  // O Show workSiteSimulation modal
  // o setting workSiteSimulation modal
  let _setWorkSiteModal = (function() {
    let executed = false;
    return function() {
      if (!executed) {
        executed = true;
        setWorkSiteModal();
      }
    };
  })();
  // o is chekck
  let workSiteCourse;                // 검사항목
  let workOrderNo;         // 작업지시번호
  let itemClassIfication; // 품목분류
  const isCheck = () => {
    let selectedRows = workSiteGridOptions.api.getSelectedRows();
    if (selectedRows == "") {
      Swal.fire("알림", "선택한 행이 없습니다.", "info");
      return true;
    } else if (selectedRows[0].inspectionStatus == 'Y' &&
        selectedRows[0].productionStatus == 'Y' &&
        selectedRows[0].completionStatus == 'Y' ) {
      Swal.fire("알림", "모든작업이 완료되었습니다.", "info");
      return true;
    }
    workOrderNo = selectedRows[0].workOrderNo;
    itemClassIfication = selectedRows[0].itemClassification;
    return false;
  }
  // o RawMaterials
  workSiteRawMaterialsBtn.addEventListener('click', () => {
    if (isCheck()) {
      return;
    }
    let selectedRows = workSiteGridOptions.api.getSelectedRows();
    if (selectedRows[0].inspectionStatus == 'Y' ||
        selectedRows[0].productionStatus == 'Y' ||
        selectedRows[0].completionStatus == 'Y') {
      return Swal.fire("알림", "완료된 작업입니다.", "info");
    }
    workSiteCourse = "RawMaterials";
    _setWorkSiteModal();
    getWorkSiteModal(workSiteCourse, workOrderNo, itemClassIfication);
    $('#workSiteModal').modal('show');
  });
  // o Production
  workSiteProductionBtn.addEventListener('click', () => {
    if (isCheck()) {
      return;
    }
    let selectedRows = workSiteGridOptions.api.getSelectedRows();
    if (selectedRows[0].completionStatus == 'Y' || selectedRows[0].productionStatus == 'Y') {
      return Swal.fire("알림", "완료된 작업입니다.", "info");
    } else if (selectedRows[0].inspectionStatus != 'Y') {
      return Swal.fire("알림", "원재료 검사가 완료되지 않았습니다.", "info");
    }
    workSiteCourse = "Production"
    _setWorkSiteModal();
    getWorkSiteModal(workSiteCourse, workOrderNo, itemClassIfication);
    $('#workSiteModal').modal('show');
  });
  // o SiteExamine
  workSiteExamineBtn.addEventListener('click', () => {
    if (isCheck()) {
      return;
    }
    let selectedRows = workSiteGridOptions.api.getSelectedRows();
    if (selectedRows[0].inspectionStatus != 'Y') {
      return Swal.fire("알림", "원재료 검사가 완료되지 않았습니다", "info");
    } else if (selectedRows[0].productionStatus != 'Y') {
      return Swal.fire("알림", "제품 제작이 완료되지 않았습니다.", "info");
    }
    workSiteCourse = "SiteExamine";
    getWorkSiteModal(workSiteCourse, workOrderNo, itemClassIfication);
    _setWorkSiteModal();
    $('#workSiteModal').modal('show');
  });
  // o 모의전개된 결과 작업지시
  completeBtn.addEventListener('click', () => {
    let displayModel = workSiteSimulationGridOptions.api.getModel();
    let modalData = displayModel.gridApi.getRenderedNodes();
    let workProduct = modalData[0].data;
    let now = new Date();
    let today = now.getFullYear() + "-" +('0' + (now.getMonth() +1 )).slice(-2) + "-" + ('0' + (now.getDate())).slice(-2);
    console.log(modalData)
    let itemCodeList = [];
    modalData.forEach(function(rowNode, index) {
      itemCodeList.push(rowNode.data.itemCode);
    });

    let confirmMsg = "완료날짜 : "+ today +"</br>"+
        "제품 "+(workProduct.itemClassIfication == "원재료" ? "검사" : "제작") +" 할 갯수"+ modalData.length +"</br>"
        + "<b>"+workProduct.wdItem+" : "+workProduct.parentItemName+ "</b>"
    console.log(itemCodeList);
    // o 데이터 전송
    Swal.fire({
      title: '작업완료',
      html: confirmMsg,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      cancelButtonText: '취소',
      confirmButtonText: '확인',
    }).then((result) => {
      if (result.isConfirmed) {
        console.log(workProduct.workOrderNo);
        console.log(workProduct.parentItemCode);
        console.log(itemCodeList);
        let xhr = new XMLHttpRequest();
        xhr.open('POST', '${pageContext.request.contextPath}/production/workCompletion.do' +
            "?method=workCompletion"
            + "&workOrderNo=" + encodeURI(workProduct.workOrderNo)
            + "&itemCode=" + encodeURI(workProduct.parentItemCode)
            + "&itemCodeList=" + encodeURI(JSON.stringify(itemCodeList)),
            true)
        xhr.setRequestHeader('Accept', 'application/json');
        xhr.send();
        xhr.onreadystatechange = () => {
          if (xhr.readyState == 4 && xhr.status == 200) {
            let txt = xhr.responseText;
            console.log(txt);
            if (txt.errorCode < 0) {
              swal.fire("오류", txt.errorMsg, "error");
              return;
            }
            Swal.fire(
                '성공!',
                '작업이 완료되었습니다.',
                'success'
            )
            getWorkSiteList();
            workSiteSimulationGridOptions.api.setRowData([]);
          }
        }
      }
    })
  });
  // O workSite Log
  // o set workSiteLog
  const workSiteLogColumn = [
    {headerName: "작업지시번호", field: "workOrderNo"},
    {headerName: "품목코드", field: "itemCode", width: 100},
    {headerName: '품목명', field: "itemName",},
    {headerName: '생산공정코드', field: 'productionProcessCode',width: 150},
    {headerName: '생산공정명', field: 'productionProcessName',},
    {headerName: '상황', field: 'reaeson', width: 450},
    {headerName: '작업장명', field: 'workStieName',},
    {headerName: '날짜', field: 'workDate',cellRenderer: function (params) {
    if (params.value == null) {
      params.value = "";
    }
    return '📅 ' + params.value;
  }},
  ];
  let workSiteLogRowData = [];
  const workSiteLogGridOptions = {
    defaultColDef: {
      flex: 1,
      minWidth: 100,
      resizable: true,
    },
    columnDefs: workSiteLogColumn,
    rowSelection: 'multiple',
    rowData: workSiteLogRowData,
    defaultColDef: {editable: false},
    overlayNoRowsTemplate: "조회된 작업로그 리스트가 없습니다.",
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
  const workSiteLog = () => {
    let _workSiteLogDate = workSiteLogDate.value;
    if (_workSiteLogDate == "") {
      Swal.fire("알림", "조회할 날짜를 입력하십시오", "info");
      return;
    }
    let xhr = new XMLHttpRequest();
    xhr.open('POST', '${pageContext.request.contextPath}/production/workSiteLog.do' +
        "?method=workSiteLogList"
        + "&workSiteLogDate=" + _workSiteLogDate,
        true)
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.send();
    xhr.onreadystatechange = () => {
      if (xhr.readyState == 4 && xhr.status == 200) {
        let txt = xhr.responseText;
        txt = JSON.parse(txt);
        if (txt.errorCode < 0) {
          swal.fire("오류", txt.errorMsg, "error");
          return;
        } else if (txt.gridRowJson == "") {
          swal.fire("알림", "조회된 작업장리스트가 없습니다.", "info");
          return;
        }
        console.log(txt);
        workSiteLogGridOptions.api.setRowData(txt.gridRowJson);
      }
    }
  }
  workSiteLogListBtn.addEventListener('click', () => {
    workSiteLog();
  });
  document.addEventListener('DOMContentLoaded', () => {
    new agGrid.Grid(workSiteGrid, workSiteGridOptions);
    new agGrid.Grid(workSiteLogGrid, workSiteLogGridOptions);
  });
</script>
</body>
</html>