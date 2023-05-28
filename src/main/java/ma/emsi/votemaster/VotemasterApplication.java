package ma.emsi.votemaster;

import ma.emsi.votemaster.repository.UserRepository;
import ma.emsi.votemaster.user.Role;
import ma.emsi.votemaster.user.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaRepositories
public class VotemasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotemasterApplication.class, args);
	}



@Bean
CommandLineRunner start(UserRepository userRepository){
		var encoder = new BCryptPasswordEncoder();
	return args -> {
		userRepository.save(new User(null,"admin","admin","admin@gmail.com", encoder.encode("123"), true,0, Role.ADMIN));
	};
}

}
