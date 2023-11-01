package uz.architect.springsecurityexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.architect.springsecurityexample.entity.Product;
import uz.architect.springsecurityexample.exception.ProductNotFoundException;
import uz.architect.springsecurityexample.payload.ApiResponse;
import uz.architect.springsecurityexample.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public HttpEntity<?> getAll() {
        return ResponseEntity.ok(
                productService.getAll()
        );
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable("id") int id) {
        return ResponseEntity.ok(
                productService.getOne(id)
        );
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody Product product) {
        return ResponseEntity.ok(
                productService.add(product)
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public HttpEntity<?> delete(@PathVariable("id") int id) {
        productService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @ExceptionHandler
    public HttpEntity<?> handleException(ProductNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse(
                        e.getMessage(),
                        HttpStatus.NOT_FOUND.value())
                );
    }
}
