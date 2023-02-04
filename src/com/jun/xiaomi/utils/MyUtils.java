package com.jun.xiaomi.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : Bojack
 * @date : Created in 10:39 2022.10.09
 */
public class MyUtils {
    //����ִ��sql��qr����
    public static QueryRunner qr = new QueryRunner(new ComboPooledDataSource());

    //�ַ���ת����
    public static Date stringToDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

