<%@taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
								<!--  <div class="d-flex align-items-end flex-wrap">
									<h2>CONFIGURATION</h2>
								</div>-->
								
								<div class="col-12 grid-margin stretch-card">
						              <div class="card">
						                <div class="card-body">
						                  <h4 class="card-title">GDPR Configurations</h4>
						                  <p class="card-description">
						                    Please click on Create button to generate GDPR configurations tables.
						                  </p>
						                  <s:form action="/gdpr/v1/createGdprTables" method="get">
						                    
						                    <button type="submit" class="btn btn-primary mb-2">Create</button>
						                    <a class="btn btn-primary mb-2" href="/gdpr/v1/welcome">Cancel</a>
						                    <c:if test="${not empty TableCreationMesg}">
						                  	     	<div class="alert alert-success alert-dismissible"><strong>${TableCreationMesg}</strong>
								                  		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
								                  	</div>
									        </c:if>
						                    
						                    <c:forEach items="${tblList}" var="tableList">
						                    	<p>${tableList}</p>
						                    </c:forEach>
						                  </s:form>
						                </div>
						              </div>
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

