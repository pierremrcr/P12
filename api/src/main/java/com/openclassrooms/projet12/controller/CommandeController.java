package com.openclassrooms.projet12.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.projet12.model.Commande;
import com.openclassrooms.projet12.service.CommandeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/commandes")
public class CommandeController {

	@Autowired
    private final CommandeService commandeService;

    @Autowired
    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    
    @PostMapping
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commande) {
        Commande nouvelleCommande = commandeService.saveCommande(commande);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouvelleCommande);
    }

    
    @GetMapping
    public ResponseEntity<List<Commande>> getAllCommandes() {
        List<Commande> commandes = (List<Commande>) commandeService.findAllCommandes();
        return ResponseEntity.ok(commandes);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        Optional<Commande> commande = commandeService.findCommandeById(id);
        return commande.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id, @RequestBody Commande commandeDetails) {
        Commande miseAJourCommande = commandeService.updateCommande(id, commandeDetails);
        return ResponseEntity.ok(miseAJourCommande);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommandeById(id);
        return ResponseEntity.ok().build();
    }
}
