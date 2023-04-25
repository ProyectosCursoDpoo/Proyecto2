package logica;

public class Spa extends Servicios {
    public int precio = 50000;
    public String ubicacion = "Spa";
    public String horario = "8:00 - 18:00";
    public String nombre;

    public int getPrecio() {
        return this.precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getUbicacion() {
        return this.ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getHorario() {
        return this.horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getNombre() {
        return "Spa";
    }
}
