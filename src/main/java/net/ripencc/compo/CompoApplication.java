package net.ripencc.compo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableCaching
public class CompoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompoApplication.class, args);
	}

}
