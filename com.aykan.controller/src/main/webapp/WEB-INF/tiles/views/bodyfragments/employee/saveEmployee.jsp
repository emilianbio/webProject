<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-6">

					<form:form modelAttribute="employee" class="form-horizontal" method="post" action="employees">
						<fieldset>
							<legend><spring:message code="label.pages.employee.add"/></legend>

							<div class="form-group">
								<label class="col-md-4 control-label" for="name"><spring:message code="label.employee.firstName"/></label>
								<div class="col-md-4">
									<!-- update isleminde kullanmak icin hidden olarak id ! -->
									<form:hidden path="employeeId" />
									<form:input path="firstName" type="text" class="form-control input-md" />
									<form:errors path="firstName"></form:errors>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="surname"><spring:message code="label.employee.lastName"/></label>
								<div class="col-md-4">
									<form:input path="lastName" type="text" class="form-control input-md" />
									<form:errors path="lastName"></form:errors>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-4 control-label" for="email"><spring:message code="label.employee.email"/></label>
								<div class="col-md-4">
									<form:input path="email" type="text" class="form-control input-md" />
									<form:errors path="email"></form:errors>
								</div>
							</div>
	
							<div class="form-group">
								<label class="col-md-4 control-label" for="phoneNumber"><spring:message code="label.employee.phoneNumber"/></label>
								<div class="col-md-4">
									<form:input path="phoneNumber" type="text" class="form-control input-md" />
									<form:errors path="phoneNumber"></form:errors>
								</div>
							</div>							
		
							<div class="form-group">
								<label class="col-md-4 control-label" for="salary"><spring:message code="label.employee.salary"/></label>
								<div class="col-md-4">
									<form:input id="salary" path="salary" type="number"
										class="form-control input-md" />
										<form:errors path="salary"></form:errors>
								</div>
							</div>
												  
							<div class="form-group">
								<label class="control-label col-lg-2" for="departments"><spring:message code="label.employee.department"/></label>
								<div class="col-lg-10">
									<form:select path="department" class="form:input-large">
										<form:options items="${departments}" itemValue="departmentId" itemLabel="departmentName"/>
									</form:select>
									<form:errors path="department"></form:errors>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-lg-2" for="jobs"><spring:message code="label.employee.job"/></label>
								<div class="col-lg-10">
									<form:select path="job" class="form:input-large"> 
										<form:options items="${jobs}" itemLabel="jobTitle" itemValue="jobId"/>
									</form:select>
									<form:errors path="job"></form:errors>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-md-4">
									<input type="submit" id="btnAdd" class="btn btn-primary" value="<spring:message code="label.form.save"/>"/>
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


