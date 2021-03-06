<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
    <title>4th Project</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/popper.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <sitemesh:write property='head'/>

    <style>
        h5 {
            font-family: 'Noto Sans KR', sans-serif;
        }
    </style>

</head>
<body>

<c:if test="${sessionID != null }">
    <div class="wrapper d-flex align-items-stretch">
        <nav id="sidebar">
            <div class="p-4 pt-5">
                <a href="${pageContext.request.contextPath}/hello2.html" class="img logo rounded-circle mb-5" style="background-image: url('${pageContext.request.contextPath}/img/logo.jpg');"></a>
                <ul class="list-unstyled components mb-5">
                    <li>
                        <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Home</a>
                        <ul class="collapse list-unstyled" id="homeSubmenu">
                            <li>
                                <a href="${pageContext.request.contextPath}/hello.html">Home 1</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/hello2.html">Home 2</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/home.html">Home 3</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">????????????</a>
                        <ul class="collapse list-unstyled" id="pageSubmenu">
                            <li>
                                <a href="${pageContext.request.contextPath}/logisticsInfo/codeInfo.html">?????? ??????</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/logisticsInfo/itemInfo.html">?????? ??????</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/logisticsInfo/wareHouseInfo.html">?????? ??????</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#pageSubmenu2" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">????????????</a>
                        <ul class="collapse list-unstyled" id="pageSubmenu2">
                            <li>
                                <a href="#pageSubmenu2-1" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">?????? ??????</a>
                                <ul class="collapse list-unstyled" id="pageSubmenu2-1">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/sales/estimateInfo.html">?????? ??????/??????</a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/sales/estimateRegister.html">?????? ??????</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="#pageSubmenu2-2" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">?????? ??????</a>
                                <ul class="collapse list-unstyled" id="pageSubmenu2-2">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/sales/contractInfo.html">?????? ??????</a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/sales/contractRegister.html">?????? ??????</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/sales/deliveryInfo.html">?????? ??????</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/sales/salesPlanInfo.html">?????? ??????</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#pageSubmenu3" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">????????????</a>
                        <ul class="collapse list-unstyled" id="pageSubmenu3">
                            <li>
                                <a href="${pageContext.request.contextPath}/material/logisticsBOM.html">???????????????(BOM)</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/material/orderRegister.html">?????? ??? ????????????</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/material/stockInfo.html">?????? ??????</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#pageSubmenu4" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">????????????</a>
                        <ul class="collapse list-unstyled" id="pageSubmenu4">
                            <li>
                                <a href="${pageContext.request.contextPath}/production/mpsRegister.html">??????????????? (MPS)</a>
                            </li>
                            <li>
                                <a href="#pageSubmenu4-1" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">???????????????/?????? (MRP)</a>
                                <ul class="collapse list-unstyled" id="pageSubmenu4-1">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/production/mrpRegisterAndGather.html">??????????????? ??????/??????</a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/production/mrpInfo.html">???????????????/?????? ??????</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/production/workInstruction.html">???????????? / ???????????? ??????</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/production/workSite.html">?????????/???????????????</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#pageSubmenu5" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">??????</a>
                        <ul class="collapse list-unstyled" id="pageSubmenu5">
                            <li>
                                <a href="${pageContext.request.contextPath}/personnel/empInfo.html">??????????????????</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/personnel/empRegister.html">????????????</a>
                            </li>
                        </ul>
                    </li>
                </ul>

                <div class="footer">
                    <p>???????????? ????????? ????????? 74-6 </br>???????????? 7???</p>
                    <p>Tel : 010 - 3581 - 0058
                        </br>Email: estimulomusic@gmail.com </p>
                </div>

            </div>
        </nav>

        <!-- Page Content  -->
        <div id="content" class="p-4 p-md-5">

            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">

                    <button type="button" id="sidebarCollapse" class="btn btn-primary">
                        <i class="fa fa-bars"></i>
                        <span class="sr-only">Toggle Menu</span>
                    </button>
                    <button class="btn btn-dark d-inline-block d-lg-none ml-auto" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <i class="fa fa-bars"></i>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="nav navbar-nav ml-auto">
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/sales/estimateRegister.html">??????</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/sales/contractRegister.html">??????</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/production/mpsRegister.html">mps</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link"  href="${pageContext.request.contextPath}/production/mrpRegisterAndGather.html">mrp</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link"  href="${pageContext.request.contextPath}/material/orderRegister.html">??????</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link"  href="${pageContext.request.contextPath}/material/stockInfo.html">??????</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link"  href="${pageContext.request.contextPath}/production/workInstruction.html">??????</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link"  href="${pageContext.request.contextPath}/production/workSite.html">?????????</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/sales/deliveryInfo.html">??????</a>
                            </li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a class="nav-link" href="${pageContext.request.contextPath}/logout.do">????????????</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
            <sitemesh:write property='body'/>
        </div>
    </div>
</c:if>
<c:if test="${sessionID == null}">
    <script>
        location.href="${pageContext.request.contextPath}/loginForm.html"
    </script>
</c:if>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>