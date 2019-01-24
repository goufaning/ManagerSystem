package com.goufaning.warehouse.app.dao;


import com.goufaning.warehouse.app.bean.OutTable;
import com.goufaning.warehouse.app.util.JDBCUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gfn on 2017-01-01.
 */
public class OutTableDao {
    public boolean insert(OutTable outTable) {
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        String sql = "insert into out_332(id,provide_id,warehouse_id,outdate,user_id) values(seq_out_332.nextval,?,?,?,?)";
        try {
            conn = JDBCUtils.getConnection();
            perstmt  = conn.prepareStatement(sql);
            perstmt.setInt(1,outTable.getProviderId());
            perstmt.setInt(2,outTable.getWarehouseId());
            perstmt.setDate(3,new Date(System.currentTimeMillis()));
            perstmt.setInt(4,outTable.getWorkerId());
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
    public List<OutTable> findAll() {
        String sql = "select * from out_332";
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            perstmt = conn.prepareStatement(sql);
            rs = perstmt.executeQuery();
            List<OutTable> outTableList = new LinkedList<>();
            while (rs.next()) {
                OutTable outTable = new OutTable();
                outTable.setId(rs.getInt("id"));
                outTable.setProviderId(rs.getInt("provide_id"));
                outTable.setWarehouseId(rs.getInt("warehouse_id"));
                outTable.setDate( rs.getDate("outdate"));
                outTable.setWorkerId(rs.getInt("user_id"));
                outTableList.add(outTable);
            }
            return outTableList;
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
        String sql = "delete from out_332 where id= ?";
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

    public int getMaxId() {
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        String sql = "select max(id) from out_332";
        try {
            conn = JDBCUtils.getConnection();
            perstmt = conn.prepareStatement(sql);
            rs = perstmt.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseResources(rs,perstmt,conn);
        }
        return 1;
    }
}
