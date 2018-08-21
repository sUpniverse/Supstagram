package net.sup22.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@GetMapping("")
	public String Qna() {
		return "/article/board";
	}
}
