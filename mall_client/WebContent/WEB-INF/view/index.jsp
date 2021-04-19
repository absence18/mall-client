<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "mall.client.vo.*" %>
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
	<h1>index</h1>
	<!-- 상품리스트 -->
	
	<%
		// 걍 리스트
		List<Ebook>	ebookList = (List<Ebook>)(request.getAttribute("ebookList"));
		// 베셀 리스트
		List<Map<String, Object>> bestOrdersList = (List<Map<String, Object>>)(request.getAttribute("bestOrdersList"));
		// 카테고리 리스트
		List<String> categoryList = (List<String>)(request.getAttribute("categoryList"));
	%>
		
		<a href="<%=request.getContextPath()%>/IndexController">전체</a>
		
		
	<!-- 카테고리 네비게이션 메뉴 -->
	<div>
		<!-- 전체 상품 출력 -->
		<a href="<%=request.getContextPath()%>/IndexController">전체보기</a>	
		<!-- 카테고리 출력 -->
		<%
			for(String e : categoryList) {
		%>
				<a href="<%=request.getContextPath()%>/IndexController?categoryName=<%=e%>"><%=e%></a>
		<%
			}
		%>
	</div>
	
	
	<!-- bestseller ebook 상품 5개 출력 -->
	<h3>베스트셀러 Ebook</h3>
	<table border="1">
		<tr>
			<%
				for(Map m : bestOrdersList) {
			%>
					<td>
						<div><img src="<%=request.getContextPath()%>/img/default.jpg"></div>
						<!-- EbookOneController - EbookDao.selectEbookOne() - ebookOne.jsp -->
						<div>
							<a href="<%=request.getContextPath()%>/EbookOneController?ebookNo=<%=m.get("ebookNo")%>">
								<%=m.get("ebookTitle")%>
							</a>
						</div>

						<div>￦<%=m.get("ebookPrice")%></div>
					</td>
			<%		
				}
			%>
		</tr>
	</table>
	
	
	<!-- ebook 상품 출력 -->
	<h3>전체 Ebook 상품</h3>
	<table border="1">
		<tr>
		<%
			int i = 0;
			for(Ebook ebook : ebookList)	{
				i += 1;
		%>		
					<td>
						<div><img src="<%=request.getContextPath() %>/img/default.jpg"></div>
						
						<!-- EbookOneController - EbookDao.selectEbookOne() - ebookOne.jsp -->
						<div>
							<a href="<%=request.getContextPath()%>/EbookOneController?ebookNo=<%=ebook.getEbookNo()%>">
								<%=ebook.getEbookTitle()%>
							</a>
						</div>
						
						<div>￦<%=ebook.getEbookPrice() %></div>
					</td>
		<%
					if(i%5==0)	{
		%>
						</tr><tr>
		<%
				}
			}
		%>
		</tr>
	</table>
	
	
	<!-- 페이징 (이전, 다음) 버튼 만들기 + 페이징 숫자 나오게 하기 + 카테고리별로 눌렀을 때 조건문으로 구분 -->
		<% 
			// 변수 받아오기 (IndexController)
			int currentPage = (int)(request.getAttribute("currentPage"));
			int rowPerPage = (int)(request.getAttribute("rowPerPage"));
			int totalRow = (int)(request.getAttribute("totalRow"));
			String categoryName = (String)(request.getAttribute("categoryName"));
			
			// 이전 버튼
			// 맨 첫 페이지에서 이전 버튼이 나오지 않게 함
			if(currentPage > 1) {			
				if(categoryName == null) { // 카테고리 메뉴를 선택하지 않았다면
		%>
					<a href="<%=request.getContextPath()%>/IndexController?currentPage=<%=currentPage-1%>&rowPerPage=<%=rowPerPage%>">이전</a>
		<%
				} else { // 카테고리를 선택했다면
		%>			
					<a href="<%=request.getContextPath()%>/IndexController?currentPage=<%=currentPage-1%>&rowPerPage=<%=rowPerPage%>&categoryName=<%=categoryName%>">이전</a>
		<%
				}
			}
		%>

		<%
			// 다음 버튼
			int lastPage = totalRow / rowPerPage; // 마지막 페이지의 번호이자, 총 몇 페이지인지 알 수 있음
			if(totalRow % rowPerPage != 0) { // 나머지가 있다면 rowPerPage보다 적은 마지막 게시물들을 보여주기 위해서, 마지막 페이지 한 개 더 추가
				lastPage += 1; // lastPage = lastPage+1; lastPage++;
			}
				
			if(currentPage < lastPage) { // 마지막 페이지에서 다음 버튼이 안 보이게 함
				if(categoryName == null) {
		%>
					<a href="<%=request.getContextPath()%>/IndexController?currentPage=<%=currentPage+1%>&rowPerPage=<%=rowPerPage%>">다음</a>
		<%
				} else {
		%>
					<a href="<%=request.getContextPath()%>/IndexController?currentPage=<%=currentPage+1%>&rowPerPage=<%=rowPerPage%>&categoryName=<%=categoryName%>">다음</a>
		<%
					}
			}
		%>
		

	<!-- 검색기능 -->
	<form method="get" action="<%=request.getContextPath()%>/IndexController">
		ebookTitle:
		<input type="text" name="searchWord">
		<button type="submit">검색</button>
	</form>
	
</body>
</html>