package com.QRMenu.menu.service.implementation;

import com.QRMenu.menu.dto.RestaurantDto;
import com.QRMenu.menu.entity.Restaurant;
import com.QRMenu.menu.mapper.RestaurantMapper;
import com.QRMenu.menu.repository.RestaurantRepository;
import com.QRMenu.menu.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    @Value("${server.upload.directory}")
    private String uploadDir;
    private final RestaurantRepository repository;

    @Override
    public List<RestaurantDto> getRestaurantInfo() {
        return RestaurantMapper.convertList(repository.findAll());
    }

    @Override
    public RestaurantDto editRestaurant(Integer id, Restaurant restaurant) {
        Restaurant oldRestaurant = repository.findByid(id);

        if(restaurant.getImageUrl() != null){
            String base64Image = restaurant.getImageUrl();
            String[] parts = base64Image.split(",");
            String imageString = parts[1];

            String fileName = "menu_image_" + System.currentTimeMillis() + ".jpg";
            String filePath = uploadDir + "/" + fileName;

            try (OutputStream outputStream = new FileOutputStream(filePath)) {
                byte[] imageBytes = Base64.getDecoder().decode(imageString);
                outputStream.write(imageBytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            oldRestaurant.setImageUrl(filePath);
        }

        //fronttan namei bak!
        oldRestaurant.setName(restaurant.getName());
        oldRestaurant.setAddress(restaurant.getAddress());
        oldRestaurant.setPhone(restaurant.getPhone());
        oldRestaurant.setEmail(restaurant.getEmail());

        return RestaurantMapper.convert(repository.save(oldRestaurant));
    }

    @Override
    public RestaurantDto createRestaurant(Restaurant restaurant) {

        String imageUrl = restaurant.getImageUrl();

        if (imageUrl != null) {
            if (imageUrl.startsWith("data:image/")) {
                // Base64 formatındaki resmi işleme
                String[] parts = imageUrl.split(",");
                String imageString = parts.length > 1 ? parts[1] : ""; // Check if parts array has at least 2 elements

                String fileName = "restaurant_image_" + System.currentTimeMillis() + ".jpg";
                String filePath = uploadDir + "/" + fileName;

                try (OutputStream outputStream = new FileOutputStream(filePath)) {
                    byte[] imageBytes = Base64.getDecoder().decode(imageString);
                    outputStream.write(imageBytes);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                restaurant.setImageUrl(filePath);
            } else {
                restaurant.setImageUrl(imageUrl);
            }
        }

        return RestaurantMapper.convert(repository.save(restaurant));
    }
}
