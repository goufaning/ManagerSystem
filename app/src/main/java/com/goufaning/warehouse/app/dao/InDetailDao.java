package com.goufaning.warehouse.app.dao;


import com.goufaning.warehouse.app.bean.InDetail;
import com.goufaning.warehouse.app.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gfn on 2017-01-01.
 */
public class InDetailDao {
    public boolean insert(InDetail inDetail) {
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        String sql = "insert into in_detail_332(id,prodect_id,size_id,num,unit_id,price,in_id) values(seq_indetail_332.nextval,?,?,?,?,?,?)";
        try {
            conn = JDBCUtils.getConnection();
            perstmt  = conn.prepareStatement(sql);
            perstmt.setInt(1,inDetail.getProductId());
            perstmt.setInt(2,inDetail.getSizeId());
            perstmt.setInt(3,inDetail.getNumber());
            perstmt.setInt(4,inDetail.getUnitId());
            perstmt.setDouble(5,inDetail.getPrice());
            perstmt.setInt(6,inDetail.getInId());
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
    public List<InDetail> findAll() {
        String sql = "select * from in_detail_332";
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            perstmt = conn.prepareStatement(sql);
            rs = perstmt.executeQuery();
            List<InDetail> inDetailList = new LinkedList<>();
            while (rs.next()) {
                InDetail inDetail = new InDetail();
                inDetail.setId(rs.getInt("id"));
                inDetail.setProductId(rs.getInt("prodect_id"));
                inDetail.setNumber(rs.getInt("num"));
                inDetail.setInId(rs.getInt("in_id"));
                inDetail.setSizeId(rs.getInt("size_id"));
                inDetail.setUnitId(rs.getInt("unit_id"));
                inDetail.setPrice(rs.getDouble("price"));
                inDetailList.add(inDetail);
            }
            return inDetailList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseResources(rs,perstmt,conn);
        }
        return null;
    }
}
