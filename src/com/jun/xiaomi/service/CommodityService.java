package com.jun.xiaomi.service;

import com.jun.xiaomi.bean.Commodity;
import com.jun.xiaomi.dao.CommodityDao;
import com.jun.xiaomi.utils.PageTools;

import java.io.File;
import java.util.List;

public class CommodityService {

    private CommodityDao commodityDao = new CommodityDao();
    /**
     * 查询总条数
     * @return
     */
    public int queryTotalSize() {
        return commodityDao.queryTotalSize();
    }

    /**
     * 分页查询
     * @param pt
     * @return
     */
    public List<Commodity> pageQuery(PageTools pt) {
        return commodityDao.pageQuery(pt);
    }

    /**
     *
     * @param cid
     * @return
     */
    public int deleteById(int cid) {
        //1、通过cid去查询商品信息
        Commodity commodity = commodityDao.queryByCid(cid);
        //2、通过cid去删除数据
        int i = commodityDao.deleteById(cid);
        if (i > 0) {
            //图片删除掉
            String pic = commodity.getPic();
            new File("D://xiaomiPic1/" + pic).delete();
        }
        return i;
    }

    //添加
    public boolean addCommodity(Commodity commodity) {
        int i = commodityDao.addCommodity(commodity);
        return i > 0;
    }

    //批量删除
    public int delAll(String cids) {
        //1、通过cids将商品信息查询出来
        List<Commodity> list = commodityDao.queryByCids(cids);
        //2、通过cids删除商品
        int i = commodityDao.deleteAll(cids);

        //3、删除图片
        if (i > 0) {
            for (Commodity comm : list) {
                String pic = comm.getPic();
                new File("D://xiaomiPic1/" + pic).delete();
            }
        }
        return i;
    }
}
