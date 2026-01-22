package com.mycommerce.service;

import com.mycommerce.model.Product;
import com.mycommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        if (product.getAvailable() == null) {
            product.setAvailable(true);
        }
        return productRepository.save(product);
    }

    public List<Product> productList(){
        return productRepository.findByAvailableTrue();
    }

    public Product searchByProductId(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }

    public List<Product> productListByRestaurant (Long restaurantID) {
        return productRepository.findByRestaurantId(restaurantID);
    }

    public Product updateProduct(Long id, Product productUpdate){
        Product productExist = searchByProductId(id);

        productExist.setName(productUpdate.getName());
        productExist.setDescription(productUpdate.getDescription());
        productExist.setCategory(productUpdate.getCategory());
        productExist.setPrice(productUpdate.getPrice());
        productExist.setAvailable(productUpdate.getAvailable());
        return productRepository.save(productExist);
    }

    public void inactivateProduct(Long id){
        Product product = searchByProductId(id);
        productRepository.delete(product);
    }

}
