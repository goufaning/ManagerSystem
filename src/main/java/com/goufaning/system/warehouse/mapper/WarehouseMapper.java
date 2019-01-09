package com.goufaning.system.warehouse.mapper;

import com.goufaning.system.warehouse.entity.Warehouse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository // 只用于去除错误提示
public interface WarehouseMapper {
    @Select("select * from warehouse_332")
    List<Warehouse> findAll();

    @Select("select * from warehouse_332 where name= #{name}")
    Warehouse findByName(@Param("name") String name);

    @Select("select * from warehouse_332 where id= #{id}")
    Warehouse findById(@Param("id") int id);
}
