package vse.cz.vseblog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import vse.cz.vseblog.data.request.EditUserRequest;
import vse.cz.vseblog.data.request.RegisterUserRequest;
import vse.cz.vseblog.data.response.CommentResponseRST;
import vse.cz.vseblog.data.response.UserResponseRST;
import vse.cz.vseblog.entity.UserEntity;
import vse.cz.vseblog.entity.UserType;
import vse.cz.vseblog.mapper.CommentDataMapper;
import vse.cz.vseblog.mapper.UserMapper;
import vse.cz.vseblog.repository.CommentRepository;
import vse.cz.vseblog.repository.UserRepository;

/**
 * Popis
 *
 * @author dusan.petren
 */
@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private CommentDataMapper commentDataMapper;


	@Override
	public void createUser(RegisterUserRequest registerUser) {
		Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(registerUser.getUsername());

		if (optionalUserEntity.isPresent()) {
			throw new IllegalArgumentException("Username already taken");
		}
		logger.info(registerUser.toString());
		UserEntity entity = new UserEntity();
		entity.setUsername(registerUser.getUsername());
		entity.setPassword(passwordEncoder.encode(registerUser.getPassword()));
		entity.setEmail(registerUser.getEmail());
		entity.setFirstname(registerUser.getFirstName());
		entity.setLastname(registerUser.getLastName());
		entity.setType(UserType.USER);

		userRepository.save(entity);
	}

	@Override
	public UserResponseRST loadUser(String username) {
		Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);
		if (!optionalUserEntity.isPresent()) {
			throw new IllegalArgumentException("User with username: " + username + " doesn't exist");
		} else {
			return userMapper.sourceToDestination(optionalUserEntity.get());
		}
	}

	@Override
	public List<UserResponseRST> loadAllUsers() {
		return userRepository.findAll().stream().map(userEntity ->
			userMapper.sourceToDestination(userEntity)).collect(Collectors.toList());
	}

	@Override
	public List<UserResponseRST> searchUsers(String searchValue) {
		return userRepository.findAllBySearch(searchValue).stream()
				.map(userEntity -> userMapper.sourceToDestination(userEntity))
				.collect(Collectors.toList());
	}

	@Override
	public UserResponseRST editUser(String username, EditUserRequest editUserRequest) {
		Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);

		if (!optionalUserEntity.isPresent()) {
			throw new IllegalArgumentException("User with username: " + username + " doesn't exist");
		} else {
			UserEntity entity = optionalUserEntity.get();
			if (editUserRequest.getEmail() != null && !editUserRequest.getEmail().isEmpty()) {
				entity.setEmail(editUserRequest.getEmail());
			}
			if (editUserRequest.getFirstname() != null && !editUserRequest.getFirstname().isEmpty()) {
				entity.setFirstname(editUserRequest.getFirstname());
			}
			if (editUserRequest.getLastname() != null && !editUserRequest.getLastname().isEmpty()) {
				entity.setLastname(editUserRequest.getLastname());
			}
			return userMapper.sourceToDestination(userRepository.save(entity));
		}
	}

	@Override
	@Transactional
	public void deleteUser(String username) {
		userRepository.deleteByUsername(username);
	}

	@Override
	public List<CommentResponseRST> loadUsersComments(String username) {
		logger.info(username);
		return commentRepository.findAllByUser_Username(username).stream()
				.map(e -> commentDataMapper.destinationToSource(e))
				.collect(Collectors.toList());
	}
}
