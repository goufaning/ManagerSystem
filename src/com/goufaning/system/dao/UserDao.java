package com.goufaning.system.dao;

import com.goufaning.system.bean.User;
import com.goufaning.system.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gfn on 2016-12-31.
 */
public class UserDao {
    public boolean insert(User user) {
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        String sql = "insert imto user_332(name,password,permissions,description) values(?,?,?,?)";
        try {
            conn = JDBCUtils.getConnection();
            perstmt  = conn.prepareStatement(sql);
            perstmt.setString(1,user.getName());
            perstmt.setString(2,user.getPassword());
            perstmt.setString(3,user.getPermissions());
            perstmt.setString(4,user.getDescription());
            int num = perstmt.executeUpdate();
            if (num > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.releaseResources(rs,perstmt,conn);
        }
        return false;
    }
    public User find(String username, String password) {
        String sql = "select * from user_332 where name= ? and password = ?";
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            perstmt = conn.prepareStatement(sql);
            perstmt.setString(1,username);
            perstmt.setString(2,password);
            rs = perstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(username);
                user.setPassword(password);
                user.setPermissions(rs.getString("permissions"));
                user.setDescription(rs.getString("description"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseResources(rs,perstmt,conn);
        }
        return null;
    }
    public List<User> findAll() {
        String sql = "select * from user_332 ";
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            perstmt = conn.prepareStatement(sql);
            rs = perstmt.executeQuery();
            List<User> userList = new LinkedList<>();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setPermissions(rs.getString("permissions"));
                user.setDescription(rs.getString("description"));
                userList.add(user);
            }
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseResources(rs,perstmt,conn);
        }
        return null;
    }

    public boolean delete(int id) {
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        String sql = "delete from user_332 where id= ?";
        try {
            conn = JDBCUtils.getConnection();
            perstmt  = conn.prepareStatement(sql);
            perstmt.setInt(1,id);
            int num = perstmt.executeUpdate();
            if (num > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.releaseResources(rs,perstmt,conn);
        }
        return false;
    }
}
