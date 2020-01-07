package vse.cz.vseblog.mapper;

import org.mapstruct.Mapper;

import vse.cz.vseblog.data.request.CreateEventRequestData;
import vse.cz.vseblog.entity.EventEntity;

/**
 * Popis
 *
 * @author dusan.petren
 */
@Mapper(componentModel="spring")
public interface CreateEventMapper {

	EventEntity sourceToDestination(CreateEventRequestData source);

	CreateEventRequestData destinationToSource(EventEntity destination);
}
