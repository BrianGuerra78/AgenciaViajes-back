package mx.com.basantader.AgenciaViajeTA.commons;

import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Messages {

	private Messages() {
		
	}
	
	private static final Logger LOG = LogManager.getLogger(Messages.class);
	
	private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("business-exception");
	
	private static final String BASE_ERROR_PROPERTY = "messages.AgenciaViajeTA.%s";
    
    
    public static String getMessage(int code) {
    	String key = String.format(BASE_ERROR_PROPERTY, code);
        String mensajeError = null;
        try{
            mensajeError = resourceBundle.getString(key);
            LOG.debug(" Exception  code: {} mensaje: {}" ,code, mensajeError);
        }catch (Exception e) {
            LOG.error(": No se encontro mensaje de error {}",key,e);
            mensajeError="";
        }
        
        return mensajeError;
    }
}
