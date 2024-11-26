package vn.iotstar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.iotstar.model.user;
import vn.iotstar.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public List<user> getAllUsers() {
		return userRepository.findAll();
	}

	public user createUser(user user) {
		return userRepository.save(user);
	}

	public user updateUser(Long id, user newUser) {
		return userRepository.findById(id).map(user -> {
			user.setFullname(newUser.getFullname());
			user.setEmail(newUser.getEmail());
			user.setPassword(newUser.getPassword());
			user.setPhone(newUser.getPhone());
			return userRepository.save(user);
		}).orElseThrow(() -> new RuntimeException("User not found"));
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}
