package com.goufaning.system.mapper;

import com.goufaning.system.entity.WarehouseAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WarehouseAdminMapper {

    @Select("select * from warehouse_admin_332 where id = #{id}")
    WarehouseAdmin selectById(@Param("id") int id);

    @Select("select * from warehouse_admin_332 where name = #{name}")
    List<WarehouseAdmin> selectByName(@Param("name") String name);

    @Select("select * from warehouse")
    List<WarehouseAdmin> selectAll();

    @Select("select * from warehouse_admin_332 where warehouse_id = #{warehouseId}")
    WarehouseAdmin selectByWarehouseId(@Param("warehouseId") int warehouseId);

    @Select("")
    void Insert(WarehouseAdmin admin);

    void update(WarehouseAdmin admin);

    void deleteById(int id);

}
