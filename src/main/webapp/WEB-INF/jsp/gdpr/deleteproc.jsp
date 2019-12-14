<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">

<head>
   <jsp:include page="/WEB-INF/jsp/component/csslibrary.jsp"/>
</head>
<body>
  <div class="container-scroller">
    <!-- partial:partials/_navbar.html -->
    <jsp:include page="/WEB-INF/jsp/component/header.jsp"/>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
      <!-- partial:partials/_sidebar.html -->
      <jsp:include page="/WEB-INF/jsp/component/menu.jsp"/>
      <!-- partial -->
			<div class="main-panel">
				<div class="content-wrapper">

					<div class="row">
						<div class="col-md-12 grid-margin"
							style="border: 1px solid #e7eaed; background: white;">
							<div class="d-flex justify-content-between flex-wrap">
								<div class="d-flex align-items-end flex-wrap">
									<h4>Genrate DeleteProc</h4>
								</div>

							</div>

							
							<div class="row">
								
								<div class="col-md-6 grid-margin stretch-card">
									<div class="card">
										<div class="card-body">
											<h4 class="card-title"></h4>
											<form action="/gdpr/v1/generateDeleteProc" method="post" class="forms-sample">
												<div class="form-group">
													<select class="form-control" name="tableName" id="exampleFormControlSelect1"
														style="outline: 1px solid #0a0a0a; color: black;">
														<option>select Config Name</option>


														<c:if test="${not empty map}">
															<c:forEach var="listValue" items="${map}">
																<option value="${listValue.key}">${listValue.value}</option>
															</c:forEach>

														</c:if>
 
													</select>
												</div>

												<c:if test="${not empty msg}">
													<div class="alert alert-success alert-dismissible" id="msg">
														<a href="#" class="close" data-dismiss="alert"
															aria-label="close">&times;</a> <strong>Success!</strong>${msg}

													</div>
												</c:if>
												<c:if test="${not empty error}">
													<div class="alert alert-danger alert-dismissible" id="msg">
														<a href="#" class="close" data-dismiss="alert"
															aria-label="close">&times;</a> <strong>Failed!</strong>${error}

													</div>
												</c:if>
												
												
												<div class="form-group">
													<div class="table-responsive">
														<table class="table table-striped" id="tableData">
															<thead>

															</thead>
															<tbody>

															</tbody>
														</table>
													</div>
												</div>
												<div id="groupButton" style="display:none">
												<button type="submit" class="btn btn-primary mr-2">Submit</button>
												<button class="btn btn-light">Cancel</button>
												</div>
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
				<jsp:include page="/WEB-INF/jsp/component/footer.jsp"/>
				  <jsp:include page="/WEB-INF/jsp/component/header.jsp"/>
			</div>
			<!-- main-panel ends -->
    </div>
  </div>
 <jsp:include page="/WEB-INF/jsp/component/jslibrary.jsp" />
 <script>
 $(document)
	.ready(
			function() {
				$("select#exampleFormControlSelect1")
						.change(
								function() {
									var selectedTable = $(this)
											.children(
													"option:selected")
											.val();
									$("#msg").remove();
									var data = null;
									$
											.ajax({
												type : "POST",
												url : "/gdpr/v1/getTableHierarchy?tableName="
														+ selectedTable,
												contentType : 'application/json',
												async : false,
												success : function(
														response) {
													data = response;
													var html = null;
													$(
															'#tableData tbody')
															.empty();
													html = "<tr><th>Table Name</th><th>Select</th></tr>";
													for ( var key in data) {
														if (data
																.hasOwnProperty(key)) {
															html += "<tr><td>"
																	+ key
																	+ "</td><td><input class='columns' type='checkbox' name='test'  id='test' value='"+key+"-"+data[key]+"' /></td></tr>";
														}
													}

													$('#tableData') .append( html);
													$('#groupButton') .show();
													 
												},
												error : function() {
													alert(
															"Message",
															"System has no response.",
															'error');
												}
											});
								});
			});
</script>
</body>

</html>

