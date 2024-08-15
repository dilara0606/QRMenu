package com.QRMenu.menu.service.implementation;

import com.QRMenu.menu.dto.CategoryDto;
import com.QRMenu.menu.dto.ItemDto;
import com.QRMenu.menu.entity.Category;
import com.QRMenu.menu.entity.Item;
import com.QRMenu.menu.entity.User;
import com.QRMenu.menu.filter.ItemFilter;
import com.QRMenu.menu.mapper.CategoryMapper;
import com.QRMenu.menu.mapper.ItemMapper;
import com.QRMenu.menu.repository.CategoryRepository;
import com.QRMenu.menu.repository.CategorysItemRepository;
import com.QRMenu.menu.repository.ItemRepository;
import com.QRMenu.menu.repository.UserRepository;
import com.QRMenu.menu.security.JwtService;
import com.QRMenu.menu.service.ItemService;
import com.QRMenu.menu.specification.ItemSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CategorysItemRepository categorysItemRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    @Value("${server.upload.directory}")
    private String uploadDir;
    private final ItemRepository repository;

    @Override
    public ItemDto saveItem(Item item, String token) {

        final String jwt = token.substring(7);
        String email = jwtService.extractUsername(jwt);

        User user = userRepository.findByemail(email);

        String imageUrl = item.getImageUrl();

        if (imageUrl != null) {
            if (imageUrl.startsWith("data:image/")) {
                // Base64 formatındaki resmi işleme
                String[] parts = imageUrl.split(",");
                String imageString = parts.length > 1 ? parts[1] : ""; // Check if parts array has at least 2 elements

                String fileName = "menu_image_" + System.currentTimeMillis() + ".jpg";
                String filePath = uploadDir + "/" + fileName;

                try (OutputStream outputStream = new FileOutputStream(filePath)) {
                    byte[] imageBytes = Base64.getDecoder().decode(imageString);
                    outputStream.write(imageBytes);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                item.setImageUrl(filePath);
            } else {
                // URL formatındaki resmi işleme
                String fileName = "menu_image_" + System.currentTimeMillis() + ".jpg";
                String filePath = uploadDir + "/" + fileName;

                try (InputStream inputStream = new URL(imageUrl).openStream();
                     OutputStream outputStream = new FileOutputStream(filePath)) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                item.setImageUrl(filePath);
            }
        }

        item.setCreatedAt(LocalDate.now());
        item.setUpdatedAt(LocalDate.now());

        return ItemMapper.convert(repository.save(item)) ;
    }

    @Override
    @Transactional
    public void deleteItem(Integer id) {
        categorysItemRepository.deleteByItemId(id);
        repository.deleteById(id);
    }

    @Override
    public ItemDto editItem(Integer id, Item item) {

        Item oldItem = repository.findByid(id);
        String imageUrl = item.getImageUrl();


        if (imageUrl != null) {
            if (imageUrl.startsWith("data:image/")) {
                // Base64 formatındaki resmi işleme
                String[] parts = imageUrl.split(",");
                String imageString = parts.length > 1 ? parts[1] : ""; // Check if parts array has at least 2 elements

                String fileName = "menu_image_" + System.currentTimeMillis() + ".jpg";
                String filePath = uploadDir + "/" + fileName;

                try (OutputStream outputStream = new FileOutputStream(filePath)) {
                    byte[] imageBytes = Base64.getDecoder().decode(imageString);
                    outputStream.write(imageBytes);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                oldItem.setImageUrl(filePath);
            } else {
                // URL formatındaki resmi işleme
                String fileName = "menu_image_" + System.currentTimeMillis() + ".jpg";
                String filePath = uploadDir + "/" + fileName;

                try (InputStream inputStream = new URL(imageUrl).openStream();
                     OutputStream outputStream = new FileOutputStream(filePath)) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                oldItem.setImageUrl(filePath);
            }
        }

        //fronttan namei bak!
        oldItem.setUpdatedAt(LocalDate.now());
        oldItem.setPrice(item.getPrice());
        oldItem.setName(item.getName());
        oldItem.setDescription(item.getDescription());
        itemRepository.save(oldItem);
        return ItemMapper.convert(oldItem);

    }

    @Override
    public List<ItemDto> getAll(String token) {

        final String jwt = token.substring(7);
        String email = jwtService.extractUsername(jwt);

        User user = userRepository.findByemail(email);

        return ItemMapper.convertList(repository.findAll());
    }

    @Override
    public List<ItemDto> searchItem(ItemFilter itemFilter) {
        return ItemMapper.convertList(repository.findAll(ItemSpecification.searchItem(itemFilter)));
    }
}
