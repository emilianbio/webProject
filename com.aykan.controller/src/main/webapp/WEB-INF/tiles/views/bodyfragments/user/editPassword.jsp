<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:url value="/users/savePassword" var="updateUser"></c:url>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-6">
					<form:form modelAttribute="passwordDto" class="form-horizontal" method="post" action="${updateUser}">
						<fieldset>
	
							<h3><spring:message code="label.pages.user.edit"></spring:message></h3>

							<div class="form-group">
								<label class="col-md-4 control-label" for="oldPassword"><spring:message code="label.user.oldPassword"></spring:message></label>
								<div class="col-md-4">
									<form:input path="oldPassword" type="password" class="form-control input-md" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-4 control-label" for="newPassword"><spring:message code="label.user.newPassword"></spring:message></label>
								<div class="col-md-4">
									<form:input path="newPassword" type="password" class="form-control input-md" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label" for="matchingOldPassword"><spring:message code="label.user.matchingNewPassword"></spring:message></label>
								<div class="col-md-4">
									<form:input path="matchingNewPassword" type="password" class="form-control input-md" />
								</div>
							</div>

							<div class="form-group">
								<div class="col-md-4">
									<input type="submit" id="btnAdd" class="btn btn-primary" value="<spring:message code="label.form.update"></spring:message>" />
								</div>
							</div>
				
						</fieldset>
					</form:form>
				</div>
				<div class="col-md-6"></div>
			</div>
		</div>
	</div>
</div>