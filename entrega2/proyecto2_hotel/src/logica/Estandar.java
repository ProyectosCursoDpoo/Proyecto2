package logica;

import java.util.*;

public class Estandar extends Habitacion {

  private int numero;
  private String ubicacion;
  private int capacidad;
  private boolean vista;
  private boolean balcon;
  private boolean cocina;
  private ArrayList<Cama> camas = new ArrayList<Cama>();
  private String estado;
  private HashMap<String, Integer> precio = new HashMap<String, Integer>();

  public Estandar(
      int numero,
      String ubicacion,
      int capacidad,
      boolean vista,
      boolean balcon,
      boolean cocina,
      ArrayList<Cama> camas,
      HashMap<String, Integer> precio,
      String estado) {
    this.numero = numero;
    this.ubicacion = ubicacion;
    this.capacidad = capacidad;
    this.vista = vista;
    this.balcon = balcon;
    this.cocina = cocina;
    this.camas = camas;
    this.estado = estado;
    this.precio = precio;
  }

  /**
   * @return int return the numero
   */
  public int getNumero() {
    return numero;
  }

  /**
   * @param numero the numero to set
   */
  public void setNumero(int numero) {
    this.numero = numero;
  }

  /**
   * @return String return the ubicacion
   */
  public String getUbicacion() {
    return ubicacion;
  }

  /**
   * @param ubicacion the ubicacion to set
   */
  public void setUbicacion(String ubicacion) {
    this.ubicacion = ubicacion;
  }

  /**
   * @return int return the capacidad
   */
  public int getCapacidad() {
    return capacidad;
  }

  /**
   * @param capacidad the capacidad to set
   */
  public void setCapacidad(int capacidad) {
    this.capacidad = capacidad;
  }

  /**
   * @return boolean return the vista
   */
  public boolean isVista() {
    return vista;
  }

  /**
   * @param vista the vista to set
   */
  public void setVista(boolean vista) {
    this.vista = vista;
  }

  /**
   * @return boolean return the balcon
   */
  public boolean isBalcon() {
    return balcon;
  }

  /**
   * @param balcon the balcon to set
   */
  public void setBalcon(boolean balcon) {
    this.balcon = balcon;
  }

  /**
   * @return boolean return the cocina
   */
  public boolean isCocina() {
    return cocina;
  }

  /**
   * @param cocina the cocina to set
   */
  public void setCocina(boolean cocina) {
    this.cocina = cocina;
  }

  /**
   * @return ArrayList<Cama> return the camas
   */
  public ArrayList<Cama> getCamas() {
    return camas;
  }

  /**
   * @param camas the camas to set
   */
  public void setCamas(ArrayList<Cama> camas) {
    this.camas = camas;
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

  public int getPrecioAhora(HashMap<String, Integer> precios, String fecha) {
    // LocalDate currentDate = LocalDate.now();
    // String mes=String.valueOf(currentDate).substring(5,7);
    // String dia=String.valueOf(currentDate).substring(8);
    // String fecha_now=String.valueOf(Integer.parseInt(mes+dia));

    int precio_por_fecha = 0;
    if (precios.containsKey(fecha)) {
      precio_por_fecha = precios.get(fecha);
    }
    return precio_por_fecha;
  }

  /**
   * @param precio the precio to set
   */
  public void setPrecio(HashMap<String, Integer> precio) {
    this.precio = precio;
  }

    public HashMap<String, Integer> getPrecio() {
        return precio;
    }

  @Override
  public String toString() {
    String cadena = "";
    cadena += getNumero() + ";";
    cadena += getUbicacion() + ";";
    cadena += getCapacidad() + ";";
    cadena += "1;";
    int contadorcamas = 1;
    for (Cama k : getCamas()) {
      cadena += k.getTamanio() + "-";
      cadena += k.getCapacidad();
      if (contadorcamas != getCamas().size()) {
        cadena += "/";
      }
      contadorcamas++;
    }

    cadena += ";";
    cadena += isVista() + ";";
    cadena += isBalcon() + ";";
    cadena += isCocina() + ";";
    cadena += getEstado() + "\n";

    return cadena;
  }
}
