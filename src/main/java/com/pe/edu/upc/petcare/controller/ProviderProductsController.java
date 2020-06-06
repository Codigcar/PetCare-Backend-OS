package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.Pet;
import com.pe.edu.upc.petcare.model.Product;
import com.pe.edu.upc.petcare.resource.PetResource;
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
import java.util.function.LongFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/business/{businessId}/providers/{providerId}/product-types/{productTypeId}/products")
public class ProviderProductsController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProductService productService;

    @GetMapping
    public Page<ProductResource> GetAllByProviderJoinTypeProductId(@PathVariable(name = "productTypeId")Long productTypeId,
                                                                   Pageable pageable){
        Page<Product> productPage=productService.getAllByProviderJoinTypeProductId(productTypeId,pageable);
        List<ProductResource>  resources=productPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @PostMapping
    public ProductResource createProduct (@PathVariable(name = "productTypeId") Long productTypeId,
                                          @Valid @RequestBody SaveProductResource resource){
        return convertToResource(productService.createProduct(productTypeId,convertToEntity(resource)));
    }

    @PutMapping("/{productId}")
    public ProductResource updateProduct(@PathVariable("productTypeId")Long productTypeId,
                                         @PathVariable(name = "productId")Long productId,
                                         @Valid @RequestBody SaveProductResource resource){
        return convertToResource(productService.updateProduct(productTypeId,productId,convertToEntity(resource)));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "productTypeId")Long productTypeId,
                                           @PathVariable(name = "productId")Long productId){
        return productService.deleteProduct(productTypeId,productId);
    }

    private Product convertToEntity(SaveProductResource resource){
        return mapper.map(resource,Product.class);
    }
    private ProductResource convertToResource(Product entity){
        return mapper.map(entity,ProductResource.class);
    }
}
