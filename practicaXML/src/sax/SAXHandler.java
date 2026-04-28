package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {

    public String file_content = "";
    private int totalLibros = 0;
    private String etiquetaActual = "";

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
            totalLibros++;
            file_content += "LIBRO " + totalLibros + ": \n";

            String publicado = attributes.getValue(0); 
            if (publicado != null) {
                file_content += "Publicado en: " + publicado + "\n";
            }
        } 
        else if (qName.equals("Titulo")) {
            etiquetaActual = "Titulo";
            file_content += "El titulo es: ";
        } 
        else if (qName.equals("Autor")) {
            etiquetaActual = "Autor";
            file_content += "El autor es: ";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String texto = new String(ch, start, length).trim();

        if (!etiquetaActual.isEmpty() && !texto.isEmpty()) {
            file_content += texto + "\n";
        }   
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("Libro")) {
            file_content += "---------------------------\n";
        } 
        else if (qName.equals("Libros")) {
            file_content += "\nTotal de libros: " + totalLibros + ".\n";
        }

        etiquetaActual = "";
    }
}