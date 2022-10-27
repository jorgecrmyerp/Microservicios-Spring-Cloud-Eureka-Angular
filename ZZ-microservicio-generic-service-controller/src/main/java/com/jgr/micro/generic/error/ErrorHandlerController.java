package com.jgr.micro.generic.error;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The Class ErrorHandlerController.
 */
@ControllerAdvice
public class ErrorHandlerController {
	
	/**
	 * Id no encontrado.
	 *
	 * @param ex the ex
	 * @param model the model
	 * @return the string
	 */
	@ExceptionHandler(IdNoEncontradoException.class)
	public String idNoEncontrado(IdNoEncontradoException ex, Model model) {
	//public ResponseEntity<?> idNoEncontrado(IdNoEncontradoException ex, Model model) {
		
		
		model.addAttribute("error", "Error: ID no encontrado!");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.NOT_FOUND);
		model.addAttribute("timestamp", new Date());
		return "error/error";
		
		
		/*
	
		Map<String, String> json = new HashMap<>();

			json.put("error", "Error: ID no encontrado!");
			json.put("message", ex.getMessage());
			json.put("status", HttpStatus.NOT_FOUND.toString());
			json.put("timestamp", new Date().toString());
	

		// PARA QUE LO ORDENE
		TreeMap<String, String> ordenado = new TreeMap<>(json);

		return new ResponseEntity<Map<String, String>>(ordenado, HttpStatus.NOT_FOUND);
		*/
		
	}
	
	@ExceptionHandler(ErrorBBDDException.class)
	public String ErrorBBDDException(ErrorBBDDException ex, Model model) {
		model.addAttribute("error", "Error: ID no encontrado!");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		return "error/error";
	}


}
