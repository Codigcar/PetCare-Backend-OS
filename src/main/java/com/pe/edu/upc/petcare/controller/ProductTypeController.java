package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.ProductType;
import com.pe.edu.upc.petcare.resource.save.SaveProductTypeResource;
import com.pe.edu.upc.petcare.resource.ProductTypeResource;
import com.pe.edu.upc.petcare.service.ProductTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/product-types")
public class ProductTypeController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping
    public Page<ProductTypeResource> getAllProductType(Pageable pageable)
    {
        List<ProductTypeResource> serviceType = productTypeService.getAllServiceType(pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int serviceTypeCount = serviceType.size();
        return new PageImpl<>(serviceType,pageable,serviceTypeCount);
    }

    @GetMapping("/{id}")
    public ProductTypeResource getProductTypeById(@PathVariable(name = "id")Long serviceTypeId){
        return convertToResource(productTypeService.getServiceTypeById(serviceTypeId));
    }

    @PostMapping
    public ProductTypeResource createProductType (@Valid @RequestBody SaveProductTypeResource resource){
        return convertToResource(productTypeService.createServiceType(convertToEntity(resource)));
    }

    @PutMapping("/{id}")
    public ProductTypeResource updateProductType (@PathVariable(name = "id")Long serviceTypeId,
                                                  @Valid @RequestBody SaveProductTypeResource resource){
        return convertToResource(productTypeService.updateServiceType(serviceTypeId,convertToEntity(resource)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductType (@PathVariable(name = "id") Long serviceTypeId){
        return productTypeService.deleteServiceType(serviceTypeId);
    }


    private ProductType convertToEntity(SaveProductTypeResource resource) {
        return mapper.map(resource, ProductType.class);
    }

    private ProductTypeResource convertToResource(ProductType entity) {
        return mapper.map(entity, ProductTypeResource.class);
    }

}
