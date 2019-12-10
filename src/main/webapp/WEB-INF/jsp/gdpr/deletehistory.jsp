<!DOCTYPE html>
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
							style="border: 1px solid #f3f3f3; background: white;">
							<div class="d-flex justify-content-between flex-wrap">
								<div class="d-flex align-items-end flex-wrap">
									<h2>DELETE HISTORY</h2>
								</div>

							</div>
						</div>
					</div>
					<div class="content-wrapper"></div>

				</div>
				<jsp:include page="/WEB-INF/jsp/component/footer.jsp"/>
				  <jsp:include page="/WEB-INF/jsp/component/header.jsp"/>
			</div>
			<!-- main-panel ends -->
    </div>
  </div>
 <jsp:include page="/WEB-INF/jsp/component/jslibrary.jsp" />
</body>

</html>

