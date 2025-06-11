package com.example.goblidas_backend.controllers;

import com.example.goblidas_backend.entities.Category;
import com.example.goblidas_backend.entities.Detail;
import com.example.goblidas_backend.entities.Product;
import com.example.goblidas_backend.services.DetailService;
import com.example.goblidas_backend.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/product")
public class ProductController extends BaseController<Product> {

    private ProductService productService;

    private DetailService detailService;

    public ProductController(ProductService productService, DetailService detailService) {
        super(productService);
        this.productService = productService;
        this.detailService = detailService;
    }


    @GetMapping("/filter")
    public ResponseEntity<?> filterProducts(
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String productType,
            @RequestParam(required = false) List<Long> categoriesIds,
            @RequestParam(required = false) Double min,
            @RequestParam(required = false) Double max,
            @RequestParam(required = false) Boolean highlighted,
            @RequestParam(required = false) Long sizeId,
            @RequestParam(required = false) Boolean asc,
            @RequestParam(required = false) Boolean desc
    ) throws Exception {
        try {
            List<Product> productos = productService.filterProd(gender, name, productType, categoriesIds, min, max, highlighted, sizeId);
            if (productos.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                // Filtrar los detalles inactivos y sin stock de cada producto
                productos.forEach(product -> {
                    List<Detail> activeAndInStockDetails = detailService.findActiveAndInStockByProduct(product);
                    product.setDetails(activeAndInStockDetails);
                });

                if (Boolean.TRUE.equals(asc) && Boolean.TRUE.equals(desc)) {
                    throw new Exception("No se puede ordenar ascendente y descendente al mismo tiempo");
                }
                if (asc == null) {
                    asc = false;
                }

                if (desc == null) {
                    desc = false;
                }

                if (asc) {
                    List<Product> productosOrdAsc = productService.orderAsc(productos);
                    return ResponseEntity.ok(productosOrdAsc);
                } else if (desc) {
                    List<Product> productosOrdDesc = productService.orderDesc(productos);
                    return ResponseEntity.ok(productosOrdDesc);
                }

                return ResponseEntity.ok(productos);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/{id}/detail")
    public ResponseEntity<?> addDetailByProduct(
            @PathVariable Long id,
            @RequestBody Detail detail)
    {
        try {
            Product product = productService.findById(id);

            detail.setProductIdj(product);
            Detail saved = detailService.save(detail); // o repositorio directamente

            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear detalle" + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
