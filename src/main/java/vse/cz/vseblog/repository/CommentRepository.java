package vse.cz.vseblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import vse.cz.vseblog.entity.CommentEntity;

/**
 * Popis
 *
 * @author dusan.petren
 */
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

	List<CommentEntity> findAllByUser_Username(String username);
}
