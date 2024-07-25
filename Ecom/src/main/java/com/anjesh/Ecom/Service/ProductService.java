package com.anjesh.Ecom.Service;

import com.anjesh.Ecom.Model.Product;
import com.anjesh.Ecom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

//Get all the products
    public List<Product> getAllProduct() {
            return repo.findAll();
    }

//Get the product by id.
    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

// Add new products
    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {

        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);
    }

//Update the existing product in the db.
    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
       product.setImageData(imageFile.getBytes());
       product.setImageName(imageFile.getOriginalFilename());
       product.setImageType(imageFile.getContentType());
        return repo.save(product);
    }

//Delete the product by its id.
    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return repo.searchProduct(keyword);
    }
}
