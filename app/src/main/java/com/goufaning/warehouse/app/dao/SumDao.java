package com.goufaning.warehouse.app.dao;


import com.goufaning.warehouse.app.bean.InBaobiaoSum;
import com.goufaning.warehouse.app.bean.OutBaobiaoSum;
import com.goufaning.warehouse.app.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gfn on 2017-01-05.
 */
public class SumDao {
    public List<InBaobiaoSum> findIn(int days) {
        String sql = "select prodect_id,sum(num),sum(price*num)" +
                "from (select * from in_332,in_detail_332 " +
                "where in_332.id = in_detail_332.in_id and in_332.indate > sysdate -? )  group by prodect_id";
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            perstmt = conn.prepareStatement(sql);
            perstmt.setInt(1,days);
            rs = perstmt.executeQuery();
            List<InBaobiaoSum> list = new LinkedList<>();
            while (rs.next()) {
                InBaobiaoSum inBaobiaoSum = new InBaobiaoSum();
                inBaobiaoSum.setProductId(rs.getInt(1));
                inBaobiaoSum.setSumNum(rs.getInt(2));
                inBaobiaoSum.setSumPrice(rs.getLong(3));
                list.add(inBaobiaoSum);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseResources(rs,perstmt,conn);
        }
        return null;
    }
    public List<OutBaobiaoSum> findOut(int days) {
        String sql = "select prodect_id,sum(num),sum(price*num)" +
                "from (select * from out_332,out_detail_332 " +
                "where out_332.id = out_detail_332.out_id and out_332.outdate > sysdate - 1)  group by prodect_id";
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            perstmt = conn.prepareStatement(sql);
            //perstmt.setInt(1,days);
            rs = perstmt.executeQuery();
            List<OutBaobiaoSum> list = new LinkedList<>();
            while (rs.next()) {
                OutBaobiaoSum outBaobiaoSum = new OutBaobiaoSum();
                outBaobiaoSum.setProductId(rs.getInt(1));
                outBaobiaoSum.setSumNum(rs.getInt(2));
                outBaobiaoSum.setSumPrice(rs.getLong(3));
                list.add(outBaobiaoSum);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseResources(rs,perstmt,conn);
        }
        return null;
    }
}
