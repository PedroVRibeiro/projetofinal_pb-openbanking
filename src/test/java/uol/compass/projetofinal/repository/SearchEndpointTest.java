package uol.compass.projetofinal.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import uol.compass.projetofinal.entities.Product;
import uol.compass.projetofinal.repositories.ProductRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SearchEndpointTest {

	@Autowired
	private ProductRepository productRepository;
	
	private Pageable pageable;

	@Test
	public void shouldFindProductsIfNoParametersAreGiven() {
		Page<Product> products = productRepository.search(null, null, null, pageable);
		Assertions.assertTrue(!products.isEmpty());
	}

	@Test
	public void shouldFindProductsIfMaxPriceIsTooHigh() {
		Double max_price = 5000.0;
		Page<Product> products = productRepository.search(max_price, null, null, pageable);
		Assertions.assertTrue(!products.isEmpty());
	}

	@Test
	public void shouldFindNoProductsIfMaxPriceIsTooLow() {
		Double max_price = 0.0;
		Page<Product> products = productRepository.search(max_price, null, null, pageable);
		Assertions.assertTrue(products.isEmpty());
	}

	@Test
	public void shouldFindProductsIfMinPriceIsTooLow() {
		Double min_price = 0.0;
		Page<Product> products = productRepository.search(null, min_price, null, pageable);
		Assertions.assertTrue(!products.isEmpty());
	}

	@Test
	public void shouldFindNoProductsIfMinPriceIsTooHigh() {
		Double min_price = 20000.0;
		Page<Product> products = productRepository.search(null, min_price, null, pageable);
		Assertions.assertTrue(products.isEmpty());
	}

	@Test
	public void shouldFindProductsIfPriceRangeIsTooWide() {
		Double max_price = 5000.0;
		Double min_price = 0.0;
		Page<Product> products = productRepository.search(max_price, min_price, null, pageable);
		Assertions.assertTrue(!products.isEmpty());
	}

	@Test
	public void shouldFindNoProductsIfPriceRangeIsTooShort() {
		Double max_price = 3500.0;
		Double min_price = 2000.0;
		Page<Product> products = productRepository.search(max_price, min_price, null, pageable);
		Assertions.assertTrue(products.isEmpty());
	}

	@Test
	public void shouldFindNoProductsIfNameDoesNotMatch() {
		String name = "Carro";
		Page<Product> products = productRepository.search(null, null, name, pageable);
		Assertions.assertTrue(products.isEmpty());
	}

	@Test
	public void shouldFindProductIfNameDoesMatch() {
		String name = "Televisão";
		Page<Product> products = productRepository.search(null, null, name, pageable);
		Assertions.assertTrue(!products.isEmpty());
	}

	@Test
	public void shouldFindProductsEvenIfNameHasMixedCase() {
		String name = "tElEvISãO";
		Page<Product> products = productRepository.search(null, null, name, pageable);
		Assertions.assertTrue(!products.isEmpty());
	}

	@Test
	public void shouldFindNoProductInPriceRangeIfNameDoesNotMatch() {
		Double max_price = 5000.0;
		Double min_price = 0.0;
		String name = "Carro";
		Page<Product> products = productRepository.search(max_price, min_price, name, pageable);
		Assertions.assertTrue(products.isEmpty());
	}

	@Test
	public void shouldFindNoProductWithMatchingNameButOutsideOfPriceRange() {
		Double max_price = 1300.0;
		Double min_price = 1290.0;
		String name = "Celular";
		Page<Product> products = productRepository.search(max_price, min_price, name, pageable);
		Assertions.assertTrue(products.isEmpty());
	}
}
