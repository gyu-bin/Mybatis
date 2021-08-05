<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://unpkg.com/ag-grid-community/dist/ag-grid-community.min.noStyle.js"></script>
    <link rel="stylesheet" href="https://unpkg.com/ag-grid-community/dist/styles/ag-grid.css">
    <link rel="stylesheet" href="https://unpkg.com/ag-grid-community/dist/styles/ag-theme-balham.css">

    <script src="${pageContext.request.contextPath}/js/modal.js?v=<%=System.currentTimeMillis()%>" defer></script>
    <script src="${pageContext.request.contextPath}/js/datepicker.js" defer></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker.css">

    <script>
        // O 날짜 설정
        $(function () {
            // set default dates
            let start = new Date();
            start.setDate(start.getDate() - 20);
            // set end date to max one year period:
            let end = new Date(new Date().setYear(start.getFullYear() + 1));
            // o set searchDate
            $('#datepicker').datepicker({
                todayHiglght: true,
                autoHide: true,
                autoaShow: true,
            });
            // o set searchRangeDate
            $('#fromDate').datepicker({
                startDate: start,
                endDate: end,
                minDate: "-10d",
                todayHiglght: true,
                autoHide: true,
                autoaShow: true,
                // update "toDate" defaults whenever "fromDate" changes
            })
            $('#toDate').datepicker({
                startDate: start,
                endDate: end,
                todayHiglght: true,
                autoHide: true,
                autoaShow: true,
            })
            $('#fromDate').on("change", function(){
                //when chosen from_date, the end date can be from that point forward
                var startVal = $('#fromDate').val();
                $('#toDate').data('datepicker').setStartDate(startVal);
            });
            $('#toDate').on("change", function(){
                //when chosen end_date, start can go just up until that point
                var endVal = $('#toDate').val();
                $('#fromDate').data('datepicker').setEndDate(endVal );
            });

        });
    </script>
    <style>
        .fromToDate {
            display: none;
            margin-bottom: 7px;
        }

        #datepicker {
            margin-bottom: 7px;
        }
        button {
            /*  background-color: #506FA9;  */
            background: #506FA9;
            border: 1px solid #506FA9;
            color: #FFF;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
            border-radius: 3px;
            margin: 2px;
        }
        button:hover{
            transform: scale(1.0);
            z-index: 1;
            background: #FFF;
            border: 1px solid #bbded6;
            color: #506FA9;
            opacity: 1;
        }
        #contractBatchInsertButton{
            background-color:#F15F5F;
            border: 1px solid #F15F5F;
        }
        #contractBatchInsertButton:hover{
            background-color:#FFFFFF;
            border: 1px solid #F15F5F;
            color: #F15F5F;
        }
        .menuButton__selectCode {
            display: inline-block;
        }

        .ag-header-cell-label {
            justify-content: center;
        }

    </style>
</head>
<body>
<article class="contract">
    <div class="contract__Title">
        <h5>📋 수주 등록</h5>
        <div style="color: black">
            <label for="searchByDateRadio">일자 검색</label>
            <input type="radio" name="searchDateCondition" value="searchByDate" id="searchByDateRadio" checked>
            &nbsp<label for="searchByPeriodRadio">기간 검색</label>
            <input type="radio" name="searchDateCondition" value="searchByPeriod" id="searchByPeriodRadio">
        </div>

        <form autocomplete="off">
            <input type="text" id="datepicker" placeholder="YYYY-MM-DD 📅" size="15" style="text-align: center">
            <div class="fromToDate">
                <input type="text" id="fromDate" placeholder="YYYY-MM-DD 📅" size="15" style="text-align: center">
                &nbsp ~ &nbsp<input type="text" id="toDate" placeholder="YYYY-MM-DD 📅" size="15"
                                    style="text-align: center">
            </div>
        </form>
        <button id="contractCandidateSearchButton">수주가능견적조회</button>
        <button class="search" id="contractTypeList" data-toggle="modal"
                data-target="#contractType">수주유형
        </button>
        <button id="estimateDetailButton">견적상세조회</button>
        <button id="contractBatchInsertButton">수주등록</button>

    </div>
</article>
<article class="contractGrid">
    <div align="center">
        <div id="myGrid" class="ag-theme-balham" style="height:20vh; width:auto; text-align: center;"></div>
    </div>
</article>
<div>
    <h5>📋 견적 상세</h5>
