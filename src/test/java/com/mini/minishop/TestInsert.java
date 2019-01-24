package com.mini.minishop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author hht
 * @create 2019-01-12 18:07
 */
public class TestInsert {

    public static void main(String[] args) throws SQLException {

      //加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");


            //获取链接
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minishop","root","root");

            //创建语句执行者
            Statement statement =
                    connection.createStatement();

            //编写sql
            for (int i = 0; i < 11; i++) {
                String sqlFormat = "insert into category values (null, '测试分类%d')";
                String sql = String.format(sqlFormat, i);

                //执行语句
                 statement.execute(sql);

            }

            connection.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
