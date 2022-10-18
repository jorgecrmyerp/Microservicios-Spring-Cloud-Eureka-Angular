package com.jgr.micro.app.usuarios;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;


/**
 * The Class SpringFoxConfig. para configurar springfox/swagger
 */
@Configuration

//@EnableSwagger2
public class SwaggerConfig {

	/*
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.jgr.micro.app.usuarios.controller"))
								.paths(PathSelectors.ant("/api/alumno/*")).build(); //solo para esta ruta
			//	.paths(PathSelectors.any()).build();// para todos
		//.paths(PathSelectors.regex("/.*")).build();
	}
	*/
	/*
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.OAS_30)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.jgr.micro.app.usuarios.controller"))              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
    
	*/
	
	
	
}
