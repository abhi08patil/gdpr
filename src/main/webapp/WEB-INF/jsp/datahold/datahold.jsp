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
						<div class="col-md-12 grid-margin"
							style="border: 1px solid #f3f3f3; background: white;">
							
				<div class="card">
                <div class="card-body">
                	
                  <h4 class="card-title">Apply Data Hold</h4>
                  <s:form action="/gdpr/v1/createDataHold" method="post" class="form-sample" modelAttribute="dataholdDto">
                  <c:if test="${not empty success}">
                  	<div class="alert alert-success alert-dismissible"><strong>${success}</strong>
                  	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                  	</div>
                  </c:if>
                  <c:if test="${not empty error}">
                  	<div class="alert alert-warning alert-dismissible"><strong>${error}</strong>
                  	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                  	</div>
                  </c:if>	
                  <div class="row">
                     <div class="col-md-6">
                      <div class="form-group row">
	                  	 <!--  <label class="col-sm-3 col-form-label">Select Module</label>
	                    <select class="form-control col-sm-9" id="exampleFormControlSelect3">
	                      <c:forEach items="${tblList}" var="tableList">
	                      	<option>${tableList}</option>
	                      </c:forEach>	
	                      
	                    </select>-->
	                   </div> 
                    </div>
                  </div>
                    
                    <div class="row">
                      <!--<div class="col-md-6">
                          <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Data Hold Type</label>
                          <div class="col-sm-9">
                            <input type="text" class="form-control" name="dataHoldType" />
                          </div>
                        </div>
                      </div>-->
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">External Data Hold Id</label>
                          <div class="col-sm-9">
                            <input type="text" class="form-control" name="dataHoldExtId" />
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Data Hold Start Date</label>
                          <div class="col-sm-9">
                           <input class="form-control" placeholder="dd/mm/yyyy" name="dataHoldStartDt"/>
                          </div>
                        </div>
                      </div>	
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Data Hold End Date</label>
                          <div class="col-sm-9">
                            <input class="form-control" placeholder="dd/mm/yyyy" name="dataHoldEndDt" />
                          </div>
                        </div>
                      </div>
                    </div>
                     <div class="row">
                     	<div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Comments</label>
                          <div class="col-sm-9">
                            <!--  <input type="text" class="form-control" />-->
                            <textarea class="form-control" id="exampleTextarea1" rows="4" name="dataHoldComments"></textarea>
                            <input type="hidden" class="form-control"  name="createdBy" value="gdpr_admin" />
                            <input type="hidden" class="form-control"  name="modifiedBy" value="gdpr_admin" />
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

