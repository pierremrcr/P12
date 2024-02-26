package com.openclassrooms.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.webapp.model.Miel;
import com.openclassrooms.webapp.repository.MielProxy;

@Service
public class MielService {

    @Autowired
    private MielProxy mielProxy;

    public Miel getMiel(int id) {
        return mielProxy.getMiel(id);
    }

    public Iterable<Miel> getMiels() {
        return mielProxy.getMiels();
    }

    public void deleteMiel(final int id) {
        mielProxy.deleteMiel(id);
    }

    public Miel saveMiel(Miel miel) {
    	
        Miel savedMiel;

        if (miel.getId() == null) {
            // Si l'id est nul, alors c'est un nouveau miel.
            savedMiel = mielProxy.createMiel(miel);
        } else {
            savedMiel = mielProxy.updateMiel(miel);
        }
        return savedMiel;
    }
}
