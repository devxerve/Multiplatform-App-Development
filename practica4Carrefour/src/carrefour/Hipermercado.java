package carrefour;

/**
 * Clase que define un Hipermercado
 * Hereda de carrefour.Tienda
 * @author xerve
 */
public final class Hipermercado extends Tienda{
    
    private int seccionesH = 0;

    public Hipermercado(){
        empleados = super.empleados*3;
        metros2 = super.metros2*3;
        seccionesH = setSecciones();
    }

    /**
     * Calcula los empleados de cada seccion
     * no existe la validacion de /0 por que ya se ha validado las secciones negativas en Tienda.setSecciones();
     * @return numero de empleados por seccion
     */
    @Override
    protected int empleadosSeccion() {
        int salida = 0;
        salida = empleados / seccionesH;
        System.out.println("Tienda tipo Hipermercado, empleados: " + empleados + " | Secciones : " + seccionesH + " | Empleados por seccion: " + salida);
        return salida;
    }

    /**
     * Para poder decir de forma dinamica el tipo de tienda al usuario
     */
    @Override
    public String toString() {
        return "Hipermercado";
    }
}
