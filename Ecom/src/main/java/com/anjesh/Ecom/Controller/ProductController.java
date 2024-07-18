package com.anjesh.Ecom.Controller;

import com.anjesh.Ecom.Model.Product;
import com.anjesh.Ecom.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
     private ProductService service;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return service.getAllProduct();
    }
    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable int id){
        return service.getProductById(id);
    }
}

