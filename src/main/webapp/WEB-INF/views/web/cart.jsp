<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="updatePriceURL" value="/api/cart"/>
<c:url var="deleteCartURL" value="/api/cart"/>
<c:url var="checkout" value="/api/cart/checkout"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Giỏ hàng</title>
</head>
<body>
<!-- Shoping Cart -->
<form class="bg0 p-t-75 p-b-85">
    <div class="container">
        <div class="row">
            <div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
                <div class="m-l-25 m-r--38 m-lr-0-xl">
                    <div class="wrap-table-shopping-cart">
                        <table class="table-shopping-cart">
                            <tr class="table_head">
                                <th class="column-1">
                                    <%--<input type="checkbox" value=""/>--%>
                                </th>
                                <th class="column-2">Tên sản phẩm</th>
                                <th class="column-3">Giá</th>
                                <th class="column-4">Số lượng</th>
                                <th class="column-5">Tổng tiền</th>
                            </tr>

                            <c:if test="${not empty carts}">
                                <c:forEach var="item" items="${carts}">
                                    <tr class="table_row">
                                        <td class="column-1">
                                            <input type="checkbox" value="${item.id}"/>
                                        </td>
                                        <td class="column-2">${item.name}</td>
                                        <td class="column-3">
                                            <c:if test="${not empty item.priceDiscount}">
                                                ${item.priceDiscount} VNĐ
                                            </c:if>
                                            <c:if test="${empty item.priceDiscount}">
                                                ${item.price} VNĐ
                                            </c:if>
                                        </td>
                                        <td class="column-4">
                                            <div class="wrap-num-product flex-w m-l-auto m-r-0">
                                                <div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m" onclick="minusQuantity(${item.id}, this)">
                                                    <i class="fs-16 zmdi zmdi-minus"></i>
                                                </div>
                                                <c:if test="${not empty item.quantity}">
                                                    <input class="mtext-104 cl3 txt-center num-product"
                                                           type="number" name="quantity" id="quantity_${item.id}" value="${item.quantity}">
                                                </c:if>
                                                <c:if test="${empty item.quantity}">
                                                    <input class="mtext-104 cl3 txt-center num-product"
                                                           type="number" name="quantity" id="quantity_${item.id}" value="0">
                                                </c:if>

                                                <div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m" onclick="plusQuantity(${item.id}, this)">
                                                    <i class="fs-16 zmdi zmdi-plus"></i>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="column-5">${item.totalPrice} VNĐ</td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </table>
                    </div>
                    <div class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-40 p-lr-15-sm">
                        <div class="flex-w flex-m m-r-20 m-tb-5">
                            <div class="flex-c-m stext-101 cl2 size-118 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-5" id="deleteProductBtn">
                                Xóa sản phẩm
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
                <div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
                    <h4 class="mtext-109 cl2 p-b-30">
                        Thành tiền
                    </h4>

                    <div class="flex-w flex-t p-t-27 p-b-33">
                        <div class="size-208">
								<span class="mtext-101 cl2">
									Tổng:
								</span>
                        </div>

                        <div class="size-209 p-t-1">
								<span class="mtext-110 cl2">
									${totalPrice} VNĐ
								</span>
                        </div>
                    </div>

                    <%--<button class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer" id="btnCheckout">
                        Thanh toán
                    </button>--%>
                    <c:url var="checkoutURL" value="/checkout">
                        <c:param name="totalPrice" value="${totalPrice}"/>
                    </c:url>
                    <a href="${checkoutURL}" class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer"
                       role="button">Tiến hành thanh toán</a>
                </div>
            </div>
        </div>
    </div>
</form>

<script>

    $(document).ready(function () {

    });

    function plusQuantity(id, btn) {
        var currentRow = $(btn).closest("tr");
        var quantity = currentRow.find("input[type=number]").val();
        var data = {};
        data["id"] = id;
        data["quantity"] = parseInt(quantity) + 1;
        updatePrice(data);
    }
    function minusQuantity(id,btn) {
        var currentRow = $(btn).closest("tr");
        var quantity = currentRow.find("input[type=number]").val();
        var data = {};
        data["id"] = id;
        data["quantity"] = parseInt(quantity) - 1;
        updatePrice(data);
    }

    function updatePrice(data) {
        $.ajax({
            url: '${updatePriceURL}',
            type: 'PUT',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/gio-hang'/>";
            },
            error: function(res) {
                console.log(res);
            }
        });
    }

    $('#deleteProductBtn').click(function (e) {
        e.preventDefault();
        var dataArray = $('.table-shopping-cart').find('input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        deleteCart(dataArray);
    });

    function deleteCart(data) {
        $.ajax({
            url: '${deleteCartURL}',
            type: 'DELETE',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/gio-hang'/>";
            },
            error: function(res) {
                console.log(res);
            }
        });
    }
</script>
</body>
</html>
