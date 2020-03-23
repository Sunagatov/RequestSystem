package com.zufar.requestsystem.service;

import com.zufar.requestsystem.dto.UserDTO;
import com.zufar.requestsystem.entity.User;
import com.zufar.requestsystem.exception.UserNotFoundException;
import com.zufar.requestsystem.repository.UserRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Collection<UserDTO> getAll() {
        return ((Collection<User>) this.userRepository.findAll())
                .stream()
                .map(UserService::convertToUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getById(Long id) {
        User userEntity = this.userRepository.findById(id).orElseThrow(() -> {
            final String errorMessage = "The user with id = " + id + " not found.";
            UserNotFoundException userNotFoundException = new UserNotFoundException(errorMessage);
            LOGGER.error(errorMessage, userNotFoundException);
            return userNotFoundException;
        });
        return UserService.convertToUserDTO(userEntity);
    }

    public UserDTO getByLogin(String login) {
        User userEntity = this.userRepository.findByLogin(login).orElseThrow(() -> {
            final String errorMessage = "The user with login = " + login + " not found.";
            UserNotFoundException userNotFoundException = new UserNotFoundException(errorMessage);
            LOGGER.error(errorMessage, userNotFoundException);
            return userNotFoundException;
        });
        return UserService.convertToUserDTO(userEntity);
    }

    public UserDTO save(UserDTO user) {
        User userEntity = UserService.convertToUser(user);
        userEntity = this.userRepository.save(userEntity);
        return UserService.convertToUserDTO(userEntity);
    }

    public UserDTO update(UserDTO user) {
        this.isExists(user.getId());
        User userEntity = UserService.convertToUser(user);
        userEntity = this.userRepository.save(userEntity);
        return UserService.convertToUserDTO(userEntity);
    }

    public void deleteById(Long id) {
        this.isExists(id);
        this.userRepository.deleteById(id);
    }

    public Boolean isExists(Long id) {
        if (!this.userRepository.existsById(id)) {
            final String errorMessage = "The user with id = " + id + " not found.";
            UserNotFoundException userNotFoundException = new UserNotFoundException(errorMessage);
            LOGGER.error(errorMessage, userNotFoundException);
            throw userNotFoundException;
        }
        return true;
    }

    public static User convertToUser(UserDTO user) {
        Objects.requireNonNull(user, "There is no user to convert.");
        return new User(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getLogin(),
                user.getPassword(),
                user.getRoles());
    }

    public static UserDTO convertToUserDTO(User user) {
        Objects.requireNonNull(user, "There is no user to convert.");
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getLogin(),
                user.getPassword(),
                user.getRoles());
    }
}
