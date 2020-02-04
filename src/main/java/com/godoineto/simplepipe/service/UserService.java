package com.godoineto.simplepipe.service;

import com.godoineto.simplepipe.api.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO create(UserDTO userDTO);
    Optional<UserDTO> update(String id, UserDTO userDTO);
    Optional<UserDTO> get(String id);
    List<UserDTO> listAll();
    void delete(String id);
}
