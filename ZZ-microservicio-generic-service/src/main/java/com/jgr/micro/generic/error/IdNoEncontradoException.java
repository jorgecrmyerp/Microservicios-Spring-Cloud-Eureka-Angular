package com.jgr.micro.generic.error;

// TODO: Auto-generated Javadoc
/**
 * The Class IdNoEncontradoException.
 */
public class IdNoEncontradoException extends RuntimeException{
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7071318539477395872L;

	/**
	 * Instantiates a new id no encontrado exception.
	 *
	 * @param id the id
	 */
	public IdNoEncontradoException(String id) {
		super("ID: ".concat(id).concat(" no existe en el sistema"));
	}

}
