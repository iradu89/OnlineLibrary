package scoalainformala.ro.OnlineLibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import scoalainformala.ro.OnlineLibrary.repository.UserRepository;


@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@SpringBootApplication
public class OnlineLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineLibraryApplication.class, args);
	}
}
