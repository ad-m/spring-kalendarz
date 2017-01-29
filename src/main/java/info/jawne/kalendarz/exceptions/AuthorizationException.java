package info.jawne.kalendarz.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Login required")
public class AuthorizationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1995715334549461103L;

}
