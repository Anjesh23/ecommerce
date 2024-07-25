package com.anjesh.Ecom.Controller;

import com.anjesh.Ecom.Model.Product;
import com.anjesh.Ecom.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PutMapping("/product/{id}")
    public ResponseEntity<String>updateProduct(@PathVariable int id,
                                               @RequestPart Product product,
                                               @RequestPart MultipartFile imageFile) throws IOException {

        Product product1 = service.updateProduct(id,product,imageFile);
        if(product1 != null){
            return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Error updating product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {

        Product product = service.getProductById(id);
        if (product != null) {
            service.deleteProduct(id);
            return new ResponseEntity<>("Product is deleted", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Error deleting product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){

        System.out.println("searching products"+ keyword);
        List<Product> products = service.searchProducts(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}

