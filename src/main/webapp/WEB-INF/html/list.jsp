<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入jstl -->
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="common/head.jsp"%>
<title>博客列表页</title>

<!-- <style type="text/css"
	src="resources/bootstrap/css/bootstrap.min.css"></style>
 -->
<%-- <link rel="stylesheet" type="text/css"
	href="${path}/resources/bootstrap/css/b1.css"></link> --%>
<link rel="stylesheet" type="text/css"
	href="resources/bootstrap/css/bootstrap-responsive.min.css"></link>

<link rel="stylesheet" type="text/css"
	href="resources/bootstrap/css/docs.css"></link>
</head>
<body>
	<!-- 页面显示部分 -->
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading text-center">
				<h2>博客列表</h2>
				<button type="button" class="btn btn-info pull-right">新增</button>
				<button type="button" class="btn btn-info pull-right btn_split">分页</button>
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
								<td><fmt:formatDate value="${sk.createTime}"
										pattern="yyy-MM-dd HH:mm:ss" /></td>
								<td><a class="btn btn-info"
									href="${basePath}${sk.blogInfoId}/detail">link</a> <a
									class="btn btn-info" href="${basePath}delete/${sk.blogInfoId}">delete</a>
									<a class="btn btn-info"
									href="${basePath}toUpdate/${sk.blogInfoId}">update</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<div class="pagination pargination-large" id="div_page">
					<!-- <ul>
						<li class="disabled"><a href="#">&laquo;</a></li>
						<li class="active"><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">》</a></li>
					</ul> -->
				</div>
			</div>
		</div>
	</div>

	<!-- <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->

	<script src="resources/js/jquery1.9.1.js"></script>
	<script type="text/javascript"
		src="resources/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(".pull-right").click(function() {
			window.location.href = "${basePath}/toAdd";
		});
		$(".btn_split").click(function(){
			/*  alert("=======:" + "${list}");*/
			
			var pageIndex = 1;
			window.location.href = "${basePath}"+ pageIndex +"/splitList";
		});
		// currentNum:第几页    allNum：数据总数   Count4page:每页显示多少条数据
        function PageSplit(currentNum, allNum, Count4page) {

            if (currentNum > allNum) {
                return "";
            }
            
            if (currentNum <= 0) {
                return "";
            }
            var endPart = " ";
            if (currentNum == allNum) {
                endPart += "<li class='disabled'><a href='#' pageindex = '0'>&rsaquo;</a></li>";
                endPart += "<li class='disabled'><a href='#' pageindex = '0'>&raquo;</a></li>";
                // endPart += "</ul>";
            } else if (currentNum * Count4page > allNum) {
                endPart += "<li class='disabled'><a href='#' pageindex = '0'>&rsaquo;</a></li>";
                endPart += "<li class='disabled'><a href='#' pageindex = '0'>&raquo;</a></li>";
            }
            else
            {
                var lastNum = allNum / Count4page;
                lastNum = (lastNum == 0) ? lastNum : lastNum+1;
                endPart += "<li><a href='#" + (currentNum + 1) + "' pageindex = '" + (currentNum + 1) + "'>&rsaquo;</a></li>";
                endPart += "<li><a href='#" + lastNum + "' pageindex = '" + lastNum + "'>&raquo;</a></li>";
                //   endPart += "</ul>";
            }
            //首页 上一页  1,2,3,4 下一页 末页 
            var middleNum = " ";
            if (currentNum - 3 > 0) {
                middleNum += "<li><a href='#link" + (currentNum - 3) + "'  pageindex='" + (currentNum - 3) + "'> " + (currentNum - 3) + "</a></li>";
            }
            if (currentNum - 2 > 0) {
                middleNum += "<li><a href='#link" + (currentNum - 2) + "'  pageindex='" + (currentNum - 2) + "'> " + (currentNum - 2) + "</a></li>";
            }
            if (currentNum - 1 > 0) {
                middleNum += "<li><a href='#link" + (currentNum - 1) + "'  pageindex='" + (currentNum - 1) + "'> " + (currentNum - 1) + "</a></li>";
            }
            if (currentNum > 0) {
                middleNum += "<li><a href='#'  pageindex='" + (currentNum) + "'>" + currentNum + "</a></li>";
            }

            var startNum = " ";
            if (currentNum <= 1) {
                //  startNum += "<ul>";
                startNum += "<li class='disabled'><a href='#' pageindex = '0'>&laquo;</a></li>";
                startNum += "<li class='disabled'><a href='#' pageindex = '0'>&lsaquo;</a></li>";
            } else {
                //   startNum += "<ul>";
                startNum += "<li><a href='#" + 1 + "' pageindex = '" + 1 + "'>&laquo;</a></li>";
                startNum += "<li><a href='#" + (currentNum - 1) + "' pageindex = '" + (currentNum - 1) + "'>&lsaquo;</a></li>";
            }
            return startNum + middleNum + endPart;

        }
	</script>
</body>
</html>