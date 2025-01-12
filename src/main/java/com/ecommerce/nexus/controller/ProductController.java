package com.ecommerce.nexus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.nexus.config.AppConstants;
import com.ecommerce.nexus.payload.ProductDTO;
import com.ecommerce.nexus.payload.ProductResponse;
import com.ecommerce.nexus.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/admin/categories/{categoryId}/product", method = RequestMethod.POST)
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO,
            @PathVariable Long categoryId) {
        ProductDTO newProductDTO = productService.addProduct(categoryId, productDTO);
        return new ResponseEntity<>(newProductDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/public/products", method = RequestMethod.GET)
    public ResponseEntity<ProductResponse> getAllProducts(
            @RequestParam(name = "pageNum", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNum,
            @RequestParam(name = "pageLimit", defaultValue = AppConstants.PAGE_LIMIT, required = false) int pageLimit,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_PRODUCTS_ORDER, required = false) String sortOrder) {

        ProductResponse productResponse = productService.getAllProducts(pageNum, pageLimit, sortBy, sortOrder);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/public/categories/{categoryId}/product", method = RequestMethod.GET)
    public ResponseEntity<ProductResponse> getProductsByCategory(@PathVariable Long categoryId,
            @RequestParam(name = "pageNum", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNum,
            @RequestParam(name = "pageLimit", defaultValue = AppConstants.PAGE_LIMIT, required = false) int pageLimit,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_PRODUCTS_ORDER, required = false) String sortOrder) {
        ProductResponse productResponse = productService.searchByCategory(categoryId, pageNum, pageLimit, sortBy,
                sortOrder);
        return new ResponseEntity<>(productResponse, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/public/products/keyword/{keyword}", method = RequestMethod.GET)
    public ResponseEntity<ProductResponse> getProductsByKeyword(@PathVariable String keyword,
            @RequestParam(name = "pageNum", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNum,
            @RequestParam(name = "pageLimit", defaultValue = AppConstants.PAGE_LIMIT, required = false) int pageLimit,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_PRODUCTS_ORDER, required = false) String sortOrder) {
        ProductResponse productResponse = productService.searchByKeyword(keyword, pageNum, pageLimit, sortBy,
                sortOrder);
        return new ResponseEntity<>(productResponse, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/admin/products/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO,
            @PathVariable Long productId) {
        ProductDTO updatedProductDTO = productService.updateProduct(productId, productDTO);
        return new ResponseEntity<>(updatedProductDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/products/{productId}", method = RequestMethod.DELETE)
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long productId) {
        ProductDTO productDTO = productService.deleteProduct(productId);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }
}
