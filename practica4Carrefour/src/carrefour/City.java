package carrefour;

/**
 * Clase que define un supermercado City
 * Hereda de carrefour.Tienda
 * @author xerve
 */
public final class City  extends Tienda{
private int seccionesC = 0;

    /**
     * Constructor que usa los atributos heredados 
     * modificandolos con las condiciones del enunciado
     */
    public City(){
        empleados = super.empleados/2;
        metros2 = super.metros2/2;
        seccionesC = setSecciones();
    }

    /**
     * Calcula los empleados de cada seccion
     * no existe la validacion de /0 por que ya se ha validado las secciones negativas en Tienda.setSecciones();
     * @return numero de empleados por seccion
     */
    @Override
    protected int empleadosSeccion() {
        int salida = 0;
        salida = empleados / seccionesC;
        System.out.println("Tienda tipo City, empleados: " + empleados + " | Secciones : " + seccionesC + " | Empleados por seccion: " + salida);
        return salida;
    }

    /**
     * Para poder decir de forma dinamica el tipo de tienda al usuario
     */
    @Override
    public String toString() {
        return "City";
    }
}
