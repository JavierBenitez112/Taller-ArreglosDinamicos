import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVManager {
    // Método para cargar datos desde un archivo CSV y devolverlos como una lista de strings
    public static List<String[]> cargarDesdeCSV(String archivo) {
        List<String[]> datos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                datos.add(campos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return datos;
    }

    // Método para guardar datos en un archivo CSV desde una lista de strings
    public static void guardarEnCSV(List<String[]> datos, String archivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (String[] fila : datos) {
                StringBuilder linea = new StringBuilder();
                for (int i = 0; i < fila.length; i++) {
                    linea.append(fila[i]);
                    if (i < fila.length - 1) {
                        linea.append(",");
                    }
                }
                bw.write(linea.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
