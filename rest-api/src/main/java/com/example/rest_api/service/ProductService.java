package com.example.rest_api.service;

import com.example.rest_api.dto.ProductDTO;
import com.example.rest_api.entity.Product;
import com.example.rest_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    private ProductDTO mapToDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    private Product mapToEntity(ProductDTO productDTO) {
        return new Product(productDTO.getName(),productDTO.getDescription(), productDTO.getPrice());
    }
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToDTO(product);

    }
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = mapToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return mapToDTO(savedProduct);
    }
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        Product updatedProduct = productRepository.save(existingProduct);
        return mapToDTO(updatedProduct);
    }
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }
}
