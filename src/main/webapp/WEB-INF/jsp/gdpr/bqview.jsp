<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
									<h4>Genrate BaseQuery</h4>
								</div>

							</div>

							
							<div class="row">
								<div class="col-md-6 grid-margin stretch-card">
									<div class="card">
										<div class="card-body">
											<h4 class="card-title"></h4>
											<form action="/gdpr/v1/createBq" method="post" class="forms-sample">
												<div class="form-group">
													<select class="form-control" name="tableName" id="exampleFormControlSelect1"
														style="outline: 1px solid #0a0a0a; color: black;">
														<option>select table name</option>


														<c:if test="${not empty lists}">

															<c:forEach var="listValue" items="${lists}">
																<option value="${listValue}">${listValue}</option>
															</c:forEach>

														</c:if>
													</select>
												</div>


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
												<div class="form-group">
													<label for="name">Module Name:</label> <input type="text"
														class="form-control" id="moduleName" name="moduleName" required="true" style="outline: 1px solid #0a0a0a; color: black;">
												</div>
												<div class="form-group">
													<label for="name">Retention(Months):</label> <input type="number"
														class="form-control" id="retention" name="retention" required="true"  style="outline: 1px solid #0a0a0a; color: black;">
												</div>
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
					<c:if test="${not empty msg}">

								<h5 id="msg">${msg}</h5>

							</c:if>
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
	 
	$(document).on('click','.columns',function () {
		$("input[name='test']").change(function() {
				var maxAllowed = 2;
				var cnt = $("input[name='test']:checked").length;
				if (cnt > maxAllowed) {
					$(this).prop("checked", "");
					alert('Select maximum ' + maxAllowed + ' columns!');
				} else {

				}
			});
		});

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
															type : "GET",
															url : "/gdpr/v1/getColumns?tableName="
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
																html = "<tr><th>Column Name</th><th>Data type</th><th>select</th></tr>";
																for ( var key in data) {
																	if (data
																			.hasOwnProperty(key)) {
																		html += "<tr><td>"
																				+ key
																				+ "</td><td>"
																				+ data[key]
																				+ "</td><td><input class='columns' type='checkbox' name='test'  id='test' value='"+key+"-"+data[key]+"' /></td></tr>";
																	}
																}

																$('#tableData')
																		.append(
																				html);
																$(
																		'#groupButton')
																		.show();
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

