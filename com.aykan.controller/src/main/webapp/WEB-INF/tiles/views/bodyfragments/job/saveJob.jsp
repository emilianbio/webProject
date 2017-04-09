<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="buttonName" value="Save"></c:set>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-6">

					<form:form modelAttribute="job" class="form-horizontal" method="post" action="jobs">
						<fieldset>
							<legend><spring:message code="label.pages.job.add"/></legend>

							<div class="form-group">
								<label class="col-md-4 control-label" for="name"><spring:message code="label.job.jobTitle"/></label>
								<div class="col-md-4">
									<form:hidden path="jobId" />
									<form:input path="jobTitle" type="text" class="form-control input-md" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="surname"><spring:message code="label.job.minSalary"/></label>
								<div class="col-md-4">
									<form:input path="minSalary" type="text" class="form-control input-md" />
								</div>
							</div>
	
							<div class="form-group">
								<label class="col-md-4 control-label" for="email"><spring:message code="label.job.maxSalary"/></label>
								<div class="col-md-4">
									<form:input path="maxSalary" type="text" class="form-control input-md" />
								</div>
							</div>

							<div class="form-group">
								<div class="col-md-4">
									<input type="submit" class="btn btn-primary" value="<spring:message code="label.form.save"/>" />
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