package uol.compass.projetofinal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uol.compass.projetofinal.dto.ProductDto;
import uol.compass.projetofinal.entities.Product;
import uol.compass.projetofinal.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public List<ProductDto> findAll() {
		List<Product> products = productRepository.findAll();
		
		return ProductDto.convert(products);
	}

}
