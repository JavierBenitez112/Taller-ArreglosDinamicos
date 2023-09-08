import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVManager {
    private static final String SEPARADOR = ",";

    // Método para cargar datos desde un archivo CSV y devolverlos como una lista de strings
    public static List<String[]> cargarDesdeCSV(String archivo) {
        List<String[]> datos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String primeraLinea = br.readLine();
            if (primeraLinea != null) {
                String[] columnas = primeraLinea.split(SEPARADOR);

                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] campos = linea.split(SEPARADOR);
                    datos.add(campos);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return datos;
    }

    // Método para guardar datos en un archivo CSV desde una lista de strings
    public static void guardarEnCSV(List<String[]> datos, String archivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            if (!datos.isEmpty()) {
                // Escribir las columnas en la primera línea
                String[] columnas = datos.get(0);
                bw.write(String.join(SEPARADOR, columnas));
                bw.newLine();
            }

            for (int i = 1; i < datos.size(); i++) {
                String[] fila = datos.get(i);
                bw.write(String.join(SEPARADOR, fila));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Vehiculo buscarVehiculoPorPlaca(String placa) {
        List<String[]> datos = cargarDesdeCSV("C:\\Users\\javib\\OneDrive\\Escritorio\\Programas\\POOvehiculos\\vehiculos.csv");
    
        if (!datos.isEmpty()) {
            // Verificar si la primera fila contiene las columnas esperadas
            String[] columnas = datos.get(0);
            if (columnas.length >= 6 && Arrays.asList(columnas).contains("Placa") &&
                Arrays.asList(columnas).contains("Marca") &&
                Arrays.asList(columnas).contains("Modelo") &&
                Arrays.asList(columnas).contains("Línea") &&
                Arrays.asList(columnas).contains("FechaIngreso") &&
                Arrays.asList(columnas).contains("NombreDueño")) {
    
                // Buscar la placa en los datos
                for (int i = 1; i < datos.size(); i++) {
                    String[] fila = datos.get(i);
                    if (fila[0].equals(placa)) {
                        // Se encontró una fila con la placa especificada
                        String marca = fila[Arrays.asList(columnas).indexOf("Marca")];
                        String modelo = fila[Arrays.asList(columnas).indexOf("Modelo")];
                        String linea = fila[Arrays.asList(columnas).indexOf("Línea")];
                        String fechaIngreso = fila[Arrays.asList(columnas).indexOf("FechaIngreso")];
                        String nombreDueño = fila[Arrays.asList(columnas).indexOf("NombreDueño")];
    
                        // Crea un objeto Vehiculo con los datos encontrados
                        return new Vehiculo(placa, marca, modelo, linea, fechaIngreso, nombreDueño);
                    }
                }
            }
        }
    
        // Si no se encontró la placa o las columnas esperadas, devuelve null
        return null;
    }
    
}
