
public class Vehiculo {
    // Atributos
    private String placa;
    private String marca;
    private String modelo;
    private String linea;
    private String fechaIngreso;
    private String nombreDueño;

    // Constructor
    public Vehiculo(String placa, String marca, String modelo, String linea, String fechaIngreso, String nombreDueño) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.linea = linea;
        this.fechaIngreso = fechaIngreso;
        this.nombreDueño = nombreDueño;
    }

    // Getters y setters para acceder a los datos privados
    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getLinea() {
        return linea;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public String getNombreDueño() {
        return nombreDueño;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    // Método para mostrar los datos del vehículo
    public void mostrarDatos() {
        System.out.println("Placa: " + placa);
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Línea: " + linea);
        System.out.println("Fecha de ingreso: " + fechaIngreso);
        System.out.println("Nombre del dueño: " + nombreDueño);
    }
}
