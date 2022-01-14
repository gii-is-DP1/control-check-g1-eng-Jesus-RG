package org.springframework.samples.petclinic.vacination;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vaccination")
public class VaccinationController {
	
	@Autowired
	private VaccinationService vs;
	
	@Autowired
	private PetService ps;
	
	private static final String VIEWS_VACCINATION_CREATE_OR_UPDATE_FORM = "vaccination/createOrUpdateVaccinationForm";
	
	@GetMapping(path = "/create")
	public String initCreationForm(ModelMap modelMap) {
		String view = VIEWS_VACCINATION_CREATE_OR_UPDATE_FORM;
		modelMap.addAttribute("vaccination", new Vaccination());
		modelMap.addAttribute("vaccines", vs.getAllVaccines());
		modelMap.addAttribute("pets", ps.findAllPets());
		return view;
	}
	
	@PostMapping(path="/create")
	public String processCreationForm(@Valid Vaccination vaccination, BindingResult result, ModelMap modelMap) {
		String view = "welcome";
		if(result.hasErrors()) {
			modelMap.addAttribute("vaccination", vaccination);
			modelMap.addAttribute("vaccine", vs.getAllVaccines());
			modelMap.addAttribute("pets", ps.findAllPets());
			return VIEWS_VACCINATION_CREATE_OR_UPDATE_FORM;
		}else {
			//vs.save(vaccination);
			modelMap.addAttribute("message", "Vaccination succesfully saved!");
		}
		return view;
	}
    
}
