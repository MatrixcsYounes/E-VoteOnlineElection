package ma.emsi.votemaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class VotemasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotemasterApplication.class, args);
	}

}
