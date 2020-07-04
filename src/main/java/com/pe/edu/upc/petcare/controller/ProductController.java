package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.Payment;
import com.pe.edu.upc.petcare.model.Product;
import com.pe.edu.upc.petcare.resource.PaymentResource;
import com.pe.edu.upc.petcare.resource.ProductResource;
import com.pe.edu.upc.petcare.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/business/{businessId}/providers/{providerId}/products-type/{productTypeId}/products")
public class ProductController {
    @Autowired
    ModelMapper mapper;
    @Autowired
    ProductService productService;

    @GetMapping
    public Page<ProductResource> getAllPaymentsByProviderId(@PathVariable(name = "productTypeId")Long productTypeId,
                                                            Pageable pageable)
    {
        Page<Product> productPage=productService.getAllByProviderJoinTypeProductId(productTypeId,pageable);
        List<ProductResource> resources=productPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    private ProductResource convertToResource(Product entity) {
        return mapper.map(entity, ProductResource.class);
    }
}
