package com.goufaning.system.product.mapper;

import com.goufaning.system.product.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository // 只用于去除错误提示
public interface ProductMapper {


    @Select("select * from product_332")
    List<Product> findAll();

    @Select("select * from product_332 where name= #{name}")
    Product findByName(@Param("name") String name);

    @Select("select * from product_332 where id= #{id}")
    Product findById(@Param("id") int id);
}
