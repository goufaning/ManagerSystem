package com.goufaning.warehouse.app.mapper;

import com.goufaning.warehouse.app.entity.Warehouse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository // 只用于去除错误提示
public interface WarehouseMapper {

    @Select("select * from warehouse_332")
    List<Warehouse> selectAll();

    @Select("select * from ")
    List<Warehouse> selectUnassign();

    @Select("select * from warehouse_332 where name= #{name}")
    List<Warehouse> findByName(@Param("name") String name);

    @Select("select * from warehouse_332 where id= #{id}")
    Warehouse findById(@Param("id") int id);

    @Select("select * from warehouse_332 where address=#{address}")
    List<Warehouse> selectByAddress(@Param("address") String address);

    @Insert("insert into warehouse_332(name,adderss,area,description,status) values (#{warehouse.name},#{warehouse.address},#{warehouse.area},#{warehouse.description},#{warehouse.status})")
    void insert(@Param("warehouse") Warehouse warehouse);

    @Update("update warehouse_332 set name=#{warehouse.name},address=#{warehouse.address},area=#{warehouse.area},description=#{warehouse.description},status=#{warehouse.status} where id = #{warehouse.id}")
    void update(@Param("warehouse") Warehouse warehouse);

    @Delete("delete from warehouse_332 where id = #{id}")
    void deleteById(@Param("id") int id);
}
