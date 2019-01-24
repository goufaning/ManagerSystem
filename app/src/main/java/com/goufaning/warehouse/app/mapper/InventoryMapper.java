package com.goufaning.warehouse.app.mapper;

import com.goufaning.warehouse.app.entity.Inventory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface InventoryMapper {

    @Select("select * from inventory_332")
    List<Inventory> findAll();

    @Insert("insert into inventory_332(product_id,num,warehouse_id,update_time) values(#{productId},#{num},#{warehouseId},#{createTime})")
    int add(@Param("productId") int productId, @Param("num") int num, @Param("warehouseId") int warehouseId, @Param("createTime") Date createTime);

    int delete();

    int update();

}
