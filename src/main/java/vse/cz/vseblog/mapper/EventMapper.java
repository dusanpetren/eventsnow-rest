package vse.cz.vseblog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import vse.cz.vseblog.data.response.EventResponseRST;
import vse.cz.vseblog.entity.EventEntity;

/**
 * @author dusan.petren
 **/
@Mapper(componentModel = "spring")
public interface EventMapper {


	EventEntity sourceToDestination(EventResponseRST source);

	@Mappings({
			@Mapping(target="author", source="destination.user.username"),
	})
	EventResponseRST destinationToSource(EventEntity destination);


}
