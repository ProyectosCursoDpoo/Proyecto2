package logica;

import java.io.*;
import java.util.*;

public class Administrador extends Empleado {

    private String usuario;
    private String contrasena;

    // public Administrador(String usuario, String contrasena) {
    // this.usuario = usuario;
    // this.contrasena = contrasena;
    // }

    public void cargarHabitaciones(File archivo, HashMap<String, Integer> precioEstandar,
            HashMap<String, Integer> precioSuite, HashMap<String, Integer> precioSuite2,
            HashMap<Integer, Habitacion> habitaciones) {
        System.out.println("Cargando Habitaciones desde Archivo");
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            linea = br.readLine();
            while (linea != null) {
                String[] partes = linea.split(";");
                int numero = Integer.valueOf(partes[0]);
                String ubicacion = partes[1];
                int capacidad = Integer.valueOf(partes[2]);
                int tipo = Integer.valueOf(partes[3]);

                ArrayList<Cama> camas_habitacion = new ArrayList<>();
                if (partes[4].contains("/")) {
                    String[] camas_total = (partes[4].split("/"));
                    for (String i : camas_total) {
                        String[] caracteristicas = i.split("-");
                        Cama cama_nueva = new Cama(caracteristicas[0], Integer.parseInt(caracteristicas[1]));
                        camas_habitacion.add(cama_nueva);
                    }
                } else {
                    String camas_total = partes[4];
                    String[] caracteristicas = camas_total.split("-");
                    Cama cama_nueva = new Cama(caracteristicas[0], Integer.parseInt(caracteristicas[1]));
                    camas_habitacion.add(cama_nueva);

                }

                boolean vista = Boolean.valueOf(partes[5]);
                boolean balcon = Boolean.valueOf(partes[6]);
                boolean cocina = Boolean.valueOf(partes[7]);
                String estado = partes[8];

                crearHabitacion(numero, ubicacion, capacidad, tipo, vista, balcon, cocina, camas_habitacion,
                        precioEstandar, precioSuite, precioSuite2,
                        habitaciones, estado);
                linea = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ejecutar_crearHabitacion(HashMap<String, Integer> precioEstandar, HashMap<String, Integer> precioSuite, HashMap<String, Integer> precioSuite2, HashMap<Integer, Habitacion> habitaciones) {
        System.out.println("Crear Habitación, Digite la info necesaria");
        int numero = Integer.parseInt(input("Ingrese número de habitación"));
        String ubicacion = input("Ingrese la ubicación");
        int capacidad = Integer.parseInt(input("Ingrese la capacidad"));
        int tipo = Integer.parseInt(input("Ingrese el tipo  (estandar: 1, suite: 2, doblesuite: 3)"));

        int opcion = 1;
        int num_cama = 1;
        ArrayList<Cama> camas_habitacion = new ArrayList<>();
        do {
            String tamanocama = input("Ingrese el tamaño de la cama #" + String.valueOf(num_cama));
            int capacidadcama = Integer.parseInt(input("Ingrese la capacidad de la cama #" + String.valueOf(num_cama)));
            Cama cama_nueva = new Cama(tamanocama, capacidadcama);
            camas_habitacion.add(cama_nueva);
            opcion = Integer.parseInt(input("Desea agregar más camas? (Si: 1 No: 2)"));
            num_cama++;
        } while (opcion != 2);

        boolean vista = Boolean.valueOf(input("Tiene Vista? true o false"));
        boolean balcon = Boolean.valueOf(input("Tiene Balcon? true o false"));
        boolean cocina = Boolean.valueOf(input("Tiene Cocina? true o false"));

        crearHabitacion(numero, ubicacion, capacidad, tipo, vista, balcon, cocina, camas_habitacion, precioEstandar,
                precioSuite, precioSuite2,
                habitaciones,"disponible");
    }

    private void crearHabitacion(int numero, String ubicacion, int capacidad, int tipo, boolean vista, boolean balcon,
            boolean cocina, ArrayList<Cama> camas, HashMap<String, Integer> precioEstandar,
            HashMap<String, Integer> precioSuite, HashMap<String, Integer> precioSuite2,
            HashMap<Integer, Habitacion> habitaciones, String estado) {
        Habitacion habi_nueva;

        if (habitaciones.get(numero) == null) {
            if (tipo == 1) {
                habi_nueva = new Estandar(numero, ubicacion, capacidad, vista, balcon, cocina, camas, precioEstandar,estado);
            } else if (tipo == 2) {
                habi_nueva = new Suite(numero, ubicacion, capacidad, vista, balcon, cocina, camas, precioSuite,estado);
            } else {
                habi_nueva = new Suite_doble(numero, ubicacion, capacidad, vista, balcon, cocina, camas, precioSuite2,estado);
            }
            habitaciones.put(numero, habi_nueva);
        } else {
            System.out.println("Este número de habitacion ya existe");
        }
    }

    public void cargarTarifa(File archivo, HashMap<String, Integer> tarifa) {
        System.out.println("Cargando Tarifas desde Archivo");
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            linea = br.readLine();
            while (linea != null) {
                String[] partes = linea.split(";");
                //String[] rangofecha = partes[0].split("-");
                String fecha=partes[0];
                int precio = Integer.valueOf(partes[1]);
                // int fecha_ini = Integer.parseInt(rangofecha[0]);
                // int fecha_fin = Integer.parseInt(rangofecha[1]);

                // while (fecha_ini != fecha_fin) {
                //     if (fecha_ini % 100 == 31) {
                //         fecha_ini = (fecha_ini - 31) + 100;
                //     }

                //     if (tarifa.get(String.valueOf(fecha_ini)) != null) {
                //         tarifa.replace(String.valueOf(fecha_ini), precio);
                //     } else {
                //         tarifa.put(String.valueOf(fecha_ini), precio);
                //     }

                //     fecha_ini++;
                // }

                if (tarifa.get(fecha) != null) {
                    tarifa.replace(fecha, precio);
                } else {
                    tarifa.put(fecha, precio);
                }

                linea = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cambiarTarifa(HashMap<String, Integer> tarifaEstandar,HashMap<String, Integer> tarifaSuite, HashMap<String, Integer> tarifaSuite2) {
        System.out.println("Cambiar Tarifa de un tipo de habitación");

        int opcionHabitacion=Integer.parseInt(input("A que tipo de habitacion desea hacer el cambio (Estandar: 1, Suite: 2, SuidteDoble: 3) "));
        String nueva_fecha=input("Ingrese el rango de las fechas a cambiar (0101-0601) ");
        int nueva_tarifa=Integer.parseInt(input("Ingrese el nuevo precio "));

        int fecha_ini = Integer.parseInt(nueva_fecha.substring(0,4));
        int fecha_fin = Integer.parseInt(nueva_fecha.substring(5));
        
        if (opcionHabitacion>=1 && opcionHabitacion<=3){
            while (fecha_ini != fecha_fin) {
                if (fecha_ini % 100 == 31) {
                    fecha_ini = (fecha_ini - 31) + 100;
                }
                if (opcionHabitacion == 1) {
                    if (tarifaEstandar.get(String.valueOf(fecha_ini)) != null) {
                        tarifaEstandar.replace(String.valueOf(fecha_ini), nueva_tarifa);
                    } else {
                        tarifaEstandar.put(String.valueOf(fecha_ini), nueva_tarifa);
                    }
                }
                
                else if (opcionHabitacion == 2) {
                        if (tarifaSuite.get(String.valueOf(fecha_ini)) != null) {
                            tarifaSuite.replace(String.valueOf(fecha_ini), nueva_tarifa);
                        } else {
                            tarifaSuite.put(String.valueOf(fecha_ini), nueva_tarifa);
                        }
                }

                else if (opcionHabitacion == 3) {
                    if (tarifaSuite2.get(String.valueOf(fecha_ini)) != null) {
                        tarifaSuite2.replace(String.valueOf(fecha_ini), nueva_tarifa);
                    } else {
                        tarifaSuite2.put(String.valueOf(fecha_ini), nueva_tarifa);
                    }
                }

                fecha_ini++;
            }

        } else{
            System.out.println("Opción de habitacion no disponible");
        }
    }

    public void cargarMenu(File archivo, HashMap<String,Plato> platos) {
        System.out.println("Cargando Menu desde Archivo");
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            linea = br.readLine();
            while (linea != null) {
                String[] partes = linea.split(";");
                String nombrePlato = partes[0];
                String nombreBebida = partes[1];
                int precio = Integer.valueOf(partes[2]);
                String rangoFecha = partes[3];
                String ubicacion = partes[4];

                Plato plato_nuevo= new Plato(nombrePlato,nombreBebida,precio,rangoFecha,ubicacion);
                if (platos.get(String.valueOf(nombrePlato)) != null) {
                    platos.replace(String.valueOf(nombrePlato), plato_nuevo);
                } else {
                    platos.put(String.valueOf(nombrePlato), plato_nuevo);
                }

                linea = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void configurarPlato(String nombreplato, int opcion, String modificacion, HashMap<String, Plato> platos) {
        System.out.println("Configurar Plato " + nombreplato);
        Plato plato_mod = platos.get(nombreplato);
        if (plato_mod != null) {

            if (opcion == 1) {
                System.out.println("Cambiar Nombre plato");
                plato_mod.setNombrePlato(nombreplato);
            } else if (opcion == 2) {
                System.out.println("Cambiar Bebida");
                plato_mod.setNombreBebida(modificacion);
            } else if (opcion == 3) {
                System.out.println("Cambiar precio");
                plato_mod.setPrecio(Integer.parseInt(modificacion));
            } else if (opcion == 4) {
                System.out.println("Cambiar Rango Hora");
                plato_mod.setRangoHora(modificacion);
            } else if (opcion == 5) {
                System.out.println("Cambair Ubicacion");
                plato_mod.setLugar(modificacion);
            }
            platos.replace(nombreplato, plato_mod);
        } else {
            System.out.println("El plato " + nombreplato + " no existe");
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
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
