<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextUrl" value="${pageContext.request.contextPath}"></c:set>

<h3 style="margin-left: 80px"><spring:message code="label.pages.job.view"/></h3>
	<table style="margin-left: 100px">
		<tr>
			<td><strong><spring:message code="label.job.jobTitle"/> : </strong></td>
			<td>${job.jobTitle}</td>
		</tr>
		<tr>
			<td><strong><spring:message code="label.job.minSalary"/> : </strong></td>
			<td>${job.minSalary}</td>
		</tr>
		<tr>
			<td><strong><spring:message code="label.job.maxSalary"/> : </strong></td>
			<td>${job.maxSalary}</td>
		</tr>
	</table>
	<br>
	<sec:authorize access="hasRole('ROLE_WRITE_PRIVILEGE')">
	<a style="margin: 80px" href="${contextUrl}/jobs/${job.jobId}/edit"><b><spring:message code="label.pages.job.edit"/></b></a>	
	</sec:authorize>
	<br>	
	<h4 style="margin-left: 80px"><spring:message code="label.pages.employee.list"/></h4>

<div class="container">
  <table class="table">
    <thead>
      <tr>
        <th><spring:message code="label.employee.firstName"/></th>
        <th><spring:message code="label.employee.lastName"/></th>
        <th><spring:message code="label.employee.email"/></th>
        <th><spring:message code="label.employee.phoneNumber"/></th>
        <th><spring:message code="label.employee.hireDate"/></th>
        <th><spring:message code="label.employee.salary"/></th>
        <sec:authorize access="hasRole('ROLE_WRITE_PRIVILEGE')">
        <th><spring:message code="label.form.detail"/></th>
        </sec:authorize>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${employees}" var="employee">
      <tr>
        <td>${employee.firstName}</td>
        <td>${employee.lastName}</td>
        <td>${employee.email}</td>
        <td>${employee.phoneNumber}</td>
        <td>${employee.hireDate}</td>
        <td>${employee.salary}</td>
        <sec:authorize access="hasRole('ROLE_WRITE_PRIVILEGE')">
        <td><a href="${contextUrl}/employees/${employee.employeeId}"><spring:message code="label.form.detail"/></a></td>
        </sec:authorize>
      </tr>
     </c:forEach>
    </tbody>
  </table>
</div>