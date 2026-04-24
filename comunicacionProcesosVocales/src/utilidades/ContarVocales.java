package utilidades;

import java.io.*;
<<<<<<< HEAD
/**?
=======
/**
>>>>>>> 673672a65872267ab5da135322c71e01153fd145
 * @author Javier Cervera Rodríguez
 */
public class ContarVocales {
/**
 * Cuenta vocales en un fichero y las muestra por stdout
 * @param args Argumentos de entrada del programa, aqui va la ruta del fichero y la vocal a buscar en el mismo
 */
public static void main(String[] args) {
    String linea = null;
    StringBuilder texto = new StringBuilder();
    int vocales = 0;
    if(args.length !=2){
        System.err.println("No se ha lanzado la clase con los parametros necesarios");
        System.exit(-1);
    }else{
        File fichero = new File(args[0]);
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {

            while((linea = br.readLine()) != null){
                texto.append(linea);
            }

            String contenido = texto.toString();
            char[] deletreado =  contenido.toCharArray();

            for(int i = 0; i<deletreado.length; i++){
                if((deletreado[i] == args[1].charAt(0)) || (deletreado[i] == args[1].toUpperCase().charAt(0)))
                    vocales++;
                }

            System.out.println(vocales);
            System.err.println("Todo ha ido correcto");
        } catch (IOException e) {
           System.err.println(e.getMessage());
        }
    }

}
}
