package logica;

import java.util.ArrayList;

public class Grupo {

    public ArrayList<Huesped> huespedes;
    public ArrayList<Habitacion> habitaciones;
    public int id;

    public Grupo(ArrayList<Huesped> huespedes, ArrayList<Habitacion> habitaciones, Integer id) {
        this.huespedes = huespedes;
        this.habitaciones = habitaciones;
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public ArrayList<Huesped> getHuespedes() {
        return huespedes;
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHuespedes(ArrayList<Huesped> huespedes) {
        this.huespedes = huespedes;
    }

    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    @Override
  public String toString() {
    String cadena = "";
    cadena += getId() + ";";

    int contadorhuesped = 1;
    for (Huesped k : getHuespedes()) {
      cadena += k.getIdentificacion();
      if (contadorhuesped != getHuespedes().size()) {
        cadena += "/";
      }
      contadorhuesped++;
    }
    cadena += ";";

    int contadorhabitaciones = 1;
    for (Habitacion k : getHabitaciones()) {
      cadena += k.getNumero();
      if (contadorhabitaciones != getHabitaciones().size()) {
        cadena += "/";
      }
      contadorhabitaciones++;
    }

    cadena += "\n";

    return cadena;
  }

}
