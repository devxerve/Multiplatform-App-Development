package jaxbHandler;

import java.io.File;
import java.io.StringWriter;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import jaxb.Libros;
import jaxb.Libros.Libro;

public class JAXBHandler {

    private Libros libros;
    private File file;

    public JAXBHandler(File file) {
        this.file = file;
    }
    /**
     * Carga el XML y lo des-serializa
     * @return codigo referente a la finalizacion del metodo (0 ok -1 fallo)
     */
    public int loadXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(Libros.class);
            Unmarshaller um = context.createUnmarshaller();
            libros = (Libros) um.unmarshal(file);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    /**
     * Procesa los libros y extrae su contenido a un String
     * @return cadena vacia si no hay libros, output con los datos si los hay
     */
    public String processJAXB() {
        if (libros == null) return "";

        String output = "";

        List<Libro> lista = libros.getLibro();
        for (Libro l : lista) {
            output += (l.getTitulo()+"\n");
            output += ("Autor: " + l.getAutor()+ "\n");
            output += ("Año: " + l.getPublicadoEn()+ "\n");
            output += ("ISBN:" + l.getIsbn()+ "\n");
            output += ("-------------------------\n");
        }

        return output;
    }
    /**
     * Busca por isbn
     * @param search codigo isbn para la busqueda
     * @return null si no hay ningun libro, libro si existe uno correspondiente
     */
    public Libro searchByIsbn(String search) {
        if (libros == null) return null;

        for (Libro l : libros.getLibro()) {
            if ((l.getIsbn() != null) && (l.getIsbn().equals(search))) {
                return l;
            }
        }
        return null;
    }
    
    /**
     * Modifica el libro mediante el search, asignandole valores nuevos al libro
     * @param search codigo isbn para buscar el libro
     * @param newTitle titulo nuevo para el libro
     * @param newAuthor nuevo autor
     * @param newYear nueva fecha de publicacion
     * @return codigo referente a la finalizacion del metodo (0 ok -2 libro 
     * no existe, -1 fallo)
     */
    public int modifyinJAX(String search, String newTitle, String newAuthor, String newYear) {
        Libro libro = searchByIsbn(search);

        if (libro == null) return -2;

        try {
            if ((newTitle != null) && (!newTitle.isBlank())) {
                libro.setTitulo(newTitle);
            }
            if ((newAuthor != null) && (!newAuthor.isBlank())) {
                libro.setAutor(newAuthor);
            }
            if ((newYear != null) && (!newYear.isBlank())) {
                libro.setPublicadoEn(java.math.BigInteger.valueOf(Long.parseLong(newYear)));
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    /**
     * Guarda el archivo XML 
     * @param outputFile archivo seleccionado en el JFileChooser en Window.java
     * @return codigo referente a la finalizacion del metodo (0 ok -1 fallo)
     */
    public int saveJAXBtoXML(File outputFile) {
        try {
            JAXBContext context = JAXBContext.newInstance(Libros.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(libros, outputFile);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}