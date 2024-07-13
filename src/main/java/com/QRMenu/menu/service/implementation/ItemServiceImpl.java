package com.QRMenu.menu.service.implementation;

import com.QRMenu.menu.dto.CategoryDto;
import com.QRMenu.menu.dto.ItemDto;
import com.QRMenu.menu.entity.Category;
import com.QRMenu.menu.entity.Item;
import com.QRMenu.menu.mapper.CategoryMapper;
import com.QRMenu.menu.mapper.ItemMapper;
import com.QRMenu.menu.repository.ItemRepository;
import com.QRMenu.menu.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    @Value("${server.upload.directory}")
    private String uploadDir;
    private final ItemRepository repository;

    @Override
    public ItemDto saveItem(Item item) {

        item.setCreatedAt(LocalDateTime.now());
        item.setUpdatedAt(LocalDateTime.now());

        return ItemMapper.convert(repository.save(item)) ;
    }

    @Override
    public void deleteItem(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public ItemDto editItem(Integer id, Item item) {

        Item oldItem = repository.findByid(id);

        if(item.getImageUrl() != null){
            String base64Image = item.getImageUrl();
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
            oldItem.setImageUrl(filePath);
        }

        //fronttan namei bak!
        oldItem.setUpdatedAt(LocalDateTime.now());
        oldItem.setPrice(item.getPrice());
        oldItem.setName(item.getName());
        oldItem.setDescription(item.getDescription());

        return ItemMapper.convert(oldItem);
    }

    @Override
    public List<ItemDto> getAll() {
        return ItemMapper.convertList(repository.findAll());
    }
}
