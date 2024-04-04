package com.openclassrooms.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.openclassrooms.webapp.controller.command.UtilisateurRegisterForm;
import com.openclassrooms.webapp.model.Adresse;
import com.openclassrooms.webapp.model.Utilisateur;
import com.openclassrooms.webapp.service.UtilisateurService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	
	@GetMapping(value = "/login")
	public String login(Model model) {
		return "login";		
	}

	@PostMapping("/login")
	public String login(@RequestParam("adresseMail") String email, @RequestParam("motDePasse") String password, HttpSession session, Model model) {
		Utilisateur utilisateur = utilisateurService.login(email, password);
		if (utilisateur != null && verifyPassword(password, utilisateur.getMotDePasse())) {
			session.setAttribute("user", utilisateur);
			return "home";
		} else {
			String errorMessage = "Identifiant ou mot de passe incorrect.";
			model.addAttribute("errorMessage", errorMessage);
			return "login";
		}
	}

	@GetMapping(value = "/signup")
	public String signup(Model model) {
		UtilisateurRegisterForm utilisateurRegisterForm = new UtilisateurRegisterForm();
		utilisateurRegisterForm.setAdresse(new Adresse());
		model.addAttribute("utilisateurRegisterForm", utilisateurRegisterForm);
		return "signup";
	}


	@PostMapping(value = "/signup")
	public ModelAndView createNewUser(@ModelAttribute("utilisateurRegisterForm") UtilisateurRegisterForm utilisateurRegisterForm, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("signup");
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
			return modelAndView;
		} else {
			try {
				Utilisateur utilisateurDto = createUser(utilisateurRegisterForm);
			} catch (Exception exception) {
				result.rejectValue("adresseMail", "error.utilisateurRegisterForm", exception.getMessage());
				return modelAndView;
			}
		}
		return new ModelAndView("home");
	}


	@GetMapping("/logout")
	public String logout(HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
		response.setHeader("Pragma", "no-cache"); 
		response.setHeader("Expires", "0"); 
		return "home";
	}


	private Utilisateur createUser(UtilisateurRegisterForm utilisateurRegisterRequest) {
		Utilisateur utilisateurDto = new Utilisateur();
		utilisateurDto.setNom(utilisateurRegisterRequest.getNom());
		utilisateurDto.setPrenom(utilisateurRegisterRequest.getPrenom());
		utilisateurDto.setAdresseMail(utilisateurRegisterRequest.getAdresseMail());
		utilisateurDto.setMotDePasse(utilisateurRegisterRequest.getMotDePasse());
		utilisateurDto.setTelephone(utilisateurRegisterRequest.getTelephone());

		Adresse adresseDto = new Adresse();
		adresseDto.setRue(utilisateurRegisterRequest.getAdresse().getRue());
		adresseDto.setVille(utilisateurRegisterRequest.getAdresse().getVille());
		adresseDto.setCodePostal(utilisateurRegisterRequest.getAdresse().getCodePostal());
		adresseDto.setPays(utilisateurRegisterRequest.getAdresse().getPays());
		utilisateurDto.setAdresse(adresseDto);

		Utilisateur utilisateur = this.utilisateurService.saveUtilisateur(utilisateurDto);

		return utilisateur;

	}
	
	  private boolean verifyPassword(String rawPassword, String encodedPassword) {
	        return passwordEncoder.matches(rawPassword, encodedPassword);
	    }



}
