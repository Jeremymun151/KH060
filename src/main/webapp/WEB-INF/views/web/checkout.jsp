<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="checkout" value="/api/cart/checkout"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thanh toán</title>
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
                                <th class="column-2">Tên sản phẩm</th>
                                <th class="column-3">Giá</th>
                                <th class="column-4">Số lượng</th>
                                <th class="column-5">Tổng tiền</th>
                            </tr>

                            <c:if test="${not empty carts}">
                                <c:forEach var="item" items="${carts}">
                                    <tr class="table_row">
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
                                            ${item.quantity}
                                        </td>
                                        <td class="column-5">${item.totalPrice} VNĐ</td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </table>
                    </div>
                </div>
            </div>

            <div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
                <div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
                    <h4 class="mtext-109 cl2 p-b-30">
                        Chi tiết
                    </h4>
                    <div class="flex-w flex-t p-t-27 p-b-33">
                        <div class="size-208">
								<span class="mtext-101 cl2">
									Thành tiền:
								</span>
                        </div>

                        <div class="size-209 p-t-1">
								<span class="mtext-110 cl2">
									${totalPrice} VNĐ
								</span>
                        </div>
                    </div>
                    <div class="flex-w flex-t bor12 p-t-15 p-b-30">
                            <div class="size-208 w-full-ssm">
								<span class="stext-110 cl2">
                                    Họ tên:
								</span>
                            </div>

                            <div class="size-209 p-r-18 p-r-0-sm w-full-ssm">
                                <p class="stext-111 cl6 p-t-2">
                                    ${userInfo.fullName}
                                </p>
                            </div>
                            <div class="size-208 w-full-ssm">
                                <span class="stext-110 cl2">
                                    SĐT:
                                </span>
                            </div>

                            <div class="size-209 p-r-18 p-r-0-sm w-full-ssm">
                                <p class="stext-111 cl6 p-t-2">
                                    ${userInfo.phone}
                                </p>
                            </div>
                            <div class="size-208 w-full-ssm">
                                <span class="stext-110 cl2">
                                    Địa chỉ:
                                </span>
                            </div>

                            <div class="size-209 p-r-18 p-r-0-sm w-full-ssm">
                                <p class="stext-111 cl6 p-t-2">
                                    ${userInfo.address}
                                </p>
                            </div>
                            <div class="size-208 w-full-ssm">
                                <span class="stext-110 cl2">
                                    Hình thức:
                                </span>
                            </div>

                            <div class="size-209 p-r-18 p-r-0-sm w-full-ssm">
                                <p class="stext-111 cl6 p-t-2">
                                    1. Thanh toán khi nhân hàng
                                </p>
                                <p class="stext-111 cl6 p-t-2">
                                    2. Chuyển khoản online qua tài khoản ngân hàng như sau
                                </p>
                                <p class="stext-111 cl6 p-t-2">
                                    Tài khoản: 123456789 - Nguyễn Văn A - Hồ Chí Minh
                                </p>
                            </div>
                    </div>
                    <button class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer" id="btnCheckout">
                        Thanh toán
                    </button>
                </div>
            </div>
        </div>
    </div>
</form>

<script>

    $(document).ready(function () {

    });

    $('#btnCheckout').click(function (e) {
        e.preventDefault();
        $.ajax({
            url: '${checkout}',
            type: 'POST',
            success: function(res) {
                window.location.href = "<c:url value='/don-hang'/>";
            },
            error: function(res) {
                console.log(res);
            }
        });
    });
</script>
</body>
</html>
