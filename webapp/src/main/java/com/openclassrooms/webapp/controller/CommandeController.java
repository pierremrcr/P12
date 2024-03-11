package com.openclassrooms.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.openclassrooms.webapp.model.Commande;
import com.openclassrooms.webapp.model.Miel;
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
	public String ajouterMiel(@PathVariable int mielId, @RequestParam("quantite") int quantite, HttpSession session) {
		Commande commande = (Commande) session.getAttribute("commande");	    
		if (commande == null) {
			commande = new Commande();
		}
		Miel miel = this.mielService.getMiel(mielId);

		Miel mielPourCommande = new Miel();
		mielPourCommande.setNom(miel.getNom());
		mielPourCommande.setPrix(miel.getPrix());
		mielPourCommande.setQuantite(quantite);

		commande.ajouterMiel(mielPourCommande);
		commande.ajouterMiel(miel);
		session.setAttribute("commande", commande);
		return "recapitulatifCommande";
	}

	@PostMapping("/validerCommande")
	public String validerCommande(HttpSession session, RedirectAttributes redirectAttributes) {
		Commande commande = (Commande) session.getAttribute("commande");
		if (commande != null) {
			commandeService.saveCommande(commande);
			redirectAttributes.addFlashAttribute("commande", commande);
	        session.removeAttribute("commande"); 
			session.removeAttribute("commande"); 
		}
		
		return "confirmationCommande"; 
	}
	
	@PostMapping("/annulerCommande")
    public String annulerCommande(SessionStatus sessionStatus, RedirectAttributes redirectAttributes) {
        sessionStatus.setComplete();
        redirectAttributes.addFlashAttribute("message", "La commande a été annulée avec succès.");

        return "home";
    }
	
	





}
