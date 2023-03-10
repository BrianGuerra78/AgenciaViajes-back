package mx.com.basantader.AgenciaViajeTA.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * This exception should be thrown in all cases when a resource cannot be found
 *
 */
public class NotAllowedOperationException extends RuntimeException {

    @Getter
	@Setter
	private String errorCode;

    /**
     *
     * @param message the message
     */
    public NotAllowedOperationException(final String message) {
        super(message);
    }
}
