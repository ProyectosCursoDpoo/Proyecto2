package logica;

public class Consumo {
    public reserva reserva;
    public Servicios servicio;
    public int precioIndv;
    public boolean estado;
    public int id;
    public int cantidad;
    public String nombre;

    public Consumo(reserva reserva, Servicios servicio, boolean estado, int id, String nombre) {
        this.id = id;
        this.reserva = reserva;
        this.estado = estado;
        this.servicio = servicio;
        this.precioIndv = servicio.getPrecio();
        this.cantidad = servicio.getCantidadPersonas();
        this.nombre = nombre;

    }

    public boolean getEstadoPago() { // true = pagado, false = pendiente
        return estado;
    }

    public int getId() {
        return id;
    }

    public reserva getReserva() {
        return this.reserva;
    }

    public void setReserva(reserva reserva) {
        this.reserva = reserva;
    }

    public void setServicio(Servicios servicio) {
        this.servicio = servicio;
    }

    public void setprecioIndv(int precioIndv) {
        this.precioIndv = precioIndv;
    }

    public Servicios getServicio() {
        return servicio;
    }

    public int getPrecioIndv() {
        return precioIndv;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecioTotal() {
        return precioIndv * cantidad;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        String cadena = "";
        cadena += getId() + ";";
        cadena += (getEstadoPago()) ? "TRUE;" : "FALSE;";
        cadena += getReserva().getNumeroReserva() + ";";
        if (cadena != "") {
            cadena += getNombre() + ";";
        }
        cadena += getPrecioTotal() + "\n";

        return cadena;
    }
}
