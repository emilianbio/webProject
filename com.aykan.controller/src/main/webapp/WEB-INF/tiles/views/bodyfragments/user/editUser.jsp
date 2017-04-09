<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:url value="/users/${user.username}/editProfile" var="updateUser"></c:url>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-6">
					<form:form modelAttribute="user" class="form-horizontal"
						method="post" action="${updateUser}">
						<fieldset>

							<h3>
								<spring:message code="label.pages.user.edit"></spring:message>
							</h3>

							<div class="form-group">
								<label class="col-md-4 control-label" for="firstName"><spring:message
										code="label.user.firstName"></spring:message></label>
								<div class="col-md-4">
									<form:input path="id" type="hidden" />
									<form:input path="firstName" type="text"
										class="form-control input-md" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="lastName"><spring:message
										code="label.user.lastName"></spring:message></label>
								<div class="col-md-4">
									<form:input path="lastName" type="text"
										class="form-control input-md" />

								</div>
							</div>

							<form:input path="password" type="hidden" />
							
							<div class="form-group">
								<label class="col-md-4 control-label" for="username"><spring:message
										code="label.user.username"></spring:message></label>
								<div class="col-md-4">
									<form:input path="username" type="text"
										class="form-control input-md" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="email"><spring:message
										code="label.user.email"></spring:message></label>
								<div class="col-md-4">
									<form:input path="email" type="text"
										class="form-control input-md" />
								</div>
							</div>
							<c:choose>
								<c:when test="${principalName == user.username}">
									<div class="form-group" style="display: none;">
										<label class="control-label col-lg-2" for="roles"><spring:message
												code="label.user.role" /></label>
										<div class="col-lg-10">
											<form:select path="role" class="form:input-large">
												<form:options items="${roles}" itemValue="id"
													itemLabel="name" />
											</form:select>
										</div>
									</div>
									<form:input path="enabled" type="hidden" />
									<form:input path="credentialsNonExpired" type="hidden" />
									<form:input path="accountNonLocked" type="hidden" />
									<form:input path="accountNonExpired" type="hidden" />
								</c:when>
							</c:choose>


							<sec:authorize access="hasRole('ROLE_WRITE_PRIVILEGE')">
								<div class="form-group">
									<label class="control-label col-lg-2" for="roles"><spring:message
											code="label.user.role" /></label>
									<div class="col-lg-10">
										<form:select path="role" class="form:input-large">
											<form:options items="${roles}" itemValue="id"
												itemLabel="name" />
										</form:select>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2" for=enabled><spring:message
											code="label.user.enabled"></spring:message></label>
									<div class="col-lg-10">
										<form:select path="enabled" class="form:input-large">
											<form:option value="true">true</form:option>
											<form:option value="false">false</form:option>
										</form:select>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2" for=credentialsNonExpired><spring:message
											code="label.user.credentialsNonExpired"></spring:message></label>
									<div class="col-lg-10">
										<form:select path="credentialsNonExpired"
											class="form:input-large">
											<form:option value="true">true</form:option>
											<form:option value="false">false</form:option>
										</form:select>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2" for=accountNonLocked><spring:message
											code="label.user.accountNonLocked"></spring:message></label>
									<div class="col-lg-10">
										<form:select path="accountNonLocked" class="form:input-large">
											<form:option value="true">true</form:option>
											<form:option value="false">false</form:option>
										</form:select>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2" for=accountNonLocked><spring:message
											code="label.user.accountNonExpired"></spring:message></label>
									<div class="col-lg-10">
										<form:select path="accountNonExpired" class="form:input-large">
											<form:option value="true">true</form:option>
											<form:option value="false">false</form:option>
										</form:select>
									</div>
								</div>
							</sec:authorize>
							<div class="form-group">
								<div class="col-md-4">
									<input type="submit" id="btnAdd" class="btn btn-primary"
										value="<spring:message code="label.form.update"></spring:message>" />
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