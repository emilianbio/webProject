<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<spring:url value="/" var="home" htmlEscape="true"></spring:url>

<spring:url value="/employees" var="employees" htmlEscape="true"></spring:url> 


<spring:url value="/jobs" var="jobs" htmlEscape="true"></spring:url> 
<spring:url value="/users/activeUsers" var="activeUsers" htmlEscape="true"></spring:url>
<spring:url value="/users" var="users" htmlEscape="true"></spring:url> 

 <div class="container2">
  <table class="table table-hover">
    <thead>
      <tr>
        <th><h3><spring:message code="menu"/></h3></th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><a href="${home}"><spring:message code="label.pages.home.title"/></a></td>
	  </tr>
	  <sec:authorize access="hasRole('ROLE_READ_PRIVILEGE')">
	  
      <tr>
        <td><a href="${employees}"><spring:message code="label.pages.employee.list"/></a></td>
	  </tr>
	   <tr>
        <td><a href="${jobs}"><spring:message code="label.pages.job.list"/></a></td>
	  </tr>
	  </sec:authorize>
	  
	  <sec:authorize access="hasRole('ROLE_WRITE_PRIVILEGE')">
	  <tr>
        <td><a href="${users}"><spring:message code="label.pages.user.list"/></a></td>
	  </tr>
	   <tr>
        <td><a href="${activeUsers}"><spring:message code="label.pages.users.message"/></a></td>
	  </tr>
	  </sec:authorize>
   </tbody>
  </table>
</div>
 