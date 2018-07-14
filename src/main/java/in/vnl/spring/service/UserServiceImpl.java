package in.vnl.spring.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import in.vnl.spring.entity.pojo.user.UserPojo;
import in.vnl.spring.entity.pojo.user.UserUpdatePojo;
import in.vnl.spring.exceptions.validation.user.UsernameNotUniqueException;
import in.vnl.spring.repository.UserRepository;
import in.vnl.spring.validation.UserValidation;

@Service
public class UserServiceImpl implements UserService,UserDetailsService {
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
			UserPojoToUserEntity userPojoToUserEntityConverter, UserEntityToUserPojo userEntityToUserPojoConverter,UserUpdatePojoToUserEntity userUpdateToUserEntityConverter ) {

		this.userValidation = userValidation;
		this.userRepository = userRepository;
		this.userPojoToUserEntityConverter = userPojoToUserEntityConverter;
		this.userEntityToUserPojoConverter = userEntityToUserPojoConverter;
		this.userUpdateToUserEntityConverter=userUpdateToUserEntityConverter;
	}

	@Override
	public UserPojo create(UserPojo userPojo) throws UsernameNotUniqueException {
		try {

			userValidation.create(userPojo);
			UserEntity userEntity = userPojoToUserEntityConverter.convert((UserPojo) userPojo);
			userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
			return userEntityToUserPojoConverter.convert(userRepository.save(userEntity));
		}
		catch(UsernameNotUniqueException exception) {
			throw exception;
		}
		catch (Exception exception) {
			throw exception;
		}

	}

	@Override
	public UserUpdatePojo update(UserUpdatePojo userPojo) {
		try {
			System.out.println("Inside update service layer");
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
	public List<UserPojo> displayUsers() {
		int pageNumber = 1;
		List<UserPojo> userPojo = new ArrayList<>();
		try {
			List<UserEntity> users = userRepository
					.findAll(PageRequest.of(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id")).getContent();
			for (UserEntity user : users) {
				userPojo.add(userEntityToUserPojoConverter.convert(user));
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			throw exception;
		}
		return userPojo;
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
		
		try{
			Optional<UserEntity> user=userRepository.findByUsername(username);
			UserEntity userEntity=user.get();
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
			userEntity.getRoles().forEach(role -> {
				authorities.add(new SimpleGrantedAuthority(role.getRole()));
			});
			return userEntity;
			
		}
		catch(Exception exception) {
			System.out.println(exception.getMessage());
			throw exception;
		}
		
	}

	

}
