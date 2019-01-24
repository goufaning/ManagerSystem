package com.goufaning.warehouse.app.dao;

import com.goufaning.warehouse.app.bean.Provider;
import com.goufaning.warehouse.app.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gfn on 2017-01-01.
 */
public class ProviderDao {
    public boolean insert(Provider provider) {
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        String sql = "insert imto provider_332(name,description) values(?,?)";
        try {
            conn = JDBCUtils.getConnection();
            perstmt  = conn.prepareStatement(sql);
            perstmt.setString(1,provider.getName());
            perstmt.setString(3,provider.getDescription());
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
    public Provider findByName(String name) {
        String sql = "select * from provider_332 where name= ?";
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            perstmt = conn.prepareStatement(sql);
            perstmt.setString(1,name);
            rs = perstmt.executeQuery();
            while (rs.next()) {
                Provider provider = new Provider();
                provider.setId(rs.getInt("id"));
                provider.setName(rs.getString("name"));
                provider.setDescription(rs.getString("description"));
                return provider;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseResources(rs,perstmt,conn);
        }
        return null;
    }

    public List<Provider> findAll() {
        String sql = "select * from provider_332";
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            perstmt = conn.prepareStatement(sql);
            rs = perstmt.executeQuery();
            List<Provider> providerList = new LinkedList<>();
            while (rs.next()) {
                Provider provider = new Provider();
                provider.setId(rs.getInt("id"));
                provider.setName(rs.getString("name"));
                provider.setDescription(rs.getString("description"));
                providerList.add(provider);
            }
            return providerList;
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
        String sql = "delete from provider_332 where id= ?";
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
