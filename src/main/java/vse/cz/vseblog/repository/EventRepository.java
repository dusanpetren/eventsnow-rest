package vse.cz.vseblog.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import vse.cz.vseblog.entity.EventEntity;
import vse.cz.vseblog.entity.EventType;

/**
 * @author dusan.petren
 */
public interface EventRepository extends PagingAndSortingRepository<EventEntity, Long> {

	List<EventEntity> findAllByType(EventType type, Pageable pageable);

	Integer countAllByType(EventType type);

	@Query("select event from EventEntity event where lower(event.content) like lower(concat('%', :search, '%')) " +
			"or lower(event.title) like lower(concat('%', :search, '%')) or lower(event.type) like lower(concat('%',:search,'%') )" +
			"or lower(user.username) like lower(concat('%',:search,'%')) ")
	List<EventEntity> findAllBySearch(@Param(value = "search") String searchValue, Pageable pageable);

	@Query("select count(event.id) from EventEntity event where lower(event.content) like lower(concat('%', :search, '%')) " +
			"or lower(event.title) like lower(concat('%', :search, '%')) or lower(event.type) like lower(concat('%',:search,'%') )" +
			"or lower(user.username) like lower(concat('%',:search,'%')) ")
	Integer countAllBySearch(@Param(value = "search") String searchValue);
}
