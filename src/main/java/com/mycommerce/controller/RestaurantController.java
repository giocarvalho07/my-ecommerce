package com.mycommerce.controller;

import com.mycommerce.model.Restaurant;
import com.mycommerce.service.RestaurantService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant){
        Restaurant newRestaurant = restaurantService.saveRestaurant(restaurant);
        return new ResponseEntity<>(newRestaurant, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> listRestaurant(){
        return ResponseEntity.ok(restaurantService.restaurantList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> searchByRestaurantId(@PathVariable Long id){
        return ResponseEntity.ok(restaurantService.searchByRestaurantId(id));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Restaurant> searchByRestaurantCategorybyId(@PathVariable Long id){
        return ResponseEntity.ok(restaurantService.searchByRestaurantId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant){
        return ResponseEntity.ok(restaurantService.updateRestaurant(id, restaurant));
    }

    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id){
        restaurantService.inactivateRestaurant(id);
        return ResponseEntity.noContent().build();
    }

}
