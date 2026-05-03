package carrefour;

/**
 * Clase que define un supermercado
 * Hereda de carrefour.Tienda
 * @author xerve
 */
public final class Supermercado extends Tienda{

    private int seccionesS = 0;
    /**
     * El constructor de esta clase solo setea las secciones (como se indica en el enunciado)
     */
    public Supermercado(){
        seccionesS = setSecciones();
    }

    /**
     * Calcula los empleados de cada seccion
     * no existe la validacion de /0 por que ya se ha validado las secciones negativas en Tienda.setSecciones();
     * @return numero de empleados por seccion
     */
    @Override
    protected int empleadosSeccion() {
        int salida = 0;
        salida = empleados / seccionesS;
        System.out.println("Tienda tipo Supermercado, empleados: " + empleados + " | Secciones : " + seccionesS + " | Empleados por seccion: " + salida);
        return salida;
    }

    /**
     * Para poder decir de forma dinamica el tipo de tienda al usuario
     */
    @Override
    public String toString() {
        return "Supermercado";
    }
}
