package com.anjesh.Ecom.Controller;

import com.anjesh.Ecom.Model.Product;
import com.anjesh.Ecom.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Provider;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
     private ProductService service;

    @GetMapping("/products")
    public ResponseEntity <List<Product>> getAllProducts(){  //Use the Response Entity to send the status
// to the frontend
        return new ResponseEntity<>(
                service.getAllProduct(), HttpStatus.OK);
    }
    @GetMapping("/product/{id}")
    public  ResponseEntity <Product> getProductById(@PathVariable int id){
        return new ResponseEntity<>(
                service.getProductById(id), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile){
        try {
            Product product1 = service.addProduct(product, imageFile);
            return new  ResponseEntity<>(product1, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>("Error adding product", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("product/{productId}/image")
    public ResponseEntity<byte[]>getImageByProductId(@PathVariable int productId){

        Product product = service.getProductById(productId);
        byte[] imageFile = product.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }
}

