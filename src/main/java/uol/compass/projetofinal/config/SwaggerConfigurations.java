package uol.compass.projetofinal.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfigurations {

	@Bean
	public Docket ProjetoFinalApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(PathSelectors.any())
				.build()
				.globalResponseMessage(RequestMethod.GET, responseMessageForGet())
				.globalResponseMessage(RequestMethod.POST, responseMessageForPost())
				.globalResponseMessage(RequestMethod.PUT, responseMessageForPut())
				.globalResponseMessage(RequestMethod.DELETE, responseMessageForDelete())
				.apiInfo(apiInfo());
	}
	
	private List<ResponseMessage> responseMessageForGet() {
		return new ArrayList<ResponseMessage>() {
			private static final long serialVersionUID = 1L;

		{
			add(new ResponseMessageBuilder()
					.code(400)
					.message("Bad Request")
					.build());
			add(new ResponseMessageBuilder()
					.code(404)
					.message("Not Found")
					.build());
			add(new ResponseMessageBuilder()
					.code(500)
					.message("Internal Error")
					.build());
			}
		};
	}
	
	private List<ResponseMessage> responseMessageForPost() {
		return new ArrayList<ResponseMessage>() {
			private static final long serialVersionUID = 1L;

		{
			add(new ResponseMessageBuilder()
					.code(201)
					.message("Created")
					.build());
			add(new ResponseMessageBuilder()
					.code(400)
					.message("Bad Request")
					.build());
			add(new ResponseMessageBuilder()
					.code(500)
					.message("Internal Error")
					.build());
			}
		};
	}
	
	private List<ResponseMessage> responseMessageForPut() {
		return new ArrayList<ResponseMessage>() {
			private static final long serialVersionUID = 1L;

		{
			add(new ResponseMessageBuilder()
					.code(400)
					.message("Bad Request")
					.build());
			add(new ResponseMessageBuilder()
					.code(404)
					.message("Not Found")
					.build());
			add(new ResponseMessageBuilder()
					.code(500)
					.message("Internal Error")
					.build());
			}
		};
	}
	
	private List<ResponseMessage> responseMessageForDelete() {
		return new ArrayList<ResponseMessage>() {
			private static final long serialVersionUID = 1L;

		{
			add(new ResponseMessageBuilder()
					.code(204)
					.message("No Content")
					.build());
			add(new ResponseMessageBuilder()
					.code(400)
					.message("Bad Request")
					.build());
			add(new ResponseMessageBuilder()
					.code(404)
					.message("Not Found")
					.build());
			add(new ResponseMessageBuilder()
					.code(500)
					.message("Internal Error")
					.build());
			}
		};
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Projeto Final - PB Compass - Open Banking 2022")
				.description("Projeto final do programa de bolsas Compass.uol - Open Banking de julho de 2022")
				.version("1.0.0")
				.license("Apache License Version 2.0")
	            .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
				.build();
	}
}
