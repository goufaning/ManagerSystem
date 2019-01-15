package com.goufaning.system.service;

import com.goufaning.system.entity.Product;

import java.util.List;
import java.util.Map;

public interface IProductService {

    List<Product> getAllProduct();

    Map<String, Object> selectAll();

    Map<String, Object> selectAll(int offset, int limit);

    Map<String, Object> selectById(int id);

    Map<String, Object> selectByName(String name);

    Map<String, Object> selectByName(int offset, int limit, String name);

    boolean addProduct(Product product);

    boolean updateProduct(Product product);

    boolean deleteProduct(int productId);
}
