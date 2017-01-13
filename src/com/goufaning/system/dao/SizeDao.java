package com.goufaning.system.dao;

import com.goufaning.system.bean.Size;
import com.goufaning.system.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gfn on 2017-01-01.
 */
public class SizeDao {
    public List<Size> findAll() {
        String sql = "select * from size_332";
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            perstmt = conn.prepareStatement(sql);
            rs = perstmt.executeQuery();
            List<Size> sizeList = new LinkedList<>();
            while (rs.next()) {
                Size size = new Size();
                size.setId(rs.getInt("id"));
                size.setName(rs.getString("name"));
                size.setDescription(rs.getString("description"));
                sizeList.add(size);
            }
            return sizeList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseResources(rs,perstmt,conn);
        }
        return null;
    }
}
