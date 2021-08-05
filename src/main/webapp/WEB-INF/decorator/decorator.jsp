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
                        <a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">물류정보</a>
                        <ul class="collapse list-unstyled" id="pageSubmenu">
                            <li>
                                <a href="${pageContext.request.contextPath}/logisticsInfo/codeInfo.html">코드 관리</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/logisticsInfo/itemInfo.html">품목 관리</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/logisticsInfo/wareHouseInfo.html">창고 관리</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#pageSubmenu2" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">영업관리</a>
                        <ul class="collapse list-unstyled" id="pageSubmenu2">
                            <li>
                                <a href="#pageSubmenu2-1" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">견적 관리</a>
                                <ul class="collapse list-unstyled" id="pageSubmenu2-1">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/sales/estimateInfo.html">견적 조회/수정</a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/sales/estimateRegister.html">견적 등록</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="#pageSubmenu2-2" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">수주 관리</a>
                                <ul class="collapse list-unstyled" id="pageSubmenu2-2">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/sales/contractInfo.html">수주 조회</a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/sales/contractRegister.html">수주 등록</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/sales/deliveryInfo.html">납품 관리</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/sales/salesPlanInfo.html">판매 계획</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#pageSubmenu3" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">자재관리</a>
                        <ul class="collapse list-unstyled" id="pageSubmenu3">
                            <li>
                                <a href="${pageContext.request.contextPath}/material/logisticsBOM.html">자재명세서(BOM)</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/material/orderRegister.html">발주 및 재고처리</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/material/stockInfo.html">재고 관리</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#pageSubmenu4" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">생산관리</a>
                        <ul class="collapse list-unstyled" id="pageSubmenu4">
                            <li>
                                <a href="${pageContext.request.contextPath}/production/mpsRegister.html">주생산계획 (MPS)</a>
                            </li>
                            <li>
                                <a href="#pageSubmenu4-1" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">소요량전개/취합 (MRP)</a>
                                <ul class="collapse list-unstyled" id="pageSubmenu4-1">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/production/mrpRegisterAndGather.html">소요량전개 등록/취합</a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/production/mrpInfo.html">소요량전개/취합 조회</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/production/workInstruction.html">작업지시 / 생산실적 관리</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/production/workSite.html">작업장/작업장로그</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#pageSubmenu5" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">인사</a>
                        <ul class="collapse list-unstyled" id="pageSubmenu5">
                            <li>
                                <a href="${pageContext.request.contextPath}/personnel/empInfo.html">사원정보조회</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/personnel/empRegister.html">사원등록</a>
                            </li>
                        </ul>
                    </li>
                </ul>

                <div class="footer">
                    <p>경상남도 진주시 가좌길 74-6 </br>혜람빌딩 7층</p>
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
                                <a class="nav-link" href="${pageContext.request.contextPath}/sales/estimateRegister.html">견적</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/sales/contractRegister.html">수주</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/production/mpsRegister.html">mps</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link"  href="${pageContext.request.contextPath}/production/mrpRegisterAndGather.html">mrp</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link"  href="${pageContext.request.contextPath}/material/orderRegister.html">발주</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link"  href="${pageContext.request.contextPath}/material/stockInfo.html">재고</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link"  href="${pageContext.request.contextPath}/production/workInstruction.html">작업</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link"  href="${pageContext.request.contextPath}/production/workSite.html">작업장</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/sales/deliveryInfo.html">납품</a>
                            </li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a class="nav-link" href="${pageContext.request.contextPath}/logout.do">로그아웃</a></li>
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