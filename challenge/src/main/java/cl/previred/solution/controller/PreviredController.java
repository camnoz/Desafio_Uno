package cl.previred.solution.controller;

import cl.previred.solution.domain.Request;
import cl.previred.solution.domain.Response;
import cl.previred.solution.domain.ResponseError;
import cl.previred.solution.exception.PreviredException;
import cl.previred.solution.service.DateValidatorService;
import cl.previred.solution.util.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/previred")
@Api (tags = "Previred challenge")
public class PreviredController {

  private final DateValidatorService dateValidatorService;

  private final Utils responseError;

  public PreviredController(DateValidatorService dateValidatorService, Utils responseError) {
    this.dateValidatorService = dateValidatorService;
    this.responseError = responseError;
  }

  @GetMapping(
      value = "/dateValidator",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ApiOperation(value = "Date Validator", notes="Services to check and validate dates missing")
  @ApiResponses(value={@ApiResponse(code=200, message = "Get dates missing with successful"),
  @ApiResponse(code=400, message = "Request not correct")})
  public ResponseEntity<Response> dateValidator(@RequestBody Request request)
      throws PreviredException {
    return new ResponseEntity<>(dateValidatorService.dateValidator(request), HttpStatus.OK);
  }

  @ExceptionHandler(PreviredException.class)
  public ResponseEntity<ResponseError> exceptionHandler(PreviredException ex) {
    return new ResponseEntity<>(responseError.createError(ex), ex.getHttpStatus());
  }
}
