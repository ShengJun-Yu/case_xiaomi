package com.jun.xiaomi.dao;

import com.jun.xiaomi.bean.User;
import com.jun.xiaomi.utils.MyUtils;
import com.jun.xiaomi.utils.PageTools;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDao {

    /**
     * 管理员登陆
     *
     * @param username
     * @param password
     * @return
     */
    public User adminLogin(String username, String password) {
        //加上管理员权限
        String sql = "select * from user where username = " +
                "? " + "and password = ? and manager = 0";
        try {
            return MyUtils.qr.query(sql,
                    new BeanHandler<>(User.class),username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证手机号唯一
     *
     * @param phone
     * @return
     */
    public User phoneIsUnique(String phone) {
        String sql = "select * from user where phone_number = ?";
        try {
            return MyUtils.qr.query(sql, new BeanHandler<>(User.class), phone);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    public int userRegister(User user) {
        String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {
                null, user.getName(), user.getSex(),
                user.getPhone_number(), user.getArea(),
                user.getManager(), user.getUsername(),
                user.getPassword(), user.getPhoto(),
                user.getCreate_time()
        };
        try {
            return MyUtils.qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 查询所有用户信息
     *
     * @return
     */
    public List<User> findUserList() {
        String sql = "select * from user";
        try {
            return MyUtils.qr.query(sql,
                    new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过uid查询数据
     *
     * @param uid
     * @return
     */
    public User queryUserByUid(int uid) {
        String sql = "select * from user where id = ?";
        try {
            return MyUtils.qr.query(sql, new BeanHandler<>(User.class), uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过id删除数据
     *
     * @param uid
     * @return
     */
    public int deleteUserByUid(int uid) {
        String sql = "delete from user where id = ?";
        try {
            return MyUtils.qr.update(sql, uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 查询总条数
     *
     * @return
     */
    public int queryTotalSize() {
        String sql = "select count(*) from user";
        try {
            long l = (long) MyUtils.qr.query(sql, new ScalarHandler());
            return (int) l;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 分页查询
     *
     * @return
     */
    public List<User> pageQuery(PageTools pt) {
        String sql = "select * from user limit ?,?";
        try {
            return MyUtils.qr.query(sql, new BeanListHandler<>(User.class),
                    pt.getIndex(), pt.getPageSize());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param ids
     * @return
     */
    public List<User> queryUserByIds(String ids) {
        String sql = "select * from user where id in(" + ids + ")";
        try {
            return MyUtils.qr.query(sql, new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    public int delAll(String ids) {
        String sql = "delete from user where id in(" + ids + ")";
        try {
            return MyUtils.qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
