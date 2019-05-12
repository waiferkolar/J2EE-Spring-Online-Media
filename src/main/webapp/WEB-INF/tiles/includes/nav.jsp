<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-fluid bg-dark">
    <nav class="container navbar navbar-expand-lg navbar-light">
        <a class="navbar-brand" href="/">
            <img src="/assets/imgs/coder.png" alt="" width="30" height="30" class="rounded">
            <span class="text-white ml-3">Online Media</span>

        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">

                <c:forEach var="cat" items="${cats}">
                    <li class="nav-item active">
                        <a class="nav-link text-white" href="/cat/${cat.id}">${cat.name}</a>
                    </li>
                </c:forEach>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Member
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <c:if test="${!pageContext.request.userPrincipal.authenticated}">
                            <a class="dropdown-item" href="/login">login</a>
                            <a class="dropdown-item" href="#">Register</a>
                        </c:if>
                        <c:url var="logoutUrl" value="/logout"></c:url>
                        <c:if test="${pageContext.request.userPrincipal.authenticated}">
                            <form action="${logoutUrl}" method="post">
                                <input type="submit" value="logout" class="btn btn-default ml-2"/>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                        </c:if>

                    </div>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-white" href="#" id="ndd" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Member
                    </a>
                    <div class="dropdown-menu" aria-labelledby="ndd">
                        <a class="dropdown-item" href="/contact">Contact</a>
                        <a class="dropdown-item" href="/cat/all">Show All Cats</a>
                        <a class="dropdown-item" href="/admin/user/all">Show All User</a>
                    </div>
                </li>

            </ul>
        </div>
    </nav>
</div>