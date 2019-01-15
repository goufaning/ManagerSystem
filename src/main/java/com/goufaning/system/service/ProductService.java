package com.goufaning.system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goufaning.system.entity.Product;
import com.goufaning.system.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService implements IProductService{

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getAllProduct() {
        return productMapper.findAll();
    }

    @Override
    public Map<String, Object> selectById(int id) {
        Map<String, Object> resultSet = new HashMap<>();
        List<Product> productList = new ArrayList<>();
        long total = 0;
        Product product = productMapper.findById(id);
        if (product != null) {
            productList.add(product);
            total = 1;
        }
        resultSet.put("data", productList);
        resultSet.put("total", total);
        return resultSet;
    }

    @Override
    public Map<String, Object> selectByName(int offset, int limit, String name) {
        Map<String, Object> resultSet = new HashMap<>();
        List<Product> productList;
        long total = 0;
        boolean isPagination = offset > 0 && limit > 0;
        if (isPagination) {
            PageHelper.offsetPage(offset, limit);
            productList = productMapper.findApproximateByName(name);
            if (productList != null) {
                PageInfo<Product> pageInfo = new PageInfo<>(productList);
                total = pageInfo.getTotal();
            } else {
                productList = new ArrayList<>();
            }
        } else {
            productList = productMapper.findApproximateByName(name);
            if (productList != null) {
                total = productList.size();
            } else {
                productList = new ArrayList<>();
            }
        }
        resultSet.put("data", productList);
        resultSet.put("total", total);
        return resultSet;
    }

    @Override
    public boolean addProduct(Product product) {
        if (product != null) {
            productMapper.insert(product);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        if (product != null) {
            productMapper.update(product.getId(), product.getName(), product.getType(), product.getSize(), product.getPrice(), product.getDescription());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int productId) {
        productMapper.deleteById(productId);
        return true;
    }

    public Map<String, Object> selectByName(String name) {
        return selectByName(-1, -1, name);
    }

    @Override
    public Map<String, Object> selectAll(int offset, int limit) {
        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        List<Product> goodsList;
        long total = 0;
        boolean isPagination = true;
        // validate
        if (offset < 0 || limit < 0)
            isPagination = false;
        // query
        if (isPagination) {
            PageHelper.startPage(offset, limit);
            goodsList = productMapper.findAll();
            if (goodsList != null) {
                PageInfo<Product> pageInfo = new PageInfo<>(goodsList);
                total = pageInfo.getTotal();
            } else
                goodsList = new ArrayList<>();
        } else {
            goodsList = productMapper.findAll();
            if (goodsList != null)
                total = goodsList.size();
            else
                goodsList = new ArrayList<>();
        }
        resultSet.put("data", goodsList);
        resultSet.put("total", total);
        return resultSet;
    }

    @Override
    public Map<String, Object> selectAll() {
        return selectAll(-1, -1);
    }



}
