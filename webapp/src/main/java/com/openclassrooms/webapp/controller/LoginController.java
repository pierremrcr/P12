package com.openclassrooms.webapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.openclassrooms.webapp.controller.command.UtilisateurRegisterForm;
import com.openclassrooms.webapp.model.Utilisateur;
import com.openclassrooms.webapp.service.UtilisateurService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	private UtilisateurService utilisateurService;
	   
	    @GetMapping(value = "/signup")
	    public String signup(Model model) {
	    	model.addAttribute("utilisateurRegisterForm", new UtilisateurRegisterForm());
	        return "signup";
	    }
	    
	    
	  
	        @PostMapping(value = "/signup")
	    	public ModelAndView createNewUser(@ModelAttribute("utilisateurRegisterForm") UtilisateurRegisterForm utilisateurRegisterForm, BindingResult result) {
	    	ModelAndView modelAndView = new ModelAndView("login");
	        if (result.hasErrors()) {
	        	result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
	            return modelAndView;
	        } else {
	            try {
	                Utilisateur utilisateurDto = createUser(utilisateurRegisterForm);
	            } catch (Exception exception) {
	                result.rejectValue("email", "error.utilisateurRegisterForm", exception.getMessage());
	                return modelAndView;
	            }
	        }
	        return new ModelAndView("home");
	    }
	    
	
	    
	    @GetMapping("/login")
	    public String login() {
	        return "login";
	    }
	    
	    @PostMapping("/login")
	    public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
	        Utilisateur utilisateur = utilisateurService.login(email, password);
	        if (utilisateur != null) {
	            session.setAttribute("user", utilisateur);
	            return "redirect:/home";
	        } else {
	            return "redirect:/login?error";
	        }
	    }
	    
	    @GetMapping("/logout")
	    public String logout(HttpSession session) {
	        session.invalidate();
	        return "redirect:/login";
	    }
	    
	    @GetMapping("/home")
	    public String home(HttpSession session) {
	        if (session.getAttribute("user") == null) {
	            return "redirect:/login";
	        }
	        return "home";
	    }
	    
	    private Utilisateur createUser(UtilisateurRegisterForm utilisateurRegisterRequest) {
	    	Utilisateur utilisateurDto = new Utilisateur();
	    	utilisateurDto.setNom(utilisateurRegisterRequest.getNom());
	    	utilisateurDto.setPrenom(utilisateurRegisterRequest.getPrenom());
	    	utilisateurDto.setAdresseMail(utilisateurRegisterRequest.getAdresseMail());
	    	utilisateurDto.setMotDePasse(utilisateurRegisterRequest.getMotDePasse());
	    	utilisateurDto.setTelephone(utilisateurRegisterRequest.getTelephone());
	    	
	    	Utilisateur utilisateur = this.utilisateurService.saveUtilisateur(utilisateurDto);
	    	
	    	return utilisateur;
	    	
	    }
	    
	    

}
