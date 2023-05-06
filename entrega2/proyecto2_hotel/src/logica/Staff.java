package logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
            HashMap<Integer, Consumo> consumos_actualizados, ArrayList<Integer> pedido, int numReserva, int nombreServicios, int cantidad, int lugar) {

        String nombre_servicio = "";
        //int numReserva = Integer.parseInt(input("Ingrese su numero de reserva"));
        //int nombreServicios = Integer.parseInt(input(
          //      "Ingrese el numero del servicio que desea pagar: \n 1. Spa \n 2. Restaurante \n 3. Guia Turistica"));

        reserva reservaActual = reserva.get(numReserva);
        Servicios servicio = null;
        if (nombreServicios == 1) {
            System.out.println("Se ha registrado un servicio de spa");
            servicio = new Spa();
            nombre_servicio = "Spa";
        } else if (nombreServicios == 2) {
            System.out.println("El servicio elegido es restaurante");
            nombre_servicio = "Restaurante";

            //int lugar = Integer.parseInt(input(
              //      "Ingrese el numero del lugar donde desea consumir los platos: \n 1. Restaurante \n 2. Habitacion"));
            servicio = new Restaurante();
            if (lugar == 1) {
                ((Restaurante) servicio).setUbicacion("Restaurante");
            } else if (lugar == 2) {
                ((Restaurante) servicio).setUbicacion("Habitacion");
            }
            ArrayList<Plato> platos = mostrarMenuRestaurante(menu, (Restaurante) servicio, lugar);
            realizarPedidoRestaurante(platos, (Restaurante) servicio, pedido);


        } else if (nombreServicios == 3) {
            //int cantidad = Integer.parseInt(input("Ingrese la cantidad de personas que desea tomar la guia turistica"));
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

        String factura = generarFactura(consumo);
        guardarFactura(numReserva, factura);

        consumos_actualizados.put(id, consumo);
        return consumos_actualizados;
    }

    public String facturaInmediata(HashMap<Integer, reserva> reserva, HashMap<String, Plato> menu,
            boolean pago,
            HashMap<Integer, Consumo> consumos_actualizados, ArrayList<Integer> pedido, int numReserva, int nombreServicios, int cantidad, int lugar) {

        String nombre_servicio = "";
        //int numReserva = Integer.parseInt(input("Ingrese su numero de reserva"));
        //int nombreServicios = Integer.parseInt(input(
          //      "Ingrese el numero del servicio que desea pagar: \n 1. Spa \n 2. Restaurante \n 3. Guia Turistica"));

        reserva reservaActual = reserva.get(numReserva);
        Servicios servicio = null;
        if (nombreServicios == 1) {
            System.out.println("Se ha registrado un servicio de spa");
            servicio = new Spa();
            nombre_servicio = "Spa";
        } else if (nombreServicios == 2) {
            System.out.println("El servicio elegido es restaurante");
            nombre_servicio = "Restaurante";

            //int lugar = Integer.parseInt(input(
              //      "Ingrese el numero del lugar donde desea consumir los platos: \n 1. Restaurante \n 2. Habitacion"));
            servicio = new Restaurante();
            if (lugar == 1) {
                ((Restaurante) servicio).setUbicacion("Restaurante");
            } else if (lugar == 2) {
                ((Restaurante) servicio).setUbicacion("Habitacion");
            }
            ArrayList<Plato> platos = mostrarMenuRestaurante(menu, (Restaurante) servicio, lugar);
            realizarPedidoRestaurante(platos, (Restaurante) servicio, pedido);


        } else if (nombreServicios == 3) {
            //int cantidad = Integer.parseInt(input("Ingrese la cantidad de personas que desea tomar la guia turistica"));
            nombre_servicio = "GuiaTuristica";

            servicio = new GuiaTuristica();
            servicio.setCantidadPersonas(cantidad);

        } 

        int id = 0;
        do {
            id = random.nextInt(1001);
        } while (consumos_actualizados.containsKey(id));
        Consumo consumo = new Consumo(reservaActual, servicio, pago, id, nombre_servicio);
        reservaActual.agregarConsumo(consumo);

        String factura = generarFactura(consumo);
        
        return factura;
    }

    public void guardarFactura(Integer numero_reserva, String factura) {
        File archivo = new File("../Proyecto2/entrega2/proyecto2_hotel/data/facturas_servicios/consumo" + String.valueOf(numero_reserva) + ".txt");
        String linea;
        String facturaActualizada = "";
        try {
                if(archivo.exists()){
                    BufferedReader br = new BufferedReader(new FileReader(archivo));
                    linea = br.readLine();
                    while (linea != null) {
                        facturaActualizada += linea;
                        facturaActualizada += "\n";
                        linea = br.readLine();
                    }
                    br.close();

                    BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                    bw.write(facturaActualizada);
                    bw.write("\n");
                    bw.write(factura);
                    bw.close();

                }else{
                    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                        "../Proyecto2/entrega2/proyecto2_hotel/data/facturas_servicios/consumo"
                        + String.valueOf(numero_reserva) + ".txt"))) ;
                        bw.write(factura);
                        bw.close();
                }
                        
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public String[][] menuRestaurante(HashMap<String, Plato> menu, int lugar) {
        int n = 1;
        ArrayList<Plato> platos = new ArrayList<Plato>();
        LocalDateTime actual = LocalDateTime.now();
        int hora = actual.getHour();
        List<Plato> menuDisponible = new ArrayList<Plato>();
        String[][] menuString = new String[menu.size()][2];
        int index = 0;
        if (lugar == 1) {
            System.out.println("Los productos disponibles para consumir en el restaurante en el Menu en este momento ("
                    + hora + "h) son: ");
            for (Map.Entry<String, Plato> entry : menu.entrySet()) {
                if (entry.getValue().getHoraInicio() <= hora &&
                        entry.getValue().getHoraFin() >= hora &&
                        (entry.getValue().getLugar().equals("Restaurante") ||
                                entry.getValue().getLugar().equals("HabitacionyRestaurante"))) {
    
                    System.out.println(n + ". " + entry.getKey() + " "
                            + entry.getValue().getPrecio());
                    platos.add(entry.getValue());
                    menuDisponible.add(entry.getValue());
                    menuString[index][0] = entry.getKey();
                    menuString[index][1] = String.valueOf(entry.getValue().getPrecio());
                    index += 1;
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
                    menuDisponible.add(entry.getValue());
                    menuString[index][0] = entry.getKey();
                    menuString[index][1] = String.valueOf(entry.getValue().getPrecio());
                    index += 1;
                    n += 1;
                }
            }
        }
        return menuString;
    }

    public ArrayList<Plato> mostrarMenuRestaurante(HashMap<String, Plato> menu, Restaurante servicio, int lugar) {
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
                                entry.getValue().getLugar().equals("HabitacionyRestaurante"))) {

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
        return platos;
    }

    public void realizarPedidoRestaurante(ArrayList<Plato> platos, Restaurante servicio, ArrayList<Integer> pedido) {
        for (int i = 0; i < pedido.size(); i++) {
            servicio.agregarPlato(platos.get(pedido.get(i) ));
        }
    }

    public String generarFactura(Consumo consumo) {
        StringBuilder sb = new StringBuilder();
        if (consumo.getServicio().getNombre().equals("Restaurante")) {
            sb = generarFacturaRestaurante(consumo);
        } else if (consumo.getServicio().getNombre().equals("Spa")) {
            sb = generarFacturaSpa(consumo);
        }

        else if (consumo.getServicio().getNombre().equals("GuiaTuristica")) {
            sb = generarFacturaGuiaTuristica(consumo);
        }
        return sb.toString();
    }

    public String mostrarFacturaPorReserva(HashMap<Integer, Consumo> consumos, int numeroReserva) {
        String info = "";
        BufferedReader br;
        String linea;
        try {
            br = new BufferedReader(
                    new FileReader(new File("../Proyecto2/entrega2/proyecto2_hotel/data/facturas_servicios/consumo"
                    + String.valueOf(numeroReserva) + ".txt")));
            linea = br.readLine();
            while (linea != null) {
                info += linea;
                info += "\n";

                linea = br.readLine();
            }
                            info += "-----------------------------------------------------\n";

        } catch (IOException e) {
        }

        return info;
        
        
    }

    public StringBuilder generarFacturaRestaurante(Consumo consumo) {
        StringBuilder sb = new StringBuilder();

        Restaurante restaurante = (Restaurante) consumo.getServicio();
        System.out.println(restaurante.getPlatos());

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