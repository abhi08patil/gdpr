<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">

<head>
<jsp:include page="/WEB-INF/jsp/component/csslibrary.jsp" />
</head>

<body>
	<div class="container-scroller">
		<!-- partial:partials/_navbar.html -->
		<jsp:include page="/WEB-INF/jsp/component/header.jsp" />
		<!-- partial -->
		<div class="container-fluid page-body-wrapper">
			<!-- partial:partials/_sidebar.html -->
			<jsp:include page="/WEB-INF/jsp/component/menu.jsp" />
			<!-- partial -->
			<div class="main-panel">
				<div class="content-wrapper">

					<div class="row">
						<div class="col-md-12 grid-margin"
							style="border: 1px solid #e7eaed; background: white;">
							<div class="d-flex justify-content-between flex-wrap">
								<div class="d-flex align-items-end flex-wrap">
									<h4>Pre-Deletion data Report</h4>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 grid-margin stretch-card">
									<div class="card">
										<div class="card-body">
											<h4 class="card-title"></h4>
											<form action="/gdpr/v1/generatePreDeletionReport" method="post" class="forms-sample">
												<div class="form-group">
													<select class="form-control" name="tableName" id="exampleFormControlSelect1"
														style="outline: 1px solid #0a0a0a; color: black;">
														<option>select Cycle</option>
														<c:if test="${not empty map}">
															<c:forEach var="listValue" items="${map}">
																<option value="${listValue.value}">${listValue.key}</option>
															</c:forEach>

														</c:if>
													</select>
												</div>
												<c:if test="${not empty list}">
													<table class="table">
														<thead class="thead-dark">
															<tr>
																<th scope="col">MODULE_ID</th>
																<th scope="col">RECORD_ID</th>
																<th scope="col">DATE_TOBE_CONSIDERED</th>
																<th scope="col">EXPECTED_DEL_DATE</th>
																<th scope="col">DEL_EXEC_DATE</th>
																<th scope="col">DATA_HOLD</th>
																<th scope="col">MAPPED_DATA_HOLD</th>
																<th scope="col">MAX_DATA_HOLD_DATE</th>
																<th scope="col">SOFT_DELETE</th>
																<th scope="col">STATUS</th>
																<th scope="col">REASON</th>
																
															</tr>
														<tbody>
															<c:forEach var="listValue" items="${list}">
																<c:set var="row" value="${listValue}" />
																<c:set var="rows" value="${fn:split(row, ',')}" />

																<tr>
																	<td>${rows[0]}</td>
																	<td>${rows[1]}</td>
																	<td>${rows[2]}</td>
																	<td>${rows[3]}</td>
																	<td>${rows[4]}</td>
																	<td>${rows[5]}</td>
																	<td>${rows[6]}</td>
																	<td>${rows[7]}</td>
																	<td>${rows[8]}</td>
																	<td>${rows[9]}</td>
																	<td>${rows[10]}</td>
																</tr>

															</c:forEach>
														</tbody>
													</table>
												</c:if>
												 
    
     
  
												<button type="submit" class="btn btn-primary mr-2">Generate Report</button>
												<button class="btn btn-light">Cancel</button>
											</form>
										</div>
									</div>
								</div>

							</div>

						</div>
					</div>
					<div class="content-wrapper">
				
				</div>

				</div>
				<jsp:include page="/WEB-INF/jsp/component/footer.jsp" />
				<jsp:include page="/WEB-INF/jsp/component/header.jsp" />
			</div>
			<!-- main-panel ends -->
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/component/jslibrary.jsp" />

	<script>
	 
	 

		 
	</script>
</body>

</html>

