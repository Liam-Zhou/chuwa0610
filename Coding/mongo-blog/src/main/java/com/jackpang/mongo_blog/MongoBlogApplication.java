package com.jackpang.mongo_blog;

import com.jackpang.mongo_blog.dao.ItemRepository;
import com.jackpang.mongo_blog.entity.GroceryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MongoBlogApplication implements CommandLineRunner {
	@Autowired
	ItemRepository itemRepository;

	public static void main(String[] args) {
		SpringApplication.run(MongoBlogApplication.class, args);
	}

	@Override
	public void run(String... args) {
		itemRepository.deleteAll();

		// save a couple of customers
		itemRepository.save(new GroceryItem("1", "Apple", 10, "Fruit"));
		itemRepository.save(new GroceryItem("2", "Orange", 20, "Fruit"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (GroceryItem groceryItem : itemRepository.findAll()) {
			System.out.println(groceryItem.toString());
		}

	}
}
