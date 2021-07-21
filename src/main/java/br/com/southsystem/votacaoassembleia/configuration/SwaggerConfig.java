package br.com.southsystem.votacaoassembleia.configuration;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.southsystem.votacaoassembleia"))
				.paths(regex("/api.*"))
				.build()
				.apiInfo(metaInfo());
		
	}
	
	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo(
				"Votação Assembleia API REST",
				"API REST de Votacao de Pautas na Assembleia.",
				"1.0",
				"",
				new Contact("Rodrigo Freitas", "", "rodrigo.freitas.rrod@gmail.com"),
				"",
				"", new ArrayList<VendorExtension>());
		return apiInfo;
	}
}
