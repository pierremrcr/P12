package com.openclassrooms.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.openclassrooms.webapp.model.Miel;
import com.openclassrooms.webapp.service.MielService;

@Controller
public class MielController {
	
   @Autowired
   private MielService mielService;
    
 
	 @GetMapping("/miels")
	 public String getMiels(Model model) {
		 
		  Iterable<Miel> listMiel = new ArrayList<Miel>();
		  listMiel = this.mielService.getMiels();  
		  model.addAttribute("miels", listMiel);
	      return "miels";
	    }
	
	 @GetMapping("/miel/{id}")
	 public String mielDetail(@PathVariable("id") final int id, Model model) {
		 
		  Miel miel = this.mielService.getMiel(id);
		  model.addAttribute("miel", miel);		    
		  return "miel";	 
	 }

}
