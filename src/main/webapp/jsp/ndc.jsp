<%@ page import="java.util.*"%>
<%@ page import="com.drug.model.*"%>
<%@ page import="com.drug.dao.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  String drugName = "";
			int pageNum = 1;
			int pageSize = 5;
			long totalSize = 1;
			long firstPageInSet = 1;
			int pageSetSize = 10;
			boolean searchClicked = false;

			if (request.getParameter("drugName") != null)
				drugName = (String) request.getParameter("drugName");

			if (request.getParameter("pageNum") != null)
				pageNum = Integer.parseInt(request.getParameter("pageNum").toString());

			if (request.getParameter("firstPageInSet") != null)
				firstPageInSet = Long.parseLong(request.getParameter("firstPageInSet").toString());

%>
<!DOCTYPE HTML>

<html>
<head>
    <link rel="stylesheet" href="../anglr/bower_components/bootstrap/dist/css/bootstrap.css" />
<LINK REL=StyleSheet HREF="../css/drug.css" TYPE="text/css">
<script type="text/javascript" src="../js/drug.js"></script>

<script language="javascript">
	function submitSearch() {
		document.forms["searchform"].submit();
	}
	function resetPaginationFields() {
		document.getElementById('pageNum').value = '1';
		document.getElementById("totalSize").value = '1';
		document.getElementById("firstPageInSet").value = '1';
	}
</script>
</head>
<body>
	<br />
	<br />
	<form method="post" name="searchform"
		action="ndc.jsp?searchclicked=true">
		<table border="0" width="80%" align="center" bgcolor="#fff">
			<tr>
				<td colspan="2" align="center">
				JSP&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
				<a href="../anglr">AngularJS</a>
				</td>
			</tr>
			<tr>
				<td colspan=2 style="font-size: 150%;" align="center">
					<h3>National Drug Codes</h3>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="text"
					name="drugName" id="drugName" value="<%=drugName%>" size="50"
					style="font-size: 100%"
					onkeydown="javascript:resetPaginationFields();"></td>
			</tr>
			<tr>
				<td colspan=2 align="center"><br /> <input type="submit"
					name="searchSubmit" value="Search" style="font-size: 100%"></td>
			</tr>
		</table>
		<br /> <br />
		<%
		  if (request.getParameter("searchclicked") != null && request.getParameter("searchclicked").equals("true")
		      && !"".equals(drugName)) {
				ProductHome productHome = new ProductHome();

				if (request.getAttribute("totalSize") != null)
					totalSize = Integer.parseInt(request.getAttribute("totalSize").toString());
				else {
					totalSize = productHome.countByDrugName("%" + drugName + "%", pageSize);
				}

				long lastPageInSet = firstPageInSet + pageSetSize - 1;
				if (lastPageInSet > totalSize)
					lastPageInSet = totalSize;
		%>
		<table class="summary" border="0" width="80%">
		<%
		if (totalSize > 0) {
		%>
				<tr >

				<th width="15%"><b>NDC</b></th>
				<th width="15%"><b>Proprietary name</b></th>
				<th width="20%"><b>Non-proprietary name</b></th>
				<th width="20%"><b>Dosage form</b></th>
				<th width="20%"><b>Strength</b></th>
			</tr>
			<%
		  }
			  int count = 0;
			    String color = "#FFF";

			    if (drugName != null) {
			      if (totalSize > 1) {
			%>

			<p style="text-align: center;">
				<%
				  if (firstPageInSet > 1) {
				%>
				<a href="#"
					onclick="document.getElementById('firstPageInSet').value='<%=firstPageInSet - 10%>'	; document.getElementById('pageNum').value='<%=firstPageInSet - 10%>'	; submit();">Previous</a>&nbsp;&nbsp;
				<%
				  ;
				        }
				        for (long i = firstPageInSet; i <= lastPageInSet; i++) {
				          if (pageNum == i) {
				%>
				<%=i%>&nbsp;&nbsp;
				<%
				  ;
				          } else {
				%>
				<a href="#"
					onclick="document.getElementById('pageNum').value='<%=i%>'	;submitSearch();"><%=i%></a>&nbsp;&nbsp;
				<%
				  ;
				          }
				        }
				        if (lastPageInSet < totalSize) {
				%>
				<a href="#"
					onclick="document.getElementById('firstPageInSet').value='<%=lastPageInSet + 1%>'	; document.getElementById('pageNum').value='<%=lastPageInSet + 1%>'	; submit();">Next
				</a>
				<%
				  ;
				        }
				%>
			</p>

			<%
			  }
			      List results = productHome.findByDrugName("%" + drugName + "%", pageNum, pageSize);

			      Iterator itr = results.iterator();
			      while (itr.hasNext()) {

			        count++;
			        Product product = (Product) itr.next();
			%>

			<tr>
				<td width="15%"><a
					href="<%="details.jsp?ndc=" + product.getProductndc()%>"><%=product.getProductndc()%></td>
				<td width="15%"><%=product.getProprietaryname() + " " + product.getProprietarynamesuffix()%></td>
				<td width="20%"><%=product.getNonproprietaryname()%></td>
				<td width="20%"><%=product.getDosageformname()%></td>
				<td width="20%"><%=product.getActiveNumeratorStrength() + " " + product.getActiveIngredUnit()%></td>
			</tr>
			<%
			  }
			      if (count == 0) {
			%>
			<tr>
				<td colspan=5 align="center" style="background-color: #fff"><b>No
						Records Found..</b></td>
			</tr>
			<%
			  }
			%>
		</table>
		<%
		  }
		  }
		%>
		<input type="hidden" id="pageNum" name="pageNum" value="<%=pageNum%>" />
		<input type="hidden" id="firstPageInSet" name="firstPageInSet"
			value="<%=firstPageInSet%>" /> <input type="hidden" id="totalSize"
			name="totalSize" value="<%=totalSize%>" />
	</form>
</body>
</html>
