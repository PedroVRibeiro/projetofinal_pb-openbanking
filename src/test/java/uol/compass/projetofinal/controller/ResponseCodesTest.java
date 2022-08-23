package uol.compass.projetofinal.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ResponseCodesTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void shouldReturn200WhenListedAllProducts() throws Exception {
		URI uri = new URI("/products");
		
		mockMvc.perform(MockMvcRequestBuilders
				.get(uri)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200));
	}
	
	@Test
	public void shouldReturn200WhenSearchedWithValidId() throws Exception {
		URI uri = new URI("/products/1");
		
		mockMvc.perform(MockMvcRequestBuilders
				.get(uri)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200));
	}
	
	@Test
	public void shouldReturn404WhenSearchedWithInvalidId() throws Exception {
		URI uri = new URI("/products/100");
		
		mockMvc.perform(MockMvcRequestBuilders
				.get(uri)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(404));
	}
	
	@Test
	public void shouldReturn400WhenSearchedWithInvalidType() throws Exception {
		URI uri = new URI("/products/e");
		
		mockMvc.perform(MockMvcRequestBuilders
				.get(uri)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
	}
	
	@Test
	public void shouldReturn201WhenCreatedWithValidData() throws Exception {
		URI uri = new URI("/products");
		
		String content = "{ \"name\" : \"Carro\" , \"description\" : \"um carro\" , \"price\" : \"1200.0\"}";
		
		mockMvc.perform(MockMvcRequestBuilders
				.post(uri)
				.content(content)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(201));
	}
	
	@Test
	public void shouldReturn400WhenCreatedWithinvalidData() throws Exception {
		URI uri = new URI("/products");
		
		String content = "{ \"name\" : \"\" , \"description\" : \"\" , \"price\" : \"1200.0\"}";
		
		mockMvc.perform(MockMvcRequestBuilders
				.post(uri)
				.content(content)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
	}
	
	@Test
	public void shouldReturn400WhenInvalidPostPathIsGiven() throws Exception {
		URI uri = new URI("/products/1");
		
		String content = "{ \"name\" : \"\" , \"description\" : \"\" , \"price\" : \"1200.0\"}";
		
		mockMvc.perform(MockMvcRequestBuilders
				.post(uri)
				.content(content)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
	}
	
	@Test
	public void shouldReturn204WhenDeletedwithValidId() throws Exception {
		URI uri = new URI("/products/3");
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete(uri)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(204));
	}
	
	@Test
	public void shouldReturn404WhenDeletedwithInvalidId() throws Exception {
		URI uri = new URI("/products/100");
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete(uri)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(404));
	}
	
	@Test
	public void shouldReturn200WhenUpdatedWithValidData() throws Exception {
		URI uri = new URI("/products/2");
		
		String content = "{ \"name\" : \"Moto\" , \"description\" : \"uma moto\" , \"price\" : \"3000.0\"}";
		
		mockMvc.perform(MockMvcRequestBuilders
				.put(uri)
				.content(content)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200));
	}
	
	@Test
	public void shouldReturn400WhenUpdatedWithInvalidData() throws Exception {
		URI uri = new URI("/products/2");
		
		String content = "{ \"name\" : \"\" , \"description\" : \"uma moto\" , \"price\" : \"3000.0\"}";
		
		mockMvc.perform(MockMvcRequestBuilders
				.put(uri)
				.content(content)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
	}
	
	@Test
	public void shouldReturn400WhenGivenWrongUrlPath() throws Exception {
		URI uri = new URI("/prod");
		
		mockMvc.perform(MockMvcRequestBuilders
				.get(uri)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
	}
}
