package carrefour;

import java.util.InputMismatchException;

/**
 * Modelo de datos del paquete carrefour
 * @author xerve
 */
public abstract class Tienda {

    protected int empleados = 50;
    protected int metros2 = 200;
    public static int numeroTiendas = 0;

    /**
     * El constructor de esta clase es usado al llamar a un constructor de sus hijos
     * Aumenta el numero de tiendas
     */
    public Tienda(){
        numeroTiendas++;
    }

    /**
     * Calcula los empleados por cada seccion
     * Hay que sobreescribirlo en cada clase que lo use 
     * @return empleados por seccion
     */
    protected abstract int empleadosSeccion();
    
    /**
     * Setea las secciones en base a la entrada del usuario
     * Implementado aqui por que es un metodo comun y que todos los metodos usan sin sobreescribir
     * Valida la entrada numerica positiva (no se pueden numeros negativos o letras)
     * @return numero de secciones
     */
    protected int setSecciones(){
        int secciones = 0;
        boolean valido = false;
        while(!valido){
            try {
                System.out.println("Introduce el numero de secciones");
                secciones = Principal.input.nextInt();
                if(secciones <=0){
                    System.out.println("[ERROR] No puedes crear secciones negativas");
                }else{
                    valido = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERROR] No se pueden poner secciones negativas o no numéricas");
                Principal.input.nextLine();
            }
        }
        return secciones;
    }
}
