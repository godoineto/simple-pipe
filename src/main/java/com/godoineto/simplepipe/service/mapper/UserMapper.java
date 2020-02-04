package com.godoineto.simplepipe.service.mapper;

import com.godoineto.simplepipe.api.dto.UserDTO;
import com.godoineto.simplepipe.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
}
