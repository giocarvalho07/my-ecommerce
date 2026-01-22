package com.mycommerce.service;

import com.mycommerce.model.Restaurant;
import com.mycommerce.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant saveRestaurant(Restaurant restaurant) {
        restaurant.setActive(true);
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> restaurantList(){
        return restaurantRepository.findByActiveTrue();
    }

    public Restaurant searchByRestaurantId(Long id){
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found by id: " + id));
    }

    public List<Restaurant> searchByReaturantCategory(String category){
        return restaurantRepository.findByCategory(category);
    }

    public Restaurant updateRestaurant(Long id, Restaurant updateRestaurant){
        Restaurant restaurantExist = searchByRestaurantId(id);

        restaurantExist.setName(updateRestaurant.getName());
        restaurantExist.setCategory(updateRestaurant.getCategory());
        restaurantExist.setAddress(updateRestaurant.getAddress());
        restaurantExist.setPhone(updateRestaurant.getPhone());
        restaurantExist.setFeeDelivery(updateRestaurant.getFeeDelivery());
        restaurantExist.setEvaluation(updateRestaurant.getEvaluation());

        return restaurantRepository.save(restaurantExist);
    }

    public void inactivateRestaurant(Long id){
        Restaurant restaurant = searchByRestaurantId(id);
        restaurant.setActive(false);
        restaurantRepository.save(restaurant);
    }

}
