package com.sergio.jwt.backend.services.impl;

import com.sergio.jwt.backend.dtos.CredentialsDto;
import com.sergio.jwt.backend.dtos.SignUpDto;
import com.sergio.jwt.backend.dtos.UserDto;
import com.sergio.jwt.backend.entites.User;
import com.sergio.jwt.backend.exceptions.AppException;
import com.sergio.jwt.backend.mappers.UserMapper;
import com.sergio.jwt.backend.repositories.UserRepository;
import com.sergio.jwt.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Override
    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByPhone(credentialsDto.phone())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    @Override
    public UserDto register(SignUpDto userDto) {
        Optional<User> optionalUser = userRepository.findByPhone(userDto.phone());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));

        User savedUser = userRepository.save(user);

        return userMapper.toUserDto(savedUser);
    }

    public UserDto findByPhone(String phone) {
        User user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }
    public Long getUserId(String phone){
        User user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return user.getId();
    }

}
