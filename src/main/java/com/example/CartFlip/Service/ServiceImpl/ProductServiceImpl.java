package com.example.CartFlip.Service.ServiceImpl;

import com.example.CartFlip.Dtos.RequestDto.ProductRequestDto;
import com.example.CartFlip.Dtos.ResponseDto.ProductResponseDto;
import com.example.CartFlip.Enum.Category;
import com.example.CartFlip.Exceptions.SellerNotFoundException;
import com.example.CartFlip.Model.Product;
import com.example.CartFlip.Model.Seller;
import com.example.CartFlip.Repository.ProductRepository;
import com.example.CartFlip.Repository.SellerRepository;
import com.example.CartFlip.Service.ProductService;
import com.example.CartFlip.Transformers.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductResponseDto addSeller(ProductRequestDto productRequestDto) {

        Seller seller = sellerRepository.findByEmailId(productRequestDto.getSellerEmailId());

        if(seller ==null){
            throw new SellerNotFoundException("Email Id is not registered");
        }

        //dto to entity

        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
        seller.getProducts().add(product);
        product.setSeller(seller);




        Seller savedSeller = sellerRepository.save(seller);
        Product savedProduct = savedSeller.getProducts().get(savedSeller.getProducts().size()-1);

        ProductResponseDto productResponseDto = ProductTransformer.ProductToProductResponseDto(savedProduct);

        return productResponseDto;

    }

    @Override
    public List<ProductResponseDto> getAllProductsByCategoryAndPrice(Category category, int price) {

        List<Product> productList = productRepository.findByCategoryAndPrice(category, price);

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product product : productList){
            productResponseDtoList.add(ProductTransformer.ProductToProductResponseDto(product));
        }

        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> getProductOfACategory(String category) {
        List<Product> productList = productRepository.getProductOfACategory(category);

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product product : productList){
            productResponseDtoList.add(ProductTransformer.ProductToProductResponseDto(product));
        }

        return productResponseDtoList;

    }

    @Override
    public List<ProductResponseDto> getProductOfACategoryPriceGreater(String category, int minPrice) {
        List<Product> productList = productRepository.getProductOfACategoryPriceGreater(category,minPrice);

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product product : productList){
            productResponseDtoList.add(ProductTransformer.ProductToProductResponseDto(product));
        }

        return productResponseDtoList;

    }

    @Override
    public List<ProductResponseDto> getKCheapestProductACategory(String category, int k) {
        List<Product> productList = productRepository.getProductOfACategory(category);

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product product : productList){
            productResponseDtoList.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        Collections.sort(productResponseDtoList,(x, y) -> x.getPrice() - y.getPrice());
        int size = productResponseDtoList.size();
        if(size <= k){
            return productResponseDtoList;
        }
        else{
            for(int i = k  ; i < size ; i++){
                productResponseDtoList.remove(k);//notice this
            }
            return productResponseDtoList;
        }
    }

    @Override
    public List<ProductResponseDto> getKCostliestProductACategory(String category, int k) {
        List<Product> productList = productRepository.getProductOfACategory(category);

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product product : productList){
            productResponseDtoList.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        Collections.sort(productResponseDtoList,(x,y) -> y.getPrice() - x.getPrice());
        int size = productResponseDtoList.size();
        if(size <= k){
            return productResponseDtoList;
        }
        else{
            for(int i = k  ; i < size ; i++){
                productResponseDtoList.remove(k);//notice this
            }
            return productResponseDtoList;
        }
    }

    @Override
    public List<ProductResponseDto> getAllProductOfASeller(String emailId) {
        Seller seller = sellerRepository.findByEmailId(emailId);
        List<Product> productList = seller.getProducts();
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for (Product product : productList){
            productResponseDtoList.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> getAllOutOfProductOfACategory(String category) {
//        List<Product> productList = productRepository.findAll();
//        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
//        for(Product product : productList){
//            if(product.getCategory().equals(category) && product.getProductStatus().equals(ProductStatus.OUT_OF_STOCK)){
//                productResponseDtoList.add(ProductTransformer.ProductToProductResponseDto(product));
//            }
//        }
//        return productResponseDtoList;

        List<Product> productList = productRepository.getAllOutOfProductOfACategory(category);
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product product : productList){
            productResponseDtoList.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        return productResponseDtoList;
    }
}
