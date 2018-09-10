package net.sup22.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sup22.domain.Question;
import net.sup22.domain.QuestionRepository;
import net.sup22.domain.User;

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
	
	@GetMapping("/form")
	public String QnaForm(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if(user == null) {
			return "redirect:/users/login";
		}
		return "/qna/form";
	}
	
	@PostMapping("")
	public String Create(String title, String contents, HttpSession session) {
		User user = (User)session.getAttribute("user");
		if(user == null) {
			return "redirect:/user/login";
		}
		
		if(title.isEmpty()) {
			return "redirect:/qna/form";
		}
		Question question = new Question(user, title, contents);
		questionRepository.save(question);
		return "redirect:/qna";
	}	
}
