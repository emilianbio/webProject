<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<c:set var="contextUrl" value="${pageContext.request.contextPath}"></c:set>
<div class="container">
<sec:authorize access="hasRole('ROLE_WRITE_PRIVILEGE')">
  <p><a href="${contextUrl}/employees?saveEmployee"><b><spring:message code="label.pages.employee.add"/></b></a></p>
 </sec:authorize>   
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
        <td><a href="${contextUrl}/employees/${employee.employeeId}"><spring:message code="label.form.detail"/></a>
        <!-- wwww.aykan.com/employees/1212 -->
        </sec:authorize>
      </tr>
     </c:forEach>
    </tbody>
  </table>
</div>