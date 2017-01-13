package com.goufaning.system.dao;

import com.goufaning.system.bean.WareHouse;
import com.goufaning.system.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gfn on 2017-01-02.
 */
public class WareHouseDao {
    public List<WareHouse> findAll() {
        String sql = "select * from warehouse_332";
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            perstmt = conn.prepareStatement(sql);
            rs = perstmt.executeQuery();
            List<WareHouse> warehouseList = new LinkedList<>();
            while (rs.next()) {
                WareHouse warehouse = new WareHouse();
                warehouse.setId(rs.getInt("id"));
                warehouse.setName(rs.getString("name"));
                warehouse.setAddress(rs.getString("address"));
                warehouse.setArea(rs.getDouble("area"));
                warehouse.setDescription(rs.getString("description"));
                warehouseList.add(warehouse);
            }
            return warehouseList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseResources(rs,perstmt,conn);
        }
        return null;
    }

    public WareHouse findByName(String name) {
        String sql = "select * from warehouse_332 where name= ?";
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            perstmt = conn.prepareStatement(sql);
            perstmt.setString(1,name);
            rs = perstmt.executeQuery();
            while (rs.next()) {
                WareHouse warehouse = new WareHouse();
                warehouse.setId(rs.getInt("id"));
                warehouse.setName(rs.getString("name"));
                warehouse.setAddress(rs.getString("address"));
                warehouse.setArea(rs.getDouble("area"));
                warehouse.setDescription(rs.getString("description"));
                return warehouse;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseResources(rs,perstmt,conn);
        }
        return null;
    }

    public WareHouse findById(int id) {
        String sql = "select * from warehouse_332 where id= ?";
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            perstmt = conn.prepareStatement(sql);
            perstmt.setInt(1,id);
            rs = perstmt.executeQuery();
            while (rs.next()) {
                WareHouse warehouse = new WareHouse();
                warehouse.setId(rs.getInt("id"));
                warehouse.setName(rs.getString("name"));
                warehouse.setAddress(rs.getString("address"));
                warehouse.setArea(rs.getDouble("area"));
                warehouse.setDescription(rs.getString("description"));
                return warehouse;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseResources(rs,perstmt,conn);
        }
        return null;
    }
}
