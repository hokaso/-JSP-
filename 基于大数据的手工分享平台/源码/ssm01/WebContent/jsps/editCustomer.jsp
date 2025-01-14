<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <script src="${pageContext.request.contextPath }/js/jquery-1.7.2.min.js" type="text/javascript"></script>
              <script src="${pageContext.request.contextPath }/js/plugs/Jqueryplugs.js" type="text/javascript"></script>
              <script src="${pageContext.request.contextPath }/js/_layout.js"></script>
             <script src="${pageContext.request.contextPath }/js/plugs/jquery.SuperSlide.source.js"></script>
            <link rel="stylesheet" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/Site.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/zy.all.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/amazeui.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css" />
</head>
<body>
<div class="am-cf admin-main" style="padding-top: 0px;">
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				
				<div class="am-g">
					<div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
						
					</div>
					<div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4"
						style="padding-top: 30px;">
						<form class="am-form am-form-horizontal"
							action="editCustomer.action" method="post">
							<input type="hidden" name="u_id" value="${customer.u_id }"/>
	
							<div class="am-form-group">
								<label for="user-name" class="am-u-sm-3 am-form-label">
									姓名 / Name </label>
								<div class="am-u-sm-9">
									<input type="text" id="user-name" required
										placeholder="姓名 / Name" name="name" value="${customer.name }">
								</div>
							</div>
							<div class="am-form-group">
								<label for="user-name" class="am-u-sm-3 am-form-label">
									头像路径 / headPicPath </label>
								<div class="am-u-sm-9">
									<input type="text" id="user-name" required
										placeholder="用户名 / username" name="headPicPath" value="${customer.headPicPath }">
								</div>
							</div>
							<div class="am-form-group">
								<label for="user-email" class="am-u-sm-3 am-form-label">
									联系电话 / phonenumber </label>
								<div class="am-u-sm-9">
									<input type="tel" id="user-phone" required
										placeholder="请输入联系电话" name="phonenumber" value="${customer.phonenumber }"/> <small>联系电话...</small>
								</div>
							</div>
							<div class="am-form-group">
								<label for="user-email" class="am-u-sm-3 am-form-label">
									密码 / password </label>
								<div class="am-u-sm-9">
									<input type="password" id="user-password" required placeholder="请输入籍贯"
										name="password" value="${customer.password }"/> <small>密码...</small>
								</div>
							</div>
							<div class="am-form-group">
								<label for="user-address" class="am-u-sm-3 am-form-label">
									收货地址 / address </label>
								<div class="am-u-sm-9">
									<input type="text" id="user-address" required placeholder="请输入籍贯"
										name="order_consigneeAddress" value="${customer.order_consigneeAddress }"/> <small>收货地址...</small>
								</div>
							</div>
							<div class="am-form-group">
								<label for="user-name" class="am-u-sm-3 am-form-label">
									性别 / gender </label>
								<div class="am-u-sm-9" style="line-height: 30px;">
									<input type="radio" id="man" name="gender" value="男"
										${customer.gender eq '男' ? "checked=checked ":""}/> <label for="man">
										男 </label> &nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" id="woman"
										name="gender" value="女" ${customer.gender eq '女'? "checked=checked ":""} />
									<label for="woman"> 女 </label> <br /> <small>性别...</small>
								</div>
							</div>
							<div class="am-form-group">
								<label for="user-email" class="am-u-sm-3 am-form-label">
									所在地 / location </label>
								<div class="am-u-sm-9">
									<input type="text" id="user-email" required
										placeholder="输入你的电子邮件 / Email" name="location" value="${customer.location }"/> <small></small>
								</div>
							</div>
						
							<div class="am-form-group">
								<label for="user-intro" class="am-u-sm-3 am-form-label">
									个性签名/ signature </label>
								<div class="am-u-sm-9">
									<textarea class="" rows="5" id="user-intro" name="signature"	
										placeholder="输入备注">${customer.signature }</textarea>
									<small>250字以内...</small>
								</div>
							</div>
							<div class="am-form-group">
								<div class="am-u-sm-9 am-u-sm-push-3">
									<input type="submit" class="am-btn am-btn-success" value="修改用户" />
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
</body>
</html>