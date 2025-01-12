package com.ecommerce.nexus.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.nexus.exceptions.APIException;
import com.ecommerce.nexus.exceptions.ResourceNotFoundException;
import com.ecommerce.nexus.model.Cart;
import com.ecommerce.nexus.model.Category;
import com.ecommerce.nexus.model.Product;
import com.ecommerce.nexus.payload.CartDTO;
import com.ecommerce.nexus.payload.ProductDTO;
import com.ecommerce.nexus.payload.ProductResponse;
import com.ecommerce.nexus.repositories.CartRepository;
import com.ecommerce.nexus.repositories.CategoryRepository;
import com.ecommerce.nexus.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private CategoryRepository categoryRepository;

        @Autowired
        private CartRepository cartRepository;

        @Autowired
        private CartService cartService;

        @Autowired
        private ModelMapper modelMapper;

        @Override
        public ProductDTO addProduct(Long categoryId, ProductDTO productDTO) {
                Category category = categoryRepository.findById(categoryId)
                                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

                Product existingProduct = productRepository.findByProductNameAndCategory(productDTO.getProductName(),
                                category);
                if (existingProduct != null) {
                        throw new APIException("Product with name " + productDTO.getProductName()
                                        + " already exists in category "
                                        + category.getCategoryName());
                }

                Product product = modelMapper.map(productDTO, Product.class);
                product.setImage("default.png");
                product.setCategory(category);
                double specialPrice = (1 - product.getDiscount() * 0.01) * product.getPrice();
                product.setSpecialPrice(specialPrice);
                Product savedProduct = productRepository.save(product);
                return modelMapper.map(savedProduct, ProductDTO.class);
        }

        @Override
        public ProductResponse getAllProducts(int pageNum, int pageLimit, String sortBy, String sortOrder) {
                Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                                ? Sort.by(sortBy).ascending()
                                : Sort.by(sortBy).descending();

                Pageable pageDetails = PageRequest.of(pageNum - 1, pageLimit, sortByAndOrder);
                Page<Product> productsPage = productRepository.findAll(pageDetails);
                List<Product> products = productsPage.getContent();
                List<ProductDTO> productDTOs = products.stream()
                                .map(product -> modelMapper.map(product, ProductDTO.class))
                                .toList();
                ProductResponse productResponse = new ProductResponse();
                productResponse.setContent(productDTOs);
                productResponse.setPageNum(productsPage.getNumber() + 1);
                productResponse.setPageSize(productsPage.getSize());
                productResponse.setTotalElements(productsPage.getTotalElements());
                productResponse.setTotalPages(productsPage.getTotalPages());
                productResponse.setLastPage(productsPage.isLast());
                return productResponse;
        }

        @Override
        public ProductResponse searchByCategory(Long categoryId, int pageNum, int pageLimit, String sortBy,
                        String sortOrder) {
                Category category = categoryRepository.findById(categoryId)
                                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

                Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                                ? Sort.by(sortBy).ascending()
                                : Sort.by(sortBy).descending();

                Pageable pageDetails = PageRequest.of(pageNum - 1, pageLimit, sortByAndOrder);
                Page<Product> productsPage = productRepository.findByCategoryOrderByPriceAsc(category, pageDetails);
                List<Product> products = productsPage.getContent();
                List<ProductDTO> productDTOs = products.stream()
                                .map(product -> modelMapper.map(product, ProductDTO.class))
                                .toList();
                ProductResponse productResponse = new ProductResponse();
                productResponse.setContent(productDTOs);
                productResponse.setPageNum(productsPage.getNumber() + 1);
                productResponse.setPageSize(productsPage.getSize());
                productResponse.setTotalElements(productsPage.getTotalElements());
                productResponse.setTotalPages(productsPage.getTotalPages());
                productResponse.setLastPage(productsPage.isLast());
                return productResponse;
        }

        @Override
        public ProductResponse searchByKeyword(String keyword, int pageNum, int pageLimit, String sortBy,
                        String sortOrder) {

                Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                                ? Sort.by(sortBy).ascending()
                                : Sort.by(sortBy).descending();

                Pageable pageDetails = PageRequest.of(pageNum - 1, pageLimit, sortByAndOrder);
                Page<Product> productsPage = productRepository.findByProductNameLikeIgnoreCase('%' + keyword + '%',
                                pageDetails);
                List<Product> products = productsPage.getContent();
                List<ProductDTO> productDTOs = products.stream()
                                .map(product -> modelMapper.map(product, ProductDTO.class))
                                .toList();
                ProductResponse productResponse = new ProductResponse();
                productResponse.setContent(productDTOs);
                return productResponse;
        }

        @Override
        public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
                Product existingProduct = productRepository.findById(productId)
                                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));
                Product product = modelMapper.map(productDTO, Product.class);
                existingProduct.setProductName(product.getProductName());
                existingProduct.setDescription(product.getDescription());
                existingProduct.setQuantity(product.getQuantity());
                existingProduct.setPrice(product.getPrice());
                existingProduct.setDiscount(product.getDiscount());
                double specialPrice = (1 - product.getDiscount() * 0.01) * product.getPrice();
                product.setSpecialPrice(specialPrice);
                existingProduct.setSpecialPrice(specialPrice);

                /*
                 * if (product.getCategory() != null
                 * && existingProduct.getCategory().getCategoryName() !=
                 * product.getCategory().getCategoryName()) {
                 * Category category =
                 * categoryRepository.findByCategoryName(product.getCategory().getCategoryName()
                 * );
                 * existingProduct.setCategory(category);
                 * }
                 */

                Product savedProduct = productRepository.save(existingProduct);

                List<Cart> carts = cartRepository.findCartsByProductId(productId);

                List<CartDTO> cartDTOs = carts.stream().map(cart -> {
                        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

                        List<ProductDTO> products = cart.getCartItems().stream()
                                        .map(p -> modelMapper.map(p.getProduct(), ProductDTO.class))
                                        .collect(Collectors.toList());

                        cartDTO.setProducts(products);

                        return cartDTO;

                }).toList();

                cartDTOs.forEach(cart -> cartService.updateProductInCarts(cart.getCartId(), productId));

                return modelMapper.map(savedProduct, ProductDTO.class);
        }

        @Override
        public ProductDTO deleteProduct(Long productId) {
                Product existingProduct = productRepository.findById(productId)
                                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

                List<Cart> carts = cartRepository.findCartsByProductId(productId);
                carts.forEach(cart -> cartService.deleteProductFromCart(cart.getCartId(), productId));

                productRepository.deleteById(productId);
                return modelMapper.map(existingProduct, ProductDTO.class);
        }
}
