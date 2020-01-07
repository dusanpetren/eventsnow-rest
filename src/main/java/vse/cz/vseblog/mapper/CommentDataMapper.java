package vse.cz.vseblog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import vse.cz.vseblog.data.response.CommentResponseRST;
import vse.cz.vseblog.entity.CommentEntity;

/**
 * Popis
 *
 * @author dusan.petren
 */

@Mapper(componentModel = "spring")
public interface CommentDataMapper {

	CommentEntity sourceToDestination(CommentResponseRST source);

	@Mappings({
			@Mapping(target="author", source="destination.user.username"),
	})
	CommentResponseRST destinationToSource(CommentEntity destination);

}
