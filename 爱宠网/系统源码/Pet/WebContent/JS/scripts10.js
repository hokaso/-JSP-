		findReview();
			function findReview() {
				(function($) {
					
					$
							.ajax({
								type : "post",
								url : "${base}/cmt_findReview1.action?",
								data : "cmtvedioid=" + thisVedioId,
								dataType : "json",
								cache : false,
								success : function(data) {
									var mydata = eval(data);
									$('.reviewul li').remove();
									for ( var i in mydata) {
										$(".reviewul")
												.append(
														" <li><i class='fa fa-user'></i> "
																+ mydata[i].member.account
																+ " <i class='fa fa-comment'></i> "
																+ mydata[i].cmtContent
																+ " </li>");
									}
								},
								error : function(data) {
									alert("该文章暂没评论，请开始第一条评论吧！");
								}
							});
				})(jQuery);
			}
			function commentAjax() {
				//var userstr = $.cookie('user');
				var arrStr = document.cookie.split(";");
			    for(var i = 0;i < arrStr.length;i ++){
			        var temp = arrStr[i].split("=");
			        if(temp[0] =='user') {
			        	var userstr= unescape(temp[1]);
			        	break;
			        }
			        	
			   } 
				var userobject;
			
				if (userstr === undefined || userstr === 'undefined') {
					alert("请先登录，再评论！");
				} else {
					(function($) {
						userobject = eval(userstr);
						var cmtContent = $("#comment").val();
						$.ajax({
							type : "post",
							url : "${base}/subcmt_addReview1.action?",
							data : "vedioId=" + thisVedioId + "&user1Id="
									+ userobject[0].id + "&cmtContent1="
									+ cmtContent,
							dataType : "json",
							cache : false,
							success : function(data) {
								findReview();
							},
							error : function(data) {
								$('.message').html("连接服务器失败，请稍后评论！");
							}
						});
					})(jQuery);
				}
			}