package in.vnl.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import in.vnl.spring.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

	Optional<UserEntity> findByUsername(String username);

}
