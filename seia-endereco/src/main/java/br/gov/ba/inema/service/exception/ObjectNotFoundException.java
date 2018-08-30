package br.gov.ba.inema.service.exception;

/**
 * Exception para entidades nao encontradas
 * @author tulio
 *
 */
public class ObjectNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Mensagens de erros
	 */
	public static final String MSGE001 = "Entidade Inexistente.";
	
	public ObjectNotFoundException(String message) {
        super(message);
    }
	
}
