package net.sup22.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sup22.domain.User;
import net.sup22.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserRepository userRepositroy;
	
	@GetMapping("/login")
	public String login() {
		return "/user/login";
	}
	
	@GetMapping("/signup")
	public String signin() {
		return "/user/signup";
	}
	
	@PostMapping("")
	public String create(User user) {
		System.out.println("USer :"+ user.toString());
		userRepositroy.save(user);
		return "redirect:/users/login";
	}	
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepositroy.findByUserId(userId);
		if(user == null) {
			return "redirect:/users/login";
		}
		if(!password.equals(user.getPassword())) {
			return "redirect:/users/login";
		}
		
		session.setAttribute("user", user);
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";
	}
	
}
