package ada.tech.tenthirty.tvpackages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ada.tech.tenthirty.tvpackages.repository")
@EntityScan(basePackages = "ada.tech.tenthirty.tvpackages.entity")
public class DefaultArchetypeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DefaultArchetypeApplication.class, args);
	}

}
