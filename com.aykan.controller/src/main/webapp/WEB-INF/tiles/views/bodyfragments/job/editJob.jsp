<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-6">
					<form:form modelAttribute="job" class="form-horizontal" method="post" action="edit">
						<fieldset>

							<legend><spring:message code="label.pages.job.edit"/></legend>
							
							<div class="form-group">
								<label class="col-md-4 control-label" for="jobTitle"><spring:message code="label.job.jobTitle"/></label>
								<div class="col-md-4">
									<form:hidden path="jobId" />
									<form:input path="jobTitle" type="text" class="form-control input-md" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="minSalary"><spring:message code="label.job.minSalary"/></label>
								<div class="col-md-4">
									<form:input path="minSalary" type="text" class="form-control input-md" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-4 control-label" for="maxSalary"><spring:message code="label.job.maxSalary"/></label>
								<div class="col-md-4">
									<form:input path="maxSalary" type="text" class="form-control input-md" />
								</div>
							</div>
		
							<div class="form-group">
								<div class="col-md-4">
									<input type="submit" id="buttonUpdate" class="btn btn-primary" value="<spring:message code="label.form.update"/>" />
								</div>
								<div class="col-md-4">
									<a href="delete" data-original-title="Delete" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><spring:message code="label.form.delete"/></a>
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