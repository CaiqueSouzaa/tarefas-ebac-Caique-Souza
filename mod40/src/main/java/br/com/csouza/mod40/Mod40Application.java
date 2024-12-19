package br.com.csouza.mod40;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.csouza.domain.User;
import br.com.csouza.interfaces.IUserRepository;

@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackages = "br.com.csouza.interfaces")
@EntityScan("br.com.csouza.domain")
@ComponentScan(basePackages = "br.com.csouza")
public class Mod40Application implements CommandLineRunner {

	@Autowired
	private IUserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Mod40Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(createUser());
		System.out.println("Olá mundo!");
	}
	
	private User createUser() {
		return User.builder()
				.name("Caique")
				.surname("Souza")
				.email("caique@email.com")
				.age(21)
				.build();
	}
}
