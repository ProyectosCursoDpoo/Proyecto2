package logica;


public class Factura {

    public int id;
    public String fecha;
    public reserva reserva;
    public int impuesto;
    public int tarifaTotal;

    public Factura(int id, String fecha, reserva reserva, int impuesto,
            int tarifaTotal) {
        this.id = id;
        this.fecha = fecha;
        this.reserva = reserva;
        this.impuesto = impuesto;
        this.tarifaTotal = tarifaTotal;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setImpuesto(int impuesto) {
        this.impuesto = impuesto;
    }

    public void setTarifaTotal(int tarifaTotal) {
        this.tarifaTotal = tarifaTotal;
    }

    public int getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }



    public int getImpuesto() {
        return impuesto;
    }

    public int getTarifaTotal() {
        return tarifaTotal;
    }

}
