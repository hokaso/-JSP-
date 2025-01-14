<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/Site.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/zy.all.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/amazeui.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/admin.css" />
</head>
<body>
	<div class="dvcontent">
		<div>
			<!--tab start-->
			<div class="tabs">

				<div class="hd">
					<ul>
						<li class="on"
							style="box-sizing: initial; -webkit-box-sizing: initial;">查看用户</li>
						<li class=""
							style="box-sizing: initial; -webkit-box-sizing: initial;">添加用户</li>
					</ul>
				</div>
				<div class="bd">
					<ul style="display: block; padding: 20px;">
						<li>
							<!--分页显示角色信息 start-->
							<div id="dv1">
								<table class="table" id="tbRecord">
									<thead>
										<tr>
											<th>编号</th>
											<th>姓名</th>
											<th>头像路径</th>
											<th>电话号码</th>
											<th>密码</th>
											<th>收货地址</th>
											<th>性别</th>
											<th>所属地</th>
											<!-- <th>个性签名</th> -->
											<th>修改</th>
											<th>删除</th>
										</tr>
									</thead>
									<c:forEach items="${customerList }" var="customer">
										<tbody>
											<tr>
												<td>${customer.u_id }</td>
												<td>${customer.name }</td>
												<td>${customer.headPicPath }</td>
												<td>${customer.phonenumber }</td>
												<td>${customer.password }</td>
												<td>${customer.order_consigneeAddress }</td>
												<td>${customer.gender }</td>
												<td>${customer.location }</td>
												<%-- <td>${customer.signature }</td> --%>
												<td class="edit"><a style="color: #ffffff"
													href="${pageContext.request.contextPath }/editCustomerPre.action?u_id=${customer.u_id }">
														<button>
															<i class="icon-edit bigger-120"></i> 编辑
														</button>
												</a></td>
												<td class="delete"><a style="color: #ffffff"
													href="javascript:showconfirm('${pageContext.request.contextPath }/deleteCustomer.action?u_id=${customer.u_id }')">
														<button>
															<i class="icon-trash bigger-120"></i> 删除
														</button>
												</a></td>
											</tr>
										</tbody>
									</c:forEach>
								</table>
							</div> <!--分页显示角色信息 end-->
						</li>
					</ul>
			
		<ul class="theme-popbod dform" style="display: none;">

			<div class="am-cf admin-main" style="padding-top: 0px;">


				<!-- content start -->

				<div class="am-cf admin-main" style="padding-top: 0px;">
					<!-- content start -->
					<div class="admin-content">
						<div class="admin-content-body">

							<div class="am-g">
								<div class="am-u-sm-12 am-u-md-4 am-u-md-push-8"></div>
								<div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4"
									style="padding-top: 30px;">
									<form class="am-form am-form-horizontal"
										action="addCustomer.action" method="post">
										<input type="hidden" name="u_id" value="" />

										<div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">
												姓名 / Name </label>
											<div class="am-u-sm-9">
												<input type="text" id="user-name" required
													placeholder="姓名 / Name" name="name" value="">
											</div>
										</div>
										<div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">
												头像路径 / headPicPath </label>
											<div class="am-u-sm-9">
												<input type="text" id="user-name" required
													placeholder="用户名 / username" name="headPicPath" value="">
											</div>
										</div>
										<div class="am-form-group">
											<label for="user-email" class="am-u-sm-3 am-form-label">
												联系电话 / phonenumber </label>
											<div class="am-u-sm-9">
												<input type="tel" id="user-phone" required
													placeholder="请输入联系电话" name="phonenumber" value="" /> <small>联系电话...</small>
											</div>
										</div>
										<div class="am-form-group">
											<label for="user-email" class="am-u-sm-3 am-form-label">
												密码 / password </label>
											<div class="am-u-sm-9">
												<input type="password" id="user-password" required
													placeholder="请输入籍贯" name="password" value="" /> <small>密码...</small>
											</div>
										</div>
										<div class="am-form-group">
											<label for="user-address" class="am-u-sm-3 am-form-label">
												收货地址 / address </label>
											<div class="am-u-sm-9">
												<input type="text" id="user-address" required
													placeholder="请输入籍贯" name="order_consigneeAddress" value="" /> <small>收货地址...</small>
											</div>
										</div>
										<div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">
												性别 / gender </label>
											<div class="am-u-sm-9" style="line-height: 30px;">
												<input type="radio" id="man" name="gender" value="男"
													checked="checked" /> <label for="man"> 男 </label>
												&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" id="woman"
													name="gender" value="女" /> <label for="woman"> 女 </label> <br />
												<small>性别...</small>
											</div>
										</div>
										<div class="am-form-group">
											<label for="user-email" class="am-u-sm-3 am-form-label">
												所在地 / location </label>
											<div class="am-u-sm-9">
												<input type="text" id="user-email" required
													placeholder="输入你的电子邮件 / Email" name="location" value="" />
												<small></small>
											</div>
										</div>

										<div class="am-form-group">
											<label for="user-intro" class="am-u-sm-3 am-form-label">
												个性签名/ signature </label>
											<div class="am-u-sm-9">
												<textarea class="" rows="5" id="user-intro" name="signature"
													placeholder="输入备注"></textarea>
												<small>250字以内...</small>
											</div>
										</div>
										<div class="am-form-group">
											<div class="am-u-sm-9 am-u-sm-push-3">
												<input type="submit" class="am-btn am-btn-success"
													value="添加用户" />
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>

					</div>
					<!-- content end -->
				</div>
				<!--添加角色 end--
						</ul>
					</div>
				</div>
				<!--tab end-->

			</div>


			<script
				src="${pageContext.request.contextPath }/js/jquery-1.7.2.min.js"
				type="text/javascript"></script>
			<script
				src="${pageContext.request.contextPath }/js/plugs/Jqueryplugs.js"
				type="text/javascript"></script>
			<script src="${pageContext.request.contextPath }/js/_layout.js"></script>
			<script
				src="${pageContext.request.contextPath }/js/plugs/jquery.SuperSlide.source.js"></script>
			<script>
				var num = 1;
				$(function() {

					$(".tabs").slide({
						trigger : "click"
					});

				});

				var btn_delete = function(id) {
					$
							.jq_Confirm({
								message : "您确定要删除吗?",
								btnOkClick : function() {
									$
											.ajax({
												type : "post",
												url : "/RawMaterialsType/DeleteRawMaterialsType",
												data : {
													id : id
												},
												success : function(data) {
													if (data > 0) {
														$
																.jq_Alert({
																	message : "删除成功",
																	btnOkClick : function() {
																		page1();
																	}
																});
													}
												}
											});
								}
							});
				}
				function showconfirm(url) {

					if (confirm("是否删除？")) {

						window.location.href = url;
					}

				}
			</script>
	</div>
</body>

</html>