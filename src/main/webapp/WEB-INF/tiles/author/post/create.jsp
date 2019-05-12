<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container my-5">

    <h1 class="text-info text-center">Create New Post</h1>

    <form:form method="post" modelAttribute="post" action="/post/create" enctype="multipart/form-data">

        <div class="form-group">
            <label for="title">Title</label>
            <form:input type="text" class="form-control" id="title" path="title" name="title"/>
        </div>
        <div class="form-group">
            <label for="cat_id">Category</label>
            <form:select class="form-control" id="cat_id" path="cat_id" name="cat_id">
                <c:forEach var="cat" items="${cats}">
                    <option value="${cat.id}">${cat.name}</option>
                </c:forEach>

            </form:select>
        </div>
        <div class="form-group">
            <label for="file">Post Image</label>
            <form:input type="file" class="form-control-file" id="file" path="file" name="file"/>
        </div>
        <div class="form-group">
            <label for="content">Content</label>
            <form:textarea class="form-control" id="content" rows="3" path="content" name="content"></form:textarea>
        </div>
        <button type="submit" class="btn btn-primary btn-sm">Create</button>
    </form:form>

</div>