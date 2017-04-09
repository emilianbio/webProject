<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-6">
					<form:form modelAttribute="employee" class="form-horizontal" method="post" action="edit">
						<fieldset>

							<h3><spring:message code="label.pages.employee.edit"></spring:message></h3>

							<div class="form-group">
								<label class="col-md-4 control-label" for="firstName"><spring:message code="label.employee.firstName"></spring:message></label>
								<div class="col-md-4">
									<form:hidden path="employeeId" />
									<form:input path="firstName" type="text" class="form-control input-md" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="lastName"><spring:message code="label.employee.lastName"></spring:message></label>
								<div class="col-md-4">
									<form:input path="lastName" type="text" class="form-control input-md" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="email"><spring:message code="label.employee.email"></spring:message></label>
								<div class="col-md-4">
									<form:input path="email" type="text" class="form-control input-md" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-4 control-label" for="phoneNumber"><spring:message code="label.employee.phoneNumber"></spring:message></label>
								<div class="col-md-4">
									<form:input path="phoneNumber" type="text" class="form-control input-md" />
								</div>
							</div>							
							
							<div class="form-group">
								<label class="col-md-4 control-label" for="salary"><spring:message code="label.employee.salary"></spring:message></label>
								<div class="col-md-4">
									<form:input id="salary" path="salary" type="number"
										class="form-control input-md" />
								</div>
							</div>	
							
							<div class="form-group">
								<label class="control-label col-lg-2" for="departments"><spring:message code="label.employee.department"></spring:message></label>
								<div class="col-lg-10">
									<form:select path="department" class="form:input-large">
										<form:options items="${departments}" itemValue="departmentId" itemLabel="departmentName"/>
									</form:select>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-lg-2" for="jobs"><spring:message code="label.employee.job"></spring:message></label>
								<div class="col-lg-10">
									<form:select path="job" class="form:input-large"> 
										<form:options items="${jobs}" itemLabel="jobTitle" itemValue="jobId"/>
									</form:select>
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