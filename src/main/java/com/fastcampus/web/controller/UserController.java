package com.fastcampus.web.controller;

import com.fastcampus.biz.domain.User;
import com.fastcampus.biz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 로그인
    @PostMapping("/login")
    public String login(User user, HttpSession session) {
        User findUser = userService.getUser(user.getUsername());

        if (findUser.getUsername() == null) {
            return "redirect:/login";
        } else {
            if (findUser.getPassword().equals(user.getPassword())) {
                session.setAttribute("blogId", findUser.getUserId());
                session.setAttribute("principal", findUser);
                return "redirect:/";
            } else {
                return "redirect:/login";
            }
        }
    }


}
