package logica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.time.LocalDateTime;

public class Staff extends Empleado {
    private String usuario;
    private String contrasena;
    private String nombre;
    private String ocupacion;
    public Random random = new Random();

    public HashMap<Integer, Consumo> registrarServicio(HashMap<Integer, reserva> reserva, HashMap<String, Plato> menu,
            boolean pago,
            HashMap<Integer, Consumo> consumos_actualizados) {

        String nombre_servicio = "";
        int numReserva = Integer.parseInt(input("Ingrese su numero de reserva"));
        int nombreServicios = Integer.parseInt(input(
                "Ingrese el numero del servicio que desea pagar: \n 1. Spa \n 2. Restaurante \n 3. Guia Turistica"));

        reserva reservaActual = reserva.get(numReserva);
        Servicios servicio = null;
        if (nombreServicios == 1) {
            System.out.println("Se ha registrado un servicio de spa");
            servicio = new Spa();
            nombre_servicio = "Spa";
        } else if (nombreServicios == 2) {
            System.out.println("El servicio elegido es restaurante");
            nombre_servicio = "Restaurante";

            int lugar = Integer.parseInt(input(
                    "Ingrese el numero del lugar donde desea consumir los platos: \n 1. Restaurante \n 2. Habitacion"));
            servicio = new Restaurante();
            if (lugar == 1) {
                ((Restaurante) servicio).setUbicacion("Restaurante");
            } else if (lugar == 2) {
                ((Restaurante) servicio).setUbicacion("Habitacion");
            }
            mostrarMenuRestaurante(menu, (Restaurante) servicio, lugar);

        } else if (nombreServicios == 3) {
            int cantidad = Integer.parseInt(input("Ingrese la cantidad de personas que desea tomar la guia turistica"));
            nombre_servicio = "GuiaTuristica";

            servicio = new GuiaTuristica();
            servicio.setCantidadPersonas(cantidad);

        } else {
            System.out.println("Porfavor ingrese un numero valido");
        }

        int id = 0;
        do {
            id = random.nextInt(1001);
        } while (consumos_actualizados.containsKey(id));
        Consumo consumo = new Consumo(reservaActual, servicio, pago, id, nombre_servicio);
        reservaActual.agregarConsumo(consumo);

        if (pago) {
            generarFactura(consumo);
        }
        consumos_actualizados.put(id, consumo);
        return consumos_actualizados;
    }

    public void mostrarMenuRestaurante(HashMap<String, Plato> menu, Restaurante servicio, int lugar) {
        int n = 1;
        ArrayList<Plato> platos = new ArrayList<Plato>();
        LocalDateTime actual = LocalDateTime.now();
        int hora = actual.getHour();
        if (lugar == 1) {
            System.out.println("Los productos disponibles para consumir en el restaurante en el Menu en este momento ("
                    + hora + "h) son: ");
            for (Map.Entry<String, Plato> entry : menu.entrySet()) {
                if (entry.getValue().getHoraInicio() <= hora &&
                        entry.getValue().getHoraFin() >= hora &&
                        (entry.getValue().getLugar().equals("Restaurante") ||
                                entry.getValue().getLugar().equals("HabitacionyRestaurante)"))) {

                    System.out.println(n + ". " + entry.getKey() + " "
                            + entry.getValue().getPrecio());
                    platos.add(entry.getValue());
                    n += 1;
                }
            }
        } else if (lugar == 2) {
            System.out.println("Los productos disponibles para consumir en su habitacion en el Menu en este momento ("
                    + hora + "h) son: ");
            for (Map.Entry<String, Plato> entry : menu.entrySet()) {
                if (entry.getValue().getHoraInicio() <= hora &&
                        entry.getValue().getHoraFin() >= hora &&
                        (entry.getValue().getLugar().equals("HabitacionyRestaurante"))) {

                    System.out.println(n + ". " + entry.getKey() + " "
                            + entry.getValue().getPrecio());
                    platos.add(entry.getValue());
                    n += 1;
                }
            }
        }
        realizarPedidoRestaurante(platos, servicio);
    }

    public void realizarPedidoRestaurante(ArrayList<Plato> platos, Restaurante servicio) {
        boolean terminarOrden = false;
        if (platos.isEmpty()) {
            terminarOrden = true;
        }

        while (!terminarOrden) {
            int opcion = Integer.parseInt(input("Ingrese el numero del plato que desea ordenar"));
            Plato plato = platos.get(opcion - 1);
            servicio.agregarPlato(plato);

            String continuar = input("Desea agregar otro plato? (s/n)");
            if (continuar.equals("n")) {
                terminarOrden = true;
            }
        }
    }

