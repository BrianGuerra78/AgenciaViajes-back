package mx.com.basantader.AgenciaViajeTA.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.io.Serializable;
import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Necessary for proper Swagger documentation.
 *
 */
@SuppressWarnings("unused")
@AllArgsConstructor
@Getter
public class CustomErrorResponse implements Serializable {

    private static final long serialVersionUID = -7755563009111273632L;

    private int httpStatusCode; // 200, 201, 400, 500
    private HttpStatus httpStatus;
    private String message;
    private int errorCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
    private Date timeStamp;
    
    public CustomErrorResponse(int httpStatusCode, HttpStatus httpStatus, String message) {
        this.timeStamp = new Date();
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
