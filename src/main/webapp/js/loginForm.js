'use strict';
// O loginForm.jsp 기능 구현
const inputs = document.querySelectorAll('.input');

function addcl() {
  let parent = this.parentNode.parentNode;
  parent.classList.add('focus');
}

function remcl() {
  let parent = this.parentNode.parentNode;
  if (this.value === '') {
    parent.classList.remove('focus');
  }
}

inputs.forEach(input => {
  input.addEventListener('focus', addcl);
  input.addEventListener('blur', remcl);
});

// O loginForm.jsp cookie

// O Click companyList btn
const companyCode = document.querySelector('#companyCode');
const workplaceCode = document.querySelector('#workplaceCode');


// O login check
const userId = document.querySelector('#userId');
const password = document.queryCommandEnabled("#password");
const login = document.querySelector("#login");

login.addEventListener("click", function() {
  if (userId.value == null || password.value == null
      || companyCode.value == null || workplaceCode.value == null) {
    alert("data를 입력하셔야합니다.");
  }
})
// o setting ag-grid
const companyList = document.querySelector('#companyList');
const workplaceList = document.querySelector('#workplaceList');

let gridOptions = {
  defaultColDef: {editable: false}, // 정의하지 않은 컬럼은 자동으로 설정
  onGridReady: function(event) {// onload 이벤트와 유사 ready 이후 필요한 이벤트 삽입한다.
    event.api.sizeColumnsToFit();
  },
  onGridSizeChanged: function(event) { // 그리드의 사이즈가 변하면 자동으로 컬럼의 사이즈 정리
    event.api.sizeColumnsToFit();
  },
  rowSelection: 'single',
  onRowDoubleClicked: function(event) {
    console.log(event);
    console.log(event.data.companyCode); // COM-O1
    if (event.data.workplaceCode == null) {
      companyCode.parentNode.parentNode.classList.add('focus');
      companyCode.value = event.data.companyCode;
      workplaceCode.value = "";
      workplaceCode.parentNode.parentNode.classList.remove('focus');
    } else {
      workplaceCode.parentNode.parentNode.classList.add('focus');
      workplaceCode.value = event.data.workplaceCode;
    }
    $("#myModal").modal('hide');
    $("#myModal2").modal('hide');
  },
};
// o data와 grid는 한번만 받아오면 됨.
let once_companyGrid = true;
let once_workplaceGrid = true;

companyList.addEventListener('click', function() {
  if (once_companyGrid == true) {
    companyData();
    companyGrid();
  }
});

workplaceList.addEventListener('click', function (event) {
  if (companyCode.value == "") {
    console.log(companyCode.value);
    console.log("**************");
    workplaceCode.value = "Company Code를 입력해주세요!";
    workplaceCode.parentNode.parentNode.classList.add('focus');
    $('#myModal2').on('show.bs.modal', (event) => {
      console.log('companyCode 입력 요망');
      event.preventDefault();
    });
    return;
  } else if (once_workplaceGrid == true) {
      $('#myModal2').off();
      workplaceData();
      workplaceGrid();
  }
});

const companyData = () => {
  $.ajax({
    type: 'GET',
    url: '/basicInfo/searchCompany.do', //   contextPath 없어도 가능 -> 서버를 artifact 를  조작하면 됨...
    data: {
      method: 'searchCompanyList',
    },
    dataType: 'json',
    cache: false,
    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    success: function(dataSet) {
   	  console.log("test");
   	  console.log(dataSet);
      gridOptions.api.setRowData(dataSet.gridRowJson);
      console.log(gridOptions.api);
    }
  });
};
// gridOptions.api.setRowData(dataSet.gridRowJson);
// codeName, divisionCodeNo
const companyGrid = () => {
  once_companyGrid = false;
  let columnDefs = [
    {headerName: '회사코드', field: 'companyCode', width: 80},
    {headerName: '회사명', field: 'companyName', width: 80},
    {headerName: '회사구분', field: 'companyDivision', width: 80},
    {headerName: '사업자번호', field: 'businessLicenseNumber', width: 80},
  ];
  gridOptions.columnDefs = columnDefs;
  let companyListGrid = document.querySelector('#companyListGrid');
  console.log(companyListGrid);
  new agGrid.Grid(companyListGrid, gridOptions);
};

const workplaceData = () => {
  $.ajax({
    type: 'GET',
    url: '/basicInfo/searchWorkplace.do', //   contextPath 없어도 가능 -> 서버를 artifact 를  조작하면 됨...
    data: {
      companyCode: companyCode.value,
      method: 'searchWorkplaceList',
    },
    dataType: 'json',
    cache: false,
    success: function (dataSet) {
      console.log(dataSet);
      gridOptions.api.setRowData(dataSet.gridRowJson);
    },
  });
}

const workplaceGrid = () => {
  once_workplaceGrid = false;
  let columnDefs = [
    {headerName: '회사코드', field: 'companyCode', width: 80},
    {headerName: '사업장코드', field: 'workplaceCode', width: 80},
    {headerName: '사업장명', field: 'workplaceName', width: 80},
    {headerName: '사업장번호', field: 'businessLicenseNumber', width: 80},
  ];
  gridOptions.columnDefs = columnDefs;
  console.log(gridOptions.columnDefs);
  let workplaceListGrid = document.querySelector('#workplaceListGrid');
  console.log(workplaceListGrid);
  new agGrid.Grid(workplaceListGrid, gridOptions);
}




