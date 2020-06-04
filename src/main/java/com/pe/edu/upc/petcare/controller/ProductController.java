package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.Product;
import com.pe.edu.upc.petcare.resource.ProductResource;
import com.pe.edu.upc.petcare.resource.save.SaveProductResource;
import com.pe.edu.upc.petcare.service.ProductService;
import org.hibernate.validator.constraints.Mod11Check;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public Page<ProductResource> getAllProducts(Pageable pageable){
        List<ProductResource> products=productService.getAllProducts(pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int productsCount=products.size();
        return new PageImpl<>(products,pageable,productsCount);
    }

    @PostMapping("/products")
    public ProductResource createProduct (@Valid @RequestBody SaveProductResource resource){
        return convertToResource(productService.createProduct(convertToEntity(resource)));
    }
    @PutMapping("/products/{productId}")
    public ProductResource updateProduct(@PathVariable(name = "productId")Long productId,
                                         @Valid @RequestBody SaveProductResource resource){
        return convertToResource(productService.updateProduct(productId,convertToEntity(resource)));
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "productId")Long productId){
        return productService.deleteProduct(productId);
    }

    private Product convertToEntity(SaveProductResource resource){
        return mapper.map(resource,Product.class);
    }
    private ProductResource convertToResource(Product entity){
        return mapper.map(entity,ProductResource.class);
    }
}
