package vse.cz.vseblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import vse.cz.vseblog.entity.UserEntity;

/**
 * @author dusan.petren
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


	Optional<UserEntity> findByUsername(String username);

	@Query("select user from UserEntity user where lower(user.firstname) like lower(concat('%', :search, '%')) " +
			"or lower(user.lastname) like lower(concat('%', :search, '%')) or lower(user.email) like lower(concat('%',:search,'%') ) ")
	List<UserEntity> findAllBySearch(@Param(value = "search") String searchValue);

	void deleteByUsername(String username);
}
