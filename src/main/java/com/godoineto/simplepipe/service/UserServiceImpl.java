package com.godoineto.simplepipe.service;

import com.godoineto.simplepipe.api.dto.UserDTO;
import com.godoineto.simplepipe.model.User;
import com.godoineto.simplepipe.repository.UserRepository;
import com.godoineto.simplepipe.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper mapper;
    private final UserRepository repository;

    public UserServiceImpl(UserMapper mapper, UserRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }
    @Override
    public UserDTO create(UserDTO userDTO) {
        User created = repository.save(mapper.toEntity(userDTO));
        return mapper.toDTO(created);
    }

    @Override
    public Optional<UserDTO> update(String id, UserDTO userDTO) {
        if (repository.existsById(id)) {
            return Optional.ofNullable(mapper.toDTO(updateUserInfo(id, userDTO)));
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> get(String id) {
        return repository.findById(id)
                .map(mapper::toDTO);
    }

    @Override
    public List<UserDTO> listAll() {
        return repository.findAll()
                .stream().map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        repository.findById(id)
                .ifPresent(repository::delete);
    }

    private User updateUserInfo(String id, UserDTO userDTO) {
        User user = mapper.toEntity(userDTO);
        user.setId(id);
        return repository.save(user);
    }
}
