<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="?language=tr" var="languageTr"></spring:url>
<spring:url value="?language=en" var="languageEn"></spring:url>
<c:url var="logoutUrl" value="logout"></c:url>
<c:url var="loginUrl" value="login"></c:url>
<c:set var="contextUrl" value="${pageContext.request.contextPath}"></c:set>
<section>
	<div class="jumbotron">
		<div class="container">
		<p><a href="${languageTr}"><spring:message code="label.form.loginTurkish"/></a> | 
		   <a href="${languageEn}"><spring:message code="label.form.loginEnglish"/></a></p>

		<c:choose>
			<c:when test="${pageContext.request.userPrincipal.name != null}">
				<br>
				<p><spring:message code="label.pages.home.message"/> ${pageContext.request.userPrincipal.name}</p><br>
				<h3><a href="${contextUrl}/users/${pageContext.request.userPrincipal.name}"><strong><spring:message code="label.user.profile"/></strong></a> </h3><br>
	
				<h3><a href="${contextUrl}/logout"><strong><spring:message code="label.pages.logout"/></strong></a> </h3><br>
				
<%-- 				<form action="${logoutUrl}" method="post"> --%>
<%-- 					<input type="submit" value="<spring:message code="label.pages.logout"/>"> <input type="hidden" --%>
<%-- 						name="${_csrf.parameterName}" value="${_csrf.token}"> --%>
<!-- 				</form> -->
			</c:when>
			<c:otherwise>
			<p><a href="register?registerUser"><spring:message code="label.user.register"/></a></p>
			<br>
			<p><a href="${contextUrl}/login"><spring:message code="label.user.login"/></a></p>
			<br>
			</c:otherwise>
		</c:choose>
		</div>
	</div>
</section>

		