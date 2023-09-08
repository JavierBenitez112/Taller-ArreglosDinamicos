import java.util.Scanner;
public class TallerMecanicoApp {
   public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
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
                    
                    Vehiculo A1 = new Vehiculo(placa,marca,modelo,linea,fechaIngreso,nombreDueño);
                    
                    System.out.println("Los datos del cliente son: ");
                    A1.mostrarDatos();
                    System.out.println("Desea almacenarlos en el sistema? (S/N)");
                    String respuesta = sc.next();
                    if(respuesta.equals("S")){
                        System.out.println("Los datos han sido almacenados");
                    }
                    else{
                        System.out.println("Los datos no han sido almacenados");
                    }
                    break;
                case 2:
                //REGISTRAR REPARACIONES
                    System.out.println("Registrar reparaciones para un vehiculo");
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
