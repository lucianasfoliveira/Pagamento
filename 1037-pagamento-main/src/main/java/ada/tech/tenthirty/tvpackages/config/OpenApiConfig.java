package ada.tech.tenthirty.tvpackages.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI defaultOpenApiConfig(){
        return new OpenAPI()
                .info(new Info().title("TV Packages architecture sample")
                        .description("Default description ")
                        .version("0.0.1"));
    }
}
