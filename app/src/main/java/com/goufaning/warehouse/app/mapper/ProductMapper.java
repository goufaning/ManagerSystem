package com.goufaning.warehouse.app.mapper;

import com.goufaning.warehouse.app.entity.Product;
import org.apache.ibatis.annotations.*;
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

    @Select("select * from product_332 where name like concat(concat('%', #{name}), '%')")
    List<Product> findApproximateByName(@Param("name") String name);

    @Insert("insert into product_332(name,type,size,price,description)VALUES (#{product.name},#{product.type},#{product.size},#{product.price},#{product.description});")
    void insert(@Param("product") Product product);

    @Insert("<script>"  +
            "INSERT INTO product_332（name,type,size,price,description） VALUES" +
            "<foreach collection=\"list\" item=\"item1\" index=\"index\"  separator=\",\">" +
            "(#{item1.name},#{item1.type},#{item1.size},#{item1.price},#{item1.description})" +
            "</foreach>" +
            "</script>")
    void insertList(@Param("list") List<Product> products);

    @Update("update product_332 set name=#{name},type=#{type},size=#{size},price=#{price},description=#{description} where id=#{id}")
    void update(@Param("id") int id, @Param("name") String name, @Param("type") String type, @Param("size") String size, @Param("price") double price, @Param("description") String description);

    @Delete("delete from product_332 where id=#{id}")
    void deleteById(@Param("id") int id);

    @Delete("delete from product_332 where name=#{name}")
    void deleteByName(@Param("name") String name);

}
