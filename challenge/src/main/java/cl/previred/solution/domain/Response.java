package cl.previred.solution.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Response {
  @JsonProperty("id")
  private int id;

  @JsonProperty("fechaCreacion")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private String fechaCreacion;

  @JsonProperty("fechaFin")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private String fechaFin;

  @JsonProperty("fechasFaltantes")
  @JsonFormat(shape = JsonFormat.Shape.ARRAY, pattern = "MM-dd-yyyy")
  private List<String> fechasFaltantes;

  public Response(int id, String fechaCreacion, String fechaFin, List<String> fechasFaltantes) {
    this.id = id;
    this.fechaCreacion = fechaCreacion;
    this.fechaFin = fechaFin;
    this.fechasFaltantes = fechasFaltantes;
  }

  public int getId() {
    return id;
  }

  public String getFechaCreacion() {
    return fechaCreacion;
  }

  public String getFechaFin() {
    return fechaFin;
  }

  public List<String> getFechasFaltantes() {
    return fechasFaltantes;
  }

}
