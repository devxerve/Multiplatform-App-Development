package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {

    public String fileContent = "";
    private int totalBooks = 0;
    private String currentTag = "";
    
    /**
     * Muestra un mensaje inicial en un JOptionPane y luego muestra los libros
     * Ademas cuenta los libros mostrados y los pone al final del String
     * @param uri espacio de nombres del elemento
     * @param localName nombre local del elemento
     * @param qName nombre cualificado del elemento
     * @param attributes atributos asociados al elemento
     * @throws SAXException 
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("Libros")) {
            javax.swing.JOptionPane.showMessageDialog(
                null,
                "Se van a mostrar los libros de este documento",
                "Informacion",
            javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
        } 
        else if (qName.equals("Libro")) {
            totalBooks++;
            fileContent += "LIBRO " + totalBooks + ": \n";

            String publicado = attributes.getValue(0); 
            if (publicado != null) {
                fileContent += "Publicado en: " + publicado + "\n";
            }
        } 
        else if (qName.equals("Titulo")) {
            currentTag = "Titulo";
            fileContent += "El titulo es: ";
        } 
        else if (qName.equals("Autor")) {
            currentTag = "Autor";
            fileContent += "El autor es: ";
        }
    }
    
    /**
     * Accede al contenido de texto del elemento 
     * @param ch array de caracteres con el contenido
     * @param start indice del primer caracter
     * @param length numero de caracteres a leer
     * @throws SAXException 
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String texto = new String(ch, start, length).trim();

        if ((!currentTag.isEmpty()) && (!texto.isEmpty())) {
            fileContent += texto + "\n";
        }   
    }
    
    /**
     * Cierra el elemento, añade un separador de cada libro, 
     * y al cerrar el elemento raiz muestra el total
     * @param uri espacio de nombres del elemento
     * @param localName nombre local del elemento
     * @param qName nombre cualificado del elemento
     * @throws SAXException 
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("Libro")) {
            fileContent += "---------------------------\n";
        } 
        else if (qName.equals("Libros")) {
            fileContent += "\nTotal de libros: " + totalBooks + ".\n";
        }

        currentTag = "";
    }
}