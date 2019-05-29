package cl.previred.solution.service;

import cl.previred.solution.domain.Request;
import cl.previred.solution.domain.Response;
import cl.previred.solution.exception.PreviredException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class DateValidatorServiceImpl implements DateValidatorService {

  public Response dateValidator(Request request) {
    List<String> dateMissing = findDatesMissing(request);
    return new Response(
        request.getId(), request.getFechaCreacion(), request.getFechaFin(), dateMissing);
  }

  private List<String> findDatesMissing(Request request) {
    List<String> missingDates = new ArrayList<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Calendar beginCalendar = Calendar.getInstance();
    Calendar endCalendar = Calendar.getInstance();
    try {
      beginCalendar.setTime(dateFormat.parse(request.getFechaCreacion()));
      endCalendar.setTime(dateFormat.parse(request.getFechaFin()));
      endCalendar.add(Calendar.MONTH, 1);

      List<String> receivedDates = request.getFechas();
      while (beginCalendar.before(endCalendar)) {
        String date = dateFormat.format(beginCalendar.getTime()).toUpperCase();
        if (!receivedDates.contains(date)) {
          missingDates.add(date);
        }
        beginCalendar.add(Calendar.MONTH, 1);
      }
    } catch (ParseException e) {
      throw new PreviredException("Error to find dates misssing", HttpStatus.NOT_FOUND);
    }
    return missingDates;
  }
}
