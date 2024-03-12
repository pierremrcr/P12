package com.openclassrooms.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {
	
	@GetMapping("/contactForm")
    public String showContactForm() {   
        return "contact";
    }
	
	
	
	@PostMapping("/submitContactForm")
    public String handleFormSubmission(
            @RequestParam("nom") String nom,
            @RequestParam("email") String email,
            @RequestParam("message") String message,
            RedirectAttributes redirectAttributes) {
       
        redirectAttributes.addFlashAttribute("messageConfirmation", "Votre demande a bien été prise en compte. Merci de nous avoir contactés. Nous reviendrons vers vous rapidement.");
        
        return "contactConfirmation";
    }

}
