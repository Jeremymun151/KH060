<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/api/admin/product"/>
<html>
<head>
    <title>${model.name}</title>
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
                <li class="active">Chỉnh sửa sản phẩm</li>
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
                            <label class="col-sm-3 control-label no-padding-right">Loại sản phẩm</label>
                            <div class="col-sm-9">
                                <form:select path="productCategoryCode" id="productCategoryCode">
                                    <form:option value="" label="--- Chọn loại sản phẩm ---"/>
                                    <form:options items="${productCategories}"/>
                                </form:select>
                            </div>
                        </div>
                        <%--<br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Thương hiệu</label>
                            <div class="col-sm-9">
                                <form:select path="brandCode" id="brandCode">
                                    <form:option value="" label="--- Chọn thương hiệu ---"/>
                                    <form:options items="${brands}"/>
                                </form:select>
                            </div>
                        </div>--%>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Khuyến mãi</label>
                            <div class="col-sm-9">
                                <form:select path="eventCode" id="eventId">
                                    <form:options items="${events}"/>
                                </form:select>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Tên sản phẩm</label>
                            <div class="col-sm-9">
                                <form:input path="name" id="name" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">SEO url</label>
                            <div class="col-sm-9">
                                <form:input path="seoUrl" id="seoUrl" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Mã sản phẩm</label>
                            <div class="col-sm-9">
                                <form:input path="code" id="code" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Nội dung</label>
                            <div class="col-sm-9" style="margin-bottom: 10px">
                                <form:textarea path="content" cols="80" rows="10" id="content" cssStyle="width: 943px; height: 72px"/>
                            </div>
                        </div>
                        <%--<br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Thông số kĩ thuật</label>
                            <div class="col-sm-9" style="margin-bottom: 10px">
                                <form:textarea path="specification" cols="80" rows="10" id="specification" cssStyle="width: 943px; height: 72px"/>
                            </div>
                        </div>--%>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Mô tả ngắn</label>
                            <div class="col-sm-9" style="margin-bottom: 10px">
                                <form:textarea path="shortDescription" cols="80" rows="10" id="shortDescription" cssStyle="width: 943px; height: 72px"/>
                            </div>
                        </div>
                        <%--<br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Tính năng</label>
                            <div class="col-sm-9" style="margin-bottom: 10px">
                                <div class="fg-line">
                                    <form:checkboxes path="featureArrays" items="${mapFeatures}"/>
                                </div>
                            </div>
                        </div>--%>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số lượng</label>
                            <div class="col-sm-9" style="margin-bottom: 10px">
                                <input type="number" class="form-control" name="quantity" id="quantity" value="${model.quantity}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Giá bán</label>
                            <div class="col-sm-9" style="margin-bottom: 10px">
                                <input type="number" class="form-control" name="price" id="price" value="${model.price}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Mô tả giá</label>
                            <div class="col-sm-9" style="margin-bottom: 10px">
                                <form:input path="priceDescription" id="priceDescription" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Hình ảnh sản phẩm</label>
                            <div class="col-sm-9">
                                <input type="file" name="file" class="textbox" multiple id="uploadImages"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-12">
                                <c:if test="${not empty model.id}">
                                    <div>
                                        <ul class="ace-thumbnails clearfix" id="image_preview">
                                            <c:if test="${not empty model.images}">
                                                <c:forEach var="item" items="${model.imageProducts}">
                                                    <li>
                                                        <c:set var="imageProduct" value="/repository${item}"/>
                                                        <a href="#" data-rel="colorbox">
                                                            <img src="${imageProduct}" width="150" height="150" alt="150x150"/>
                                                        </a>
                                                    </li>
                                                </c:forEach>
                                            </c:if>
                                        </ul>
                                    </div>
                                </c:if>
                                <c:if test="${empty model.id}">
                                    <div>
                                        <ul class="ace-thumbnails clearfix" id="image_preview">

                                        </ul>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật sản phẩm" id="btnAddOrUpdateProduct"/>
                                </c:if>
                                <c:if test="${empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm mới sản phẩm" id="btnAddOrUpdateProduct"/>
                                </c:if>
                            </div>
                        </div>
                        <form:hidden path="id" id="productId"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var content = '';
    var specification = '';
    var shortDescription = '';
    var base64Images = [];
    var nameImages = [];

    $(document).ready(function () {
        shortDescription = CKEDITOR.replace( 'shortDescription');
        //specification = CKEDITOR.replace( 'specification');
        content = CKEDITOR.replace( 'content', {
            filebrowserBrowseUrl : '/ckfinder/ckfinder.html',
            filebrowserImageBrowseUrl : '/ckfinder/ckfinder.html?type=Images',
            filebrowserFlashBrowseUrl : '/ckfinder/ckfinder.html?type=Flash',
            filebrowserUploadUrl : '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
            filebrowserImageUploadUrl : '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
            filebrowserFlashUploadUrl : '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
        });
        /*specification = CKEDITOR.replace( 'specification', {
            filebrowserBrowseUrl : '/ckfinder/ckfinder.html',
            filebrowserImageBrowseUrl : '/ckfinder/ckfinder.html?type=Images',
            filebrowserFlashBrowseUrl : '/ckfinder/ckfinder.html?type=Flash',
            filebrowserUploadUrl : '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
            filebrowserImageUploadUrl : '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
            filebrowserFlashUploadUrl : '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
        });*/
    });

    $('#btnAddOrUpdateProduct').click(function (event) {
        event.preventDefault();
        var data = {};
        var featureArrays = [];
        var formData = $('#formEdit').serializeArray();
        $.each(formData, function (i,v) {
            data["" + v.name + ""] = v.value;
        });
        //data["featureArrays"] = featureArrays;
        if (base64Images.length > 0) {
            data['base64Images'] = base64Images;
            data['nameImages'] = nameImages;
        }
        data["content"] = content.getData();
        //data["specification"] = specification.getData();
        data["shortDescription"] = shortDescription.getData();
        var id = $('#productId').val();
        if (id == "") {
            addProduct(data);
        } else {
            updateProduct(data);
        }
    });

    function addProduct(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'POST',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/admin/product/edit?id="+res.id+"&message=insert_success'/>";
            },
            error: function(res) {
                window.location.href = "<c:url value='/admin/product/edit?id="+res.id+"&message=error_system'/>";
            }
        });
    }

    function updateProduct(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'PUT',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/admin/product/edit?id="+res.id+"&message=update_success'/>";
            },
            error: function(res) {
                window.location.href = "<c:url value='/admin/product/edit?id="+res.id+"&message=error_system'/>";
            }
        });
    }

    $('#uploadImages').change(function (event) {
        $.each(event.target.files, function(index, file) {
            var reader = new FileReader();
            reader.onload = function(event) {
                nameImages.push(file.name);
                base64Images.push(event.target.result);
            };
            reader.readAsDataURL(file);
        });
        $('#image_preview').html("");
        var total_file = document.getElementById("uploadImages").files.length;
        for(var i=0; i < total_file; i++) {
            var images = "<li>";
            images += "<a href='#' data-rel='colorbox'>";
            images += "<img src='"+URL.createObjectURL(event.target.files[i])+"' width='150' height='150' alt='150x150'>";
            images += "</a>";
            images += "</li>";
            $('#image_preview').append(images);
        }
    });

</script>
</body>
</html>
