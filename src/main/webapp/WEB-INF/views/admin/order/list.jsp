<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="updateStatusURL" value="/api/order"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Quản lý đơn hàng</title>
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
                <li class="active">Quản lý đơn hàng</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${messageResponse!=null}">
                        <div class="alert alert-block alert-${alert}">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>
                                ${messageResponse}
                        </div>
                    </c:if>
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th>Tên sản phẩm</th>
                                        <th>Số lượng</th>
                                        <th>Giá</th>
                                        <th>Tổng giá</th>
                                        <th>Người mua</th>
                                        <th>Trạng thái</th>
                                        <th>Ngày</th>
                                        <th>Thao tác</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="item" items="${orders}">
                                            <tr>
                                                <td>${item.productName}</td>
                                                <td>${item.quantity}</td>
                                                <td>${item.price}</td>
                                                <td>${item.totalPrice}</td>
                                                <td>${item.fullName}</td>
                                                <c:choose>
                                                    <c:when test="${item.status == 'waiting_confirm'}">
                                                        <td>Chờ xác nhận</td>
                                                    </c:when>
                                                    <c:when test="${item.status == 'waiting_delivery'}">
                                                        <td>Chờ vận chuyển</td>
                                                    </c:when>
                                                    <c:when test="${item.status == 'waiting_customer_confirm'}">
                                                        <td>Chờ khách hàng xác nhận</td>
                                                    </c:when>
                                                    <c:when test="${item.status == 'customer_confirm'}">
                                                        <td>khách hàng xác nhận</td>
                                                    </c:when>
                                                    <c:when test="${item.status == 'customer_cancel'}">
                                                        <td>Khách hàng hủy</td>
                                                    </c:when>
                                                </c:choose>
                                                <td>${item.createdDate}</td>
                                                <td>
                                                    <button type="button" class="btn btn-xs btn-primary btn-edit"
                                                            data-toggle="tooltip" title="Xác nhận đơn hàng"
                                                            onclick="updateStatus(${item.id},'waiting_delivery')">
                                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                    </button>
                                                    <button type="button" class="btn btn-xs btn-primary btn-edit"
                                                                data-toggle="tooltip" title="Xác nhận chuyển hàng"
                                                                onclick="updateStatus(${item.id},'waiting_customer_confirm')">
                                                            <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

    $(document).ready(function () {

    });
    function updateStatus(id,status) {
        var data = {};
        data["id"] = id;
        data["status"] = status;
        $.ajax({
            url: '${updateStatusURL}',
            type: 'PUT',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/admin/order/list'/>";
            },
            error: function(res) {
                console.log(res);
            }
        });
    }
</script>
</body>
</html>
