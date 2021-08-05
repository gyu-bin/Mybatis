<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://unpkg.com/ag-grid-community/dist/ag-grid-community.min.noStyle.js"></script>
    <link rel="stylesheet" href="https://unpkg.com/ag-grid-community/dist/styles/ag-grid.css">
    <link rel="stylesheet" href="https://unpkg.com/ag-grid-community/dist/styles/ag-theme-balham.css">

    <script src="${pageContext.request.contextPath}/js/datepicker.js" defer></script>
    <script src="${pageContext.request.contextPath}/js/datepickerUse.js" defer></script>
    <script src="${pageContext.request.contextPath}/js/modal.js?v=<%=System.currentTimeMillis()%>" defer></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker.css">
    <script>
        $(function () {
            let end = new Date();
            let year = end.getFullYear();              //yyyy
            let month = (1 + end.getMonth());          //M
            month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
            let day = end.getDate();                   //d
            day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
            end =   year + '' + month + '' + day;
            // o set searchDate
            $('#datepicker').datepicker({
                startDate: '-1d',
                endDate: end,
                todayHiglght: true,
                autoHide: true,
                autoaShow: true,
            })})
    </script>
    <style>
        * {
            margin: 0px;
        }

        h5 {
            margin-top: 3px;
            margin-bottom: 3px;
        }

        input {
            padding: 2px 0 2px 0;
            text-align: center;
            border-radius: 3px;
        }

        .ag-header-cell-label {
            justify-content: center;
        }
        .ag-cell-value {
            padding-left: 50px;
        }

        .estimate {
            margin-bottom: 10px;
        }

        .estimateDetail {
            margin-bottom: 10px;
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
        #batchSaveButton{
            background-color:#F15F5F;
            border: 1px solid #F15F5F;
        }
        .menuButton__selectCode {
            display: inline-block;
        }

    </style>
</head>
<body>
<article class="estimate">
    <div class="estimate__Title">
        <h5>📋 견적등록</h5>
        <input type="text" id="datepicker" placeholder="YYYY-MM-DD 📅" size="15" autocomplete="off" style="text-align: center">
        <span style="color: black">견적등록일자</span>
        <div class="menuButton">
            <button id="estimateInsertButton" onclick="addRow(this)">견적추가</button>
            <button onclick="deleteRow(this)">견적삭제</button>
            <button id="batchSaveButton" style="float: right;">일괄저장</button>
            <div class="menuButton__selectCode">
                <button class="search" id="customerList" data-toggle="modal"
                        data-target="#customerModal">거래처코드
                </button>
            </div>
        </div>
    </div>
</article>
<article class="estimateGrid">
    <div align="center">
        <div id="myGrid" class="ag-theme-balham" style="height:100px; width:auto; text-align: center;"></div>
    </div>
</article>
<article class="estimateDetail">
    <div class="estimateDetail__Title">
        <h5>📋 견적상세등록</h5>
        <div class="menuButton">
            <button id="estimateDetailInsertButton" onclick="addRow(this)">견적상세추가</button>
            <button id="estimateDetailDeleteButton" onclick="deleteRow(this)">견적상세삭제</button>
            <div class="menuButton__selectCode">
                <button class="search" id="itemList" data-toggle="modal"
                        data-target="#itemModal">품목코드
                </button>
                <button class="search" id="unitList" data-toggle="modal"
                        data-target="#unitModal">단위코드
                </button>
                <button class="search" id="amountList" data-toggle="modal"
                        data-target="#amountModal">수량
                </button>
            </div>
        </div>
    </div>
</article>
<article class="estimateDetailGrid">
    <div align="center" class="ss">
        <div id="myGrid2" class="ag-theme-balham" style="height:50vh;width:auto;"></div>
    </div>
</article>

<%--Customer Modal--%>
<div class="modal fade" id="customerModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">CUSTOMER CODE</h5>
                <button type="button" class="close" data-dismiss="modal" style="padding-top: 0.5px">&times;</button>
            </div>
            <div class="modal-body">
                <div id="customerGrid" class="ag-theme-balham" style="height:500px;width:auto;">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<%--Item Modal--%>
