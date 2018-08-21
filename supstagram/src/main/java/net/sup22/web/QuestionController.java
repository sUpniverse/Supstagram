package net.sup22.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/qna")
public class QuestionController {
	
	@GetMapping("")
	public String Qna() {
		return "/qna/board";
	}
}
