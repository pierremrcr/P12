package com.openclassrooms.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.openclassrooms.webapp.model.Commande;
import com.openclassrooms.webapp.model.Utilisateur;
import com.openclassrooms.webapp.service.UtilisateurService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CompteController {

	@Autowired
	UtilisateurService utilisateurService;
	

	@GetMapping("/compte")
	public String showAccountPage(HttpServletRequest request, Model model, Utilisateur utilisateur) {
		HttpSession session = request.getSession();
		utilisateur = (Utilisateur) session.getAttribute("user");
		Long userId = utilisateur.getId();  
		utilisateur = utilisateurService.getUtilisateurById(userId);
		model.addAttribute("utilisateur", utilisateur);
		return "compte";
	}

	
	@PostMapping("/updateAccount")
    public String updateUtilisateur(@ModelAttribute Utilisateur utilisateur) {
		try {
        utilisateurService.updateUtilisateur(utilisateur);
		return "compte";
		} catch (Exception e) {
			return "error";
		}
	}

	@PostMapping("/deleteAccount")
	public String deleteAccount(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");
		if (utilisateur == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Vous devez être connecté pour effectuer cette action.");
			return "compte";
		}
		try {     
			Long userId = utilisateur.getId();          
			utilisateurService.deleteUtilisateurById(userId);
			session.invalidate();         
			redirectAttributes.addFlashAttribute("successMessage", "Votre compte a été supprimé avec succès.");
			return "home";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Un problème est survenu lors de la suppression de votre compte.");
			return "compte";
		}

	}
	
	
	@GetMapping("/commandes")
    public String Commandes(HttpServletRequest request,Model model, Utilisateur utilisateur) {
		HttpSession session = request.getSession();
		utilisateur = (Utilisateur) session.getAttribute("user");
		List<Commande> commandes = utilisateur.getCommandes();
        model.addAttribute("commmandes", commandes);
        return "commandes"; 
    }
	
	
	@GetMapping("/detail-commandes")
	public String detailCommandes(HttpServletRequest request,Model model, Utilisateur utilisateur) {
		return "detail-commande";
		
	}

	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "home"; 

	}


}



