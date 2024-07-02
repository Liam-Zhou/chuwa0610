package com.jackpang.mongo_blog.dao;

import com.jackpang.mongo_blog.entity.GroceryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description: PostRepository
 * date: 6/28/24 10:02 PM
 * author: jinhao_pang
 * version: 1.0
 */
@Repository
public interface ItemRepository extends MongoRepository<GroceryItem, String> {
    @Query("{name:'?0'}")
    GroceryItem findItemByName(String name);

    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    List<GroceryItem> findAll(String category);

    public long count();
}
