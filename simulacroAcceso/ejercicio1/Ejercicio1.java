package ejercicio1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Ejercicio1 {
    
    static int anadirDom(Document doc, String mueble, String precio, String modelo ){
        int resultado = 0;
        try{
            Node nmueble = doc.createElement("Mueble");
            Node nmuebletext = doc.createTextNode(mueble);
            ((Element)nmueble).setAttribute("Modelo",modelo);
            nmueble.appendChild(nmuebletext);

            Node nNombre = doc.createElement("Nombre");
            Node nNombreText = doc.createTextNode(modelo);
            nNombre.appendChild(nNombreText);

            Node nPrecio = doc.createElement("Precio");
            Node nPrecioText = doc.createTextNode(precio);
            nPrecio.appendChild(nPrecioText);

            nmueble.appendChild(nPrecio);
            nmueble.appendChild(nNombre);

            return 0;

        }catch(Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
    }
}
