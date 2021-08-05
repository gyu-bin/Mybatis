<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Page Title</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/introduce.css">
</head>
<body>
    <div class="container">
        <div class="card">
            <div class="imgBx">
                <img src="${pageContext.request.contextPath}img/iu3.jpg">
                <p>on mouse over</p>
            </div>
               <div class="content">
            <h2>Introduce</h2>
            <p>
                WORKPLACE : ${sessionScope.workplaceName} &emsp;&emsp;
                DEPT : ${sessionScope.deptName} &emsp;&emsp;

                POSITION : ${sessionScope.positionName} &emsp;&emsp;
                NAME : ${sessionScope.empName} &emsp;&emsp;
                ID : ${sessionScope.userId} &emsp;&emsp;
            </p>
        </div>
    </div>
        <%--2번--%>
        <div class="card">
            <div class="imgBx">
                <img src="${pageContext.request.contextPath}img/iu.jpg">
                <p>on mouse over</p>
            </div>
            <div class="content">
                <h2>Introduce</h2>
                <p>
                    WORKPLACE : ${sessionScope.workplaceName} &emsp;&emsp;
                    DEPT : ${sessionScope.deptName} &emsp;&emsp;

                    POSITION : ${sessionScope.positionName} &emsp;&emsp;
                    NAME : ${sessionScope.empName} &emsp;&emsp;
                    ID : ${sessionScope.userId} &emsp;&emsp;
                </p>
            </div>
        </div>
        <%--3번--%>
        <div class="card">
            <div class="imgBx">
                <img src="${pageContext.request.contextPath}img/iu2.jpg">
                <p>on mouse over</p>
            </div>
            <div class="content">
                <h2>Introduce</h2>
                <p>
                    WORKPLACE : ${sessionScope.workplaceName} &emsp;&emsp;
                    DEPT : ${sessionScope.deptName} &emsp;&emsp;

                    POSITION : ${sessionScope.positionName} &emsp;&emsp;
                    NAME : ${sessionScope.empName} &emsp;&emsp;
                    ID : ${sessionScope.userId} &emsp;&emsp;
                </p>
            </div>
        </div>
    </div>
</body>
</html>