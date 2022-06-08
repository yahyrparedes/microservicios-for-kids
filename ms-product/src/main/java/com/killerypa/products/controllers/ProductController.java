package com.killerypa.products.controllers;


import com.killerypa.products.config.EurekaClient;
import com.killerypa.products.dto.CategoryDTO;
import com.killerypa.products.models.Product;
import com.killerypa.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private DiscoveryClient discoveryClient;


    @GetMapping("/products")
    public List<Product> productList() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> productDetail(@PathVariable("id") Integer id) {
        return productService.findById(id)
                .map(value -> ResponseEntity.status(200).body(value))
                .orElseGet(() -> ResponseEntity.status(400).build());
    }

    @PostMapping("/products")
    public ResponseEntity<Product> report(@RequestBody Product product) {
        Product reportNew = productService.create(product);
        try {
            return ResponseEntity.status(201).body(reportNew);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    /*********************************************************
     INFO:
     --- Implementacion rapida de como comunicarte
     --- con otro microservicio para pedir informacion
     ********************************************************/

    @Autowired
    private EurekaClient eureka;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    // Llamando a la API de detalle de categorias
    @RequestMapping(value = "products/categories/{id}", method = RequestMethod.GET)
    public ResponseEntity<CategoryDTO> getCategoryByID(@PathVariable(value = "id") Integer id) {
        URI categoryURI = eureka.getUri("MS-CATEGORY");
        CategoryDTO c = restTemplate.getForObject(
                categoryURI.resolve("/categories/" + id),
                CategoryDTO.class);

        /***
         *  Aqui haces tu procesamiento y retornas lo necesario
         * ....
         *
         ***/

        // Para el ejemplo retornamos todo el objeto de la categoria seleccionada
        return ResponseEntity.status(200).body(c);

    }

    // Llamando a la API ping de categorias
    @RequestMapping(value = "products/categories/ping", method = RequestMethod.GET)
    public String getPingCategories() {
        URI categoryURI = eureka.getUri("MS-CATEGORY");

        return restTemplate.getForObject(
                categoryURI.resolve("/ping"),
                String.class);

    }

}


