<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:url var="loginUrl" value="${pageContext.request.contextPath}/login"></c:url>

<c:if test="${not empty param['error']}"> 
    <h3><c:out value="${SPRING_SECURITY_LAST_EXCEPTION}" /></h3>
</c:if> 

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-6">
				
					<form class="form-horizontal" method="post" action="login">
						<fieldset>
							<legend><spring:message code="label.form.loginTitle"/></legend>

							<div class="form-group">
								<label class="col-md-4 control-label" for="username"><spring:message code="label.user.username"/></label>
								<div class="col-md-4">
									<input name="email" id="username" type="text" class="form-control input-md" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label" for="password"><spring:message code="label.user.password"/></label>
								<div class="col-md-4">
									<input name="password" id="password" type="password" class="form-control input-md" />
								</div>
							</div>
			
							<div class="form-group">
								<div class="col-md-4">
									<input type="submit" id="btnAdd" class="btn btn-primary" value="<spring:message code="label.form.loginTitle"/>"/>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
				<div class="col-md-6"></div>
			</div>
		</div>
	</div>
</div>


