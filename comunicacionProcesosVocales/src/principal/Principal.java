package principal;
import java.io.*;

/**
 * @author Javier Cervera Rodríguez
 * Esta clase lanza el proceso que a su vez ejecuta la clase ContarVocales.java, ademas asigna que el stdin y stderr 
 * de este proceso se guarden en ficheros especificos.
 */
public class Principal {
    static final File FICHERO = new File("lib/fichero.txt");
    static final String CLASE = "utilidades.ContarVocales";
    static final File RUTA = new File("./bin");
    static int totales = 0;
    
    /**
     * Lanza el proceso con los parametros indicados, redirecciona el stdout y stderr a los ficheros creados (ficheroResultado |)
     * @param letra la letra con la cual se lanza el proceso
     * @param fichero el fichero a leer
     * @return codigo de resultado en base a la finalización del proceso, si ocurre una IOException o InterruptedException devuelve -1
     */

    static int lanzarProceso(String letra, File fichero){
        File ficheroResultado = new File("resultado_"+letra+".txt");
        File ficheroError = new File("log_"+letra + ".txt");

        ProcessBuilder pb = new ProcessBuilder("java",CLASE,"../"+FICHERO.getPath(),letra); // * Añadimos ../ para que ContarVocales lo encuentre
        pb.directory(RUTA);
        pb.redirectOutput(ficheroResultado);
        pb.redirectError(ficheroError);
        Process proceso;

        try {

            proceso = pb.start();
            int retorno = proceso.waitFor();      
            return retorno;

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

        return -1;
    }
    public static void main(String[] args) {
        String [] vocales = new String[5];
        String contenido = getContenido();
        
        vocales[0] = "a";
        vocales[1] = "e";
        vocales[2] = "i";
        vocales[3] = "o";
        vocales[4] = "u";
        
            for(int i = 0; i<vocales.length;i++){
                String error = " ";
                System.out.println("[Main] - Lanzando proceso " + (i+1) + " (" + vocales[i] + ")");
                int resultado = lanzarProceso(vocales[i],FICHERO);

                if(resultado != 0){
                    error = "Ha ocurrido algo con el proceso y no se ha ejecutado correctamente";
                }

                System.out.println("[Main] - Resultado proceso " + (i+1) + " = "+ resultado +" | " + error);
            }

            for(int i = 0;i<vocales.length;i++){
               sumarVocales(vocales[i]);
            }

        System.out.println("[Main] - Total de vocales en el texto = " + totales);
        System.out.println("\n"+contenido);
        System.out.println("[MAIN] - BYEEE");
    }
    
    /**
     * Lee un archivo para sacar su contenido
     * @return Contenido del archivo o mensaje de error si no se puede acceder a el
     */

    static String getContenido() {
        
        try(BufferedReader br = new BufferedReader(new FileReader(FICHERO))){
            String linea;
            StringBuilder texto = new StringBuilder();
            while((linea = br.readLine()) != null){
                texto.append(linea + "\n");
            }
            return texto.toString();
        }catch(IOException e){
            System.out.println(e.getMessage());
            return "[Main - getContenido] Error accediendo al contenido del fichero";
        }
}
    /**
     * El metodo accede al contenido del archivo basandose en el patron de nombre y castea el contenido a un entero
     * y suma el numero a las vocales totales del texto
     * @param letra Sirve para poder acceder dinamicamente al fichero que se quiere mirar
     */

    static void sumarVocales(String letra) {
        int vocales = 0;

        try(BufferedReader br = new BufferedReader(new FileReader("resultado_"+letra+".txt"))){

            vocales = Integer.parseInt(br.readLine());
            System.out.println("[Main - sumarVocales] Vocales en el fichero resultado_"+letra+".txt = " + vocales);
            totales += vocales;

        }catch(IOException e){
            System.out.println("[Main - sumarVocales] Error accediendo a los ficheros para sumar las vocales");
        }
    }
    
}