<div class="modal fade" id="itemModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">ITEM CODE</h5>
                <button type="button" class="close" data-dismiss="modal" style="padding-top: 0.5px">&times;</button>
            </div>
            <div class="modal-body">
                <div id="itemGrid" class="ag-theme-balham" style="height:500px;width:auto;">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<%--Unit Modal--%>
<div class="modal fade" id="unitModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">UNIT CODE</h5>
                <button type="button" class="close" data-dismiss="modal" style="padding-top: 0.5px">&times;</button>
            </div>
            <div class="modal-body">
                <div id="unitGrid" class="ag-theme-balham" style="height:500px;width:auto;">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<%--Amount Modal--%>
<div class="modal fade" id="amountModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">AMOUNT</h5>
                <button type="button" class="close" data-dismiss="modal" style="padding-top: 0.5px">&times;</button>
            </div>
            <div class="modal-body">
                <div style="width:auto; text-align:left">
                    <label style='font-size: 20px; margin-right: 10px'>견적수량</label>
                    <input type='text' id='estimateAmountBox' autocomplete="off"/><br>
                    <label for='unitPriceOfEstimateBox' style='font-size: 20px; margin-right: 10px'>견적단가</label>
                    <input type='text' id='unitPriceOfEstimateBox' autocomplete="off"/><br>
                    <label for='sumPriceOfEstimateBox' style='font-size: 20px; margin-right: 30px'>합계액  </label>
                    <input type="text" id='sumPriceOfEstimateBox' autocomplete="off"></input>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id ="amountSave" class="btn btn-default" data-dismiss="modal">Save</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