    public void generarFactura(Consumo consumo) {
        StringBuilder sb = new StringBuilder();
        if (consumo.getServicio().getNombre().equals("Restaurante")) {
            sb = generarFacturaRestaurante(consumo);
        } else if (consumo.getServicio().getNombre().equals("Spa")) {
            sb = generarFacturaSpa(consumo);
        }

        else if (consumo.getServicio().getNombre().equals("GuiaTuristica")) {
            sb = generarFacturaGuiaTuristica(consumo);
        }
        System.out.println(sb.toString());
    }

    public void mostrarFacturaPorReserva(HashMap<Integer, Consumo> consumos) {
        int numeroReserva = Integer
                .parseInt(input("Ingrese el numero de reserva para ver los consumos asociados a esta reserva"));
        for (Map.Entry<Integer, Consumo> entry : consumos.entrySet()) {
            if (entry.getValue().getReserva().getNumeroReserva() == numeroReserva) {
                generarFactura(entry.getValue());
                System.out.println("\n");
            }
        }
    }

    public StringBuilder generarFacturaRestaurante(Consumo consumo) {
        StringBuilder sb = new StringBuilder();

        Restaurante restaurante = (Restaurante) consumo.getServicio();

        if (restaurante.getPlatos().isEmpty()) {
            System.out.println("---------------------\n");
            System.out.println("No se ha podido generar la factura, ya que no hay platos disponibles en este momento");
            System.out.println("---------------------\n");
        } else {
            sb.append("---------------------\n");
            sb.append("Factura Restaurante: \n");
            sb.append("---------------------\n");
            ArrayList<Plato> platos = restaurante.getPlatos();
            sb.append("Reserva numero: " + consumo.getReserva().getNumeroReserva() + "\n");
            sb.append("Ubicacion: " + restaurante.getUbicacion() + "\n");
            sb.append("---------------------\n");
            sb.append("Platos ordenados:\n");
            int total = 0;
            for (Plato plato : platos) {
                sb.append("     " + plato.getNombrePlato() + " - $" + plato.getPrecio() + "\n");
                total += plato.getPrecio();
            }
            sb.append("---------------------\n");
            sb.append("Precio total: $" + total + "\n");
            sb.append("---------------------\n");
            Boolean pago = consumo.getEstadoPago();
            if (pago) {
                sb.append("Tipo de pago: Inmediato\n");
            } else {
                sb.append("Tipo de pago: No inmediato - Se sumara a su checkout\n");
            }
            consumo.setprecioIndv(total);

        }
        return sb;
    }

    public StringBuilder generarFacturaGuiaTuristica(Consumo consumo) {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------\n");
        sb.append("Factura Guia Turística: \n");
        sb.append("---------------------\n");
        sb.append("Reserva numero: " + consumo.getReserva().getNumeroReserva() + "\n");
        sb.append("Cantidad de personas: " + consumo.getServicio().getCantidadPersonas() + "\n");
        sb.append("Precio por persona: " + consumo.getPrecioIndv() + "\n");
        sb.append("Precio total: " + consumo.getPrecioTotal() + "\n");
        Boolean pago = consumo.getEstadoPago();
        sb.append("---------------------\n");

        if (pago) {
            sb.append("Tipo de pago: Inmediato\n");
        } else {
            sb.append("Tipo de pago: No inmediato - Se sumara a su checkout\n");
        }
        return sb;
    }

    public StringBuilder generarFacturaSpa(Consumo consumo) {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------\n");
        sb.append("Factura Spa: \n");
        sb.append("---------------------\n");
        sb.append("Reserva numero: " + consumo.getReserva().getNumeroReserva() + "\n");
        sb.append("Precio por persona por servicio de spa: " + consumo.getPrecioIndv() + "\n");
        sb.append("---------------------\n");

        Boolean pago = consumo.getEstadoPago();
        if (pago) {
            sb.append("Tipo de pago: Inmediato\n");
        } else {
            sb.append("Tipo de pago: No inmediato - Se sumara a su checkout\n");
        }
        return sb;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public String getContrasena() {
        return this.contrasena;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getOcupacion() {
        return this.ocupacion;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String input(String mensaje) {
        try {
            System.out.print(mensaje + ": ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

}
