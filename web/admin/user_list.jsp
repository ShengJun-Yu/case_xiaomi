<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--引入c标签  只有引入了c标签 该页面才能使用c标签
    c标签可以对后台传递过来的list集合进行遍历操作
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	
	//获取项目名
	String path = request.getContextPath();
	//获取tomcat 协议+地址+端口号+ 获取项目名
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户信息</title>
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
    <li><a href="#">商品管理</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        
        <!-- 
        
        <li class="click"><span><img src="images/t02.png" /></span>修改</li>
        <li><span><img src="images/t04.png" /></span>统计</li>
         -->
        <li style="cursor: pointer;" id="li1"><span><img src="<%=basePath %>admin/images/t03.png" /></span>批量删除</li>
        </ul>

    </div>
    <table class="tablelist">
    	<thead>
	    	<tr>
	        <th><input id="inp" name="" type="checkbox" value="" /></th>
	        <th>序号<i class="sort"><img src="<%=basePath %>admin/images/px.gif" /></i></th>
	        <th>姓名</th>
	        <th>性别</th>
	        <th>电话号码</th>
	        <th>所在地区</th>
	        <th>权限</th>
	        <th>账号</th>
	       	<th>头像</th>
	       	<th>注册时间</th>
	       	<th>操作</th>
	        </tr>
	        </thead>
        <tbody>
        <%--后台携带过来的userList集合进行遍历的操作
            c:forEach遍历的操作
            items="${userList}"  要遍历的集合
            var="user" 代表当前遍历的元素
            varStatus="status"  status.index 代表的是索引 从0开始
        --%>
        <c:forEach items="${userList}" var="user" varStatus="status">
            <tr align="center">
                <td>
                    <input class="inps" value="${user.id}" type="checkbox">
                </td>
                <td>${status.index + 1}</td>
                <td>${user.name}</td>
                <%--  1 男  0 女 --%>
                <td>
                    <c:if test="${user.sex == 1}">男</c:if>
                    <c:if test="${user.sex == 0}">女</c:if>
                </td>
                <td>${user.phone_number}</td>
                <td>${user.area}</td>
                <%--1用户 0管理员--%>
                <td>
                    <c:if test="${user.manager == 1}">用户</c:if>
                    <c:if test="${user.manager == 0}">管理员</c:if>
                </td>
                <td>${user.username}</td>
                <td>
                    <img src="http://localhost:8080/xiaomiPic1/${user.photo}"
                         width="60px" alt="">
                </td>
                <td>${user.create_time}</td>
                <td>
    <%--点击删除  请求后台 通过主键uid将数据库的该条数据删除掉--%>
                    <a href="userServlet?mark=deleteUser&id=${user.id}">删除</a>
                </td>

            </tr>
        </c:forEach>
        
        </tbody>
    </table>
   
    <div class="pagin">
    	<div class="message">共<i class="blue">${pt.totalSize }</i>条记录，当前显示第&nbsp;<i class="blue">${pt.currentPage }&nbsp;</i>页</div>
        <ul class="paginList">
	        
	         <li class="paginItem"><a href="userServlet?mark=pageQuery">首页</a></li>
	         <li class="paginItem"><a href="userServlet?mark=pageQuery&currentPage=${pt.prePage}">上一页</a></li>
	         <li class="paginItem"><a href="userServlet?mark=pageQuery&currentPage=${pt.nextPage}">下一页</a></li>
	         <li class="paginItem"><a href="userServlet?mark=pageQuery&currentPage=${pt.totalPage}">尾页</a></li>
	         
	        
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
    //给id为inp的复选框绑定点击事件
    $("#inp").click(function () {
        //实现全选和全不选
        var bo = $(this).prop("checked");
        $(".inps").prop("checked",bo);
    })
    //批量删除
    //绑定单击事件
    var ids = "";
    $("#li1").click(function() {
        //确认框
        if (confirm("确定要删除吗?")) {
            //遍历下面的复选框
            $(".inps").each(function () {

                if ($(this).prop("checked")) {
                   //拿到该条数据的id
                   var id = $(this).val();
                   ids += "," + id;
                }
            })
            if (ids == "") {
                alert("没有选择任何数据");
            }else {
                //携带ids去请求后台完成批量删除
                ids = ids.substring(1);
                //ajax发送请求
                $.ajax({
                    //请求的地址
                    url:"userServlet",
                    //请求的方式
                    type:"get",
                    //携带的参数
                    data:{"mark":"delAll","ids":ids},
                    dataType:"json",//返回的数据类型
                    //成功的回调函数  obj代表返回的数据
                    success:function (obj) {
                        if (obj > 0) {
                            //删除成功 请求分页
                            location.href="userServlet?mark=pageQuery";
                        }else if(obj == -1) {
                            alert("有管理员用户，重新选择");
                        }else {
                            alert("批量删除失败");
                        }
                    }
                })
            }

        }else {
            alert("取消删除");
        }
    })
</script>