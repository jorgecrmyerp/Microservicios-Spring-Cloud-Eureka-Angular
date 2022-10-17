package com.jgr.micro.app.usuarios.error;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// TODO: Auto-generated Javadoc
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
		model.addAttribute("error", "Error: ID no encontrado!");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		return "error/error";
	}

}
