package ejercicio5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio5 {
    public static int escribirArchivo(String archivo1, String archivo2){
	try(BufferedReader br = new BufferedReader(new FileReader(archivo2));
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo1));) {

        String texto = br.readLine();
        while((texto = br.readLine())!= null){
            bw.write(texto);
            bw.newLine();
        }

        return 0;
    } catch (IOException e) {
        return -1;
    }

}
}