</div>
<article class="estimateDetailGrid">
    <div align="center" class="ss">
        <div id="myGrid2" class="ag-theme-balham" style="height:30vh;width:auto;"></div>
    </div>
</article>

<div class="modal fade" id="contractType" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">CONTRACT TYPE</h5>
                <button type="button" class="close" data-dismiss="modal" style="padding-top: 0.5px">&times;</button>
            </div>
            <div class="modal-body">
                <div id="contractGrid" class="ag-theme-balham" style="height:500px;width:auto;">
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
    const searchByDateRadio = document.querySelector("#searchByDateRadio");
    const searchByPeriodRadio = document.querySelector("#searchByPeriodRadio");
    const datepicker = document.querySelector("#datepicker");
    const fromToDate = document.querySelector(".fromToDate");
    const startDatePicker = document.querySelector("#fromDate");
    const endDatePicker = document.querySelector("#toDate");
    const searchBtn = document.querySelector("#contractCandidateSearchButton");
    const contractTypeList = document.querySelector("#contractTypeList");
    const estimateDetailBtn = document.querySelector("#estimateDetailButton");
    const contractBatchInsertBtn = document.querySelector("#contractBatchInsertButton");

    searchByDateRadio.addEventListener("click", () => {
        datepicker.style.display = "inline-block";
        fromToDate.style.display = "none";

    });
    searchByPeriodRadio.addEventListener("click", () => {
        fromToDate.style.display = "inline-block";
        datepicker.style.display = "none";
    });

    // O customerList Grid
    let contractColumn = [
        {headerName: ' ', checkboxSelection: true,  width: 35, cellStyle: {'textAlign': 'center'}, },
        {headerName: "견적일련번호", field: "estimateNo"},
        {headerName: "수주유형분류", field: "contractType", editable: true},
        {headerName: "거래처코드", field: "customerCode"},
        {headerName: "수주요청자", field: "contractRequester", editable: true},
        {headerName: "견적일자", field: "estimateDate",  cellRenderer: function (params) {
                if (params.value == "") { params.value = "YYYY-MM-DD";}
                return '📅 ' + params.value;
            }},
        {headerName: "유효일자", field: "effectiveDate",  cellRenderer: function (params) {
                if (params.value == "") { params.value = "YYYY-MM-DD";}
                return '📅 ' + params.value;
            }},
        {headerName: "견적담당자코드", field: "personCodeInCharge"},
        {headerName: "비고", field: "description", editable: true},
    ];
    // event.colDef.field
    let rowData = [];
    let contractRowNode;
    let contractGridOptions = {
        columnDefs: contractColumn,
        rowSelection: 'single',
        rowData: rowData,
        getRowNodeId: function (data) {
            return data.estimateNo;
        },
        defaultColDef: {editable: false},
        overlayNoRowsTemplate: "수주 가능한 견적이 없습니다.",
        onGridReady: function (event) {// onload 이벤트와 유사 ready 이후 필요한 이벤트 삽입한다.
            event.api.sizeColumnsToFit();
        },
        onGridSizeChanged: function (event) {
            event.api.sizeColumnsToFit();
        },
        onCellEditingStarted: (event) => {
            if (event.colDef.field == "contractType") {
                let rowNode = contractGridOptions.api.getDisplayedRowAtIndex(event.rowIndex);
                contractRowNode = rowNode;
                contractTypeList.click();
            }
        },
        getSelectedRowData() {
            let selectedNodes = this.api.getSelectedNodes();
            let selectedData = selectedNodes.map(node => node.data);
            return selectedData;
        }
    }

    // O 수주 가능 견적 조회
    let estimateDetailList = [];
    searchBtn.addEventListener("click", () => {
        let searchCondition = document.querySelector("#searchByDateRadio").checked;
        console.log(searchCondition);
        let startDate = (searchCondition) ? datepicker.value : startDatePicker.value;
        let endDate = (searchCondition) ? datepicker.value : endDatePicker.value;
        console.log(startDate);
        console.log(endDate);
        // o 초기화
        contractGridOptions.api.setRowData([]);
        // estDetailGridOptions.api.setRowData([]);
        // o ajax
        let xhr = new XMLHttpRequest();
        xhr.open('POST', "${pageContext.request.contextPath}/sales/searchEstimateInContractAvailable.do?"
            + "method=searchEstimateInContractAvailable"
            + "&startDate=" + startDate
            + "&endDate=" + endDate,
            true);
        xhr.setRequestHeader('Accept', 'application/json');
        xhr.send();
        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4 && xhr.status == 200) {
                let txt = xhr.responseText;
                txt = JSON.parse(txt);

                if (txt.gridRowJson == "") {
                    swal.fire("수주 가능 견적이 없습니다.");
                    return;
                } else if (txt.errorCode < 0) {
                    swal.fire("알림", txt.erroMsg, "error");
                    return;
                }
                console.log("txt다@@@@@@@@@@@@@@"+txt);
                contractGridOptions.api.setRowData(txt.gridRowJson);
                txt.gridRowJson.map((unit, idx) => {
                    [].forEach.bind(unit.estimateDetailTOList)((val) => {
                        estimateDetailList.push(val); // estDetailGrid에서 사용하기 위해 담음
                    });
                });
            }
        }
    });
    // o if customer modal hide, next cell
    $("#contractType").on('hide.bs.modal', function () {
        contractGridOptions.api.stopEditing();
        console.log(contractRowNode);
        if (contractRowNode != undefined) { // rowNode가 없는데 거래처 코드 탐색시 에러
            setDataOnCustomerName();
        }
    });

    // o change customerName cell
    function setDataOnCustomerName() {
        let to = transferVar();
        contractRowNode.setDataValue("contractType", to.detailCode);
        let newData = contractRowNode.data;
        contractRowNode.setData(newData);
    }

    // O Estimate Detail Grid
    let estDetailColumn = [
        {headerName: "견적상세일련번호", field: "estimateDetailNo",  suppressSizeToFit: true},
        {headerName: "품목코드", field: "itemCode",  suppressSizeToFit: true, editable: true},
        {headerName: "품목명", field: "itemName"},
        {headerName: "단위", field: "unitOfEstimate",},
        {headerName: "납기일", field: "dueDateOfEstimate", editable: true, cellRenderer: function (params) {
                if (params.value == "") { params.value = "YYYY-MM-DD";}
                return '📅 ' + params.value;
            }, cellEditor: 'datePicker'},
        {headerName: "견적수량", field: "estimateAmount"},
        {headerName: "견적단가", field: "unitPriceOfEstimate"},
        {headerName: "합계액", field: "sumPriceOfEstimate"},
        {headerName: "비고", field: "description"}
    ];
    let estDetailrowData = [];
    let itemRowNode;
    let estDetailGridOptions = {
        columnDefs: estDetailColumn,
        rowSelection: 'multiple',
        rowData: estDetailrowData,
        defaultColDef: {editable: false},
        overlayNoRowsTemplate: "견적상세 버튼으로 내용을 조회해주세요.",
        onGridReady: function (event) {
            event.api.sizeColumnsToFit();
        },
        onGridSizeChanged: function (event) {
            event.api.sizeColumnsToFit();
        },
        onCellDoubleClicked: (event) => {
            if (event != undefined) {
                console.log("IN onRowSelected");
                let rowNode = estDetailGridOptions.api.getDisplayedRowAtIndex(event.rowIndex);
                console.log(rowNode);
                itemRowNode = rowNode;
            }
        },
        onRowSelected: function (event) { // checkbox
            console.log(event);
        },
        onSelectionChanged(event) {
            console.log("onSelectionChanged" + event);
        },
        components: {
            datePicker: getDatePicker("dueDateOfEstimate") // getDatePicker
        },
    }
    // O getDataPicker
    function getDatePicker(paramFmt) {
        let _this = this;
        _this.fmt = "yyyy-mm-dd";
        console.log(_this);

        // function to act as a class
        function Datepicker() {
        }

        // gets called once before the renderer is used
        Datepicker.prototype.init = function (params) {
            // create the cell
            this.autoclose = true;
            this.eInput = document.createElement('input');
            this.eInput.style.width = "100%";
            this.eInput.style.border = "0px";
            this.cell = params.eGridCell;
            this.oldWidth = this.cell.style.width;
            this.cell.style.width = this.cell.previousElementSibling.style.width;
            this.eInput.value = params.value;
            $(this.eInput).datepicker({
                dateFormat: _this.fmt
            }).on('change', function () {
                estDetailGridOptions.api.stopEditing();
                $(".datepicker-container").hide();
            });
        };
        // gets called once when grid ready to insert the element
        Datepicker.prototype.getGui = function () {
            return this.eInput;
        };

        // focus and select can be done after the gui is attached
        Datepicker.prototype.afterGuiAttached = function () {
            this.eInput.focus();
            console.log(this.eInput.value);
        };

        // returns the new value after editing
        Datepicker.prototype.getValue = function () {
            console.log(this.eInput);
            return this.eInput.value;
        };

        // any cleanup we need to be done here
        Datepicker.prototype.destroy = function () {
            estDetailGridOptions.api.stopEditing();
        };

        return Datepicker;
    }
    // O select estimateDetail
    estimateDetailBtn.addEventListener("click", () => {
        let selectedNodes = contractGridOptions.api.getSelectedNodes();
        if (selectedNodes == "") {
            Swal.fire({
                position: "top",
                icon: 'error',
                title: '체크 항목',
                text: '선택한 행이 없습니다.',
            })
            return;
        }
        console.log(selectedNodes[0].data);
        estDetailGridOptions.api.setRowData([]);
        estimateDetailList.map((unit, idx) => {
            if (unit.estimateNo == selectedNodes[0].data.estimateNo) {
                estDetailGridOptions.api.updateRowData({add: [unit]});
            }
        });
        /*[].forEach.bind(unit.estimateDetailTOList)((val) => {
            console.log(val);
            estimateDetailList.push(val);
        });*/
    })

    // O select contract type
    contractTypeList.addEventListener("click", () => {
        getCustomerData("CT");
        setCutomerModal("CONTRACT");
    }, {once: true});

    // O register contract
    contractBatchInsertBtn.addEventListener("click", () => {
        let selectedNodes = contractGridOptions.api.getSelectedNodes();
        // o No seleted Nodes
        if (selectedNodes === "") {
            Swal.fire({
                position: "top",
                icon: 'error',
                title: '체크 항목',
                text: '선택한 행이 없습니다.',
            })
            return;
        }
        // o No contractType
        console.log(selectedNodes[0]);
        let newEstimateRowValue = selectedNodes[0].data;
        newEstimateRowValue = JSON.stringify(newEstimateRowValue);
        if (selectedNodes[0].data.contractType === undefined) {
            Swal.fire({
                position: "top",
                icon: 'error',
                title: '체크 항목',
                text: '수주유형을 입력해야합니다.',
            })
            return;
        }
        Swal.fire({
            title: '수주 등록',
            text:  selectedNodes[0].data.estimateNo + "등록하시겠습니까?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            cancelButtonText: '취소',
            confirmButtonText: '확인',
        }).then( (result) => {
            if (result.isConfirmed) {
                let xhr = new XMLHttpRequest();
                let now = new Date();
                let today = now.getFullYear() + "-" +('0' + (now.getMonth() +1 )).slice(-2) + "-" + ('0' + now.getDate()).slice(-2);
                //slice()안에 음수는 뒤에서 부터 짜른다.
                xhr.open('POST', "${pageContext.request.contextPath}/sales/addNewContract.do?"
                    + "method=addNewContract"
                    + "&batchList=" + encodeURI(newEstimateRowValue)
                    + "&personCodeInCharge=" + "${sessionScope.empCode}"
                    + "&contractDate=" + today,
                    true);
                //encodeURI는 URI의 특정한 문자를 UTF-8로 인코딩
                xhr.setRequestHeader('Accept', 'application/json');//컨텐츠 타입을 JSON타입으로
                xhr.send();
                xhr.onreadystatechange = () => {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        // 초기화
                        contractGridOptions.api.setRowData([]);
                        estDetailGridOptions.api.setRowData([]);
                        // 데이터 확인
                        let txt = xhr.responseText;
                        txt = JSON.parse(txt);
                        if (txt.errorCode < 0) {
                            Swal.fire("오류", txt.errorMsg, "error");
                            return;
                        }
                        console.log("수주 완료");
                        console.log(txt);
                        Swal.fire(
                            '수주 완료',
                            '완료되었습니다.',
                            'success'
                        )
                    }
                };
            }})
    });

    // O setup the grid after the page has finished loading
    // 데이터를 보내고 페이지 로딩이 끝났을때 grid를 새로 만들어서 세팅한다
    document.addEventListener('DOMContentLoaded', () => {
        new agGrid.Grid(myGrid, contractGridOptions);
        new agGrid.Grid(myGrid2, estDetailGridOptions);
    })
</script>
</body>
</html>