package mx.com.basantader.AgenciaViajeTA.exceptions;
import java.text.MessageFormat;
import java.util.MissingResourceException;

import mx.com.basantader.AgenciaViajeTA.commons.Messages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.Getter;
import lombok.Setter;

/**
 * This exception should be thrown in all cases when a resource cannot be found
 *
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {

    private static final Logger LOG = LogManager.getLogger(BusinessException.class);

    /** Serial Number. */
    private static final long serialVersionUID = 1L;

    /** C&oacute;digo de error. */
    private int code;

    private int httpCode;

    /** Descripci&oacute;n de error. */
    private String description;

    private String menssage;



    /**
     * Constructor de la clase que incluye mensaje de error.
     */
    public BusinessException() {
    }

    /**
     * Constructor de la clase que incluye mensaje de error.
     *
     * @param message
     *            the message
     */
    public BusinessException(String message) {
        super(message);

    }

    /**
     * Constructor de la clase que incluye mensaje de error.
     *
     * @param code
     *            the code
     */
    public BusinessException(Integer code) {

        this.description = Messages.getMessage(code);
        this.code = code;
        this.menssage = Messages.getMessage(code);
        super.initCause(new Throwable(Messages.getMessage(code)));// NOSONAR
    }

    public BusinessException(Integer code, Integer httpStatus) {
        this.description = Messages.getMessage(code);
        this.code = code;
        this.httpCode=httpStatus;
        super.initCause(new Throwable(Messages.getMessage(code)));// NOSONAR
    }

    /**
     * Constructor de la clase que incluye mensaje de error.
     *
     * @param code
     *            the code
     * @param params
     *            the params
     * @return the baja california exception
     */
    public static <T extends BusinessException> T createExceptionParams(Class<T> clazz, Integer code, Object... params) {
        BusinessException exception=null;
        try {
            exception = clazz.newInstance();

            String mensajeError = MessageFormat.format(Messages.getMessage(code), params);
            exception.description = mensajeError;
            exception.code = code;
            exception.initCause(new Throwable(mensajeError));
            LOG.debug(" BusinessException  code: {} mensaje: {}" ,code, mensajeError);
        }catch (MissingResourceException e) {
            LOG.error("WARNING: No se encontro mensaje de error {}",e);
        }catch (InstantiationException e) {
            LOG.error("Error al iniciarlizar el objeto ",e);
        } catch (IllegalAccessException e) {
            LOG.error("Error al accesar al objeto ",e);
        }

        return  (T) exception;
    }

    /**
     * Constructor de la clase que incluye mensaje de error y causa del error.
     *
     * @param message
     *            the message
     * @param cause
     *            the cause
     */
    public BusinessException(Integer code, Throwable cause) {
        String mensajeError = getMessage(code);
        this.description = mensajeError;
        this.code = code;
        initCause(new Throwable(mensajeError));
    }


    /**
     * Gets the message.
     *
     * @param code the code
     * @param params the params
     * @return the message
     */
    public String getMessage(Integer code, Object...params){
        return MessageFormat.format(Messages.getMessage(code), params);
    }

    /**
     * Constructor de la clase que incluye mensaje de error y causa del error.
     *
     * @param message
     *            the message
     * @param cause
     *            the cause
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor de la clase que incluye la causa del error.
     *
     * @param cause
     *            the cause
     */
    public BusinessException(Throwable cause) {
        super(cause);
    }
}

