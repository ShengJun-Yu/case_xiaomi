<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
    <link rel="stylesheet" href="css/index.css">
    <%--	引入jquery.js文件  该页面就可以使用jQuery框架--%>
    <script src="js/jquery-3.3.1.js"></script>
</head>

<body>
<div class="sign_background">
    <div class="sign_background_in">
        <div class="sign_background_no1">
            <a href="index.html"><img src="img/logo.jpg" alt=""></a>
        </div>
        <div class="sign_background_no2">注册小米帐号</div>
        <div class="sign_background_no3">

            <div class="sign_background_no5">
                <%--
                    图片上传的三个准备步骤:
                        1、请求的方式为post   method="post"
                        2、编译的方式enctpye  enctype="multipart/form-data"
                        3、在servlet上加上注解  @MultipartConfig
                --%>
                <form id="f1" action="../admin/userServlet" method="post"
                      enctype="multipart/form-data">
<%--					携带mark用来匹配后台的方法--%>
					<input type="hidden" name="mark" value="userRegister">
                    <table style="width: 500px;" border="0" cellspacing="0">
                        <tr>
                            <td width="25%" class="_left">姓名：</td>
                            <td>
                                <input id="inp1" type="text" name="name">
                                <span id="s1"></span>
                            </td>
                        </tr>
                        <tr>
                            <td width="25%" class="_left">性别：</td>
                            <td>
                                男<input type="radio" checked name="sex" value="1">
                                女<input type="radio" name="sex" value="0">
                            </td>
                        </tr>
                        <tr>
                            <td width="25%" class="_left">电话号码：</td>
                            <td>
                                <input id="inp2" type="text" name="phone_number">
                                <span id="s2"></span>
                            </td>
                        </tr>
                        <tr>
                            <td width="25%" class="_left">所在地区：</td>
                            <td><input type="text" name="area"></td>
                        </tr>
                        <tr>
                            <td width="25%" class="_left">账号：</td>
                            <td><input type="text" name="username"></td>
                        </tr>
                        <tr>
                            <td width="25%" class="_left">密码：</td>
                            <td><input type="text" name="password"></td>
                        </tr>
                        <tr>
                            <td width="25%" class="_left">上传头像：</td>
                            <td><input type="file" name="photo"></td>
                        </tr>
                    </table>
                    <div class="sign_background_no6" id="btn">立即注册</div>
                </form>

            </div>
        </div>
        <div class="sign_background_no7">注册帐号即表示您同意并愿意遵守小米 <span>用户协议</span>和<span>隐私政策</span></div>
    </div>
    <div class="sign_background_no8"><img src="img/sign01.jpg" alt=""></div>

</div>
</body>
</html>
<%--js代码都是写在script标签里面--%>
<script>
	//定义两个变量
	var b1 = false;//控制姓名
	var b2 = false;//控制手机号
    //1、给姓名的input框一个失去焦点的事件
    $("#inp1").blur(function () {
        //2、获取用户输入的姓名
        var value = $("#inp1").val();
        //3、非空判断
        if (value == "") {
            //用户没有输入姓名  提示姓名不能为空  字体颜色设置为红色
            $("#s1").html("姓名不能为空");
            $("#s1").css("color", "red");
            b1 = false;
            return;
        }
        //用户输入的不为空
        $("#s1").html("姓名ok");
        $("#s1").css("color", "green");
        b1 = true;

    });

    //验证手机号
    $("#inp2").blur(function () {
        //获取用户输入的手机号
        var value = $("#inp2").val();
        //非空判断
        if (value == "") {
            $("#s2").html("手机号不能为空");
            $("#s2").css("color", "red");
            b2 = false;
            return;
        }
        //说明手机号不为空
        //验证手机号格式是否合法  正则表达式
        //正则代表的是一类的字符串  第一位必须是1 第二位必须是3-9中的一个，后面跟上9位数字
        var reg = /^1[3-9]\d{9}$/;
        //格式匹配失败
        if (!reg.test(value)) {
            $("#s2").html("手机号格式不对");
            $("#s2").css("color", "red");
            b2 = false;
            return;
        }
        //验证手机号是否是唯一的   ajax发送请求到后台查询数据库
        $.ajax({
            //请求的地址url
            url: "../admin/userServlet",
            //携带的参数  mark参数用来匹配方法    phone代表手机号
            data: {"mark": "phoneIsUnique", "phone": value},
            //请求的方式  get/post
            type: "get",
            dataType: "json",
            //成功的回调函数
            success: function (obj) {
                //obj代表后台返回的数据
                //true 手机号不存在  可以注册   false  手机号已存在  不可以注册
                if (obj) {
                    $("#s2").html("手机号ok");
                    $("#s2").css("color", "green");
                    b2 = true;
                } else {
                    $("#s2").html("手机号已注册过");
                    $("#s2").css("color", "red");
                    b2 = false;
                }
            }
        })

    })

	//给立即注册的div绑定一个单击事件
	$("#btn").click(function() {
		//判断表单验证是否成功
		if (b1 && b2) {
			//提交form表单，完成注册
			$("#f1").submit();
		}else {
			//表单验证不成功
			alert("表单验证不成功");
		}
	})

</script>