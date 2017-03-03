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
	<form action="<%=path%>/add" method="post">
		<table class="table table-hover">
			<tr>
				<th>mark：</th>
				<td><input type="text" name="mark" id="blogMark"
					placeholder="博客类型" class="form-control" /></td>
			</tr>

			<tr>
				<th>title：</th>
				<td><input type="text" name="title" id="blogTitle"
					placeholder="标题" class="form-control" /></td>
			</tr>
			<tr>
				<th>content：</th>
				<td>
				<input type="text" name="content" id="blogContent"
					placeholder=内容 class="form-control" />
					</td>
			</tr>
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
					<button type="button" class="btn btn-info pull-right" id="btnAdd">保存</button>
					<button type="button" class="btn btn-info pull-right"
						id="btnCancle">取消</button> 
				</td>
			</tr>
		</table>
		</form>
		<br />
	</div>

	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script>
		$().ready(function() {
			var ue = UE.getEditor('editor');
		});
		function btnSave() {
			$('#txtContent2').val(UE.getEditor('editor').getContent());
			$('#Content_txtContent2').val(UE.getEditor('editor').getContent());

		}
	</script>
</body>
</html>