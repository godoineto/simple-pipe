package com.godoineto.simplepipe.service;

import com.godoineto.simplepipe.api.dto.UserDTO;
import com.godoineto.simplepipe.model.User;
import com.godoineto.simplepipe.repository.UserRepository;
import com.godoineto.simplepipe.service.mapper.UserMapper;
import fixture.UserDTOFixture;
import fixture.UserFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    UserService service;
    UserRepository repository;
    UserMapper mapper;

    @BeforeEach
    void setup() {
        repository = mock(UserRepository.class);
        mapper = mock(UserMapper.class);
        service = new UserServiceImpl(mapper, repository);
    }

    @Test
    void shouldCreateUserCorrectly() {
        UserDTO userDTO = UserDTOFixture.get();
        User user = UserFixture.get();
        when(mapper.toEntity(userDTO)).thenReturn(user);

        service.create(userDTO);

        verify(repository, times(1)).save(user);
    }

    @Test
    void shouldUpdateUserCorrectly() {
        UserDTO userDTO = UserDTOFixture.get();
        User user = UserFixture.get();
        when(mapper.toEntity(userDTO)).thenReturn(user);
        when(repository.existsById(userDTO.getId())).thenReturn(true);
        when(repository.save(user)).thenReturn(user);

        service.update(userDTO.getId(), userDTO);

        verify(repository, times(1)).save(any(User.class));
        verify(repository, times(1)).save(user);
    }

    @Test
    void shouldNotUpdateUserWheNotFoundId() {
        UserDTO userDTO = UserDTOFixture.get();
        when(repository.existsById(userDTO.getId())).thenReturn(false);

        service.update(userDTO.getId(), userDTO);

        verify(repository, never()).save(any(User.class));
    }

    @Test
    void shouldGetUserCorrectly() {
        UserDTO userDTO = UserDTOFixture.get();
        User user = UserFixture.get();
        when(repository.findById(userDTO.getId())).thenReturn(Optional.of(user));
        when(mapper.toDTO(user)).thenReturn(userDTO);

        service.get(userDTO.getId());

        verify(repository, times(1)).findById(userDTO.getId());
    }

    @Test
    void shouldListAllUsersCorrectly() {
        service.listAll();

        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldDeleteUserCorrectly() {
        UserDTO userDTO = UserDTOFixture.get();
        User user = UserFixture.get();
        when(repository.findById(userDTO.getId())).thenReturn(Optional.of(user));

        service.delete(userDTO.getId());

        verify(repository, times(1)).delete(user);
    }

}