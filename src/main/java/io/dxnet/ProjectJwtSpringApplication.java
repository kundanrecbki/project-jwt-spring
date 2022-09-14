package io.dxnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.scheduling.annotation.EnableAsync;

@EntityScan(
        basePackageClasses = {ProjectJwtSpringApplication.class, Jsr310JpaConverters.class}
)
@SpringBootApplication
@EnableAsync
public class ProjectJwtSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectJwtSpringApplication.class, args);
	}

}
