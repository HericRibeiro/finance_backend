package com.finance.finance.presentation.user;

import com.finance.finance.application.user.UserService;
import com.finance.finance.common.exception.BusinessException;
import com.finance.finance.domain.user.User;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public User create(@RequestBody User user) {
        return service.create(user);
    }

    @GetMapping
    public List<User> list() {
        return service.findAll();
    }

    @GetMapping("/test-error")
    public void testError() {
        throw new BusinessException("Test error functionality");
    }
    
}
