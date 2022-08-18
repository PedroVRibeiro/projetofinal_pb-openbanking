package uol.compass.projetofinal.services;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uol.compass.projetofinal.controllers.ProductController;
import uol.compass.projetofinal.dto.ProductDto;
import uol.compass.projetofinal.dto.ProductForm;
import uol.compass.projetofinal.entities.Product;
import uol.compass.projetofinal.repositories.ProductRepository;
import uol.compass.projetofinal.services.exceptions.ProductNotFoundException;

public class ProductServiceTest {

	@InjectMocks
	private ProductService productService;
	@Mock
	private ProductRepository productRepository;
	@Mock
	private ProductController productController;
	
	private Product product;
	private ProductDto productDto;
	private ProductForm productForm;
	private Optional<Product> optionalProduct;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startProduct();
	}
	
	@Test
	void shouldReturnAllProducts() {
		Mockito.when(productRepository.findAll()).thenReturn(List.of(product));
		
		List<ProductDto> response = productService.findAll();
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(ProductDto.class, response.get(0).getClass());
	
	}
	
	@Test
	void shouldReturnProductWhenSearchedById() {
		Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(optionalProduct);
		
		ProductDto response = productService.findById(1);
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(ProductDto.class, response.getClass());
		Assertions.assertEquals(1, response.getId());
		Assertions.assertNotEquals(2, response.getId());
	}
	
	@Test
	void shouldReturnNotFoundWhenSearchedByInexistentId() {
		Mockito.when(productRepository.findById(Mockito.anyInt())).thenThrow(new ProductNotFoundException());
		
		try {
			productService.findById(1);
		} catch (Exception e) {
			Assertions.assertEquals(ProductNotFoundException.class, e.getClass());
			Assertions.assertEquals("Product not found." , e.getMessage());
		}
	}
	
	@Test
	void shouldDeleteWithAValidId() {
		Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(optionalProduct);
		Mockito.doNothing().when(productRepository).deleteById(Mockito.anyInt());
		productService.delete(1);
		
		Mockito.verify(productRepository, Mockito.times(1)).deleteById(Mockito.anyInt());
	}
	
	@Test
	void shouldReturnNotFoundWhenDeletingAnInexistentId() {
		Mockito.when(productRepository.findById(Mockito.anyInt())).thenThrow(new ProductNotFoundException());
		
		try {
			productService.delete(1);
		} catch (Exception e) {
			Assertions.assertEquals(ProductNotFoundException.class, e.getClass());
			Assertions.assertEquals("Product not found." , e.getMessage());
		}
	}
	
	@Test
	void shouldReturnNotFoundWhenUpdatingAnInexistentId() {
		Mockito.when(productRepository.findById(Mockito.anyInt())).thenThrow(new ProductNotFoundException());
		
		try {
			productService.update(1, productForm);
		} catch (Exception e) {
			Assertions.assertEquals(ProductNotFoundException.class, e.getClass());
			Assertions.assertEquals("Product not found." , e.getMessage());
		}
	}
	
	private void startProduct() {
		product = new Product("Celular", "um celular", 1000.0);
		product.setId(1);
		productDto = new ProductDto(product);
		productForm = new ProductForm();
		productForm.setName("Celular");
		productForm.setDescription("um celular");
		productForm.setPrice(1000.0);
		optionalProduct = Optional.of(product);
		
	}
}
