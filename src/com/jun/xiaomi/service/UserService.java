package com.jun.xiaomi.service;

import com.jun.xiaomi.bean.User;
import com.jun.xiaomi.dao.UserDao;
import com.jun.xiaomi.utils.PageTools;
import java.io.File;
import java.util.List;

public class UserService {

    private UserDao userDao = new UserDao();

    /**
     * 管理员登陆
     *
     * @param username
     * @param password
     * @return
     */
    public boolean adminLogin(String username, String password) {
        User user = userDao.adminLogin(username, password);
        return user != null;
    }

    /**
     * 验证手机号是否唯一
     *
     * @param phone
     * @return
     */
    public boolean phoneIsUnique(String phone) {
        User user = userDao.phoneIsUnique(phone);
        //user等于null 没有查询到 可以注册   return true
        return user == null;
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    public boolean userRegister(User user) {
        int i = userDao.userRegister(user);
        //i > 0 注册成功
        return i > 0;
    }

    /**
     * 查询所有用户信息
     *
     * @return
     */
    public List<User> findUserList() {
        return userDao.findUserList();
    }

    /**
     * 删除用户
     *
     * @param uid
     * @return
     */
    public boolean deleteUser(int uid) {
        /**
         * 1、将表中的数据删除掉 delete from user where uid = ?
         * 2、要将本地文件夹D://xiaomiPic1对应的图片删除掉
         * 分析:
         *  先删除数据，拿不到了图片名称，无法删除图片
         *  先删除图片，万一数据删除的时候报错
         *  处理:
         *      先将该条数据从数据库查询出来，保存到一个变量
         *      当删除数据成功之后，在通过保存的变量拿到图片名称
         *      去本地文件夹删除对应的图片
         */
        //1、通过uid查询数据
        User user = userDao.queryUserByUid(uid);
        //2、通过uid去删除数据
        //i > 0 删除成功   否则删除失败
        int i = userDao.deleteUserByUid(uid);
        if (i > 0) {
            //删除成功  本地文件夹的图片删除掉
            new File("D://xiaomiPic1/"
                    + user.getPhoto()).delete();

        }
        return i > 0;
    }

    /**
     * 查询总条数
     *
     * @return
     */
    public int queryTotalSize() {
        return userDao.queryTotalSize();
    }

    /**
     * 分页查询
     *
     * @param pt
     * @return
     */
    public List<User> pageQuery(PageTools pt) {
        return userDao.pageQuery(pt);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    public int delAll(String ids) {
        /**
         * 1、先去通过ids把对应的数据查询出来
         * 2、判断里面是否有管理员  不让删除
         * 3、将数据库的数据删除掉
         * 4、将本地的图片删除掉
         */
        //1、先去通过ids把对应的数据查询出来
        List<User> lists = userDao.queryUserByIds(ids);
        //2、判断里面是否有管理员  不让删除
        for (User user : lists) {
            if (user.getManager() == 0) {
                return -1;
            }

        }
        //3、将数据库的数据删除掉
        int i = userDao.delAll(ids);
        //4、将本地图片删除掉
        for (User user : lists) {
            //获取图片名称
            String photo = user.getPhoto();
            new File("D://xiaomiPic1/" + photo).delete();
        }
        return i;

    }
}
