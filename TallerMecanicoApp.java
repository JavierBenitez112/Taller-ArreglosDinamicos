import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

public class TallerMecanicoApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        // Variables para las rutas de los archivos CSV, si se está corriendo en otra computadora cambiarlas

        String rutaVehiculosCSV = "C:\\Users\\javib\\OneDrive\\Escritorio\\Progra\\Vehiculos.csv"; // Reemplazar con la ruta a usar 
        String rutaReparacionesCSV = "C:\\Users\\javib\\OneDrive\\Escritorio\\Progra\\Servicios.csv"; // Reemplazar con la ruta a usar
        String rutaSucursalesCSV = "C:\\Users\\javib\\OneDrive\\Escritorio\\Progra\\Sucursales.csv";// Reemplazar con la ruta a usar

        List<String[]> datos = CSVManager.cargarDesdeCSV(rutaVehiculosCSV);

        // Guardar datos en un archivo CSV
        CSVManager.guardarEnCSV(datos, rutaReparacionesCSV);
        // Menu opciones
        System.out.println("Bienvenido a Talleres Perez");
        do {
            System.out.println("1. Registrar un vehiculo");
            System.out.println("2. Registrar reparaciones para un vehiculo");
            System.out.println("3. Obtener Estadisticas");
            System.out.println("4. Agregar nuevas sedes del taller y registrar informacion");
            System.out.println("Porfavor Indique que desea realizar: ");
            opcion = sc.nextInt();
            
            sc.nextLine(); // Consumir el salto de línea pendiente
            
            switch (opcion) {
                case 1:
                    // REGISTRO VEHICULOS
                    System.out.println("Registrar un vehiculo");
                    System.out.println("Ingrese la placa del vehiculo: ");
                    String placa = sc.nextLine();
                    System.out.println("Ingrese la marca del vehiculo: ");
                    String marca = sc.nextLine();
                    System.out.println("Ingrese el modelo del vehiculo: ");
                    String modelo = sc.nextLine();
                    System.out.println("Ingrese la linea del vehiculo: ");
                    String linea = sc.nextLine();
                    System.out.println("Ingrese la fecha de ingreso del vehiculo");
                    System.out.println("Debe cumplir con el siguiente formato dd/mm/aaaa");
                    String fechaIngreso = sc.nextLine();
                    System.out.println("Ingrese el nombre del dueño del vehiculo: ");
                    String nombreDueño = sc.nextLine();
                
                    // Crear el Array con los datos
                    String[] datosVehiculo = { placa, marca, modelo, linea, fechaIngreso, nombreDueño };
                    List<String[]> listaVehiculos = CSVManager.cargarDesdeCSV(rutaVehiculosCSV);
                
                    System.out.println("Los datos del vehículo son: ");
                    System.out.println("Placa: " + placa);
                    System.out.println("Marca: " + marca);
                    System.out.println("Modelo: " + modelo);
                    System.out.println("Línea: " + linea);
                    System.out.println("Fecha de ingreso: " + fechaIngreso);
                    System.out.println("Nombre del dueño: " + nombreDueño);
                    System.out.println("");
                    // Agregar los nuevos datos a la lista
                    listaVehiculos.add(datosVehiculo);
                
                    // Guardar toda la lista nuevamente en el archivo CSV
                    CSVManager.guardarEnCSV(listaVehiculos, rutaVehiculosCSV);
                    System.out.println("Los datos han sido almacenados");
                    break;
                    
                case 2:
                    // REGISTRAR REPARACIONES
                    System.out.println("Registrar reparaciones para un vehiculo");
                    System.out.println("Por favor ingrese la placa del vehiculo: ");
                    String placaVehiculo = sc.nextLine();
                    Vehiculo VH1 = CSVManager.buscarVehiculoPorPlaca(placaVehiculo, rutaVehiculosCSV);

                    if (VH1 != null) {
                        System.out.println("Los datos del vehiculo son: ");
                        VH1.mostrarDatos();
                        System.out.println("Desea registrar una reparación para este vehiculo? (S/N)");
                        String respuesta2 = sc.nextLine().toLowerCase().trim();

                        if (respuesta2.equals("s")) {
                            System.out.println("Ingrese el numero de factura: ");
                            int numeroFactura = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Ingrese el tipo de reparacion: ");
                            String tipoReparacion = sc.nextLine();
                            System.out.println("Ingrese la fecha de reparación: ");
                            System.out.println("Debe cumplir con el siguiente formato dd/MM/yyyy");
                            String fechaReparacionStr = sc.nextLine();

                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaReparacion;
                            try {
                                fechaReparacion = dateFormat.parse(fechaReparacionStr);
                            } catch (ParseException e) {
                                System.out.println("Formato de fecha incorrecto. Debe ser dd/MM/yyyy.");
                                return;
                            }

                            System.out.println("Ingrese el monto total: ");
                            double montoTotal = sc.nextDouble();
                            sc.nextLine(); // Consumir el salto de línea pendiente

                            Reparación R1 = new Reparación(numeroFactura, tipoReparacion, fechaReparacion, montoTotal);

                            // Construir el arreglo datosReparacion en el orden deseado
                            String[] datosReparacion = { dateFormat.format(fechaReparacion),
                                    Integer.toString(numeroFactura), tipoReparacion, Double.toString(montoTotal),
                                    placaVehiculo };

                            // Guardar los datos de reparación en el archivo CSV de reparaciones
                            CSVManager.guardarReparacionEnCSV(datosReparacion, rutaReparacionesCSV);

                            System.out.println("Los datos de la reparacion son: ");
                            R1.mostrarDetallesReparacion();
                            System.out.println("Desea almacenarlos en el sistema? (S/N)");
                            String respuesta3 = sc.nextLine().toLowerCase().trim();

                            if (respuesta3.equals("s")) {
                                System.out.println("Los datos han sido almacenados");
                            } else {
                                System.out.println("Los datos no han sido almacenados, ingreselos nuevamente");
                            }
                        } else {
                            System.out.println("No se han registrado reparaciones para este vehiculo");
                        }
                    } else {
                        System.out.println("No se ha encontrado el vehiculo");
                    }
                    break;
                
                case 3:
                    // Submenu de estadísticas
                    boolean submenuEstadisticas = true;
                    int opcionEstadisticas;
                    List<String[]> vehiculos = CSVManager.cargarDesdeCSV(rutaVehiculosCSV);
                    List<String[]> reparaciones = CSVManager.cargarDesdeCSV(rutaReparacionesCSV);

                    do {
                        System.out.println("Estadísticas:");
                        System.out.println("1. Promedio de montos de reparaciones");
                        System.out.println("2. Vehículos reparados en una semana");
                        System.out.println("3. Tipos de servicios más solicitados");
                        System.out.println("4. Marcas más comunes");
                        System.out.println("5. Modelos más frecuentes");
                        System.out.println("6. Ingresos totales en un rango de fechas");
                        System.out.println("7. Volver al menú principal");
                        System.out.println("Elija una opción: ");
                        opcionEstadisticas = sc.nextInt();

                        switch (opcionEstadisticas) {
                            case 1:
                                // Cálculo del promedio de montos de reparaciones
                                double promedioMontos = Estadisticas.PromedioMt(reparaciones);
                                System.out.println("Promedio de montos de reparaciones: " + promedioMontos);
                                break;
                            case 2:
                                System.out.println("Ingrese la fecha de inicio (dd/MM/yyyy): ");
                                String fechaInicio = sc.next();
                                System.out.println("Ingrese la fecha de fin (dd/MM/yyyy): ");
                                String fechaFin = sc.next();
                                List<String[]> vehiculosReparadosEnSemana = Estadisticas.encontrarVehiculosReparadosEnSemana(reparaciones, fechaInicio, fechaFin);

                                // Imprimir el número de vehículos reparados en la semana
                                int numVehiculosReparados = vehiculosReparadosEnSemana.size();
                                System.out.println("Número de vehículos reparados en la semana: " + numVehiculosReparados);
                                break;
                            case 3:
                                List<String> tiposServiciosMasSolicitados = Estadisticas.encontrarTiposServiciosMasSolicitados(reparaciones);
                                System.out.println("Tipos de servicios más solicitados: " + tiposServiciosMasSolicitados);
                                break;
                            case 4:
                                List<String> marcasMasComunes = Estadisticas.encontrarMarcasMasComunes(vehiculos);
                                System.out.println("Marcas más comunes: " + marcasMasComunes);
                                break;
                            case 5:
                                List<String> modelosMasFrecuentes = Estadisticas.encontrarModelosMasFrecuentes(vehiculos);
                                System.out.println("Modelos más frecuentes: " + modelosMasFrecuentes);
                                break;
                            case 6:
                                System.out.println("Ingrese la fecha de inicio (dd/MM/yyyy): ");
                                fechaInicio = sc.next();
                                System.out.println("Ingrese la fecha de fin (dd/MM/yyyy): ");
                                fechaFin = sc.next();
                                double ingresosTotales = Estadisticas.calcularIngresosTotales(reparaciones, fechaInicio, fechaFin);
                                System.out.println("Ingresos totales en el rango de fechas: " + ingresosTotales);
                                break;
                            case 7:
                                submenuEstadisticas = false;
                                break;
                            default:
                                System.out.println("Opción no válida.");
                        }
                    } while (submenuEstadisticas);
                    break;
                
                case 4:
                    // Gestionar sucursales
                    List<String> sucursales = GestionarSucursales.cargarSucursalesDesdeCSV(rutaSucursalesCSV);
                    GestionarSucursales.gestionarSucursales(sc, sucursales, rutaSucursalesCSV);
                    break;
                
                default:
                    System.out.println("Solo números entre 1 y 4");
            }
        } while (!salir);
    }
}
