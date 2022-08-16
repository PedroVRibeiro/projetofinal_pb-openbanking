package uol.compass.projetofinal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uol.compass.projetofinal.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM Product p WHERE p.price < ?1 AND p.price > ?2")
	public List<Product> findByPriceRange(Double max_price, Double min_price);
	
}
