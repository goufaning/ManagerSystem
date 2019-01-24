package com.goufaning.warehouse.app.dao;


import com.goufaning.warehouse.app.bean.OutDetail;
import com.goufaning.warehouse.app.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gfn on 2017-01-01.
 */
public class OutDetailDao {
    public boolean insert(OutDetail outDetail) {
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        String sql = "insert into out_detail_332(id,prodect_id,size_id,num,unit_id,price,out_id) values(seq_outdetail_332.nextval,?,?,?,?,?,?)";
        try {
            conn = JDBCUtils.getConnection();
            perstmt  = conn.prepareStatement(sql);
            perstmt.setInt(1,outDetail.getProductId());
            perstmt.setInt(2,outDetail.getSizeId());
            perstmt.setInt(3,outDetail.getNumber());
            perstmt.setInt(4,outDetail.getUnitId());
            perstmt.setDouble(5,outDetail.getPrice());
            perstmt.setInt(6,outDetail.getOutId());
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
    public List<OutDetail> findAll() {
        String sql = "select * from out_detail_332";
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            perstmt = conn.prepareStatement(sql);
            rs = perstmt.executeQuery();
            List<OutDetail> outDetails = new LinkedList<>();
            while (rs.next()) {
                OutDetail outDetail = new OutDetail();
                outDetail.setId(rs.getInt("id"));
                outDetail.setProductId(rs.getInt("prodect_id"));
                outDetail.setNumber(rs.getInt("num"));
                outDetail.setOutId(rs.getInt("out_id"));
                outDetail.setSizeId(rs.getInt("size_id"));
                outDetail.setUnitId(rs.getInt("unit_id"));
                outDetail.setPrice(rs.getDouble("price"));
                outDetails.add(outDetail);
            }
            return outDetails;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseResources(rs,perstmt,conn);
        }
        return null;
    }
}
