<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="../CSS/pintuer.css">
    <link rel="stylesheet" href="../CSS/admin.css">
    <link rel="stylesheet" href="../CSS/datedropper.css">
    <script src="../JS/jQuery.js"></script>
    <script src="../JS/datedropper.min.js"></script>
    <script src="../JS/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span> 编辑一条视频进行发布</strong></div>
    <div class="body-content">

        <form method="post" class="form-x" enctype="multipart/form-data" action="vedio_addVedio.action">

            <div class="form-group">
                <div class="label">
                    <label>* 视频标题：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="" name="vedioTitle" data-validate="required:请输入视频标题"/>
                    <div class="tips"></div>
                </div>
            </div>

            <if condition="$iscid eq 1">
                <div class="form-group">
                    <div class="label">
                        <label>* 视频分类：</label>
                    </div>
                    <div class="field">
                        <select name="vedioTypePO.typeId" class="input w50">
                            <option value="0">请选择分类</option>
                            <option value="1">宠物搞笑</option>
                            <option value="2">宠物比赛</option>
                        </select>
                        <div class="tips"></div>
                    </div>
                </div>
            </if>

            <div class="form-group">
                <div class="label">
                    <label>视频关键词：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="vedioKey" value=""  placeholder="请用英文逗号间隔"/>
                </div>
            </div>


            <div class="form-group">
                <div class="label">
                    <label>* 视频内容：</label>
                </div>
                <div class="field">
                    <textarea name="vedioContent" class="input" style="height:300px; border:1px solid #ddd;"></textarea>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>上传视频：</label>
                </div>
                <div class="field">
                    <input type="text" id="fileurl" name="img" class="input" style="width:25%; float:left;" value=""
                           data-toggle="hover" data-place="right"/>
                    <input type="button" class="button bg-blue margin-left" onclick="myfile.click();"
                           value="选择视频">
                    <input type="file" name="file" id="myfile" onchange="fileurl.value=this.value"
                           style="display: none;float: left;">

                    <%--<input type="text" id="url1" name="img" class="input tips" style="width:25%; float:left;" value=""--%>
                           <%--data-toggle="hover" data-place="right" data-image=""/>--%>
                    <%--<input type="button" class="button bg-blue margin-left" id="image1" value="浏览上传"--%>
                           <%--style="float:left;">--%>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>视频作者：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="vedioAuthor" value=""/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>发布时间：</label>
                </div>
                <div class="field">
                    <input type="text" class="laydate-icon input w50" name="vedioDate" id="pickdate" placeholder="点击选取日期"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group btn-top">
                <div class="label">
                    <label style="color: red">* 请认真核对信息 </label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit"> 发布视频</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    $("#pickdate").dateDropper({
        animate: false,
        format: 'Y-m-d',
        maxYear: '2020'
    });
</script>

</body>
</html>