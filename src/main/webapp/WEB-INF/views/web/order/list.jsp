<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="updateStatusURL" value="/api/order"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đơn hàng</title>
</head>
<body>
<!-- Shoping Cart -->
<form class="bg0 p-t-75 p-b-85">
    <div class="container">
        <div class="row">
            <div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
                <div class="m-l-25 m-r--38 m-lr-0-xl">
                    <div class="wrap-table-shopping-cart">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Tên sản phẩm</th>
                                <th>Số lượng</th>
                                <th>Giá</th>
                                <th>Tổng giá</th>
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
                                                data-toggle="tooltip" title="Xác nhận mua hàng"
                                                onclick="updateStatus(${item.id},'customer_confirm')">
                                            <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                        </button>
                                        <button type="button" class="btn btn-xs btn-primary btn-edit"
                                                data-toggle="tooltip" title="Hủy hàng"
                                                onclick="updateStatus(${item.id},'customer_cancel')">
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
</form>

<script>

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
                window.location.href = "<c:url value='/don-hang'/>";
            },
            error: function(res) {
                console.log(res);
            }
        });
    }

</script>
</body>
</html>
