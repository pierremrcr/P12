package com.openclassrooms.webapp.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.openclassrooms.webapp.model.Commande;
import com.openclassrooms.webapp.model.Miel;
import com.openclassrooms.webapp.model.Utilisateur;
import com.openclassrooms.webapp.model.enums.StatutCommande;
import com.openclassrooms.webapp.service.CommandeService;
import com.openclassrooms.webapp.service.MielService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CommandeController {

	@Autowired
	private MielService mielService;

	@Autowired
	private CommandeService commandeService;

	@PostMapping("/ajouterMiel/{mielId}")
	public String ajouterMiel(@PathVariable int mielId, @RequestParam("quantite") int quantite, HttpSession session, Model model) {
		Commande commande = (Commande) session.getAttribute("commande");

		if (commande == null) {
			commande = new Commande();
			session.setAttribute("commande", commande);
		}

		Miel miel = this.mielService.getMiel(mielId);
		Miel mielPourCommande = new Miel();
		mielPourCommande.setNom(miel.getNom());
		mielPourCommande.setPrix(miel.getPrix());
		mielPourCommande.setQuantite(quantite);

		commande.ajouterMiel(mielPourCommande);
		commande.setStatut(StatutCommande.EN_ATTENTE);
		session.setAttribute("commande",commande);
		model.addAttribute("commande", commande);

		return "recapitulatifCommande";		
	}

	@GetMapping("/recapitulatifCommande")
	public String afficherRecapitulatifCommande(Model model, HttpSession session) {
		Commande commande = (Commande) model.getAttribute("commande");
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");
	//	Iterable<Commande> commandesPasses = commandeService.findCommandesByUtilisateur(utilisateur);
		if (commande == null) {
			commande = new Commande(); 
			commande.setMiels(new ArrayList<>());
		}
		
	//	model.addAttribute("commandes",commandesPasses);
		model.addAttribute("commande", commande);

		return "recapitulatifCommande";

	}

	@PostMapping("/validerCommande")
	public String validerCommande(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		Commande commande = (Commande) session.getAttribute("commande");
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");

		if (commande != null && utilisateur != null) {
			commande.setDateCommande(LocalDateTime.now()); 
			commande.setUtilisateur(utilisateur); 
			commande.setStatut(StatutCommande.EN_COURS);
			commandeService.saveCommande(commande);
			model.addAttribute("commande", commande);
			redirectAttributes.addFlashAttribute("commande", commande);
			session.removeAttribute("commande"); 
		}
		return "confirmationCommande"; 
	}

	@PostMapping("/annulerCommande")
	public String annulerCommande(HttpSession session,SessionStatus sessionStatus, RedirectAttributes redirectAttributes) {
		sessionStatus.setComplete();
		session.removeAttribute("commande"); 
		redirectAttributes.addFlashAttribute("message", "La commande a été annulée avec succès.");
		return "home";
	}







}
