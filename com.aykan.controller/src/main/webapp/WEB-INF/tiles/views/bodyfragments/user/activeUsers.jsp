<!-- http://bootsnipp.com/similar/84M5?page=2 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
${param.message}
<c:url value="${pageContex.request.contextPath}/users" var="viewProfilee"></c:url>
<c:url value="users" var="viewProfile"></c:url>
<div class="container">
    <div class="row">
        <c:forEach items="${activeUsers}" var="user">
        <div class="col-xs-12 col-sm-6 col-md-6">
            <div class="well well-sm">
                <div class="row">
                    <div class="col-sm-6 col-md-8">
                        <h4>${user.username}</h4>
                   		<p>
                         	Login date : ${user.date}
                        </p>
        				 <a href="${viewProfile}/${user.username}" class="btn btn-info" role="button"><spring:message code="label.pages.view.profile"></spring:message></a>
                    </div>
                </div>
            </div>
        </div>
		</c:forEach>        
    </div>
</div>
