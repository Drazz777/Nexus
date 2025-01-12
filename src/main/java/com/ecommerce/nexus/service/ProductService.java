package com.ecommerce.nexus.service;

import com.ecommerce.nexus.payload.ProductDTO;
import com.ecommerce.nexus.payload.ProductResponse;

public interface ProductService {

    public ProductDTO addProduct(Long categoryId, ProductDTO productDTO);

    public ProductResponse getAllProducts(int pageNum, int pageLimit, String sortBy, String sortOrder);

    public ProductResponse searchByCategory(Long categoryId, int pageNum, int pageLimit, String sortBy,
            String sortOrder);

    public ProductResponse searchByKeyword(String keyword, int pageNum, int pageLimit, String sortBy, String sortOrder);

    public ProductDTO updateProduct(Long productId, ProductDTO productDTO);

    public ProductDTO deleteProduct(Long productId);

}
