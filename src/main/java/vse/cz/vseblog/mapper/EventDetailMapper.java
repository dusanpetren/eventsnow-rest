package vse.cz.vseblog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import vse.cz.vseblog.data.response.EventDetailResponseRST;
import vse.cz.vseblog.entity.EventEntity;

/**
 * @author dusan.petren
 */
@Mapper(componentModel = "spring")
public interface EventDetailMapper {

	EventEntity sourceToDestination(EventDetailResponseRST source);

	@Mappings({
			@Mapping(target="author", source="destination.user.username"),

	})
	EventDetailResponseRST destinationToSource(EventEntity destination);


}
