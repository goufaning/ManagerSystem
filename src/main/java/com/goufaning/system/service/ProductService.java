package com.goufaning.system.service;

import com.goufaning.system.product.entity.Product;
import com.goufaning.system.dao.ProductDao;

import java.util.List;

/**
 * Created by gfn on 2017-01-01.
 */
public class ProductService {
    ProductDao dao = new ProductDao();
    public List<Product> getAllProduct() {

        return dao.findAll();
    }
    public Product findByName(String name) {
        return dao.findByName(name);
    }

    public  Product findById(int id) {
        return dao.findById(id);
    }
}
