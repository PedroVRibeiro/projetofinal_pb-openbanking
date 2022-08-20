package uol.compass.projetofinal.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import uol.compass.projetofinal.entities.Product;
import uol.compass.projetofinal.repositories.ProductRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SearchEndpointTest {

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void shouldFindProductsIfNoParametersAreGiven() {
		List<Product> products = productRepository.search(null, null, null);
		Assertions.assertTrue(!products.isEmpty());
	}

	@Test
	public void shouldFindProductsIfMaxPriceIsTooHigh() {
		Double max_price = 5000.0;
		List<Product> products = productRepository.search(max_price, null, null);
		Assertions.assertTrue(!products.isEmpty());
	}

	@Test
	public void shouldFindNoProductsIfMaxPriceIsTooLow() {
		Double max_price = 0.0;
		List<Product> products = productRepository.search(max_price, null, null);
		Assertions.assertTrue(products.isEmpty());
	}

	@Test
	public void shouldFindProductsIfMinPriceIsTooLow() {
		Double min_price = 0.0;
		List<Product> products = productRepository.search(null, min_price, null);
		Assertions.assertTrue(!products.isEmpty());
	}

	@Test
	public void shouldFindNoProductsIfMinPriceIsTooHigh() {
		Double min_price = 5000.0;
		List<Product> products = productRepository.search(null, min_price, null);
		Assertions.assertTrue(products.isEmpty());
	}

	@Test
	public void shouldFindProductsIfPriceRangeIsTooWide() {
		Double max_price = 5000.0;
		Double min_price = 0.0;
		List<Product> products = productRepository.search(max_price, min_price, null);
		Assertions.assertTrue(!products.isEmpty());
	}

	@Test
	public void shouldFindNoProductsIfPriceRangeIsTooShort() {
		Double max_price = 3500.0;
		Double min_price = 2000.0;
		List<Product> products = productRepository.search(max_price, min_price, null);
		Assertions.assertTrue(products.isEmpty());
	}

	@Test
	public void shouldFindNoProductsIfNameDoesNotMatch() {
		String name = "Carro";
		List<Product> products = productRepository.search(null, null, name);
		Assertions.assertTrue(products.isEmpty());
	}

	@Test
	public void shouldFindProductIfNameDoesMatch() {
		String name = "Televisão";
		List<Product> products = productRepository.search(null, null, name);
		Assertions.assertTrue(!products.isEmpty());
	}

	@Test
	public void shouldFindProductsEvenIfNameHasMixedCase() {
		String name = "tElEvISãO";
		List<Product> products = productRepository.search(null, null, name);
		Assertions.assertTrue(!products.isEmpty());
	}

	@Test
	public void shouldFindNoProductInPriceRangeIfNameDoesNotMatch() {
		Double max_price = 5000.0;
		Double min_price = 0.0;
		String name = "Carro";
		List<Product> products = productRepository.search(max_price, min_price, name);
		Assertions.assertTrue(products.isEmpty());
	}

	@Test
	public void shouldFindNoProductWithMatchingNameButOutsideOfPriceRange() {
		Double max_price = 5000.0;
		Double min_price = 900.0;
		String name = "Celular";
		List<Product> products = productRepository.search(max_price, min_price, name);
		Assertions.assertTrue(products.isEmpty());
	}
}