<script>
    const myGrid = document.querySelector('#myGrid');
    const myGrid2 = document.querySelector('#myGrid2');
    const datepicker = document.querySelector('#datepicker');
    const customerList = document.querySelector('#customerList');
    const itemList = document.querySelector('#itemList');
    const unitList = document.querySelector('#unitList');
    const amountList = document.querySelector('#amountList');
    const batchSaveButton = document.querySelector("#batchSaveButton");
    // O customerList Grid
    let estColumn = [
        {headerName: "거래처명", field: "customerName", editable: true},
        {headerName: "거래처코드", field: "customerCode", editable: true, hide: true},
        {headerName: "견적일자", field: "estimateDate"},
        {
            headerName: "유효일자", field: "effectiveDate", editable: true, cellRenderer: function (params) {
                if (params.value === "") { params.value = "YYYY-MM-DD";}
                return '📅 ' + params.value;
            }, cellEditor: 'datePicker1'
        },
        {headerName: "견적담당자", field: "personNameCharge", editable: true},
        {headerName: "견적담당자코드", field: "personCodeInCharge", hide: true},
        {headerName: "견적요청자", field: "estimateRequester", editable: true},
        {headerName: "비고", field: "description", editable: true},
        {headerName: "status", field: "status"},
    ];
    let estRowData = [];
    // event.colDef.field
    let estGridOptions = {
        columnDefs: estColumn,
        rowSelection: 'single',
        rowData: estRowData,
        getRowNodeId: function (data) {
            return data.estimateDate;
        },
        defaultColDef: {editable: false, resizable:true},
        overlayNoRowsTemplate: "추가된 견적이 없습니다.",
        onGridReady: function (event) {// onload 이벤트와 유사 ready 이후 필요한 이벤트 삽입한다.
            event.api.sizeColumnsToFit();
        },
        onGridSizeChanged: function (event) {
            event.api.sizeColumnsToFit();
        },
        onCellEditingStarted: (event) => {
            if (event.colDef.field === "customerName") {
                customerList.click();
            }
        },
        getSelectedRowData() {
            let selectedNodes = this.api.getSelectedNodes();
            let selectedData = selectedNodes.map(node => node.data);
            return selectedData;
        },
        components: {
            datePicker1: getDatePicker("effectiveDate"),
        }
    }

    // O DATEPICKER => dbClick 하면 할 수 있게끔
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
            // Setting startDate and endDate
            console.log(paramFmt);
            let _startDate = datepicker.value;
            let _endDate = new Date(_startDate);
            let days = 14; // 유효 일자는 현재일자 + 14일
            if (paramFmt == "dueDateOfEstimate") {
                _startDate = new Date(_startDate)
                days = 9;
                _startDate.setTime(_startDate.getTime() + days * 86400000);
                let dd = _startDate.getDate();
                let mm = _startDate.getMonth() + 1; //January is 0!
                let yyyy = _startDate.getFullYear();
                _startDate = yyyy + '-' + mm + '-' + dd;
                console.log(_startDate);
                _endDate = new Date(_startDate);
                days = 19;
            }
            _endDate.setTime(_endDate.getTime() + days * 86400000);
            let dd = _endDate.getDate();
            let mm = _endDate.getMonth() + 1; //January is 0!
            let yyyy = _endDate.getFullYear();
            _endDate = yyyy + '-' + mm + '-' + dd;

            $(this.eInput).datepicker({
                dateFormat: _this.fmt,
                startDate: _startDate,
                endDate: _endDate,
            }).on('change', function () {
                estGridOptions.api.stopEditing();
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
            estGridOptions.api.stopEditing();
        };

        return Datepicker;
    }

    // o if customer modal hide, next cell
    $("#customerModal").on('hide.bs.modal', function () {
        estGridOptions.api.stopEditing();
        let rowNode = estGridOptions.api.getRowNode(datepicker.value);
        console.log("rowNode:" + rowNode);
        if (rowNode != undefined) { // rowNode가 없는데 거래처 코드 탐색시 에러
            setDataOnCustomerName();
        }
    });

    // o change customerName cell
    function setDataOnCustomerName() {
        let rowNode = estGridOptions.api.getRowNode(datepicker.value);
        let to = transferVar();
        rowNode.setDataValue("customerName", to.detailCodeName);
        rowNode.setDataValue("customerCode", to.detailCode);
        let newData = rowNode.data;
        rowNode.setData(newData);
        console.log(rowNode.data);
    }

    // O estimateDetail Grid
    let estDetailColumn = [
        {headerName: "품목코드", field: "itemCode",  suppressSizeToFit: true, editable: true, suppressSizeToFit: true, headerCheckboxSelection: true,
            headerCheckboxSelectionFilteredOnly: true,
            checkboxSelection: true},
        {headerName: "품목명", field: "itemName"},
        {headerName: "단위", field: "unitOfEstimate",},
        {headerName: "납기일", field: "dueDateOfEstimate", editable: true, cellRenderer: function (params) {
                if (params.value === "") { params.value = "YYYY-MM-DD";}
                return '📅 ' + params.value;
            }, cellEditor: 'datePicker2'},
        {headerName: "견적수량", field: "estimateAmount", editable: true,},
        {headerName: "견적단가", field: "unitPriceOfEstimate", hide: false},
        {headerName: "합계액", field: "sumPriceOfEstimate"},
        {headerName: "비고", field: "description", editable: true},
        {headerName: "status", field: "status"},
        {headerName: "beforeStatus", field: "beforeStatus", hide: true},
    ];
    let itemRowNode;
    let estDetailRowData = [];
    let estDetailGridOptions = {
        columnDefs: estDetailColumn,
        rowSelection: 'multiple',
        rowData: estDetailRowData,
        defaultColDef: {editable: false},
        overlayNoRowsTemplate: "추가된 견적상세가 없습니다.",
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
            if (event.colDef.field === "itemCode" || event.colDef.field === "itemName") {
                itemList.click();
            } else if (event.colDef.field === "unitOfEstimate") {
                unitList.click();
            } else if (event.colDef.field === "estimateAmount") {
                amountList.click();
            }
        },
        onRowSelected: function (event) { // checkbox
            console.log(event);
        },
        onSelectionChanged(event) {
            console.log("onSelectionChanged" + event);
        },
        components: {
            datePicker2: getDatePicker("dueDateOfEstimate") // getDatePicker
        },

    }

    // o if Item modal hide, next cell
    $("#itemModal").on('hide.bs.modal', function () {
        if (itemRowNode != undefined) { // rowNode가 없는데 거래처 코드 탐색시 에러
            setDataOnItemName();
        }
    });
    let detailItemCode = [];
    function setDataOnItemName() {
        estDetailGridOptions.api.stopEditing();
        let to = transferVar();
        if (!detailItemCode.includes(to.detailCode)) {
            detailItemCode.push(to.detailCode);
            console.log("detailItemCode:" + detailItemCode);
        } /* else {
      Swal.fire({
        text: "중복되는 코드입니다.",
        icon: "info",
      });
      return;
    }*/
        itemRowNode.setDataValue("itemCode", to.detailCode);
        itemRowNode.setDataValue("itemName", to.detailCodeName);
        let newData = itemRowNode.data;
        itemRowNode.setData(newData);
        console.log(itemRowNode.data);
    }

    $("#amountModal").on('show.bs.modal', function () {
        $('#estimateAmountBox').val("");
        $('#sumPriceOfEstimateBox').val("");
        $('#estimateAmountBox, #unitPriceOfEstimateBox').on("keyup", function() {
            let sum = $('#estimateAmountBox').val() * $('#unitPriceOfEstimateBox').val();
            $('#sumPriceOfEstimateBox').val(sum);
        });
    });
    $("#amountModal").on('shown.bs.modal', function () {
        $('#estimateAmountBox').focus();
    });
    document.querySelector("#amountSave").addEventListener("click", () => {
        if (itemRowNode == undefined) {return;}
        estDetailGridOptions.api.stopEditing();
        itemRowNode.setDataValue("estimateAmount", $('#estimateAmountBox').val());
        itemRowNode.setDataValue("unitPriceOfEstimate", $('#unitPriceOfEstimateBox').val());
        itemRowNode.setDataValue("sumPriceOfEstimate", $('#sumPriceOfEstimateBox').val());
        let newData = itemRowNode.data;
        itemRowNode.setData(newData);
    })

    // O add and Delete function
    function addRow(event) {
        if (event.innerText === "견적추가") {
            if (datepicker.value === "") {
                Swal.fire({
                    text: "견적일자를 등록하셔야합니다.",
                    icon: "info",
                });
                return;
            }  else if (estGridOptions.api.getDisplayedRowCount() > 0) {
                Swal.fire({
                    text: "처리한 견적이 없습니다.",
                    icon: "info",
                });
                return;
            }
            let row = {
                estimateDate: datepicker.value,
                personCodeInCharge: "${sessionScope.empCode}",
                personNameCharge: "${sessionScope.empName}",
                effectiveDate: "",
                estimateRequester: "${sessionScope.empName}",
                description: "",
                status: "INSERT"
            };
            estGridOptions.api.updateRowData({add: [row]});
        }
        else if (event.innerText === "견적상세추가") {
            console.log(event.innerText);
            let estDate = estGridOptions.getSelectedRowData(); // 선택된 기존 setting 값
            console.log(estDate);
            if (estDate.length === 0) {
                Swal.fire({
                    text: "견적 상세를 추가할 행을 선택 하세요.",
                    icon: "info",
                })
                return;
            }
            let rowNode = estGridOptions.api.getRowNode(datepicker.value);
            console.log("견적상세" + rowNode.data);
            if (rowNode.data.customerName === undefined) {
                Swal.fire({
                    text: "견적 거래처 코드를 등록하셔야 합니다.",
                    icon: "info",
                })
                return;
            }
            if (rowNode.data.effectiveDate === "") {
                Swal.fire({
                    text: "견적 유효일자를 등록하셔야 합니다.",
                    icon: "info",
                })
                return;
            }
            // o 견적 상세 추가 start
            let row = {
                itemCode: "",
                dueDateOfEstimate: "", //납기일
                unitOfEstimate: "EA",
                status: "INSERT",
                description: "",
                beforeStatus: "",
                estimateAmount: "",
            };
            estDetailGridOptions.api.updateRowData({add: [row]});
        }
    }

    function deleteRow(event) {
        let selected = estGridOptions.api.getFocusedCell();                   // 견적
        if (selected === undefined) {
            Swal.fire({
                text: "선택한 행이 없습니다.",
                icon: "info",
            })
            return;
        }
        if (event.innerText === "견적삭제") {
            estGridOptions.rowData.splice(selected.rowIndex, 1);
            estGridOptions.api.setRowData(estGridOptions.rowData)
        } else if (event.innerText === "견적상세삭제"){
            console.log("견적상세삭제");
            let selectedRows   = estDetailGridOptions.api.getSelectedRows();
            console.log("선택된 행" + selectedRows );
            selectedRows.forEach( function(selectedRow, index) {
                console.log(selectedRow);
                detailItemCode.splice(detailItemCode.indexOf(selectedRow.itemCode), 1); // 배열요소 제거
                estDetailGridOptions.api.updateRowData({remove: [selectedRow]});
            });
        }
    }

    // 일괄저장 <= 선택된 항목만 저장
    batchSaveButton.addEventListener("click", () => {
        console.log("일괄저장");
        let newEstimateRowValue = estGridOptions.getSelectedRowData();
        console.log(newEstimateRowValue);
        let selectedRows = estDetailGridOptions.api.getSelectedRows();
        if (newEstimateRowValue === "") {
            Swal.fire({
                text: "선택한 행이 없습니다.",
                icon: "info",
            })
            return;
        } else if (newEstimateRowValue[0].customerCode === '' || newEstimateRowValue[0].effectiveDate === '') {
            Swal.fire({
                text: "거래처명과, 유효일자는 필수항목입니다.",
                icon: "info",
            })
            return ;
        }
        for(index in selectedRows){
            selectedRow=selectedRows[index];
            console.log(selectedRow);
            if (selectedRow.itemCode === ""
                || selectedRow.dueDateOfEstimate === ""
                || selectedRow.estimateAmount === "") {
                Swal.fire({
                    text: "견적상세의 품목코드, 단위, 납기일, 견적수량은 필수항목입니다.",
                    icon: "info",
                })
                return;
            }else if (selectedRow === null) {
                Swal.fire({
                    text: "상세 견적을 추가하지 않았습니다",
                    icon: "info",
                })
                return;
            }
        }

        newEstimateRowValue = estGridOptions.getSelectedRowData()[0];
        console.log(newEstimateRowValue);
        console.log(selectedRows);
        newEstimateRowValue.estimateDetailTOList = selectedRows;
        console.log("newEstimateRowValue")
        newEstimateRowValue = JSON.stringify(newEstimateRowValue);
        console.log(newEstimateRowValue)
        Swal.fire({
            title: "견적을 등록하시겠습니까?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            cancelButtonText: '취소',
            confirmButtonText: '확인',
        }).then( (result) => {
            if (result.isConfirmed) {
                let xhr = new XMLHttpRequest();
                xhr.open('POST', "${pageContext.request.contextPath}/sales/addNewEstimate.do?"
                    +"method=addNewEstimate&estimateDate="+ datepicker.value
                    + "&newEstimateInfo=" + encodeURI(newEstimateRowValue), //UTF-8 형식으로
                    true);
                xhr.setRequestHeader('Accept', 'application/json');
                xhr.send();
                xhr.onreadystatechange = () => {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        // 초기화
                        estGridOptions.api.setRowData([]);
                        estDetailGridOptions.api.setRowData([]);
                        let txt = xhr.responseText;
                        txt = JSON.parse(txt);
                        let resultMsg =
                            "<h5>< 견적 등록 내역 ></h5>"
                            + "새로운 견적번호 : <b>" + txt.result.newEstimateNo + "</b></br>"
                            + "견적상세번호 : <b>" + txt.result.INSERT  + "</b></br>"
                            + "위와 같이 작업이 처리되었습니다";
                        Swal.fire({
                            title: "견적등록이 완료되었습니다.",
                            html: resultMsg,
                            icon: "success",
                        });
                    }
                };
            }})
    })
    // O Button Click evenet
    // o customerListModal Click
    customerList.addEventListener('click', () => {
        getCustomerData("CL-01");
        setCutomerModal("CUSTOMER");
    }, {once: true});

    // o estimateDetailListModal Click
    itemList.addEventListener('click', () => {
        getCustomerData("IT-_I");
        setCutomerModal("ITEM");
    }, {once: true});
    unitList.addEventListener('click', () => {
        getCustomerData("UT");
        setCutomerModal("UNIT");
    }, {once: true});
    amountList.addEventListener('click', () => {
        console.log(itemRowNode);
        if (itemRowNode === undefined) {return;}
        if (itemRowNode.data.itemCode !== undefined) {
            getStandardUnitPrice(itemRowNode.data.itemCode, "EA"); // BOX이면
        }
    });

    // O setup the grid after the page has finished loading
    document.addEventListener('DOMContentLoaded', () => {
        new agGrid.Grid(myGrid, estGridOptions);
        new agGrid.Grid(myGrid2, estDetailGridOptions);
    })
</script>
</body>
</html>
