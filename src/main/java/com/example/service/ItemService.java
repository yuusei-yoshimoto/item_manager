package com.example.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Item;
import com.example.form.ItemForm;
import com.example.repository.ItemRepository;

@Service
public class ItemService {
	private final ItemRepository itemRepository;

	@Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

	public List<Item> findAll() {
		return this.itemRepository.findAll();
	}

	public Item save(ItemForm itemForm) {
	    Item item = new Item();
	    item.setName(itemForm.getName());
	    item.setPrice(itemForm.getPrice());
	    item.setCategoryId(itemForm.getCategoryId());
	    item.setStock(0);
	    return this.itemRepository.save(item);
	}

	public Item findById(Integer id) {
		Optional<Item> optionalItem = this.itemRepository.findById(id);
		Item item = optionalItem.get();
		return item;
	}

	public Item update(Integer id, ItemForm itemForm) {
		Item item = this.findById(id);
		item.setName(itemForm.getName());
		item.setPrice(itemForm.getPrice());
		item.setCategoryId(itemForm.getCategoryId());
		return this.itemRepository.save(item);
	}

	public Item delete(Integer id) {
		Item item = this.findById(id);
		item.setDeletedAt(LocalDateTime.now());
		return this.itemRepository.save(item);
	}

	public List<Item> findByDeletedAtIsNull() {
		return this.itemRepository.findByDeletedAtIsNull();
	}

	public Item nyuka(Integer id, Integer inputValue) {
		Item item = this.findById(id);
		item.setStock(item.getStock() + inputValue);
		return this.itemRepository.save(item);
	}

	public Item shukka(Integer id, Integer inputValue) {
		Item item = this.findById(id);
		if (item.getStock() >= inputValue) {
			item.setStock(item.getStock() - inputValue);
		}
		return this.itemRepository.save(item);
	}

}
