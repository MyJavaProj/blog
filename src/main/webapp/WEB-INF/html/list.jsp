<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入jstl -->
<%@include file="common/tag.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="common/head.jsp"%>
<title>博客列表页</title>
</head>
<body>
	<!-- 页面显示部分 -->
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading text-center">
				<h2>博客列表</h2>
				<button type="button" class="btn btn-info pull-right">新增</button>
			</div>
			<div class="panel-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>博客类型</th>
							<th>标题</th>
							<th>内容简介</th>
							<th>创建时间</th>
							<th>详情页</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sk" items="${list}">
							<tr>
								<td>${sk.mark}</td>
								<td>${sk.title}</td>
								<td>${sk.content}</td>
								<td>
									<fmt:formatDate value="${sk.createTime}" pattern="yyy-MM-dd HH:mm:ss"/>
								</td>
								<td>
									<a class="btn btn-info" href="${basePath}${sk.blogInfoId}/detail">link</a>
									<a class="btn btn-info" href="${basePath}delete/${sk.blogInfoId}" >delete</a>
									<a class="btn btn-info" href="${basePath}toUpdate/${sk.blogInfoId}" >update</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(".pull-right").click(function(){
			window.location.href = "${basePath}/toAdd";
		});
	</script>
</body>
</html>