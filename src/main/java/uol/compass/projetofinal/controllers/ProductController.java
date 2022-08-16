package uol.compass.projetofinal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uol.compass.projetofinal.dto.ProductDto;
import uol.compass.projetofinal.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<ProductDto>> findAll() {
		return productService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> findById(@PathVariable Integer id) {
		return productService.findById(id);
	}
}
