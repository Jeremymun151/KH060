<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/api/profile"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Thông tin tài khoản</title>
</head>
<body>
<div class="container">
    <div class="login-form">
        <div class="main-div">
            <c:if test="${not empty messageResponse}">
                <div class="alert alert-block alert-${alert}">
                    <button type="button" class="close" data-dismiss="alert">
                        <i class="ace-icon fa fa-times"></i>
                    </button>
                        ${messageResponse}
                </div>
            </c:if>
            <form:form id="formEdit" commandName="model">
                <div class="form-group">
                    <input type="text" class="form-control" id="userName" name="userName" placeholder="Tên đăng nhập" value="${model.userName}">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Mật khẩu" value="">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="fullName" name="fullName" placeholder="Họ tên" value="${model.fullName}">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="phone" name="phone" placeholder="SĐT" value="${model.phone}">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="email" name="email" placeholder="Email" value="${model.email}">
                </div>
                <div class="form-group">
                    <textarea class="form-control" id="address" name="address">${model.email}</textarea>
                </div>
                <c:if test="${not empty model.id}">
                    <input type="button" class="btn btn-primary" value="Cập nhật thông tin" id="btnAddOrUpdateAccount"/>
                </c:if>
                <c:if test="${empty model.id}">
                    <input type="button" class="btn btn-primary" value="Thêm tài khoản" id="btnAddOrUpdateAccount"/>
                </c:if>
                <form:hidden path="id" id="userId"/>
             </form:form>
        </div>
    </div>
</div>
<script>

    $(document).ready(function () {
    });

    $('#btnAddOrUpdateAccount').click(function (event) {
        event.preventDefault();
        var data = {};
        var formData = $('#formEdit').serializeArray();
        $.each(formData, function (i,v) {
            data["" + v.name + ""] = v.value;
        });
        var id = $('#userId').val();
        if (id == "") {
            addUser(data);
        } else {
            updateUser(data);
        }
    });

    function addUser(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'POST',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/trang-chu'/>";
            },
            error: function(res) {
                window.location.href = "<c:url value='/trang-chu'/>";
            }
        });
    }

    function updateUser(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'PUT',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/profile?id="+res.id+"&message=update_success'/>";
            },
            error: function(res) {
                window.location.href = "<c:url value='/profile?id="+res.id+"&message=error_system'/>";
            }
        });
    }
</script>
</body>
</html>