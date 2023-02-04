package com.jun.xiaomi.service;

import com.jun.xiaomi.bean.Category;
import com.jun.xiaomi.bean.Commodity;
import com.jun.xiaomi.dao.CategoryDao;
import com.jun.xiaomi.dao.CommodityDao;
import com.jun.xiaomi.utils.PageTools;

import java.util.List;

public class CategoryService {


    private CommodityDao commodityDao = new CommodityDao();
    private CategoryDao categoryDao = new CategoryDao();
    /**
     * 查询总条数
     * @return
     */
    public int queryTotalSize() {
       return categoryDao.queryTotalSize();
    }

    /**
     * 分页查询
     * @param pt
     * @return
     */
    public List<Category> pageQuery(PageTools pt) {
        return categoryDao.pageQuery(pt);
    }

    /**
     * 添加类别
     * @param category
     * @return
     */
    public int addCategory(Category category) {
        return categoryDao.addCategory(category);
    }

    /**
     * 通过gid查询
     * @param gid
     * @return
     */
    public Category queryCategoryByGid(int gid) {
        return categoryDao.queryCategoryByGid(gid);
    }

    /**
     * 修改商品分类
     * @param category
     * @return
     */
    public int updateCategory(Category category) {
        return categoryDao.updateCategory(category);
    }

    /**
     * 批量删除
     * @param gids
     * @return
     */
    public int delAll(String gids) {
        //1、查询该商品分类下是否还有商品
        int count = commodityDao.queryByGids(gids);
        if (count > 0) {
            return -1;
        }
        return categoryDao.delAll(gids);
    }

    /**
     * 删除商品分类
     * @param gid
     * @return
     */
    public int deleteCategory(int gid) {
       return categoryDao.deleteCategory(gid);
    }

    //查询所有
    public List<Category> queryAll() {
        return categoryDao.queryAll();
    }
}
