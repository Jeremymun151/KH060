<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/api/admin/event"/>
<html>
<head>
    <title>Chi tiết khuyến mãi</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Chỉnh sửa khuyến mãi</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
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
                            <label class="col-sm-3 control-label no-padding-right">Tên khuyến mãi</label>
                            <div class="col-sm-9">
                                <form:input path="name" id="name" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Mã khuyến mãi</label>
                            <div class="col-sm-9">
                                <form:input path="code" id="code" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Phần trăm</label>
                            <div class="col-sm-9">
                                <input type="number" class="form-control" name="percent" id="percent" value="${model.percent}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật khuyến mãi" id="btnAddOrUpdateEvent"/>
                                </c:if>
                                <c:if test="${empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm mới khuyến mãi" id="btnAddOrUpdateEvent"/>
                                </c:if>
                            </div>
                        </div>
                        <form:hidden path="id" id="eventId"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

    $(document).ready(function () {
    });

    $('#btnAddOrUpdateEvent').click(function (event) {
        event.preventDefault();
        var data = {};
        var formData = $('#formEdit').serializeArray();
        $.each(formData, function (i,v) {
            data["" + v.name + ""] = v.value;
        });
        var id = $('#eventId').val();
        if (id == "") {
            addEvent(data);
        } else {
            updateEvent(data);
        }
    });

    function addEvent(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'POST',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/admin/event/edit?id="+res.id+"&message=insert_success'/>";
            },
            error: function(res) {
                window.location.href = "<c:url value='/admin/event/edit?id="+res.id+"&message=error_system'/>";
            }
        });
    }

    function updateEvent(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'PUT',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/admin/event/edit?id="+res.id+"&message=update_success'/>";
            },
            error: function(res) {
                window.location.href = "<c:url value='/admin/event/edit?id="+res.id+"&message=error_system'/>";
            }
        });
    }
</script>
</body>
</html>
