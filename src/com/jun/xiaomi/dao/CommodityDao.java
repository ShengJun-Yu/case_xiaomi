package com.jun.xiaomi.dao;

import com.jun.xiaomi.bean.Commodity;
import com.jun.xiaomi.utils.MyUtils;
import com.jun.xiaomi.utils.PageTools;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class CommodityDao {
    public int queryTotalSize() {
        String sql = "select count(*) from commodity";
        try {
            long l = (long) MyUtils.qr.query(sql,new ScalarHandler());
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
    public List<Commodity> pageQuery(PageTools pt) {
        String sql = "select * from commodity limit ?,?";
        try {
            return MyUtils.qr.query(sql,new BeanListHandler<>(Commodity.class),
                    pt.getIndex(),pt.getPageSize());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询通过cid
     * @param cid
     * @return
     */
    public Commodity queryByCid(int cid) {
        String sql = "select * from commodity where cid = ?";
        try {
            return MyUtils.qr.query(sql,new BeanHandler<>(Commodity.class),cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除通过cid
     * @param cid
     * @return
     */
    public int deleteById(int cid) {
        String sql = "delete from commodity where cid = ?";
        try {
           return MyUtils.qr.update(sql,cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //添加
    public int addCommodity(Commodity c) {
        String sql = "insert into commodity values(?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {
                null,c.getGid(),c.getName(),
                c.getColor(),c.getSize(),c.getPrice(),
                c.getDescription(),c.getFull_description(),
                c.getPic(),c.getState(),c.getVersion(),
                c.getProduct_date()
        };
        try {
            return MyUtils.qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //查询通过gids
    public int queryByGids(String gids) {
        String sql = "select count(*) from commodity where gid in(" + gids + ")";
        try {
            long l = (long)MyUtils.qr.query(sql,new ScalarHandler());
            return (int)l;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //查询
    public List<Commodity> queryByCids(String cids) {
        String sql = "select * from commodity where cid in(" + cids + ")";
        try {
            return MyUtils.qr.query(sql,new BeanListHandler<>(Commodity.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //删除
    public int deleteAll(String cids) {
        String sql = "delete from commodity where cid in(" + cids + ")";
        try {
            return MyUtils.qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
