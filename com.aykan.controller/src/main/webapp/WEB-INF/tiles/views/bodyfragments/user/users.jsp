<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextUrl" value="${pageContext.request.contextPath}"></c:set>
<div class="container">

  <table class="table">
    <thead>
      <tr>
        <th><spring:message code="label.user.firstName"/></th>
        <th><spring:message code="label.user.lastName"/></th>
        <th><spring:message code="label.user.username"/></th>
        <th><spring:message code="label.user.email"/></th>
        <th><spring:message code="label.user.enabled"/></th>
        <th><spring:message code="label.user.credentialsNonExpired"/></th>
        <th><spring:message code="label.user.accountNonLocked"/></th>
         <th><spring:message code="label.user.accountNonExpired"/></th>
        <th><spring:message code="label.link.edit"/></th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
      <tr>
        <td>${user.firstName}</td>
        <td>${user.lastName}</td>
        <td>${user.username}</td>
        <td>${user.email}</td>
        <td>${user.enabled}</td>
        <td>${user.credentialsNonExpired}</td>
        <td>${user.accountNonLocked}</td>
        <td>${user.accountNonExpired}</td>
        <td><a href="${contextUrl}/users/${user.username}"><spring:message code="label.link.edit"/></a>
      </tr>
     </c:forEach>
    </tbody>
  </table>
</div>