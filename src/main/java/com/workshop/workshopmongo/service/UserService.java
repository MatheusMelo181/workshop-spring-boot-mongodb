package com.workshop.workshopmongo.service;

import com.workshop.workshopmongo.domain.User;
import com.workshop.workshopmongo.dto.UserDTO;
import com.workshop.workshopmongo.repository.UserRepository;
import com.workshop.workshopmongo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User obj){
		return repository.insert(obj);
	}

	public User fromDTO(UserDTO objDTO){
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}

	public void delete(String id){
		findById(id);
		repository.deleteById(id);
	}

	public User update(User user){
		User newUser = findById(user.getId());
		updateData(newUser, user);
		return repository.save(newUser);
	}

	private void updateData(User newUser, User user) {
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
	}
}
