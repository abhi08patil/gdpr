<%@taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
						<div class="col-md-12 grid-margin"x`
							style="border: 1px solid #f3f3f3; background: white;">
							
							<div class="card">
                <div class="card-body">
                	
                  <h4 class="card-title">Data Hold Mapping</h4>
                  <s:form action="/gdpr/v1/createDataHold" method="post" class="form-sample" modelAttribute="dataholdDto">
                  	
                  <div class="row">
                     <div class="col-md-6">
                      <div class="form-group row">
	                  	 <label class="col-sm-3 col-form-label">Select Module</label>
	                    <select class="form-control col-sm-9" id="exampleFormControlSelect3">
	                      <c:forEach items="${tblList}" var="tableList">
	                      	<option>${tableList}</option>
	                      </c:forEach>	
	                      
	                    </select>
	                   </div> 
                    </div>
                  </div>
                    
                    <div class="col-lg-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Module Details</h4>
                  <div class="table-responsive pt-3">
                    <!--  <table class="table table-bordered">
                      <thead>
                        <tr>
                          <th>
                            Column1
                          </th>
                          <th>
                            Column2
                          </th>
                          <th>
                            Column3
                          </th>
                          <th>
                            Column4
                          </th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td>
                            Herman Beck
                          </td>
                          <td>
                            <div class="progress">
                              <div class="progress-bar bg-success" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </td>
                          <td>
                            $ 77.99
                          </td>
                          <td>
                            May 15, 2015
                          </td>
                        </tr>
                      
                        
                      </tbody>
                    </table>-->
                  </div>
                </div>
              </div>
            </div>
                    
                     
                    <button type="submit" class="btn btn-primary mb-2">Submit</button>
                    <a class="btn btn-primary mb-2" href="/gdpr/v1/welcome">Cancel</a>
                  </s:form>
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

