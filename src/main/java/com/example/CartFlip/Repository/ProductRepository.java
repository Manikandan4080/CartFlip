package com.example.CartFlip.Repository;

import com.example.CartFlip.Enum.Category;
import com.example.CartFlip.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findByCategoryAndPrice(Category category, int price);

    @Query(value = "select * from product where product.category =:category",nativeQuery = true)
    List<Product> getProductOfACategory(String category);


    @Query(value = "select * from product where product.category =:category AND product.price > :minPrice",nativeQuery = true)
    List<Product> getProductOfACategoryPriceGreater(String category, int minPrice);

    @Query(value = "select * from product where product.category =:category AND product.product_status = 'OUT_OF_STOCK' ",nativeQuery = true)
    List<Product> getAllOutOfProductOfACategory(String category);

}
