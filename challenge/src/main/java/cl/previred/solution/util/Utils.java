package cl.previred.solution.util;

import org.springframework.stereotype.Service;
import cl.previred.solution.domain.ResponseError;
import cl.previred.solution.exception.PreviredException;

@Service
public class Utils {

	public ResponseError createError(PreviredException ex) {
		ResponseError error = new ResponseError();
		error.setMessage(ex.getMessage());
		return error;
	}

}
