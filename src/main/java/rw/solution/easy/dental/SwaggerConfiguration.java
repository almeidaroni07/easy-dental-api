package rw.solution.easy.dental;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurationSupport {
	
	/*
	 * @Autowired private BuildProperties properties;
	 */

	
	@Bean
	protected Docket productAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
				.securityContexts(Arrays.asList(SecurityContext.builder().securityReferences(defaultAuth()).build())) .securitySchemes(Arrays.asList(new ApiKey("JWT","Authorization", "header")))
				.select()
				.apis(RequestHandlerSelectors.basePackage("rw.solution.easy.dental"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("API Easy Dental")
				.description("Documentação com os métodos do produto Easy dental")
				.version("1.0.0")
				.contact(new Contact(null, null, null))
				.build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope [] scopes = new AuthorizationScope[1];
		scopes [0] = new AuthorizationScope("global", "accessEverything");
		return Arrays.asList(new SecurityReference("JWT", scopes));
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("swagger-ui.html")
	      .addResourceLocations("classpath:/META-INF/resources/");
	 
	    registry.addResourceHandler("/webjars/**")
	      .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
}
