package com.goufaning.system.dao;

import com.goufaning.system.bean.Inventory;
import com.goufaning.system.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gfn on 2017-01-01.
 */
public class InventoryDao {
    public List<Inventory> findAll() {
        String sql = "select * from inventory_332";
        Connection conn = null;
        PreparedStatement perstmt  = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            perstmt = conn.prepareStatement(sql);
            rs = perstmt.executeQuery();
            List<Inventory> inventoryList = new LinkedList<>();
            while (rs.next()) {
                Inventory inventory = new Inventory();
                inventory.setId(rs.getInt("id"));
                inventory.setWarehouseId(rs.getInt("warehouse_id"));
                inventory.setProductId(rs.getInt("product_id"));
                inventory.setNum(rs.getInt("num"));
                inventory.setUpdateDate(rs.getDate("update_time"));
                inventoryList.add(inventory);
            }
            return inventoryList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseResources(rs,perstmt,conn);
        }
        return null;
    }
}
