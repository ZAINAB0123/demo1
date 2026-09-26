package com.example.demo.service;

import com.example.demo.dto.user.ChangePasswordRequest;
import com.example.demo.dto.user.UserCreateRequest;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.dto.user.UserUpdateRequest;
import com.example.demo.entity.user.User;
import com.example.demo.exception.InvalidPasswordException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final PhoneNumberService phoneNumberService;

    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        String normalizedEmail = request.email().trim().toLowerCase(Locale.ROOT);
        userRepository.findByEmail(normalizedEmail).ifPresent(user -> {
            throw new UserAlreadyExistsException("User с таким email существует!");
        });
        String encodedPassword = passwordEncoder.encode(request.password());
        String normalizedNumber = phoneNumberService.normalizePhoneNumber(request.phone());
        User user = userMapper.toEntity(request, normalizedEmail, normalizedNumber, encodedPassword);
        User userSaved = userRepository.save(user);
        return userMapper.toResponse(userSaved);

    }

    @Transactional
    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found!"));
        String normalizedEmail = request.email().trim().toLowerCase(Locale.ROOT);
        if (!user.getEmail().equals(normalizedEmail)) {
            userRepository.findByEmail(normalizedEmail).ifPresent(userSaved -> {
                throw new UserAlreadyExistsException("Email занят!");
            });
        }
        user.setName(request.name());
        user.setEmail(normalizedEmail);
        user.setPhone(phoneNumberService.normalizePhoneNumber(request.phone()));
        return userMapper.toResponse(user);

    }

    @Transactional
    public void changePassword(ChangePasswordRequest passwordRequest) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found!"));
        if (!passwordEncoder.matches(
                passwordRequest.currentPassword(),
                user.getPassword()
        )) {
            throw new InvalidPasswordException("Current password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(passwordRequest.newPassword()));

    }

    @Transactional(readOnly = true)
    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found!"));
        return userMapper.toResponse(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAll() {
        return userMapper.toResponseList(userRepository.findAll());
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found!"));
        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    public UserResponse getUserByName(String name) {
        User user = userRepository.findByName(name).orElseThrow(() ->
                new UserNotFoundException("User not found!"));
        return userMapper.toResponse(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAllByDeals() {
        return userMapper.toResponseList(userRepository.findAllWithDeals());
    }


}
