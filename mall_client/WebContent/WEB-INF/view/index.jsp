<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	<!-- 캘린더(이번달에 나온 책들)-->
	<!-- 베스트셀러(주문량) -->
	<!-- 메뉴2 카테고리-->
	
	<div>
		<div>오늘 접속자 : ${statsCount }</div>
		<div>전체 접속자 : ${total }</div>
	</div>
	
	
	<h1>index</h1>
	<!-- 상품리스트 -->
	
	
		<a href="${pageContext.request.contextPath }/IndexController">전체</a>
		
		
	<!-- 카테고리 네비게이션 메뉴 -->
	<div>
	
		<!-- 전체 상품 출력 -->
		<a href="${pageContext.request.contextPath }/IndexController">전체보기</a>	
		<!-- 카테고리 출력 -->
		<c:forEach var="e" items="${categoryList }">
			<a href="${pageContext.request.contextPath }/IndexController?categoryName=${e}">${e}</a>
		</c:forEach>
		
	</div>
	
	
	<!-- bestseller ebook 상품 5개 출력 -->
	<h3>베스트셀러 Ebook</h3>
	<table border="1">
		<tr>
			<c:forEach var="m" items="${bestOrdersList }">
			
				<td>
						<div><img src="${pageContext.request.contextPath }/img/default.jpg"></div>
						<!-- EbookOneController - EbookDao.selectEbookOne() - ebookOne.jsp -->
						<div>
							<a href="${pageContext.request.contextPath }/EbookOneController?ebookNo=${m.ebookNo}">
								${m.ebookTitle}
							</a>
						</div>

						<div>￦${m.ebookPrice}</div>
					</td>
			
			</c:forEach>
		</tr>
	</table>
	
	
	<!-- ebook 상품 출력 -->
	<h3>전체 Ebook 상품</h3>
	<table border="1">
	
		<tr>
			<c:set var="i" value="0"/>
			<c:forEach var="ebook" items="${ebookList }">
				<c:set var="i" value="${i+1 }"/>
				
				<td>
					<div><img src="${pageContext.request.contextPath }/img/default.jpg"></div>
					<!-- ebookOneController -> ebookDao.selectEbookOne() -> ebookOne.jsp -->
					<div>
						<a href="${pageContext.request.contextPath }/EbookOneController?ebookNo=">
							${ebook.ebookTitle}
						</a>
					</div>
					<div>￦${ebook.ebookPrice }</div>
				</td>
				<c:if test="${i%5 == 0 }">
					</tr><tr>
				</c:if>
			</c:forEach>
		
		</tr>
	</table>
	
	
	<!-- 페이징 (이전, 다음) 버튼 만들기 + 페이징 숫자 나오게 하기 + 카테고리별로 눌렀을 때 조건문으로 구분 -->
		
		<!-- 이전 버튼 -->
		<c:choose>
			<c:when test="${currentPage > 1 }">
				<c:if test="${categoryName == null }">
					<a href="${pageContext.request.contextPath }/IndexController?currentPage=${currentPage - 1 }&rowPerPage=${rowPerPage}">이전</a>
				</c:if>
				
				<c:otherwise>
					<a href="${pageContext.request.contextPath }/IndexController?currentPage=${currentPage - 1 }&rowPerPage=${rowPerPage}&categoryName=${categoryName}">이전</a>
				</c:otherwise>
			</c:when>
		</c:choose>
		<!-- 다음 버튼 -->
		<c:choose>
			<c:when test="${currentPage < lastPage }">
				<c:if test="${categoryName == null }">
					<a href="${pageContext.request.contextPath }/IndexController?currentPage=${currentPage + 1 }&rowPerPage=${rowPerPage}">다음</a>
				</c:if>
				
				<c:otherwise>
					<a href="${pageContext.request.contextPath }/IndexController?currentPage=${currentPage + 1 }&rowPerPage=${rowPerPage}&categoryName=${categoryName}">다음</a>
				</c:otherwise>
			</c:when>
		</c:choose>
		
		
		<!-- 검색기능 -->
		<form method="get" action="${pageContext.request.contextPath }/IndexController">
			ebookTitle:
			<input type="text" name="searchWord">
			<button type="submit">검색</button>
		</form>
	
</body>
</html>