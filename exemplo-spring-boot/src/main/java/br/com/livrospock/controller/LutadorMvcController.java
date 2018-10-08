package br.com.livrospock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LutadorMvcController {

	@GetMapping("/home")
	public ModelAndView home(@RequestParam("usuario") String usuario) {
		// ações com o argumento 'usuario'
		return new ModelAndView("home");
	}

}
