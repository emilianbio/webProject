<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextUrl" value="${pageContext.request.contextPath}"></c:set>
<h3><spring:message code="label.pages.employee.view"/></h3>
	<table style="margin-left: 100px">
		<tr style="margin: 10px">
			<td><strong><spring:message code="label.employee.firstName"/> : </strong></td>
			<td> ${employee.firstName}</td>
		</tr>
		<tr>
			<td><strong><spring:message code="label.employee.lastName"/> : </strong></td>
			<td> ${employee.lastName}</td>
		</tr>
		<tr>
			<td><strong><spring:message code="label.employee.phoneNumber"/> : </strong></td>
			<td> ${employee.phoneNumber}</td>
		</tr>
		<tr>
			<td><strong><spring:message code="label.employee.email"/> : </strong></td>
			<td> ${employee.email}</td>
		</tr>
		<tr>
			<td><strong><spring:message code="label.employee.salary"/> : </strong></td>
			<td> ${employee.salary}</td>
		</tr>
		<tr>
			<td><strong><spring:message code="label.employee.hireDate"/> : </strong></td>
			<td> ${employee.hireDate}</td>
		</tr>
		<tr>
			<td><strong><spring:message code="label.employee.job"/> : </strong></td>
			<td><a href="${contextUrl}/jobs/${employee.job.jobId}"> ${employee.job.jobTitle}</a></td>
		</tr>
		<tr>
			<td><strong><spring:message code="label.employee.department"/> : </strong></td>
			<td><a href="${contextUrl}/departments/${employee.department.departmentId}"> ${employee.department.departmentName}</a></td>
		</tr>
	</table>
	<br>
	<br>
	<sec:authorize access="hasRole('ROLE_WRITE_PRIVILEGE')">
	<div style="margin-top: 25px;margin-left: 10px" >
		<a href="${employee.employeeId}/edit"><b><spring:message code="label.pages.employee.edit"/></b></a>
	</div>
	</sec:authorize>