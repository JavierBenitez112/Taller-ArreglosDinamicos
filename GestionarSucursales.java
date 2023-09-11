import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionarSucursales {

    public static void main(String[] args) {
        ArrayList<String> sucursales = cargarSucursalesDesdeCSV("sucursales.csv");
        Scanner sc = new Scanner(System.in);

        boolean submenuSedes = true;

        do {
            System.out.println("Gestión de sedes:");
            System.out.println("1. Agregar nueva sucursal");
            System.out.println("2. Absorber una sucursal");
            System.out.println("3. Mostrar sucursales");
            System.out.println("4. Salir");
            System.out.println("Elija una opción: ");
            int opcionSedes = sc.nextInt();

            switch (opcionSedes) {
                case 1:
                    // Agregar nueva sucursal
                    System.out.println("Ingrese el nombre de la nueva sucursal: ");
                    String nuevaSucursal = sc.next();
                    sucursales.add(nuevaSucursal);
                    guardarSucursalesEnCSV("sucursales.csv", sucursales);
                    System.out.println("Nueva sucursal agregada con éxito.");
                    break;
                case 2:
                    // Absorber una sucursal
                    System.out.println("Seleccione la sucursal a absorber (ingrese el nombre): ");
                    String sucursalAabsorber = sc.next();
                    if (sucursales.contains(sucursalAabsorber)) {
                        sucursales.remove(sucursalAabsorber);
                        guardarSucursalesEnCSV("sucursales.csv", sucursales);
                        System.out.println("Sucursal absorbiendo información de " + sucursalAabsorber);
                    } else {
                        System.out.println("La sucursal no existe.");
                    }
                    break;
                case 3:
                    // Mostrar sucursales
                    System.out.println("Sucursales disponibles:");
                    for (String sucursal : sucursales) {
                        System.out.println(sucursal);
                    }
                    break;
                case 4:
                    submenuSedes = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (submenuSedes);
    }

    private static ArrayList<String> cargarSucursalesDesdeCSV(String archivoCSV) {
        ArrayList<String> sucursales = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivoCSV));
            String linea;
            while ((linea = br.readLine()) != null) {
                sucursales.add(linea);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo CSV.");
        }
        return sucursales;
    }

    private static void guardarSucursalesEnCSV(String archivoCSV, ArrayList<String> sucursales) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCSV));
            for (String sucursal : sucursales) {
                bw.write(sucursal);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo CSV.");
        }
    }
}
