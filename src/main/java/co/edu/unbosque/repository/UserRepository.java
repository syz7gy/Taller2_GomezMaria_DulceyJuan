package co.edu.unbosque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public Optional<User> findByName(String username);
	
	public void deleteByName(String username);

}
