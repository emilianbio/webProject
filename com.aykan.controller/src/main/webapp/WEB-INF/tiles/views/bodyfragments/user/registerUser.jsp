<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:url var="registerUrl" value="/register/registration"></c:url>
${error}
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-6">

					<form:form modelAttribute="userDto" class="form-horizontal" method="post" action="${registerUrl}">
						<fieldset>
							<legend><spring:message code="label.form.title"/></legend>

							<div class="form-group">
								<label class="col-md-4 control-label" for="name"><spring:message code="label.user.firstName"/></label>
								<div class="col-md-4">
									<form:input path="firstName" type="text" class="form-control input-md" />
									<form:errors path="firstName"></form:errors>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="surname"><spring:message code="label.user.lastName"/></label>
								<div class="col-md-4">
									<form:input path="lastName" type="text" class="form-control input-md" />
									<form:errors path="lastName"></form:errors>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label" for="username"><spring:message code="label.user.username"/></label>
								<div class="col-md-4">
									<form:input path="username" type="text" class="form-control input-md" />
									<form:errors path="username"></form:errors>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-4 control-label" for="email"><spring:message code="label.user.email"/></label>
								<div class="col-md-4">
									<form:input path="email" type="text" class="form-control input-md" />
									<form:errors path="email"></form:errors>
								</div>
							</div>
	
							<div class="form-group">
								<label class="col-md-4 control-label" for="password"><spring:message code="label.user.password"/></label>
								<div class="col-md-4">
									<form:input path="password" type="password" class="form-control input-md" />
									<form:errors path="password"></form:errors>
								</div>
							</div>							
		
							<div class="form-group">
								<label class="col-md-4 control-label" for="matchingPassword"><spring:message code="label.user.password"/></label>
								<div class="col-md-4">
									<form:input path="matchingPassword" type="password" class="form-control input-md" />
									<form:errors path="matchingPassword"></form:errors>
								</div>
							</div>
				
							<div class="form-group">
								<div class="col-md-4">
									<input type="submit" id="btnAdd" class="btn btn-primary" value="<spring:message code="save"/>"/>
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


