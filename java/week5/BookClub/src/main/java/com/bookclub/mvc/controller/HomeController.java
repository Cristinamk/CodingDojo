package com.bookclub.mvc.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.bookclub.mvc.models.Book;
import com.bookclub.mvc.models.LoginUser;
import com.bookclub.mvc.models.User;
import com.bookclub.mvc.service.BookService;
import com.bookclub.mvc.service.UserService;




@Controller

public class HomeController {
	@Autowired
	private  UserService userService;
	@Autowired 
	BookService bookService;
	
	
	
	@GetMapping("/")
	public String index(Model model) {
	model.addAttribute("user",new User());
	model.addAttribute("loginUser",new LoginUser());
	
		return "index.jsp";
	}
	
	@PostMapping("/")
		
	public String registration(@Valid @ModelAttribute("user") User user,BindingResult result,HttpSession session,Model model) {
		
		User newUser= userService.registration(user, result);
		if (result.hasErrors()) {
			model.addAttribute("loginUser",new LoginUser());
			return "index.jsp";
		}else {
		session.setAttribute("loggedIn",newUser.getId());
		return "redirect:/books";
		}
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute ("loginUser") LoginUser loginUser,BindingResult result,HttpSession session,Model model) {
		User loggedUser =userService.getOneByEmail(loginUser,result);
		if(result.hasErrors()) {
			model.addAttribute("user",new User());
			return "index.jsp";
		}else{session.setAttribute("loggedIn", loggedUser.getId());
			  return "redirect:/books";
		}
		
	}
	
	
	@GetMapping("/books")
	public String dashboard(Model model,HttpSession session) {
		model.addAttribute("user",userService.find((Long)session.getAttribute("loggedIn")));
		model.addAttribute("books", bookService.allBooks());
		return "dashboard.jsp";
	}
	
    @GetMapping("/newbook")
    public String newBook(@ModelAttribute("book") Book book, Model model, HttpSession session) {
    	
    	User user = userService.find((Long)session.getAttribute("loggedIn"));
    	model.addAttribute("user", user);
    	
    	return "newbook.jsp";
    }

    @PostMapping("/books/new")
    public String createBook(@Valid @ModelAttribute("book") Book book, BindingResult result) {

    	if (result.hasErrors()) {
    		return "newbook.jsp";
    	}
    	
    	bookService.createBook(book);
    	
    	return "redirect:/books";
    	
    }
    
	@GetMapping("/books/{id}")
	public String showBook(Model model,@PathVariable("id") Long id,HttpSession session) {
		if (session.getAttribute("loggedIn")==null) return "index.jsp";
		model.addAttribute("books", bookService.findBook(id));
		model.addAttribute("user", userService.find((Long)session.getAttribute("loggedIn")));
		return "show.jsp";
	}
	
    @DeleteMapping(value="/delete/{id}")
    public String destroy(@PathVariable("id") Long id ,HttpSession session) {
    	Book book = bookService.findBook(id);
    	if (!session.getAttribute("loggedIn").equals(book.getUser().getId())) return "redirect:/books";
    	else {  bookService.destroyBook(id);
    	 return "redirect:/books";
    	}
    }
//    
//    public String destroy(@PathVariable("id") Long id) {
//		bookService.destroyBook(id);
//		return "redirect:/books";
//   
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model,HttpSession session) {
     Book book = bookService.findBook(id);
 	 	if (!session.getAttribute("loggedIn").equals(book.getUser().getId())) return "redirect:/books";
        model.addAttribute("book", book);
        return "edit.jsp";
    }
    
    @PutMapping("/update/{id}")
    public String update(@Valid @ModelAttribute("book") Book book,BindingResult result, @PathVariable("id") Long id) {
    	if (result.hasErrors()) {
            return "edit.jsp";
        } else {
            bookService.updateBook(book);
            return "redirect:/books";
        }
    }
    
    
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loggedIn");
		return "redirect:/";
	}
	

}
