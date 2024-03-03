package com.openclassrooms.projet12.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.projet12.model.Miel;
import com.openclassrooms.projet12.repository.MielRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Data;

@Data
@Service
public class MielService {
	
	@Autowired
	private MielRepository mielRepository;
	
	public Optional<Miel> getMiel(final Long id) {
        return mielRepository.findById(id);
    }

    public Iterable<Miel> getAllMiel() {
        return mielRepository.findAll();
    }

    public void deleteMiel(final Long id) {
    	mielRepository.deleteById(id);
    }

    public Miel saveMiel(Miel miel) {
    	Miel savedMiel = mielRepository.save(miel);
        return savedMiel;
    }
    
    @Transactional
    public void deleteMielById(Long id) {
        if (!mielRepository.existsById(id)) {
            throw new EntityNotFoundException("Miel with ID " + id + " not found.");
        }
        mielRepository.deleteById(id);
    }
	

}
