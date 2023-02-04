<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    //获取项目名
    String path = request.getContextPath();
    //获取tomcat 协议+地址+端口号+ 获取项目名
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    //获取tomcat 协议+地址+端口号
    String imgPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";

%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>商品信息</title>
    <link href="<%=basePath %>admin/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=basePath %>user/js/jquery-3.3.1.js"></script>

    <script type="text/javascript">

        // old write
        $(document).ready(function () {
            $(".click").click(function () {
                $(".tip").fadeIn(200);
            });

            $(".tiptop a").click(function () {
                $(".tip").fadeOut(200);
            });

            $(".sure").click(function () {
                $(".tip").fadeOut(100);
            });

            $(".cancel").click(function () {
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
            <li style="cursor: pointer;" onclick="add_goods()"><span><img
                    src="<%=basePath %>admin/images/t01.png"/></span>添加商品
            </li>
            <li style="cursor: pointer;" onclick="batchDelete()"><span><img
                    src="<%=basePath %>admin/images/t03.png"/></span>批量删除
            </li>
        </ul>
    </div>
    <table class="tablelist">
        <thead>
        <tr>
            <th><input name="" type="checkbox" value="" id="inp"/></th>
            <th>序号<i class="sort"><img src="<%=basePath %>admin/images/px.gif"/></i></th>
            <th>商品类别</th>
            <th>商品名称</th>
            <th>商品颜色</th>
            <th>商品价格</th>
            <th width="10%">简介</th>
            <th width="20%">详情</th>
            <th>商品展示图</th>
            <th>是否热推</th>
            <th>型号</th>
            <th>生产日期</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${commodityList}" var="commodity" varStatus="status">
            <tr align="center">
                <td><input type="checkbox" class="inps" value="${commodity.cid}"></td>
                <td>${status.index + 1}</td>
                <td>${commodity.category.name}</td>
                <td>${commodity.name}</td>
                <td>${commodity.color}</td>

                <td>${commodity.price}</td>
                <td>${commodity.description}</td>
                <td>${commodity.full_description}</td>
                    <%--显示图片--%>
                <td>
                    <img src="http://localhost:8080/xiaomiPic1/${commodity.pic}"
                         width="50px" alt="">
                </td>
                    <%--处理是否热推--%>
                <td>
                    <c:if test="${commodity.state == 0}">
                        否
                    </c:if>
                    <c:if test="${commodity.state > 0}">
                        是
                    </c:if>
                </td>

                <td>${commodity.size}</td>
                    <%--生产日期显示年月日--%>
                <td>
                    <fmt:formatDate value="${commodity.product_date}" pattern="yyyy-MM-dd"></fmt:formatDate>
                </td>
                <td>
                    <a href="commodityServlet?mark=deleteById&cid=${commodity.cid}">删除</a>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagin">
        <div class="message">共<i class="blue">${pt.totalSize}</i>条记录，当前显示第&nbsp;<i class="blue">${pt.currentPage}</i>页
        </div>
        <ul class="paginList">
            <li class="paginItem"><a href="commodityServlet?mark=pageQuery">首页</a></li>
            <li class="paginItem"><a href="commodityServlet?mark=pageQuery&currentPage=${pt.prePage}">上一页</a></li>
            <li class="paginItem"><a href="commodityServlet?mark=pageQuery&currentPage=${pt.nextPage}">下一页</a></li>
            <li class="paginItem"><a href="commodityServlet?mark=pageQuery&currentPage=${pt.totalPage}">尾页</a></li>
        </ul>
    </div>
    <div class="tip">
        <div class="tiptop"><span>提示信息</span><a></a></div>
        <div class="tipinfo">
            <span><img src="images/ticon.png"/></span>
            <div class="tipright">
                <p>是否确认对信息的修改 ？</p>
                <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
            </div>
        </div>
        <div class="tipbtn">
            <input name="" type="button" class="sure" value="确定"/>&nbsp;
            <input name="" type="button" class="cancel" value="取消"/>
        </div>
    </div>
</div>
<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
<script>
    //添加商品
    function add_goods() {
        location.href = "categoryServlet?mark=queryAll";
    }

    //实现全选和全不选
    $("#inp").click(function () {
        //获取自己的选中状态
        var bo = $(this).prop("checked");
        $(".inps").prop("checked", bo);
    })

    //批量删除
    function batchDelete() {
        //确认框
        if (confirm("确定要删除吗?")) {
            var cids = "";
            //遍历下面所有的复选框
            $(".inps").each(function () {
                //判断是否选中
                if ($(this).prop("checked")) {
                    //拿到主键gid
                    var cid = $(this).val();
                    cids += "," + cid;
                }
            })
            //非空判断
            if (cids == "") {
                //没有选择任何数据
                alert("没有选择数据");
                return;
            }
            //将gids前面的逗号去掉
            cids = cids.substring(1);
            $.post('commodityServlet?mark=delAll&cids=' + cids, function (obj) {
                if (obj) {
                    location.href = "commodityServlet?mark=pageQuery";
                } else {
                    alert("批量删除失败");
                }
            }, "json")
        } else {
            alert("取消了批量删除");
        }
    }
</script>
