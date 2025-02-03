package iftm.pmvc.crud_proj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudProjApplication.class, args);
	}

	@Configuration
	public static class CorsConfiguracao {

		@Bean
		public WebMvcConfigurer corsConfigurer() {

			return new WebMvcConfigurer() {

				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**")
						.allowedMethods("HEAD","GET","PUT","POST","DELETE","PATCH");
				}
			};
		}
	}

}
