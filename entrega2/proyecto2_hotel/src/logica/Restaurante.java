package logica;

import java.util.ArrayList;

public class Restaurante extends Servicios {
    public int precio;
    public String ubicacion;
    public String horario;
    public ArrayList<Plato> platos;
    public String nombre;

    public Restaurante() {
        this.platos = new ArrayList<Plato>();
    }

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
        return "Restaurante";
    }

    public ArrayList<Plato> getPlatos() {
        return this.platos;
    }

    public void agregarPlato(Plato plato) {
        this.platos.add(plato);
        this.precio = this.precio + plato.getPrecio();
    }

}
