package com.jun.xiaomi.servlet;

import com.jun.xiaomi.bean.User;
import com.jun.xiaomi.service.UserService;
import com.jun.xiaomi.utils.PageTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author : Bojack
 * @date : Created in 10:40 2022.10.09
 */
@MultipartConfig
@WebServlet("/admin/userServlet")
public class UserServlet extends BaseServlet {

    //创建UserService对象
    private UserService userService = new UserService();

    /**
     * 管理员登陆
     *
     * @param req
     * @param resp
     */
    public void adminLogin(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        //1、接收用户输入的账号和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //操作数据库  bo true 登陆成功   false 登陆失败
        boolean bo = userService.adminLogin(username, password);
        if (bo) {
            //登陆成功 跳转到主页 main.jsp
            resp.sendRedirect("main.jsp");
        } else {
            //登陆失败 带上提示消息  跳转到login.jsp
            req.setAttribute("msg", "登陆失败");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    /**
     * 验证手机号是否唯一
     *
     * @param req
     * @param resp
     */
    public void phoneIsUnique(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1、接收手机号
        String phone = req.getParameter("phone");
        //2、通过手机号去查询数据库看是否存在
        //true 手机号不存在 可以注册    false 手机号存在 不让注册
        boolean bo = userService.phoneIsUnique(phone);
        //将bo响应给前台
        resp.getWriter().print(bo);
    }

    /**
     * 用户注册
     *
     * @param req
     * @param resp
     */
    public void userRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //1、接收姓名、手机号、性别、地区、账号、密码
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String area = req.getParameter("area");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String phone_number = req.getParameter("phone_number");
        //  20个图片   压缩包
        //2、将上面的参数封装到user对象中
        User user = new User();
        user.setName(name);
        user.setArea(area);
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone_number(phone_number);
        //Integer.parseInt(sex) 将字符串的sex转成int类型
        user.setSex(Integer.parseInt(sex));
        //3、添加创建时间
        user.setCreate_time(new Date());
        //4、添加权限为普通用户
        user.setManager(1);

        /**
         * 1、将图片名称存储到user对象中
         * 2、将图片本身通过流的方式存储到D://xiaomiPic文件夹中
         */
        //接收图片   图片已经存储到part中了
        Part part = req.getPart("photo");
        //获取图片名称
        String fileName = part.getSubmittedFileName();
        /**
         * 处理的问题:
         *  1、张三上传了图像1.jpg  保存到了D://xiaomiPic文件
         *  2、李四上传了图片1.jpg  会覆盖张三上传的1.jpg
         *  原因: 图片名称重复了
         *  解决:让图片名称不重复
         *  使用UUID去生成36位永远都不会重复的字符串当作图片名称
         */
        //通过uuid拼接图片名称
        fileName = UUID.randomUUID() + fileName;
        //准备D://xiaomiPic文件夹
        File path = new File("D://xiaomiPic");
        if (!path.exists()) {//该路径不存在
            path.mkdirs();//创建该路径
        }
        //将图片写到D://xiaomiPic文件夹
        //  D://xiaomiPic/1.jpg
        part.write(path + "/" + fileName);
        //将图片名称存储到user对象中
        user.setPhoto(fileName);


        //5、调用service层的方法完成注册
        boolean bo = userService.userRegister(user);
        if (bo) {
            //注册成功  跳转到管理员登陆页面
            resp.sendRedirect("login.jsp");
        } else {
            //注册失败
            System.out.println("注册失败");
        }
    }

    /**
     * 查询所有用户信息
     *
     * @param req
     * @param resp
     */
    public void findUserList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //执行sql  select * from user
        List<User> list = userService.findUserList();
        //要把list集合放到req的作用域中，转发到user_list.jsp
        req.setAttribute("userList", list);
        req.getRequestDispatcher("user_list.jsp").forward(req, resp);
    }

    /**
     * 删除用户
     *
     * @param req
     * @param resp
     */
    public void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收id
        String id = req.getParameter("id");
        //把id转成int类型
        int uid = Integer.parseInt(id);
        //调用service层的方法进行删除
        //bo true 删除成功   false  删除失败
        boolean bo = userService.deleteUser(uid);
        if (bo) {
            //调用查询所有数据的方法
            findUserList(req, resp);
        } else {
            System.out.println("删除失败");
        }

    }

    /**
     * 分页查询
     *
     * @param req
     * @param resp
     */
    public void pageQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、接收当前页
        String currentPage = req.getParameter("currentPage");
        //2、前台如果没有传递当前页 默认就是第一页
        int cp = 1;
        if (currentPage != null && !currentPage.equals("")) {
            cp = Integer.parseInt(currentPage);
        }
        //3、每页显示的条数
        int pageSize = 3;
        //4、总条数 查询数据库
        int totalSize = userService.queryTotalSize();
        //5、通过上面三个参数去创建分页工具类对象
        PageTools pt = new PageTools(cp, pageSize, totalSize);
        //6、通过pt对象去查询对应的用户信息
        List<User> list = userService.pageQuery(pt);
        //7、将list集合放到作用域中
        req.setAttribute("userList", list);
        //8、将pt对象放到req作用域中
        req.setAttribute("pt", pt);
        //9、转发到user_list.jsp
        req.getRequestDispatcher("user_list.jsp")
                .forward(req, resp);
    }

    /**
     * 批量删除
     */

    public void delAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String ids = req.getParameter("ids");
        //调用service层的方法
        int i = userService.delAll(ids);
        resp.getWriter().print(i);
    }


}
