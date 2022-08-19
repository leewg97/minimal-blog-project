package com.fastcampus.biz.service;

import com.fastcampus.biz.domain.User;
import com.fastcampus.biz.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User getUser(String username) {
        Optional<User> findUser = userRepository.findByUsername(username);
        if (findUser.isPresent()) {
            return findUser.get();
        }
        return findUser.orElseGet(User::new);
    }

}
