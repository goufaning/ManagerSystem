package com.goufaning.warehouse.app.dao;


import com.goufaning.warehouse.app.bean.InTable;
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
public class InTableDao {
    public boolean insert(InTable inTable) {
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        String sql = "insert into in_332(id,provide_id,warehouse_id,indate,user_id) values(seq_in_332.nextval,?,?,?,?)";
        try {
            conn = JDBCUtils.getConnection();
            perstmt  = conn.prepareStatement(sql);
            perstmt.setInt(1,inTable.getProviderId());
            perstmt.setInt(2,inTable.getWarehouseId());
            perstmt.setDate(3,new Date(System.currentTimeMillis()));
            perstmt.setInt(4,inTable.getWorkerId());
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
    public List<InTable> findAll() {
        String sql = "select * from in_332";
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            perstmt = conn.prepareStatement(sql);
            rs = perstmt.executeQuery();
            List<InTable> inTableList = new LinkedList<>();
            while (rs.next()) {
                InTable inTable = new InTable();
                inTable.setId(rs.getInt("id"));
                inTable.setProviderId(rs.getInt("provide_id"));
                inTable.setWarehouseId(rs.getInt("warehouse_id"));
                inTable.setDate(rs.getDate("indate"));
                inTable.setWorkerId(rs.getInt("user_id"));
                inTableList.add(inTable);
            }
            return inTableList;
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
        String sql = "delete from in_332 where id= ?";
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
        String sql = "select max(id) from in_332";
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
