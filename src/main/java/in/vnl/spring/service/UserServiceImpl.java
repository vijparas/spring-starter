package in.vnl.spring.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.vnl.spring.converters.user.UserEntityToUserPojo;
import in.vnl.spring.converters.user.UserPojoToUserEntity;
import in.vnl.spring.converters.user.UserUpdatePojoToUserEntity;
import in.vnl.spring.entity.UserEntity;
import in.vnl.spring.entity.pojo.user.PasswordUpdatePojo;
import in.vnl.spring.entity.pojo.user.UserPojo;
import in.vnl.spring.entity.pojo.user.UserUpdatePojo;
import in.vnl.spring.exceptions.user.CurrentPasswordDoNotMatchException;
import in.vnl.spring.exceptions.user.UserNameNotFoundException;
import in.vnl.spring.exceptions.validation.user.UsernameNotUniqueException;
import in.vnl.spring.repository.UserRepository;
import in.vnl.spring.utilities.PaginationObject;
import in.vnl.spring.validation.UserValidation;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserValidation userValidation;

	private UserRepository userRepository;

	private UserPojoToUserEntity userPojoToUserEntityConverter;

	private UserEntityToUserPojo userEntityToUserPojoConverter;

	private UserUpdatePojoToUserEntity userUpdateToUserEntityConverter;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private final static int PAGESIZE = 3;

	public UserServiceImpl(UserValidation userValidation, UserRepository userRepository,
			UserPojoToUserEntity userPojoToUserEntityConverter, UserEntityToUserPojo userEntityToUserPojoConverter,
			UserUpdatePojoToUserEntity userUpdateToUserEntityConverter) {

		this.userValidation = userValidation;
		this.userRepository = userRepository;
		this.userPojoToUserEntityConverter = userPojoToUserEntityConverter;
		this.userEntityToUserPojoConverter = userEntityToUserPojoConverter;
		this.userUpdateToUserEntityConverter = userUpdateToUserEntityConverter;
	}

	@Override
	public UserPojo create(UserPojo userPojo) throws UsernameNotUniqueException {
		try {
			
			userValidation.create(userPojo);
			UserEntity userEntity = userPojoToUserEntityConverter.convert((UserPojo) userPojo);
			userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
			return userEntityToUserPojoConverter.convert(userRepository.save(userEntity));
		} catch (UsernameNotUniqueException exception) {
			throw exception;
		} catch (Exception exception) {
			throw exception;
		}

	}

	@Override
	public UserUpdatePojo update(UserUpdatePojo userPojo) {
		try {
			userValidation.update(userPojo);
			userRepository.save(userUpdateToUserEntityConverter.convert((UserUpdatePojo) userPojo));
			return userPojo;
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			throw exception;
		}

	}

	@Override
	public UserPojo delete(UserPojo userPojo) {
		try {

			userValidation.delete(userPojo);
			userRepository.delete(userPojoToUserEntityConverter.convert((UserPojo) userPojo));
			return userPojo;
		} catch (Exception exception) {
			logger.error(exception.getMessage());
			throw exception;
		}

	}

	@Override
	public Map<String, Object> displayUsers(int pageNumber) {

		List<UserPojo> userPojo = new ArrayList<>();
		Map<String, Object> usersMap = new HashMap<>();
		try {
			Page<UserEntity> usersPage = userRepository
					.findAll(PageRequest.of(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id"));
			List<UserEntity> users = usersPage.getContent();
			int numberOfPages = usersPage.getTotalPages();

			for (UserEntity user : users) {
				userPojo.add(userEntityToUserPojoConverter.convert(user));
			}
			usersMap.put("users", userPojo);
			usersMap.put("pagination", new PaginationObject(numberOfPages, pageNumber));

		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			throw exception;
		}
		return usersMap;
	}

	@Override
	public UserPojo getUserById(long id) {
		try {

			UserEntity userEntity = userRepository.findById(id).get();
			UserPojo userPojo = userEntityToUserPojoConverter.convert((UserEntity) userEntity);
			return userPojo;
		} catch (Exception exception) {
			logger.error(exception.getMessage());
			throw exception;
		}

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			Optional<UserEntity> user = userRepository.findByUsername(username);
			UserEntity userEntity = user.get();
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
			userEntity.getRoles().forEach(role -> {
				authorities.add(new SimpleGrantedAuthority(role.getRole()));
			});
			return userEntity;

		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			throw exception;
		}

	}

	@Override
	public UserPojo getUserByUsername(String username) throws UserNameNotFoundException {
		try {
			Optional<UserEntity> user = userRepository.findByUsername(username);
			if (!user.isPresent()) {
				throw new UserNameNotFoundException(username);
			}
			UserEntity userEntity = user.get();
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
			userEntity.getRoles().forEach(role -> {
				authorities.add(new SimpleGrantedAuthority(role.getRole()));
			});
			return userEntityToUserPojoConverter.convert(userEntity);

		} catch (UserNameNotFoundException exception) {
			System.out.println(exception.getMessage());
			throw exception;
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			throw exception;
		}
	}

	@Override
	public PasswordUpdatePojo updateUserPassword(PasswordUpdatePojo passwordUpdatePojo) throws CurrentPasswordDoNotMatchException, UserNameNotFoundException {
		try {
			UserPojo userPojo=this.getUserByUsername(passwordUpdatePojo.getUsername());
			userValidation.validateCurrentPassword(userPojo.getCurrentPassword(),
					passwordEncoder.encode(passwordUpdatePojo.getCurrentPassword()));
		} catch (CurrentPasswordDoNotMatchException exception) {
			throw exception;
		} catch (UserNameNotFoundException exception) {
			throw exception;
		}
		return passwordUpdatePojo;
	}

}
