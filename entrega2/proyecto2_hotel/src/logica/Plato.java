package logica;

import java.time.LocalTime;

public class Plato {
    
    public String nombrePlato;
    public String nombreBebida;
    public int precio;
    public String rangoHora;
    public String lugar;

    public Plato(String nombrePlato, String nombreBebida, int precio, String rangoHora, String lugar) {
        this.nombrePlato = nombrePlato;
        this.nombreBebida = nombreBebida;
        this.precio = precio;
        this.rangoHora = rangoHora;
        this.lugar = lugar;
    }

    public int getHoraInicio() {
        String horaString = getRangoHora().substring(0, getRangoHora().indexOf("-"));
        LocalTime horaInicio = LocalTime.parse(horaString);
        int hora = horaInicio.getHour();
        return hora;
    }

    public int getHoraFin() {
        String horaString = getRangoHora().substring(getRangoHora().indexOf("-") + 1);
        LocalTime horaInicio = LocalTime.parse(horaString);
        int hora = horaInicio.getHour();
        return hora;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public String getNombreBebida() {
        return nombreBebida;
    }

    public void setNombreBebida(String nombreBebida) {
        this.nombreBebida = nombreBebida;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getRangoHora() {
        return rangoHora;
    }

    public void setRangoHora(String rangoHora) {
        this.rangoHora = rangoHora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @Override
    public String toString() {
        String cadena = "";
        cadena += getNombrePlato() + ";";
        cadena += getNombreBebida() + ";";
        cadena += getPrecio() + ";";
        cadena += getRangoHora() + ";";
        cadena += getLugar() + "\n";
    
       return cadena;


}

}