package net.sup22.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sup22.domain.Question;
import net.sup22.domain.QuestionRepository;

@Controller
@RequestMapping("/qna")
public class QuestionController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("")
	public String Qna(Model model) {		
		model.addAttribute("questions", questionRepository.findAll());		
		return "/qna/board";
	}
	
	@GetMapping("/{id}")
	public String viewQuestion(@PathVariable Long id, Model model) {
		Question question = questionRepository.findById(id).get();
		model.addAttribute("question", question);
		return "/qna/show";
	}
}
