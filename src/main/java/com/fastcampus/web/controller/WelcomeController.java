package com.fastcampus.web.controller;

import com.fastcampus.biz.domain.Blog;
import com.fastcampus.biz.persistence.BlogRepository;
import com.fastcampus.biz.service.BlogService;
import com.fastcampus.web.dto.BlogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class WelcomeController {

	private final BlogRepository blogRepository;
	private final BlogService blogService;

	@GetMapping({"", "/"})
	public String welcome(Model model) {
		model.addAttribute("blogList", blogRepository.findAll());
		return "welcome";
	}

	// 로그인 화면으로 이동
	@GetMapping("/login")
	public String loginView() {
		return "system/login";
	}

	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	// 검색 리스트
	@GetMapping("/blog/search")
	public String search(BlogDto blogDto, Model model) {
		if (Objects.equals(blogDto.getSearchCondition(), "TITLE")) blogDto.setSearchCondition("TITLE");
		if (blogDto.getSearchKeyword() == null) blogDto.setSearchKeyword("");

		model.addAttribute("condition", blogDto.getSearchCondition());
		model.addAttribute("keyword", blogDto.getSearchKeyword());

		List<Blog> blogList = blogService.blogList(blogDto);

		model.addAttribute("blogList", blogList);
		return "welcome";
	}

}
