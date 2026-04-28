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

    public String processJAXB() {
        if (libros == null) return "";

        StringBuilder sb = new StringBuilder();

        List<Libro> lista = libros.getLibro();
        for (Libro l : lista) {
            sb.append("Título: ").append(l.getTitulo()).append("\n");
            sb.append("Autor: ").append(l.getAutor()).append("\n");
            sb.append("Año: ").append(l.getPublicadoEn()).append("\n");
            sb.append("ISBN: ").append(l.getIsbn()).append("\n");
            sb.append("-------------------------\n");
        }

        return sb.toString();
    }

    public Libro searchByIsbn(String isbn) {
        if (libros == null) return null;

        for (Libro l : libros.getLibro()) {
            if (l.getIsbn() != null && l.getIsbn().equals(isbn)) {
                return l;
            }
        }
        return null;
    }

    public int modifyBookByIsbn(String isbn, String newTitle, String newAuthor, String newYear) {
        Libro libro = searchByIsbn(isbn);

        if (libro == null) return -1;

        try {
            if (newTitle != null && !newTitle.isBlank()) {
                libro.setTitulo(newTitle);
            }
            if (newAuthor != null && !newAuthor.isBlank()) {
                libro.setAutor(newAuthor);
            }
            if (newYear != null && !newYear.isBlank()) {
                libro.setPublicadoEn(java.math.BigInteger.valueOf(Long.parseLong(newYear)));
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
    }

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