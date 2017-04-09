<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<h3>${message}</h3>
<c:if test="${param.error != null}">
	<h3>Invalid email</h3>
</c:if>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-6">
				
					<form class="form-horizontal" method="post" action="resendRegistrationToken">
						<fieldset>
							<legend><spring:message code="login"/></legend>

							<div class="form-group">
								<label class="col-md-4 control-label" for="email"><spring:message code="label.user.username"/></label>
								<div class="col-md-4">
									<input name="email" id="username" type="text" class="form-control input-md" />
								</div>
							</div>
	
							<div class="form-group">
								<div class="col-md-4">
									<input type="submit" id="btnAdd" class="btn btn-primary" value="<spring:message code="label.form.resendRegistrationToken"/>"/>
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


<h3><a style="float: left;" href="${pageContext.request.contextPath}/">Home Page</a></h3><br>