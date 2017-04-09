<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- http://bootsnipp.com/user/snippets/emRmv -->
<div class="container">
	<div class="row">
		<div class="col-md-5  toppad  pull-right col-md-offset-3 ">
			<a href="${user.username}/editProfile"><spring:message code="label.user.edit.profile"></spring:message></a> 
			<a href="${user.username}/sendPasswordToken"><spring:message code="label.user.edit.password"></spring:message></a>
			<a href="/logout"><spring:message code="label.pages.logout"></spring:message></a>
			<br>
			<p class=" text-info">May 05,2014,03:00 pm</p>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">

			<div class="panel panel-info">
				<div class="panel-heading">

					<h3 class="panel-title">
						${message} <br> ${user.firstName} ${user.lastName}

					</h3>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-3 col-lg-3 " align="center">
							<img alt="User Picture" src="" class="img-circle img-responsive">
						</div>

						<div class=" col-md-9 col-lg-9 ">
							<table class="table table-user-information">
								<tbody>
									<tr>
										<td>User Role :</td>
										<td>${user.role.name}</td>
									</tr>
									<tr>
										<td>Hire date:</td>
										<td>06/23/2013</td>
									</tr>
									<tr>
										<td>Date of Birth</td>
										<td>01/24/1988</td>
									</tr>
									<tr>
										<td>Gender</td>
										<td>Male</td>
									</tr>
									<tr>
										<td>Status :</td>
										<td>${loginStatus}</td>
									</tr>
									<tr>
										<td>Email</td>
										<td><a href="mailto:${user.email}">${user.email}</a></td>
									</tr>
									<tr>
										<td>Phone Number</td>
										<td>123-4567-890(Landline)<br>
										<br>555-4567-890(Mobile)
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				
			<sec:authorize access="hasRole('ROLE_WRITE_PRIVILEGE')">
				<!-- Admin için kalsın -->
				<div class="panel-footer">
					<a data-original-title="Broadcast Message" data-toggle="tooltip"
						type="button" class="btn btn-sm btn-primary"><i
						class="glyphicon glyphicon-envelope"></i></a> <span class="pull-right">
						
						<a href="${user.username}/editProfile" data-original-title="Edit this user"
							data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i
							class="glyphicon glyphicon-edit"></i></a> 
						<a data-original-title="Remove this user" data-toggle="tooltip"
							type="button" class="btn btn-sm btn-danger"><i
							class="glyphicon glyphicon-remove"></i></a>
					</span>
				</div>
			</sec:authorize>
			</div>
		</div>
	</div>
</div>