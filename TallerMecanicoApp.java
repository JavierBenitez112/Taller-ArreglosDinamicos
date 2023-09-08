import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class TallerMecanicoApp {
   public static void main(String [] args){
        try (Scanner sc = new Scanner(System.in)) {
            boolean salir = false;
            int opcion;
            
            //Menu opciones

            System.out.println("Bienvenido a Talleres Perez");
            do{
                System.out.println("1. Registrar un vehiculo");
                System.out.println("2. Registrar reparaciones para un vehiculo");
                System.out.println("3. Obtener Estadisticas");
                System.out.println("4. Agregar nuevas sedes del taller y registrar informacion");
                System.out.println("Porfavor Indique que desea realizar: ");
                opcion = sc.nextInt();
                switch(opcion){
                    case 1:
                    // REGISTRO VEHICULOS
                        System.out.println("Registrar un vehiculo");
                        System.out.println("Ingrese la placa del vehiculo: ");
                        String placa = sc.next();
                        System.out.println("Ingrese la marca del vehiculo: ");
                        String marca = sc.next();
                        System.out.println("Ingrese el modelo del vehiculo: ");
                        String modelo = sc.next();
                        System.out.println("Ingrese la linea del vehiculo: ");
                        String linea = sc.next();
                        System.out.println("Ingrese la fecha de ingreso del vehiculo");
                        System.out.println("Ddebe cumplir con el siguiente formato dd/mm/aaaa");
                        String fechaIngreso = sc.next();
                        System.out.println("Ingrese el nombre del dueño del vehiculo: ");
                        String nombreDueño = sc.next();
                        
                        //Crear el objeto Vehiculo

                        Vehiculo A1 = new Vehiculo(placa,marca,modelo,linea,fechaIngreso,nombreDueño);

                        //Crear el Array con los datos

                        String[] datosVehiculo = {placa, marca, modelo, linea, fechaIngreso, nombreDueño};
                        List<String[]> listaVehiculos = new ArrayList<>();

                        //Confirmar si los datos son los correctos
                        System.out.println("Los datos del cliente son: ");
                        A1.mostrarDatos();
                        System.out.println("Desea almacenarlos en el sistema? (S/N)");
                        String respuesta = sc.next();
                        if(respuesta.equals("S")){

                            //se cargan los datos al ArrayList
                            listaVehiculos.add(datosVehiculo);
                            CSVManager.guardarEnCSV(listaVehiculos, "vehiculos.csv");
                            System.out.println("Los datos han sido almacenados");
                            
                        }
                        else{
                            System.out.println("Los datos no han sido almacenados ingreselos nuevamente");
                        }
                        break;
                    case 2:
                    //REGISTRAR REPARACIONES
                        System.out.println("Registrar reparaciones para un vehiculo");
                        System.out.println("Porfavor ingrese la placa del vehiculo: ");
                        String placaVehiculo = sc.next();
                        Vehiculo VH1 = CSVManager.buscarVehiculoPorPlaca(placaVehiculo);

                        if(VH1 != null){
                            System.out.println("Los datos del vehiculo son: ");
                            VH1.mostrarDatos();
                            System.out.println("Desea registrar una reparacion para este vehiculo? (S/N)");
                            String respuesta2 = sc.next();
                            if(respuesta2.equals("S")){
                                System.out.println("Ingrese el numero de factura: ");
                                int numeroFactura = sc.nextInt();
                                System.out.println("Ingrese el tipo de reparacion: ");
                                String tipoReparacion = sc.next();
                                System.out.println("Ingrese la fecha de reparacion: ");
                                System.out.println("Debe cumplir con el siguiente formato dd/mm/aaaa");
                                String fechaReparacion = sc.next();
                                System.out.println("Ingrese el monto total: ");
                                double montoTotal = sc.nextDouble();

                                //Crear el objeto Reparacion

                                Reparación R1 = new Reparación(numeroFactura,tipoReparacion,fechaReparacion,montoTotal);

                                //Crear el Array con los datos

                                String[] datosReparacion = {Integer.toString(numeroFactura), tipoReparacion, fechaReparacion, Double.toString(montoTotal)};
                                List<String[]> listaReparaciones = new ArrayList<>();

                                //Confirmar si los datos son los correctos
                                System.out.println("Los datos de la reparacion son: ");
                                R1.mostrarDetallesReparacion();
                                System.out.println("Desea almacenarlos en el sistema? (S/N)");
                                String respuesta3 = sc.next();
                                if(respuesta3.equals("S")){

                                    //se cargan los datos al ArrayList
                                    listaReparaciones.add(datosReparacion);
                                    CSVManager.guardarEnCSV(listaReparaciones, "reparaciones.csv");
                                    System.out.println("Los datos han sido almacenados");
                                    
                                }
                                else{
                                    System.out.println("Los datos no han sido almacenados ingreselos nuevamente");
                                }
                            }
                            else{
                                System.out.println("No se han registrado reparaciones para este vehiculo");
                            }
                        }
                        else{
                            System.out.println("No se ha encontrado el vehiculo");
                        }

                        break;
                    case 3:
                    //OBTENER DATOS ESTADISTICOS
                        System.out.println("Obtener Estadisticas");
                        break;
                    case 4:
                    //AGREGAR NUEVAS SEDES
                        System.out.println("Agregar nuevas sedes del taller y registrar informacion");
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 4");
                }
            }
            while(!salir);
        }

   }
}
