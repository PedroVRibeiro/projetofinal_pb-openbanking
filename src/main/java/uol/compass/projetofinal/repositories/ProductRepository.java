package uol.compass.projetofinal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uol.compass.projetofinal.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM Product p WHERE "
			+ "(:max_price IS NULL OR p.price <= :max_price) AND "
			+ "(:min_price IS NULL OR p.price >= :min_price) AND "
			+ "(:name IS NULL OR lower(p.name) = lower(:name))")
	public List<Product> search(@Param("max_price") Double max_price, @Param("min_price") Double min_price, @Param("name") String name);
	
}
