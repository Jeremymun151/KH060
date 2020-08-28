<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.util.SecurityUtils" %>
<!DOCTYPE html>
<!-- Header -->
<header class="header-v4">
	<!-- Header desktop -->
	<div class="container-menu-desktop">
		<!-- Topbar -->
		<div class="top-bar">
			<div class="content-topbar flex-sb-m h-full container">
				<div class="left-top-bar">
				</div>

				<div class="right-top-bar flex-w h-full">
					<security:authorize access="isAuthenticated()">
						<c:set var="id" value="<%=SecurityUtils.getPrincipal().getId()%>"/>
						<a href="<c:url value='/profile?id=${id}'/>" class="flex-c-m trans-04 p-lr-25">
							Wellcome : <%=SecurityUtils.getPrincipal().getFullName()%>
						</a>
						<a href="<c:url value='/gio-hang'/>" class="flex-c-m trans-04 p-lr-25">
							Giỏ hàng
						</a>
						<a href="<c:url value='/don-hang'/>" class="flex-c-m trans-04 p-lr-25">
							Đơn hàng
						</a>
						<a href="<c:url value='/logout'/>" class="flex-c-m trans-04 p-lr-25">
							Thoát
						</a>
					</security:authorize>
					<security:authorize access="isAnonymous()">
						<a href="<c:url value='/login'/>" class="flex-c-m trans-04 p-lr-25">
							Đăng nhập
						</a>
						<a href="<c:url value='/profile'/>" class="flex-c-m trans-04 p-lr-25">
							Đăng ký
						</a>
					</security:authorize>
				</div>
			</div>
		</div>

		<div class="wrap-menu-desktop how-shadow1">
			<nav class="limiter-menu-desktop container">
				
				<!-- Logo desktop -->		
				<a href="<c:url value="/trang-chu?productCategoryCode=ghe-sofa"/>" class="logo">
					<img src="/template/web/images/online-shop.jpg" alt="IMG-LOGO">
				</a>

				<!-- Menu desktop -->
				<div class="menu-desktop">
					<ul class="main-menu">
						<li>
							<a href="<c:url value="/trang-chu?productCategoryCode=bong-den-trang-tri"/>">Trang chủ</a>
						</li>
						<c:forEach var="item" items="${categories}">
							<li>
								<a href="<c:url value="/trang-chu?productCategoryCode=${item.code}"/>">${item.name}</a>
							</li>
						</c:forEach>
						<li>
							<a href="#">Liên hệ</a>
						</li>
					</ul>
				</div>
			</nav>
		</div>
	</div>

	<!-- Header Mobile -->
	<div class="wrap-header-mobile">
		<!-- Logo moblie -->		
		<div class="logo-mobile">
			<a href="index.html"><img src="images/icons/logo-01.png" alt="IMG-LOGO"></a>
		</div>

		<!-- Icon header -->
		<div class="wrap-icon-header flex-w flex-r-m m-r-15">
			<a href="<c:url value="/gio-hang"/>">
				<div class="icon-header-item cl2 hov-cl1 trans-04 p-r-11 p-l-10 icon-header-noti js-show-cart" data-notify="3">
					<i class="zmdi zmdi-shopping-cart"></i>
				</div>
			</a>
		</div>

		<!-- Button show menu -->
		<div class="btn-show-menu-mobile hamburger hamburger--squeeze">
			<span class="hamburger-box">
				<span class="hamburger-inner"></span>
			</span>
		</div>
	</div>


	<!-- Menu Mobile -->
	<div class="menu-mobile">
		<ul class="topbar-mobile">
			<li>
				<div class="left-top-bar">
				</div>
			</li>

			<li>
				<div class="right-top-bar flex-w h-full">
					<security:authorize access="isAuthenticated()">
						<a href="#" class="flex-c-m trans-04 p-lr-25">
							Wellcome : <%=SecurityUtils.getPrincipal().getFullName()%>
						</a>

						<a href="<c:url value='/logout'/>" class="flex-c-m trans-04 p-lr-25">
							Thoát
						</a>
					</security:authorize>
					<security:authorize access="isAnonymous()">
						<a href="<c:url value='/login'/>" class="flex-c-m trans-04 p-lr-25">
							Đăng nhập
						</a>
						<a href="#" class="flex-c-m trans-04 p-lr-25">
							Đăng ký
						</a>
					</security:authorize>
				</div>
			</li>
		</ul>

		<ul class="main-menu-m">
			<li>
				<a href="">Trang chủ</a>
				<span class="arrow-main-menu-m">
					<i class="fa fa-angle-right" aria-hidden="true"></i>
				</span>
			</li>
			<li>
				<a href="">Liên hệ</a>
			</li>
		</ul>
	</div>

	<!-- Modal Search -->
	<div class="modal-search-header flex-c-m trans-04 js-hide-modal-search">
		<div class="container-search-header">
			<button class="flex-c-m btn-hide-modal-search trans-04 js-hide-modal-search">
				<img src="images/icons/icon-close2.png" alt="CLOSE">
			</button>

			<form class="wrap-search-header flex-w p-l-15">
				<button class="flex-c-m trans-04">
					<i class="zmdi zmdi-search"></i>
				</button>
				<input class="plh3" type="text" name="search" placeholder="Search...">
			</form>
		</div>
	</div>
</header>