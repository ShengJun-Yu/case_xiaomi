package com.jun.xiaomi.servlet;

import com.jun.xiaomi.bean.Category;
import com.jun.xiaomi.service.CategoryService;
import com.jun.xiaomi.utils.MyUtils;
import com.jun.xiaomi.utils.PageTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/admin/categoryServlet")
public class CategoryServlet extends BaseServlet{

    private CategoryService categoryService = new CategoryService();
    /**
     * 分页查询
     * @param req
     * @param resp
     */
    public void pageQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、接收当前页
        String currentPage = req.getParameter("currentPage");
        //2、前台没有传递当前页  默认设置第一页
        int cp = 1;
        if(currentPage != null && !currentPage.equals("")) {
            cp = Integer.parseInt(currentPage);
        }
        //3、定义每页显示的条数
        int pageSize = 3;
        //4、查询总条数
        int totalSize = categoryService.queryTotalSize();
        //5、创建分页工具类对象
        PageTools pt = new PageTools(cp,pageSize,totalSize);
        //6、查询数据
        List<Category> lists = categoryService.pageQuery(pt);
        req.setAttribute("pt",pt);
        req.setAttribute("lists",lists);
        req.getRequestDispatcher("category_list.jsp")
                .forward(req,resp);

    }

    /**
     * 添加商品类别
     */
    public void addCategory(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        //1、接收参数  名称、启用状态、创建时间、排序序号、类别描述
        String name = req.getParameter("name");
        String state = req.getParameter("state");
        String order_number = req.getParameter("order_number");
        String description = req.getParameter("description");
        String create_time = req.getParameter("create_time");
        //2、处理参数
        int state1 = Integer.parseInt(state);
        int order_number1 = Integer.parseInt(order_number);
        //3、把create_time转成日期类型
        Date date = MyUtils.stringToDate(create_time);
        //4、封装
        Category category = new Category(name, state1, order_number1, description, date);
        //5、传递给service
        int i = categoryService.addCategory(category);
        if (i > 0) {
            pageQuery(req,resp);
        }else {
            System.out.println("添加失败");
        }


    }

    /**
     * 通过gid去查询
     * @param req
     * @param resp
     */
    public void queryCategoryByGid(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        //接收gid
        int gid = Integer.parseInt(req.getParameter("gid"));
        //调用service
        Category category = categoryService.queryCategoryByGid(gid);
        //放到作用域中
        req.setAttribute("category",category);
        req.getRequestDispatcher("category_update.jsp")
                .forward(req,resp);
    }

    /**
     * 修改商品分类
     * @param req
     * @param resp
     */
    public void updateCategory(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        //1、接收参数
        String name = req.getParameter("name");
        String state = req.getParameter("state");
        String order_number = req.getParameter("order_number");
        String description = req.getParameter("description");
        String create_time = req.getParameter("create_time");
        String gid = req.getParameter("gid");
        //2、处理参数
        int state1 = Integer.parseInt(state);
        int order_number1 = Integer.parseInt(order_number);
        int gid1 = Integer.parseInt(gid);
        Date date = MyUtils.stringToDate(create_time);
        Category category = new Category(gid1, name, state1, order_number1, description, date);
        //调用service方法
        int i = categoryService.updateCategory(category);
        if (i > 0) {
            //调用分页查询
            pageQuery(req,resp);
        }else {
            System.out.println("更新失败");
        }
    }

    /**
     * 删除所有
     * @param req
     * @param resp
     */
    public void delAll(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String gids = req.getParameter("gids");
        int i = categoryService.delAll(gids);
        resp.getWriter().print(i);
    }

    /**
     * 删除商品分类
     * @param req
     * @param resp
     */
    public void deleteCategory(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        int gid = Integer.parseInt(req.getParameter("gid"));
        int i = categoryService.deleteCategory(gid);
        if (i > 0) {
            pageQuery(req,resp);
        }else {
            System.out.println("删除失败");
        }
    }

    //查询所有商品分类
    public void queryAll(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        List<Category> list = categoryService.queryAll();
        req.setAttribute("categoryList",list);
        //跳转到商品添加页面
        req.getRequestDispatcher("goods_add.jsp")
                .forward(req,resp);
    }
}


