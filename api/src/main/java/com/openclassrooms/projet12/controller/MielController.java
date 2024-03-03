package com.openclassrooms.projet12.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.projet12.model.Miel;
import com.openclassrooms.projet12.model.enums.TypeMiel;
import com.openclassrooms.projet12.service.MielService;

@RestController
public class MielController {

	@Autowired
	private MielService mielService;

	@GetMapping("/miels")
	public Iterable<Miel> getAllMiel() {
		return mielService.getAllMiel();
	}

	@GetMapping("/miel/{id}")
	public Miel getMiel(@PathVariable("id") final Long id) {
		Optional<Miel> miel = mielService.getMiel(id);
		if(miel.isPresent()) {
			return miel.get();
		} else {
			return null;
		}
	}

	@PostMapping("/miel")
	public ResponseEntity<Miel> createMiel(@RequestBody Miel miel) {
	    Miel savedMiel = mielService.saveMiel(miel);
	    return new ResponseEntity<>(savedMiel, HttpStatus.CREATED);
	}

	public Miel updateMiel(@PathVariable("id") final Long id, @RequestBody Miel miel) {
		Optional<Miel> m = mielService.getMiel(id);
		if(m.isPresent()) {
			Miel currentMiel = m.get();

			String nom = miel.getNom();
			if (nom != null) {
				currentMiel.setNom(nom);
			}

			String description = miel.getDescription();
			if (description != null) {
				currentMiel.setDescription(description);
			}

			TypeMiel typeMiel = miel.getTypeMiel();
			if (typeMiel != null) {
				currentMiel.setTypeMiel(typeMiel);
			}

			Integer stock = miel.getStock();
			if (stock != null) {
				currentMiel.setStock(stock);
			}

			Double prix = miel.getPrix();
			if (prix != null) {
				currentMiel.setPrix(prix);
			}

			mielService.saveMiel(currentMiel);
			return currentMiel;

		} else {
			return null;
		}
	}
	
	@DeleteMapping("/miel/{id}")
    public void deleteMiel(@PathVariable("id") Long id) {
        mielService.deleteMielById(id);
    }
}








