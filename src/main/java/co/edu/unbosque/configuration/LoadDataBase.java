package co.edu.unbosque.configuration;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.edu.unbosque.model.User;
import co.edu.unbosque.repository.UserRepository;
import co.edu.unbosque.util.AESUtil;

@Configuration
public class LoadDataBase {
	
	private static final Logger LOG = LoggerFactory.getLogger(LoadDataBase.class);
	
	@Bean
	CommandLineRunner initDataBase(UserRepository userRepo) {
		return args -> {
			String encrypted = AESUtil.encrypt("admin");
			String g1encrypted = AESUtil.encrypt("5.0");
			String g2encrypted = AESUtil.encrypt("5.0");
			String g3encrypted = AESUtil.encrypt("5.0");
			Optional<User> found = userRepo.findByName(encrypted);
			if(found.isPresent()) {
				LOG.info("Admin already exists. Skipping admin creation");
			} else  {
				userRepo.save(new User(encrypted, g1encrypted, g2encrypted, g3encrypted));
				LOG.info("pre-loading admin-user");
			}
		};
	}

}
