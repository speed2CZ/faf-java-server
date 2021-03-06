package com.faforever.server;

import com.faforever.server.config.ServerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ServerProperties.class})
public class FafServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FafServerApplication.class, args);
	}
}
