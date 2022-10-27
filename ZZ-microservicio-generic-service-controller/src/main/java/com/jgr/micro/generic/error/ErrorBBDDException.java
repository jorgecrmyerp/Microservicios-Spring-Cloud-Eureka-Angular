package com.jgr.micro.generic.error;

/**
 * The Class ErrorBBDDException.
 */
public class ErrorBBDDException extends RuntimeException{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5534692661974329026L;

	/**
	 * Instantiates a new error BBDD exception.
	 *
	 * @param id the id
	 */
	public ErrorBBDDException(String id) {
		super("Error en acceso a BBDD: ".concat(id));
	}

}
