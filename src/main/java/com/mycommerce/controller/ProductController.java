package com.mycommerce.controller;

import com.mycommerce.model.Product;
import com.mycommerce.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product newProduct = productService.saveProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> productList(){
        return ResponseEntity.ok(productService.productList());
    }

    @GetMapping("/id")
    public ResponseEntity<Product> searchByProductId(@PathVariable Long id){
        return ResponseEntity.ok(productService.searchByProductId(id));
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Product>> productListBuRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(productService.productListByRestaurant(restaurantId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct (@PathVariable Long id, @RequestBody Product product){
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.inactivateProduct(id);
        return ResponseEntity.noContent().build();
    }

}
