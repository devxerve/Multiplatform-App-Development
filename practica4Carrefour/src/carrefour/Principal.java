package carrefour;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase que sirve para gestionar todo el sistema de tiendas de Carrefour
 * @author xerve
 */
public class Principal {
    public static Scanner input = new Scanner(System.in);

    /**
     * Metodo principal del programa, contiene un switch y 3 arrayList (1 por cada tipo de tienda)
     * El switch funciona en base a la variable opcion, que se le pide al usuario que establezca su valor
     * En base a esa variable, se crea un tipo de tienda u otro
     * @param args
     */
    public static void main(String[] args) {
        int opcion = 0;
        ArrayList<Hipermercado> listaHipermercados = new ArrayList<Hipermercado>();
        ArrayList<Supermercado> listaSupermercados = new ArrayList<Supermercado>();
        ArrayList<City> listaCity = new ArrayList<City>();
        do{
            System.out.println("Elige la tienda que vas a crear\n" +
                "1) Hipermercado\n2) Supermercado\n3) City\n" +
                "Pulsa cualquier otro número para salir"
            );
            opcion = leerInt();
            switch (opcion) {
                case 1:
                    System.out.println("Has elegido HIPERMERCADO");
                    listaHipermercados.add(new Hipermercado());
                    altaTienda(listaHipermercados.getLast());
                    break;
                case 2:
                    System.out.println("Has elegido SUPERMERCADO");
                    listaSupermercados.add(new Supermercado());
                    altaTienda(listaSupermercados.getLast());
                    break;
                case 3:
                    System.out.println("Has elegido CITY");
                    listaCity.add(new City());
                    altaTienda(listaCity.getLast());
                    break;
                default:
                    System.out.println("El numero de tiendas creadas es: " + Tienda.numeroTiendas + "\nDivididas en\n\t" +
                        listaHipermercados.size() + " Hipermercado(s)\n\t" + listaSupermercados.size()
                        + " Supermercado(s)\n\t" + listaCity.size() + " City(s)"
                    );
                    System.out.println("BYEEEEE");
                    System.exit(0);
            }
        }while(true);
    }
    
    /**
     * Da de alta una tienda, pasada por parametros, valida que los empleados minimos de cada seccion sean al menos 10
     * @param tienda Objeto tienda creado en el switch, se llama a sus metodos para poder setear todas las caracteristicas especificas de ese tipo de tienda
     */
    static void altaTienda(Tienda tienda){
        int empleados = tienda.empleadosSeccion();
        while(empleados <10){
            System.out.println("Es necesario más empleados, añadiendo");
            tienda.empleados += 10;
            System.out.println("Nuevos empleados de " + tienda.toString() + tienda.empleados);
            empleados = tienda.empleadosSeccion();
        }        
    }
    /**
     * Validacion de entrada de número entero
     * Comprueba que la entrada sea un número entero, y hasta que no lo sea no termina la ejecucion del metodo
     * @return numero entero introducido por el usuario
     */
    static int leerInt(){
        int salida = 0;
        boolean valido = false;
        while(!valido){
            try{
                salida = input.nextInt();
                valido = true;   
            } catch (InputMismatchException e) {
                System.out.println("[ERROR] Se esperaba un numero");
                input.nextLine();
                System.out.println("Introduce un numero de las opciones anteriores");
            }
        }
        return salida;    
    }

}
