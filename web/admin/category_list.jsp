<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品分类</title>
<link href="<%=basePath %>admin/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>user/js/jquery-3.3.1.js"></script>

<script type="text/javascript">

// old write 
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});

</script>
</head>
<body>
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    	<li><a href="#">分类管理</a></li>
	    </ul>
    </div>
    <div class="rightinfo">
	    <div class="tools">
	    	<ul class="toolbar">
		        <li style="cursor: pointer;" onclick="add_category()"><span><img src="<%=basePath %>admin/images/t01.png"  /></span>添加类别</li>
		        <li style="cursor: pointer;" onclick="batchDelete()"><span><img src="<%=basePath %>admin/images/t03.png" /></span>批量删除</li>
	        </ul>
	    </div>
	    <table class="tablelist">
	    	<thead>
		    	<tr>
			        <th><input id="inp" name="" type="checkbox" value="" /></th>
			        <th>序号<i class="sort"><img src="<%=basePath %>admin/images/px.gif" /></i></th>
			        <th>类别名称</th>
			        <th>启用状态</th>
			        <th>排序序号</th>
			        <th>创建时间</th>
			        <th>描述</th>
			        <th>操作</th>
		        </tr>
	        </thead>
	        <tbody>
				<c:forEach items="${lists}" var="category" varStatus="status">
					<tr align="center">
						<td><input type="checkbox" class="inps" value="${category.gid}"></td>
						<td>${status.index + 1}</td>
						<td>${category.name}</td>
						<td>
							<c:if test="${category.state == 1}">
								启用
							</c:if>
							<c:if test="${category.state == 0}">
								禁用
							</c:if>
						</td>
						<td>${category.order_number}</td>
						<td>${category.create_time}</td>
						<td>${category.description}</td>
						<td>
							<a href="categoryServlet?mark=queryCategoryByGid&gid=${category.gid}">修改</a>
							||
							<a href="categoryServlet?mark=deleteCategory&gid=${category.gid}">删除</a>
						</td>

					</tr>
				</c:forEach>
	        </tbody>
	    </table>
	    <div class="pagin">
	    	<div class="message">共<i class="blue">${pt.totalSize}</i>条记录，当前显示第&nbsp;<i class="blue">${pt.currentPage}</i>页</div>
	        <ul class="paginList">
		         <li class="paginItem"><a href="categoryServlet?mark=pageQuery">首页</a></li>
		         <li class="paginItem"><a href="categoryServlet?mark=pageQuery&currentPage=${pt.prePage}">上一页</a></li>
		         <li class="paginItem"><a href="categoryServlet?mark=pageQuery&currentPage=${pt.nextPage}">下一页</a></li>
		         <li class="paginItem"><a href="categoryServlet?mark=pageQuery&currentPage=${pt.totalPage}">尾页</a></li>
	        </ul>
	    </div>
	    <div class="tip">
	    	<div class="tiptop"><span>提示信息</span><a></a></div>
		      	<div class="tipinfo">
			        <span><img src="images/ticon.png" /></span>
			        <div class="tipright">
				        <p>是否确认对信息的修改 ？</p>
				        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
			        </div>
	    		</div>
	        <div class="tipbtn">
		        <input name="" type="button"  class="sure" value="确定" />&nbsp;
		        <input name="" type="button"  class="cancel" value="取消" />
	        </div>
	    </div>
    </div>
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
<script>
	//添加类别的单击事件
	function add_category() {
		//跳转到category_add.jsp
		location.href="category_add.jsp";
	}

	//实现全选和全不选
	$("#inp").click(function() {
		//获取自己的选中状态
		var bo = $(this).prop("checked");
		$(".inps").prop("checked",bo);
	})
	//批量删除
	function batchDelete() {
		//确认框
		if(confirm("确定要删除吗?")) {
			var gids = "";
			//遍历下面所有的复选框
			$(".inps").each(function () {
				//判断是否选中
				if ($(this).prop("checked")) {
					//拿到主键gid
					var gid = $(this).val();
					gids += "," + gid;
				}
			})
			//非空判断
			if (gids == "") {
				//没有选择任何数据
				alert("没有选择数据");
				return;
			}
			//将gids前面的逗号去掉
			gids = gids.substring(1);
			//请求后台  携带mark匹配方法  携带gids 删除数据
			$.post('categoryServlet?mark=delAll&gids=' + gids,function (obj) {
				if (obj == -1) {
					alert("该商品分类下还有商品，不然删除");
				}else if(obj > 0) {
					//删除成功  走分页查询的方法
					location.href="categoryServlet?mark=pageQuery";
				}
			},"json")
		}else {
			alert("取消了批量删除");
		}
	}
</script>
