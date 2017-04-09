<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextUrl" value="${pageContext.request.contextPath}"></c:set>
<div class="container">
  <h2><spring:message code="label.pages.job.list"/></h2>
  <br>
  <sec:authorize access="hasRole('ROLE_WRITE_PRIVILEGE')">
  <p><a href="jobs?saveJob"><b><spring:message code="addJob"/></b></a></p>
  </sec:authorize>
  <table class="table">
    <thead>
      <tr>
        <th><spring:message code="label.job.jobTitle"/></th>
        <th><spring:message code="label.job.minSalary"/></th>
        <th><spring:message code="label.job.maxSalary"/></th>
        <sec:authorize access="hasRole('ROLE_WRITE_PRIVILEGE')">
        <th><spring:message code="label.form.detail"/></th>
        </sec:authorize>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${jobs}" var="job">
      <tr>
        <td>${job.jobTitle}</td>
        <td>${job.minSalary}</td>
        <td>${job.maxSalary}</td>
        <sec:authorize access="hasRole('ROLE_WRITE_PRIVILEGE')">
        <th><a href="jobs/${job.jobId}"><spring:message code="label.form.detail"/></a></th>
        </sec:authorize>
      </tr>
     </c:forEach>
    </tbody>
  </table>
</div>