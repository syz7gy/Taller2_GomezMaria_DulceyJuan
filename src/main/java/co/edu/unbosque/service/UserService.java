package co.edu.unbosque.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.User;
import co.edu.unbosque.repository.UserRepository;
import co.edu.unbosque.util.AESUtil;

@Service
public class UserService implements CRUDOperations<User>{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public int create(User data) {
		if(findNameAlreadyTaken(data)) {
			return 1;
		} else {
			data.setName(AESUtil.encrypt(data.getName()));
			data.setGrade1(AESUtil.encrypt(data.getGrade1()));
			data.setGrade2(AESUtil.encrypt(data.getGrade2()));
			data.setGrade3(AESUtil.encrypt(data.getGrade3()));
			userRepo.save(data);
		}
		return 0;
	}

	@Override
	public List<User> getAll() {
		return userRepo.findAll();
	}

	@Override
	public int deleteById(Long id) {
		Optional<User> found = userRepo.findById(id);
		if(found.isPresent()) {
			userRepo.delete(found.get());
			return 0;
		} else {
			return 0;
		}
	}

	@Override
	public int updateById(Long id, User newData) {
		Optional<User> found = userRepo.findById(id);
		Optional<User> newFound = userRepo.findByName(newData.getName());
		if(found.isPresent() && !newFound.isPresent()) {
			User temp = found.get();
			temp.setName(newData.getName());
			temp.setGrade1(AESUtil.encrypt(temp.getGrade1()));
			temp.setGrade2(AESUtil.encrypt(temp.getGrade2()));
			temp.setGrade3(AESUtil.encrypt(temp.getGrade3()));
			userRepo.save(temp);
			
			return 0;
		} else if(found.isPresent() && newFound.isPresent()) {
			return 1;
		} else if(!found.isPresent()) {
			return 2;
		} else {
			return 3;
		}
	}

	@Override
	public long count() {
		return userRepo.count();
	}

	@Override
	public boolean exists(Long id) {
		return userRepo.existsById(id)?true:false;
	}
	
	public boolean findNameAlreadyTaken(User newUser) {
		Optional<User> found = userRepo.findByName(newUser.getName());
		if(found.isPresent()) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
