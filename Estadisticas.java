import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Estadisticas {

    public static double PromedioMt(List<String[]> reparaciones) {
        double sumaMontos = 0.0;
        int cantidadReparaciones = 0;

        for (String[] reparacion : reparaciones) {
            double montoTotal = Double.parseDouble(reparacion[3]);
            sumaMontos += montoTotal;
            cantidadReparaciones++;
        }

        if (cantidadReparaciones > 0) {
            return sumaMontos / cantidadReparaciones;
        } else {
            return 0.0;
        }
    }

    public static List<String[]> encontrarVehiculosReparadosEnSemana(List<String[]> reparaciones, String fechaInicio, String fechaFin) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<String[]> vehiculosReparados = new ArrayList<>();

        try {
            Date inicio = sdf.parse(fechaInicio);
            Date fin = sdf.parse(fechaFin);

            for (String[] reparacion : reparaciones) {
                Date fechaReparacion = sdf.parse(reparacion[2]);
                if (fechaReparacion.after(inicio) && fechaReparacion.before(fin)) {
                    vehiculosReparados.add(reparacion);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return vehiculosReparados;
    }

    public static List<String> encontrarTiposServiciosMasSolicitados(List<String[]> reparaciones) {
        Map<String, Integer> tiposServiciosContador = new HashMap<>();

        for (String[] reparacion : reparaciones) {
            String tipoServicio = reparacion[1];
            tiposServiciosContador.put(tipoServicio, tiposServiciosContador.getOrDefault(tipoServicio, 0) + 1);
        }

        int maxCantidad = 0;
        List<String> tiposMasSolicitados = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : tiposServiciosContador.entrySet()) {
            if (entry.getValue() > maxCantidad) {
                maxCantidad = entry.getValue();
                tiposMasSolicitados.clear();
                tiposMasSolicitados.add(entry.getKey());
            } else if (entry.getValue() == maxCantidad) {
                tiposMasSolicitados.add(entry.getKey());
            }
        }

        return tiposMasSolicitados;
    }

    public static List<String> encontrarMarcasMasComunes(List<String[]> vehiculos) {
        Map<String, Integer> marcasContador = new HashMap<>();

        for (String[] vehiculo : vehiculos) {
            String marca = vehiculo[1];
            marcasContador.put(marca, marcasContador.getOrDefault(marca, 0) + 1);
        }

        int maxCantidad = 0;
        List<String> marcasMasComunes = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : marcasContador.entrySet()) {
            if (entry.getValue() > maxCantidad) {
                maxCantidad = entry.getValue();
                marcasMasComunes.clear();
                marcasMasComunes.add(entry.getKey());
            } else if (entry.getValue() == maxCantidad) {
                marcasMasComunes.add(entry.getKey());
            }
        }

        return marcasMasComunes;
    }

    public static List<String> encontrarModelosMasFrecuentes(List<String[]> vehiculos) {
        Map<String, Integer> modelosContador = new HashMap<>();

        for (String[] vehiculo : vehiculos) {
            String modelo = vehiculo[2];
            modelosContador.put(modelo, modelosContador.getOrDefault(modelo, 0) + 1);
        }

        List<String> modelosMasFrecuentes = modelosContador.entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return modelosMasFrecuentes;
    }

    public static double calcularIngresosTotales(List<String[]> reparaciones, String fechaInicio, String fechaFin) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        double ingresosTotales = 0.0;

        try {
            Date inicio = sdf.parse(fechaInicio);
            Date fin = sdf.parse(fechaFin);

            for (String[] reparacion : reparaciones) {
                Date fechaReparacion = sdf.parse(reparacion[2]);
                if (fechaReparacion.after(inicio) && fechaReparacion.before(fin)) {
                    double montoTotal = Double.parseDouble(reparacion[3]);
                    ingresosTotales += montoTotal;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ingresosTotales;
    }
}
