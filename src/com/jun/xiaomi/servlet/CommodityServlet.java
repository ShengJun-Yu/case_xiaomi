package com.jun.xiaomi.servlet;

import com.jun.xiaomi.bean.Category;
import com.jun.xiaomi.bean.Commodity;
import com.jun.xiaomi.service.CategoryService;
import com.jun.xiaomi.service.CommodityService;
import com.jun.xiaomi.utils.MyUtils;
import com.jun.xiaomi.utils.PageTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//支持文件上传
@MultipartConfig
@WebServlet("/admin/commodityServlet")
public class CommodityServlet extends BaseServlet {

    private CategoryService categoryService = new CategoryService();
    private CommodityService commodityService = new CommodityService();
    /**
     * 分页查询
     * @param req
     * @param resp
     */
    public void pageQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、接收当前页
        String currentPage = req.getParameter("currentPage");
        //2、处理
        int cp = 1;
        if (currentPage != null && currentPage.length() > 0) {
            cp = Integer.parseInt(currentPage);
        }
        //3、定义每页显示的条数
        int pageSize = 3;
        //4、总条数   查询数据库
        int totalSize = commodityService.queryTotalSize();
        //5、创建工具类对象
        PageTools pt = new PageTools(cp,pageSize,totalSize);
        //6、查询数据
        List<Commodity> list = commodityService.pageQuery(pt);
        //遍历list
        for (Commodity comm : list) {
            //拿到商品分类的主键
            Integer gid = comm.getGid();
            //通过gid去查询对应的商品分类
            Category category = categoryService.queryCategoryByGid(gid);
            comm.setCategory(category);
        }
        //7、将list集合和pt对象放到req作用域中
        req.setAttribute("pt",pt);
        req.setAttribute("commodityList",list);
        //8、转发到goods_list.jsp
        req.getRequestDispatcher("goods_list.jsp")
                .forward(req,resp);
    }

    /**
     * 删除
     * @param req
     * @param resp
     */
    public void deleteById(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        //1、接收cid
        int cid = Integer.parseInt(req.getParameter("cid"));
        //2、通过cid查询该条数据
        int i = commodityService.deleteById(cid);
        if (i > 0) {
            pageQuery(req,resp);
        }else {
            System.out.println("删除失败");
        }
    }

    //添加商品
    public void addCommodity(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
        //接收参数
        String name = req.getParameter("name");
        int gid = Integer.parseInt(req.getParameter("gid"));
        //color size price description full_description
        //pic  //state //version //product_date
        String color = req.getParameter("color");
        String size = req.getParameter("size");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        String full_description = req.getParameter("full_description");
        int state = Integer.parseInt(req.getParameter("state"));
        String version = req.getParameter("version");
        String product_date = req.getParameter("product_date");
        Date date = MyUtils.stringToDate(product_date);

        //封装
        Commodity commodity = new Commodity(gid,name,color,size,price,description,full_description,state,version,date);

        //处理图片
        Part part = req.getPart("pic");
        String fileName = part.getSubmittedFileName();
        //使用uuid拼接
        fileName = UUID.randomUUID() + fileName;
        commodity.setPic(fileName);
        part.write("D://xiaomiPic1/" + fileName);

        //调用service层方法
        boolean bo = commodityService.addCommodity(commodity);

        if (bo) {
            pageQuery(req,resp);
        }



    }

    //批量删除
    public void delAll(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String cids = req.getParameter("cids");
        int i = commodityService.delAll(cids);
        resp.getWriter().print(i > 0);
    }
}
