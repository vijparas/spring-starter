package in.vnl.spring.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import in.vnl.spring.entity.RoleEntity;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<RoleEntity, Long> {
	Optional<RoleEntity> findByRole(String roleName);
}
