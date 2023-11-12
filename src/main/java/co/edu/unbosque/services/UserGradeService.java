package co.edu.unbosque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.UserGrade;
import co.edu.unbosque.repository.UserGradeRepository;
import co.edu.unbosque.util.AESUtil;


@Service
public class UserGradeService implements CRUDOperations<UserGrade>{
	
	@Autowired
	private UserGradeRepository userGradeRepo;
	
	public UserGradeService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int create(UserGrade data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UserGrade> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateById(Long id, UserGrade newData) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean findByNameAlreadyTaken(UserGrade newUserGrade) {
		Optional<UserGrade> found = userGradeRepo.findByName(AESUtil.encrypt(newUserGrade.getName()));
		
		return found.isPresent()?true:false;
	}

}
