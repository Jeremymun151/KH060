<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="apiAddToCart" value="/api/cart"/>
<c:url var="comment" value="/api/comment"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Chi tiết sản phẩm</title>
</head>
<body>
<!-- Product Detail -->
<section class="sec-product-detail bg0 p-t-65 p-b-60">
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-lg-7 p-b-30">
				<div class="p-l-25 p-r-30 p-lr-0-lg">
					<div class="wrap-slick3 flex-sb flex-w">
						<div class="wrap-slick3-dots"></div>
						<div class="wrap-slick3-arrows flex-sb-m flex-w"></div>

						<div class="slick3 gallery-lb">
							<c:forEach var="item" items="${model.imageProducts}">
								<c:set var="imageProduct" value="/repository${item}"/>
								<div class="item-slick3" data-thumb="${imageProduct}">
									<div class="wrap-pic-w pos-relative">
										<img src="${imageProduct}" alt="IMG-PRODUCT">

										<a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="${imageProduct}">
											<i class="fa fa-expand"></i>
										</a>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-6 col-lg-5 p-b-30">
				<div class="p-r-50 p-t-5 p-lr-0-lg">
					<h4 class="mtext-105 cl2 js-name-detail p-b-14">
						${model.name}
					</h4>

					<span class="mtext-106 cl2">
						Giá gốc: ${model.priceDescription}
						<br/>
						Khuyến mãi: ${model.priceDiscount} VNĐ
					</span>

					<p class="stext-102 cl3 p-t-23">
						${model.shortDescription}
					</p>
					<input type="hidden" value="${model.id}" id="productId"/>
					<div class="p-t-33">
						<div class="flex-w flex-r-m p-b-10">
							<div class="size-204 flex-w flex-m respon6-next">
								<security:authorize access="isAuthenticated()">
									<input type="button" class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04"
										   value="Mua ngay" id="btnBuyNow"/>
								</security:authorize>
								<security:authorize access="isAnonymous()">
									<c:url var="loginURL" value="/login?isRequired"/>
									<a href="${loginURL}" class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04"
									   role="button">Mua ngay</a>
								</security:authorize>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="bor10 m-t-50 p-t-43 p-b-40">
			<!-- Tab01 -->
			<div class="tab01">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li class="nav-item p-b-10">
						<a class="nav-link active" data-toggle="tab" href="#description" role="tab">Mô tả</a>
					</li>

					<%--<li class="nav-item p-b-10">
						<a class="nav-link" data-toggle="tab" href="#information" role="tab">Thông số kĩ thuật</a>
					</li>--%>

					<li class="nav-item p-b-10">
						<a class="nav-link" data-toggle="tab" href="#reviews" role="tab">Xem review</a>
					</li>
				</ul>

				<!-- Tab panes -->
				<div class="tab-content p-t-43">
					<!-- - -->
					<div class="tab-pane fade show active" id="description" role="tabpanel">
						<div class="how-pos2 p-lr-15-md">
							<p class="stext-102 cl6">
								${model.content}
							</p>
						</div>
					</div>

					<!-- - -->
					<div class="tab-pane fade" id="information" role="tabpanel">
						<div class="row">
							<div class="col-sm-10 col-md-8 col-lg-6 m-lr-auto">
								${model.specification}
							</div>
						</div>
					</div>

					<!-- reviews -->
					<div class="tab-pane fade" id="reviews" role="tabpanel">
						<div class="row">
							<div class="col-sm-10 col-md-8 col-lg-6 m-lr-auto">
								<div class="p-b-30 m-lr-15-sm">
									<!-- Review -->
									<c:if test="${not empty comments}">
										<c:forEach var="item" items="${comments}">
											<div class="flex-w flex-t p-b-68">
												<div class="size-207">
													<div class="flex-w flex-sb-m p-b-17">
													<span class="mtext-107 cl2 p-r-20">
														${item.fullName}
													</span>
													</div>
													<p class="stext-102 cl6">
														${item.content}
													</p>
												</div>
											</div>
										</c:forEach>
									</c:if>

									<!-- Add review -->
									<form class="w-full">
										<h5 class="mtext-108 cl2 p-b-7">
											Bình luận sản phẩm
										</h5>

										<div class="row p-b-25">
											<div class="col-12 p-b-5">
												<textarea class="size-110 bor8 stext-102 cl2 p-lr-20 p-tb-10" id="review" name="review"></textarea>
											</div>
										</div>


										<security:authorize access="isAuthenticated()">
											<button class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10"
													onclick="comment(${model.id}, '${model.seoUrl}-${model.id}')" type="button">
												Bình luận
											</button>
										</security:authorize>
										<security:authorize access="isAnonymous()">
											<c:url var="review" value="/login?isRequired"/>
											<a href="${review}" class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10"
											   role="button">Bình luận</a>
										</security:authorize>
									</form>
								</div>
							</div>
						</div>
					</div>
					<%-- end review --%>
				</div>
			</div>
		</div>
	</div>
</section>

<script>

    $(document).ready(function () {

    });

    $('#btnBuyNow').click(function (event) {
        event.preventDefault();
        var productId = $('#productId').val();
        $.ajax({
            url: '${apiAddToCart}/'+productId,
            type: 'POST',
            success: function(res) {
                window.location.href = "<c:url value='/gio-hang'/>";
            },
            error: function(res) {
                console.log(res);
            }
        });
    });

    function comment(productId, url) {
        var data = {};
        data["productId"] = productId;
        data["content"] = $('#review').val();
        $.ajax({
            url: '${comment}',
            type: 'POST',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/"+url+"'/>";
            },
            error: function(res) {
                console.log(res);
            }
        });
    }
</script>
</body>
</html>