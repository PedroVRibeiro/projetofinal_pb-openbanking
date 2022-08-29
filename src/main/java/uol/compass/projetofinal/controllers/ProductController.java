package uol.compass.projetofinal.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;
import uol.compass.projetofinal.dto.ProductDto;
import uol.compass.projetofinal.dto.ProductForm;
import uol.compass.projetofinal.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	@ApiOperation(value = "Lists all products")
	@ApiResponse(code = 200, message = "OK")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Starting page", defaultValue = "0"),
		@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of products in each page", defaultValue = "3"),
		@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting by name, description or price")
	})
	public ResponseEntity<Page<ProductDto>> findAll(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 3) @ApiIgnore Pageable pageable) {
		return ResponseEntity.ok().body(productService.findAll(pageable));
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Searches a product with the given ID")
	@ApiResponses(value = {
			 @ApiResponse(code = 400, message = "Bad Request"),
			 @ApiResponse(code = 404, message = "Not Found"),
			 @ApiResponse(code = 500, message = "Internal Error")
		})
	public ResponseEntity<ProductDto> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(productService.findById(id));
	}

	@GetMapping("/search")
	@ApiOperation(value = "Searches products within a given price interval and/or by name")
	@ApiResponses(value = {
			 @ApiResponse(code = 400, message = "Bad Request"),
			 @ApiResponse(code = 404, message = "Not Found"),
			 @ApiResponse(code = 500, message = "Internal Error")
		})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Starting page", defaultValue = "0"),
		@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of products in each page", defaultValue = "3"),
		@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting by name, description or price")
	})
	public ResponseEntity<Page<ProductDto>> search(
			@PageableDefault(sort = "price", direction = Direction.ASC, page = 0, size = 3) @ApiIgnore Pageable pageable,
			@RequestParam(required = false) Double max_price,
			@RequestParam(required = false) Double min_price, 
			@RequestParam(required = false) String name) {
		return ResponseEntity.ok().body(productService.search(max_price, min_price, name, pageable));
	}

	@PostMapping
	@ApiOperation(value = "Creates a new product on the database")
	@ApiResponses(value = {
			 @ApiResponse(code = 201, message = "Created"),
			 @ApiResponse(code = 40, message = "Bad Request"),
			 @ApiResponse(code = 500, message = "Internal Error")
		})
	public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductForm form) {
		ProductDto productDto = productService.create(form);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/products/{id}")
				.buildAndExpand(productDto.getId()).toUri();
		return ResponseEntity.created(uri).body(productDto);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletes a product with the given ID")
	@ApiResponses(value = {
			 @ApiResponse(code = 204, message = "No Content"),
			 @ApiResponse(code = 400, message = "Bad Request"),
			 @ApiResponse(code = 404, message = "Not Found"),
			 @ApiResponse(code = 500, message = "Internal Error")
		})
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		productService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Updates a product with the given ID")
	@ApiResponses(value = {
			 @ApiResponse(code = 400, message = "Bad Request"),
			 @ApiResponse(code = 404, message = "Not Found"),
			 @ApiResponse(code = 500, message = "Internal Error")
		})
	public ResponseEntity<ProductDto> update(@PathVariable Integer id, @Valid @RequestBody ProductForm form) {
		return ResponseEntity.ok().body(productService.update(id, form));
	}
}
