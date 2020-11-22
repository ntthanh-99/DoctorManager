package doctors;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import doctors.data.DoctorRepository;

@Controller
public class HomeController {
	private final DoctorRepository doctorRepo;
	@Autowired
	public HomeController(DoctorRepository doctorRepo) {
	this.doctorRepo = doctorRepo;
	}

	@GetMapping("/")
	public ModelAndView addModeltoView() {
		List<Doctor> listDoctor = (List<Doctor>) doctorRepo.findAll();
	    ModelAndView modelAndView = new ModelAndView("home");
	    modelAndView.addObject("listDoctor", listDoctor);
	    return modelAndView;
	}
	@GetMapping("/new")
	public String addNew(Model model) {
		model.addAttribute("doctor", new Doctor());
		return "addDoctor";
	}
	@PostMapping("/save")
	public String save(Doctor doctor) {
		doctorRepo.save(doctor);
		return "redirect:/";
	}
	@GetMapping("/edit")
	public String edit(@RequestParam String id,Model model) {
		Optional<Doctor> doctorEdit=doctorRepo.findById(id);
		doctorEdit.ifPresent(doctor -> model.addAttribute("doctor", doctor)); 
		return "editDoctor";
	}
	@GetMapping("/delete")
	public String delete(@RequestParam String id) {
		doctorRepo.deleteById(id);
		return "redirect:/";
	}
	@GetMapping("/search")
	public ModelAndView search(@RequestParam String keyword,Model model) {
		List<Doctor> listDoctor = (List<Doctor>) doctorRepo.search(keyword);
	    ModelAndView modelAndView = new ModelAndView("searchResult");
	    modelAndView.addObject("listDoctor", listDoctor);
	    return modelAndView;
	}
}