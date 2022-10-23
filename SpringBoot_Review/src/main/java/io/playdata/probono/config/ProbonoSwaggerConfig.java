package io.playdata.probono.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ProbonoSwaggerConfig {
	
	@Bean
	public Docket swaggerApi() {
		
		return new Docket(DocumentationType.SWAGGER_2)
        		.ignoredParameterTypes(ApiIgnore.class)
        		.apiInfo(swaggerInfo())
        		.select()
                .apis(RequestHandlerSelectors.basePackage("io.playdata.probono.controller"))
                .build()
                .useDefaultResponseMessages(false); 
    }
    

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder().title("Probono Project에 관한 Doc 입니다")
                .description("Probono Project")
                .license("Probono Project").licenseUrl("http://localhost:8080/encore/index.html")
                .version("1").build();
    }
		

}
