package uol.compass.projetofinal.services;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uol.compass.projetofinal.dto.ProductDto;
import uol.compass.projetofinal.entities.Product;
import uol.compass.projetofinal.repositories.ProductRepository;

public class ProductServiceTest {

	@InjectMocks
	private ProductService productService;
	@Mock
	private ProductRepository productRepository;
	
	private Product product;
	private ProductDto productDto;
	private Optional<Product> optionalProduct;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startProduct();
	}
	
	@Test
	void shouldReturnObjectWhenSearchedById() {
		Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(optionalProduct);
		
		ProductDto response = productService.findById(1);
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(ProductDto.class, response.getClass());
		Assertions.assertEquals(1, response.getId());
	}
	
	private void startProduct() {
		product = new Product("Celular", "um celular", 1000.0);
		product.setId(1);
		productDto = new ProductDto(product);
		optionalProduct = Optional.of(product);
		
	}
}
