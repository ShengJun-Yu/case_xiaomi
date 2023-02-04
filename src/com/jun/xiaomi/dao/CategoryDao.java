package com.jun.xiaomi.dao;

import com.jun.xiaomi.bean.Category;
import com.jun.xiaomi.utils.MyUtils;
import com.jun.xiaomi.utils.PageTools;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDao {

    /**
     * 查询总条数
     * @return
     */
    public int queryTotalSize() {
        String sql = "select count(*) from category";
        try {
            long l = (long)MyUtils.qr.query(sql,new ScalarHandler());
            return (int)l;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 分页查询
     * @param pt
     * @return
     */
    public List<Category> pageQuery(PageTools pt) {
        String sql = "select * from category limit ?,?";
        try {
            return MyUtils.qr.query(sql,new BeanListHandler<>(Category.class),
                    pt.getIndex(),pt.getPageSize());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加商品类别
     * @param category
     * @return
     */
    public int addCategory(Category category) {
        String sql = "insert into category values(?,?,?,?,?,?)";
        Object[] params = {
                null,category.getName(),category.getState(),
                category.getOrder_number(),category.getDescription(),
                category.getCreate_time()
        };
        try {
           return MyUtils.qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 通过gid查询
     * @param gid
     * @return
     */
    public Category queryCategoryByGid(int gid) {
        String sql = "select * from category where gid = ?";
        try {
            return MyUtils.qr.query(sql,
                    new BeanHandler<>(Category.class),gid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改商品分类
     * @param category
     * @return
     */
    public int updateCategory(Category category) {
        String sql = "update category set name = ?,state = ?,order_number = ?,description = ?,create_time = ? where gid = ?";
        Object[] params = {category.getName(),category.getState(),
            category.getOrder_number(),category.getDescription(),
            category.getCreate_time(),category.getGid()};
        try {
            return MyUtils.qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 删除选中
     * @param gids
     * @return
     */
    public int delAll(String gids) {
        String sql = "delete from category where gid in (" + gids + ")";
        try {
            return MyUtils.qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     *
     * @param gid
     * @return
     */
    public int deleteCategory(int gid) {
        String sql = "delete from category where gid = ?";
        try {
            return MyUtils.qr.update(sql,gid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //查询所有
    public List<Category> queryAll() {
        String sql = "select * from category";
        try {
           return MyUtils.qr.query(sql,new BeanListHandler<>(Category.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
