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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import uol.compass.projetofinal.dto.ProductDto;
import uol.compass.projetofinal.dto.ProductForm;
import uol.compass.projetofinal.entities.Product;
import uol.compass.projetofinal.exceptionhandler.ProductNotFoundException;
import uol.compass.projetofinal.repositories.ProductRepository;

public class ProductServiceTest {

	@InjectMocks
	private ProductService productService;
	@Mock
	private ProductRepository productRepository;

	private Product product;
	private ProductForm productForm;
	private Optional<Product> optionalProduct;
	private List<Product> productList;
	private Page<Product> productPage;
	private Pageable pageable;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startProduct();
	}

	@Test
	void shouldReturnAllProducts() {
		Mockito.when(productRepository.findAll(pageable)).thenReturn(productPage);

		Page<ProductDto> response = productService.findAll(pageable);

		Assertions.assertNotNull(response);
		Assertions.assertEquals(1, response.getNumberOfElements());

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
			Assertions.assertEquals("product not found.", e.getMessage());
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
			Assertions.assertEquals("product not found.", e.getMessage());
		}
	}

	@Test
	void shouldCreateProduct() {
		Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);

		ProductDto productDto = productService.create(productForm);

		Assertions.assertNotNull(productDto);
		Assertions.assertEquals(ProductDto.class, productDto.getClass());
		Assertions.assertEquals("Celular", productDto.getName());
		Assertions.assertEquals("um celular", productDto.getDescription());
		Assertions.assertEquals(1000.0, productDto.getPrice());
	}

	@Test
	void shouldUpdateProduct() {
		Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(optionalProduct);

		ProductDto productDto = productService.update(1, productForm);

		Assertions.assertNotNull(productDto);
		Assertions.assertEquals(ProductDto.class, productDto.getClass());
		Assertions.assertEquals("Celular", productDto.getName());
		Assertions.assertEquals("um celular", productDto.getDescription());
		Assertions.assertEquals(1000.0, productDto.getPrice());
	}

	@Test
	void shouldReturnNotFoundWhenUpdatingAnInexistentId() {
		Mockito.when(productRepository.findById(Mockito.anyInt())).thenThrow(new ProductNotFoundException());

		try {
			productService.update(1, productForm);
		} catch (Exception e) {
			Assertions.assertEquals(ProductNotFoundException.class, e.getClass());
			Assertions.assertEquals("product not found.", e.getMessage());
		}
	}

	private void startProduct() {
		product = new Product("Celular", "um celular", 1000.0);
		product.setId(1);

		new ProductDto(product);

		productForm = new ProductForm();
		productForm.setName("Celular");
		productForm.setDescription("um celular");
		productForm.setPrice(1000.0);

		optionalProduct = Optional.of(product);

		productList = List.of(product);

		productPage = new PageImpl<>(productList);
	}
}
