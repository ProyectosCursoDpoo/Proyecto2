package logica;

import java.util.ArrayList;

public class reserva {

  private int numeroReserva;
  private Grupo grupo;
  private int tarifaReserva;
  private String estado;
  private String fechaRealizada;
  private String rangoFechaReserva;
  private Empleado empleado;
  private ArrayList<Consumo> consumos;
  private double saldoPendiente;

  public reserva(
      int numeroReserva,
      Grupo grupo,
      int tarifaReserva,
      String fechaRealizada,
      String rangoFechaReserva,
      Empleado empleado) {
    this.numeroReserva = numeroReserva;
    this.grupo = grupo;
    this.tarifaReserva = tarifaReserva;
    this.estado = "INICIADA";
    this.fechaRealizada = fechaRealizada;
    this.rangoFechaReserva = rangoFechaReserva;
    this.empleado = empleado;
    this.consumos = new ArrayList<Consumo>();

  }

  public int getNumeroReserva() {
    return this.numeroReserva;
  }

  public void setNumeroReserva(int numeroReserva) {
    this.numeroReserva = numeroReserva;
  }

  /**
   * @return Grupo return the grupo
   */
  public Grupo getGrupo() {
    return grupo;
  }

  /**
   * @param grupo the grupo to set
   */
  public void setGrupo(Grupo grupo) {
    this.grupo = grupo;
  }

  /**
   * @return int return the tarifaReserva
   */
  public int getTarifaReserva() {
    return tarifaReserva;
  }

  /**
   * @param tarifaReserva the tarifaReserva to set
   */
  public void setTarifaReserva(int tarifaReserva) {
    this.tarifaReserva = tarifaReserva;
  }

  /**
   * @return String return the estado
   */
  public String getEstado() {
    return estado;
  }

  /**
   * @param estado the estado to set
   */
  public void setEstado(String estado) {
    this.estado = estado;
  }

  /**
   * @return Date return the fechaRealizada
   */
  public String getFechaRealizada() {
    return fechaRealizada;
  }

  /**
   * @param fechaRealizada the fechaRealizada to set
   */
  public void setFechaRealizada(String fechaRealizada) {
    this.fechaRealizada = fechaRealizada;
  }

  /**
   * @return String return the rangoFechaReserva
   */
  public String getRangoFechaReserva() {
    return rangoFechaReserva;
  }

  /**
   * @param rangoFechaReserva the rangoFechaReserva to set
   */
  public void setRangoFechaReserva(String rangoFechaReserva) {
    this.rangoFechaReserva = rangoFechaReserva;
  }

  /**
   * @return Empleado return the empleado
   */
  public Empleado getEmpleado() {
    return empleado;
  }

  /**
   * @param empleado the empleado to set
   */
  public void setEmpleado(Empleado empleado) {
    this.empleado = empleado;
  }

  public ArrayList<Consumo> getConsumosPendientes() {
    ArrayList<Consumo> consumosPendientes = new ArrayList<Consumo>();
    for (Consumo consumo : consumos) {
      if (!consumo.getEstadoPago()) {
        consumosPendientes.add(consumo);
      }
    }
    return consumosPendientes;
  }

  public double getSaldoPendiente() {
    for (Consumo consumo : consumos) {
      if (!consumo.getEstadoPago()) {
        saldoPendiente += consumo.getPrecioTotal();
      }
    }
    return saldoPendiente;
  }

  public void agregarConsumo(Consumo consumo) {
    this.consumos.add(consumo);

  }

  public ArrayList<Consumo> getConsumos() {
    return consumos;
  }

  @Override
  public String toString() {
    String cadena = "";
    cadena += getNumeroReserva() + ";";
    cadena += getGrupo().getId() + ";";
    cadena += getTarifaReserva() + ";";
    cadena += getFechaRealizada() + ";";
    cadena += getRangoFechaReserva() + ";";
    cadena += getEmpleado().getUsuario() + "\n";

    return cadena;
  }
}
