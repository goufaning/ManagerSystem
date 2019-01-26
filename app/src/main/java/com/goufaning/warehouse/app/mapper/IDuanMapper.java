package com.goufaning.warehouse.app.mapper;

import com.goufaning.warehouse.app.entity.Duan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IDuanMapper {

    @Insert("inset into duan (content, time) values (#{duan.content}, #{duan.time})")
    void insert(@Param("duan") Duan duan);
}
