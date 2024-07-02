package com.jackpang.mongo_blog.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("groceryitems")
public class GroceryItem {

        @Id
        private String id;

        private String name;
        private int quantity;
        private String category;
        
        public GroceryItem(String id, String name, int quantity, String category) {
            super();
            this.id = id;
            this.name = name;
            this.quantity = quantity;
            this.category = category;
        }

    @Override
    public String toString() {
        return "GroceryItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                '}';
    }
}