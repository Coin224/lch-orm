package com.lch.orm;

import com.lch.dbpool.pool.DbPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ORM {

    // insert into firsttable(id,name,password) values(#{id},#{name},#{password})
    // 把sql中的#{id}，#{name}，#{password}换成问号,并得到里面的值
    public void parseSql(String sql) {
        StringBuilder newSql = new StringBuilder();//用来拼接sql
            //insert into firsttable(id,name,password) values(
            newSql.append(sql.substring(0,sql.indexOf("#{")));
            sql.substring(sql.indexOf("#{"),sql.indexOf("}"));
            newSql.append("?,");
        System.out.println(newSql.toString());
    }


    public static void main(String[] args) {
        ORM orm = new ORM();
        String sql = "insert into firsttable(id,name,password) values(#{id},#{name},#{password})";
        orm.parseSql(sql);
    }




    // insert into firsttable(id,name,password) values(#{id},#{name},#{password})
    // 把sql中的#{id}，#{name}，#{password}换成问号
    public void save(String sql , Object obj) {

    }



    public void save() {
        String sql = "insert into firsttable(id,name,password) values(?,?,?)";
        Connection connection = null;
        PreparedStatement state = null;
        try {
            connection = DbPool.getConn();
            state = connection.prepareStatement(sql);
            state.setInt(1,50);
            state.setString(2,"李春宏");
            state.setString(3,"qwert");
            int count = state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (state != null) {
                    state.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
