<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入jstl -->
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="common/head.jsp"%>
<title>博客列表页</title>

<style type="text/css">
div {
	width: 100%;
}
</style>

</head>
<body>
	<!-- 页面显示部分 -->
	<div class="container">
		<form action="<%=path%>/update" method="post">
			<table class="table table-hover">
				<tr>
					<th>mark：</th>
					<td><input type="text" name="mark" id="blogMark"
						placeholder="博客类型" class="form-control" value="${model.mark}"/></td>
				</tr>

				<tr>
					<th>title：</th>
					<td><input type="text" name="title" id="blogTitle"
						placeholder="标题" class="form-control" value="${model.title }"/></td>
				</tr>
				<tr>
					<th>content：</th>
					<td><input type="text" name="content" id="blogContent"
						placeholder=内容 class="form-control" value="${model.content}"/></td>
						
				</tr>
				
				
				<!-- ================================================================================================== -->
				<tr>
					<th>blogInfoId：</th>
					<td><input type="text" name="blogInfoId" id="blogInfoId"
						placeholder=内容 class="form-control" value="${model.blogInfoId}"/></td>
						
				</tr>
				<tr>
					<th>createTime：</th>
					<td><input type="text" name="createTime" id="createTime"
						placeholder=内容 class="form-control" value="${model.createTime}"/></td>
						
				</tr>
				<tr>
					<th>typeNumber：</th>
					<td><input type="text" name="typeNumber" id="typeNumber"
						placeholder=内容 class="form-control" value="${model.typeNumber}"/></td>
						
				</tr>
					<%-- <input type="hidden" value="${model.blogInfoId}">
		<input type="hidden" value="${model.createTime}">
		<input type="hidden" value="${model.typeNumber}"> --%>
				<!-- <tr>
				<td colspan="8">
					<div class="details">
						<script id="editor" type="text/plain"></script>
					</div> 
					<input type="hidden" id="txtContent2">
				</td>
			</tr> -->
				<tr>
					<td class="tdbg" align="center" valign="bottom">
						<button type="submit" class="btn btn-info pull-right" id="btnAdd"
							onclick="btnSave">保存</button>
						<button type="button" class="btn btn-info pull-right"
							id="btnCancle" onclick="btnCancle">取消</button>
					</td>
				</tr>
			</table>
		</form>
		<br />
	</div>

	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script>
		function btnSave() {
			$("form").submit(function(e) {
				alert("Submitted");
			});
		}

		function btnCancle() {
			console.log("cancle");
		}
	</script>
</body>
</html>