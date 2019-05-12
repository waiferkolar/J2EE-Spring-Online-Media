<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="container my-5">
    <h1 class="text-info text-center mb-3">Category Edit</h1>
    <div class="col-md-6 offset-md-3">

        <form:form method="post" modelAttribute="category" action="/cat/edit">
            <div class="form-group">
                <label for="name">Category Name</label>
                <form:input type="text" class="form-control" id="name" name="name" path="name" valu="${category.name}"/>
            </div>
            <form:input type="hidden" name="id" path="id" value="${category.id}"/>
            <button type="submit" class="btn btn-primary btn-sm">Update</button>
        </form:form>

    </div>

</div>