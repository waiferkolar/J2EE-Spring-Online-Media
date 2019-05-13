<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container my-5">

    <table class="table table-bordered">
        <thead>
        <tr class="bg-dark text-white">
            <th scope="col">id</th>
            <th scope="col">name</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="cat" items="${cats}">
            <tr>
                <td>${cat.id}</td>
                <td>${cat.name}</td>
                <td>
                    <a href="/cat/edit/${cat.id}" class="btn btn-warning btn-sm"><i class="fa fa-edit"></i></a>
                    <a href="/cat/delete/${cat.id}" class="btn btn-danger btn-sm"><i class="fa fa-trash"></i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>