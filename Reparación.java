import java.text.SimpleDateFormat;

public class Reparación {
    private int numeroFactura;
    private String tipoReparacion;
    private String fechaReparacion;
    private double montoTotal;

    public Reparación(int numeroFactura, String tipoReparacion, String fechaReparacion, double montoTotal) {
        this.numeroFactura = numeroFactura;
        this.tipoReparacion = tipoReparacion;
        this.fechaReparacion = fechaReparacion;
        this.montoTotal = montoTotal;
    }

    // Getters y setters
    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getTipoReparacion() {
        return tipoReparacion;
    }

    public void setTipoReparacion(String tipoReparacion) {
        this.tipoReparacion = tipoReparacion;
    }

    public String getFechaReparacion() {
        return fechaReparacion;
    }

    public void setFechaReparacion(String fechaReparacion) {
        this.fechaReparacion = fechaReparacion;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    // Método para mostrar los detalles de la reparación
    public void mostrarDetallesReparacion() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Número de Factura: " + numeroFactura);
        System.out.println("Tipo de Reparación: " + tipoReparacion);
        System.out.println("Fecha de Reparación: " + dateFormat.format(fechaReparacion));
        System.out.println("Monto Total: $" + montoTotal);
    }
}
