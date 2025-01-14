<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
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
<script src="${pageContext.request.contextPath }/js/jquery-1.7.2.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath }/js/plugs/Jqueryplugs.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/_layout.js"></script>
<script
	src="${pageContext.request.contextPath }/js/plugs/jquery.SuperSlide.source.js"></script>
<script type="text/javascript">	
	function showconfirm(url) {

		if (confirm("是否删除？")) {

			window.location.href = url;
		}

	}
	$(function() {

		$(".tabs").slide({
			trigger : "click"
		});
		
		$("#btn").click(function(){
			$("#guige").append("<br/>规格名称：<input type='text' name='specs_attrs'/>"+
					" 库存：<input type='text' name='specs_stock'/>"+
					" 价格：<input type='text' name='specs_price'/>"+
					"<br/>--------------------------------------------------------------------");
		});
	});
	
</script>
</head>
<body>
	<div class="dvcontent">
		<div>
			<!--tab start-->
			<div class="tabs">

				<div class="hd">
					<ul>
						<li class="on"
							style="box-sizing: initial; -webkit-box-sizing: initial;">查看商品</li>
						<li class=""
							style="box-sizing: initial; -webkit-box-sizing: initial;">添加商品</li>
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
											<th>商品编号</th>
											<th>图片</th>
											<th>商品名称</th>
											<th>价格范围</th>
											<th>状态</th>
											<th>分类</th>
											<!-- <th>商品描述</th> -->
											<th>总售量</th>
											<th>详情</th>
											<!-- <th>个性签名</th> -->
											<th>修改</th>
											<th>删除</th>
										</tr>
									</thead>
									<c:forEach items="${goodsList }" var="good">
										<tbody>
											<tr>
												<td>${good.goods_id }</td>
												<td>
												<img alt="" border="0" width="54" align="top"
												 src="${pageContext.request.contextPath }/${good.goods_coverPic }">
											
												</td>
												<td>${good.goods_name }</td>
												<td>${good.goods_price }</td>
												<td>${good.goods_state }</td>
												<td>${categoryArr[good.goods_category] }</td>
												<%-- <td>${good.goods_describe }</td> --%>
												<td>${good.goods_sales_volume }</td>
												<td><a href="${pageContext.request.contextPath }/findGoodsDetail.action?goods_id=${good.goods_id }"><button style="color:#000000">查看详情</button></a></td>
												<td class="edit"><a style="color: #ffffff"
													href="${pageContext.request.contextPath }/editGoodsPre.action?goods_id=${good.goods_id }">
														<button>
															<i class="icon-edit bigger-120"></i> 编辑
														</button>
												</a></td>
												<td class="delete"><a style="color: #ffffff"
													href="javascript:showconfirm('${pageContext.request.contextPath }/deleteGoods.action?goods_id=${good.goods_id }')">
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
													action="${pageContext.request.contextPath }/addGoods.action" enctype="multipart/form-data" method="post">                           
													
													<div class="am-form-group">
														<label for="user-name" class="am-u-sm-3 am-form-label">
															商品名称  </label>
														<div class="am-u-sm-9">
															<input type="text" id="goods_name" required
																placeholder="商品名称" name="goods_name" value="">
														</div>
													</div>
													<div class="am-form-group">
														<label for="user-name" class="am-u-sm-3 am-form-label">
															商品图片 </label>
														<div class="am-u-sm-9">
															<input type="file" id="user-name" required
																 name="file" value="">
														</div>
													</div>
													<div class="am-form-group">
														<label for="user-email" class="am-u-sm-3 am-form-label">
															商品状态  </label>
														<div class="am-u-sm-9">
															<select name="goods_state">
																<option value="">未选择</option>
																<option value="0">0</option>
																<option value="1">1</option>
															</select>
														</div>
													</div>
													<div class="am-form-group">
														<label for="user-email" class="am-u-sm-3 am-form-label">
															商品分类  </label>
														<div class="am-u-sm-9">
															
															<select name="goods_category">
																<option value="">未选择</option>
																<c:forEach items="${categoryArr }" var="category" begin="1" varStatus="status">
																	<option value="${status.index }">${category }</option>
																</c:forEach>	
															</select>
														</div>
													</div>
													<div class="am-form-group">
														<label for="user-name" class="am-u-sm-3 am-form-label">
															商品描述</label>
														<div class="am-u-sm-9" id="guige">
															<textarea rows="5" cols="30" name="goods_describe"></textarea>
														</div>
													</div>
													<div class="am-form-group">
														<label for="user-name" class="am-u-sm-3 am-form-label">
															商品规格 </label>
														<div class="am-u-sm-9" id="guige">
															
														</div>
													</div>
													<div class="am-form-group">
														<div class="am-u-sm-9 am-u-sm-push-3">
															<input type="submit" class="am-btn am-btn-success"
																value="添加商品" />
														</div>
													</div>
												</form>
												<button id="btn">添加规格</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</ul>
				</div>
		
			</div>
		</div>
	</div>

</body>
</html>