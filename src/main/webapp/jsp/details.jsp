<%@ page import="java.util.*"%>
<%@ page import="com.drug.model.*"%>
<%@ page import="com.drug.dao.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  String ndc = "";
  if (request.getParameter("ndc") != null)
    ndc = (String) request.getParameter("ndc");
%>
<!DOCTYPE HTML>

<html>
<head>
<LINK REL=StyleSheet HREF="../css/drug.css" TYPE="text/css">
</head>
<body>
	<br />
	<br />
	<table  border="0" width="80%" align="center">
		<tr>
			<td align="center" style="background-color: #fff"><h2>
					Drug Details for NDC:
					<%=ndc%></h2></td>
		</tr>
		<%
		  ProductHome productHome = new ProductHome();
		  Product product = productHome.findByNDC(ndc);
		  if (product != null) {
		%>
		<tr>
			<td>
				<table class="details" width="100%">
					<tr>
						<td width="20%"><b>Proprietary Name</b></td>
						<td width="30%"><%=product.getProprietaryname() + " " + product.getProprietarynamesuffix()%></td>
						<td width="20%"><b>Non-proprietary Name</b></td>
						<td width="30%"><%=product.getNonproprietaryname()%></td>
					</tr>
					<tr>
						<td width="20%"><b>Dosage Form</b></td>
						<td width="30%"><%=product.getDosageformname()%></td>
						<td width="20%"><b>Strength</b></td>
						<td width="30%"><%=product.getActiveNumeratorStrength() + " " + product.getActiveIngredUnit()%></td>
					</tr>
					<tr>
						<td width="20%"><b>Product Type</b></td>
						<td width="30%"><%=product.getProducttypename()%></td>
						<td width="20%"><b>Route</b></td>
						<td width="30%"><%=product.getRoutename()%></td>
					</tr>
					<tr>
						<td><b>Marketing Start Date</b></td>
						<td><%=product.getStartmarketingdate()%></td>
						<td><b>Marketing End Date</b></td>
						<td><%=product.getEndmarketingdate()%></td>
					</tr>
					<tr>
						<td><b>Marketing Category</b></td>
						<td><%=product.getMarketingcategoryname()%></td>
						<td><b>Application Number</b></td>
						<td><%=product.getApplicationnumber()%></td>
					</tr>
					<tr>
						<td><b>Labeler</b></td>
						<td><%=product.getLabelername()%></td>
						<td><b>DEA Schedule</b></td>
						<td><%=product.getDeaschedule()%></td>
					</tr>
					<tr>
						<td><b>Pharmaceutical Classes</b></td>
						<td colspan="3"><%=product.getPharmClasses()%></td>
					</tr>
				</table>
			</td>
		</tr>

		<%
		  } else {
		%>
		<tr>
			<td align="center" style="background-color: #fff"><b>No
					Record Found..</b></td>
		</tr>

		<%
		  }
		%>

		<tr>
			<td align="center" style="background-color: #fff"><h2>Packages</h2></td>
		</tr>
		<%
		  int count = 0;
		  PackageHome pkgHome = new PackageHome();
		  List results = pkgHome.findByProductNdc(ndc);

		  Iterator itr = results.iterator();

		  if (!itr.hasNext()) {
		%>

		<tr>
			<td align="center" style="background-color: #fff"><b>No
					Packages Found..</b></td>
		</tr>
		<%
		  } else {
		%>
		<tr>
			<td>
				<table class="packages" width=100%>
					<tr>
						<th width=\"20%\"><b>Package NDC</b>
			</td>
			<th width=\"80%\"><b>Package Description</b>
			</td>
		</tr>

		<%
		  }
		  while (itr.hasNext()) {

		    count++;
		    com.drug.model.Package pkg = (com.drug.model.Package) itr.next();
		%>
		<tr>
			<td width="20%"><%=pkg.getId().getNdcpackagecode()%></td>
			<td width="80%"><%=pkg.getPackagedescription()%></td>
		</tr>
		<%
		  }
		%>
	</table>
	</td>
	</tr>

	</table>

</body>
</html>