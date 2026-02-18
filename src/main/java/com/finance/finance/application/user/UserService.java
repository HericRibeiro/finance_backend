package com.finance.finance.application.user;

import com.finance.finance.domain.user.User;
import com.finance.finance.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User create(User user) {
        user.setCreatedAt(LocalDateTime.now());
        return repository.save(user);
    }

    public List<User> findAll() {
        return repository.findAll();
    }
}
