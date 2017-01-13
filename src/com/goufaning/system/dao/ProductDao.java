package com.goufaning.system.dao;

import com.goufaning.system.bean.Product;
import com.goufaning.system.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gfn on 2017-01-01.
 */
public class ProductDao {
    public boolean insert(Product product) {
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        String sql = "insert imto product_332(name,price,description) values(?,?,?)";
        try {
            conn = JDBCUtils.getConnection();
            perstmt  = conn.prepareStatement(sql);
            perstmt.setString(1,product.getName());
            perstmt.setDouble(2,product.getPrice());
            perstmt.setString(3,product.getDescription());
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
    public Product findByName(String name) {
        String sql = "select * from product_332 where name= ?";
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            perstmt = conn.prepareStatement(sql);
            perstmt.setString(1,name);
            rs = perstmt.executeQuery();
            while (rs.next()) {
               Product product = new Product();
               product.setId(rs.getInt("id"));
               product.setName(rs.getString("name"));
               product.setDescription(rs.getString("description"));
               product.setPrice(rs.getDouble("price"));
               return product;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseResources(rs,perstmt,conn);
        }
        return null;
    }

    public Product findById(int id) {
        String sql = "select * from product_332 where id= ?";
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            perstmt = conn.prepareStatement(sql);
            perstmt.setInt(1,id);
            rs = perstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                return product;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseResources(rs,perstmt,conn);
        }
        return null;
    }

    public List<Product> findAll() {
        String sql = "select * from product_332";
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            perstmt = conn.prepareStatement(sql);
            rs = perstmt.executeQuery();
            List<Product> productList = new LinkedList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                productList.add(product);
            }
            return productList;
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
        String sql = "delete from product_332 where id= ?";
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
