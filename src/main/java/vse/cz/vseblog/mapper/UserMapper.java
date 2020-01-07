package vse.cz.vseblog.mapper;

import org.mapstruct.Mapper;

import vse.cz.vseblog.data.response.UserResponseRST;
import vse.cz.vseblog.entity.UserEntity;

@Mapper(componentModel="spring")
public interface UserMapper {
	UserResponseRST sourceToDestination(UserEntity source);
	UserEntity destinationToSource(UserResponseRST destination);
}