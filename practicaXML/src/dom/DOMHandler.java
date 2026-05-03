/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dom;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class DOMHandler {
    private static Document doc = null;
    private File file = null;
    
    public DOMHandler(File file){
        this.file = file;
        
    };
    
    /**
     * Devuelve el puntero doc correspondiente al XML
     * @return Codigo referente a la finalizacion del metodo (-1 error 0 ok)
     */
    public int loadXML(){
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(file);
        }catch(Exception e){
            return -1;
        }
        return 0;
    }
    /**
     * Crea y procesa los nodos del arbol dom
     * @return output - String con el contenido de los nodos en formato texto
     */
    public String processDOM(){
        String output = "";
        Node root = doc.getFirstChild();
        NodeList nodes = root.getChildNodes();
        
        for(int i = 0; i<nodes.getLength();i++){
            Node node = nodes.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){  
                String[] datum = processNodes(node); // llama al metodo processNodes
                output += datum[1];
                output += "\nEl isbn es: " + datum[3];
                output += "\nPublicado en: " + datum[0];
                output += "\nEl autor es: " + datum[2];
                output += "\n--------------\n";
            }
        }
        System.out.println(output);
        return output;
    }
    /**
     * Procesa el nodo que se le pasa por parametro
     * Consigue el contenido de sus nodos hijo y de sus atributos
     * @param n nodo elemento (Libro)
     * @return data String[] de 4 posiciones (Fecha, titulo, autor e ISBN)
     */
    private String[] processNodes(Node n){
        String [] data = new String[4];
        Node ntemp = null;
        int counter = 1;

        NodeList nodes = n.getChildNodes();
        
        for(int i = 0; i<nodes.getLength();i++){
            ntemp = nodes.item(i);
            if(ntemp.getNodeType() == Node.ELEMENT_NODE){
                data[0] = n.getAttributes().item(0).getNodeValue();
                data[counter] = ntemp.getFirstChild().getNodeValue();
                counter++;
            }
        }
        return data;
    }
    /**
     * Añade un libro al arbol DOM (NO LO AÑADE AL FICHERO SOLO CARGA EN MEMORIA
     * EL NUEVO DOM)
     * @param title titulo del nuevo libro  
     * @param author autor del nuevo libro
     * @param year año de publicacion del nuevo libro (ira al atributo publicado_en)
     * @param isbn codigo identificacion isbn del nuevo libro
     * @return codigo referente a la finalizacion del metodo (0 ok -1 fallo)
     */
    public int addToDOM(String title, String author, String year,String isbn){
        try{
            Node ntitle = doc.createElement("Titulo");
            Node ntitleText = doc.createTextNode(title);
            ntitle.appendChild(ntitleText);
            
            Node nauthor = doc.createElement("Autor");
            Node nauthorText = doc.createTextNode(author);
            nauthor.appendChild(nauthorText);
            
            Node nbook = doc.createElement("Libro");
            ((Element)nbook).setAttribute("publicado_en",year);
            
            Node nisbn = doc.createElement("isbn");
            Node nisbnText = doc.createTextNode(isbn); 
            nisbn.appendChild(nisbnText);
            
            
            nbook.appendChild(ntitle);
            nbook.appendChild(nauthor);
            nbook.appendChild(nisbn);
            
            Node root = doc.getFirstChild();
            
            root.appendChild(nbook);
            return 0;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
    }
    
    /**
     * Guarda el DOM en un ficheor XML, si el xml existe pregunta por la sobreescritura
     * Usa un JFileChooser para seleccionar donde guardar el fichero
     * El JFileChooser tiene marcado como preferencia que guardes el archivo como XML
     * @return codigo referente a la finalizacion del metodo (0 ok -1 fallo) 
     */
    public int saveDOMtoXML() {
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Guardar XML");
    chooser.setSelectedFile(new File("libros.xml"));
    chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XML files", "xml"));

    int userSelection = chooser.showSaveDialog(null);

    if (userSelection != JFileChooser.APPROVE_OPTION) {
        return -1;
    }

    File outputFile = chooser.getSelectedFile();

    if (outputFile.exists()) {
        int confirm = javax.swing.JOptionPane.showConfirmDialog(
            null,
            "El fichero ya existe. ¿Sobreescribir?",
            "Confirmar",
            javax.swing.JOptionPane.YES_NO_OPTION
        );
        if (confirm != javax.swing.JOptionPane.YES_OPTION) {
            return -1;
        }
    }

    try {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(outputFile);
        transformer.transform(source, result);
        
        return 0;
    } catch (TransformerException ex) {
        Logger.getLogger(DOMHandler.class.getName()).log(Level.SEVERE, null, ex);
        return -1;
    }
}
    /**
     * Modifica el libro que se corresponda con el campo ISBN de la interfaz
     * Primero busca los nodos elementos y sus hijos (LIBROS y atributos)
     * Despues itera sobre los hijos, y si el isbn coincide con el del nodo 
     * hijo isbn entonces modifica el libro con los valores correspondientes
     * @param search codigo isbn para identificar el libro
     * @param newTitle nuevo titulo
     * @param newAuthor nuevo autor
     * @param newYear nuevo año de publicacion
     * @return codigo referente a la finalizacion del metodo (0 ok -1 fallo)
     */
    public int modifyInDOM(String search, String newTitle, String newAuthor, String newYear) {
    Node root = doc.getFirstChild();
    NodeList nodes = root.getChildNodes();

    for (int i = 0; i < nodes.getLength(); i++) {
        Node node = nodes.item(i);
        if (node.getNodeType() == Node.ELEMENT_NODE){
            
            NodeList children = node.getChildNodes();
            
            for (int j = 0; j < children.getLength(); j++) {
                Node child = children.item(j);
                
                if ("isbn".equals(child.getNodeName())) {
                    
                    if (child.getFirstChild().getNodeValue().equals(search)) {
                        
                        for (int k = 0; k < children.getLength(); k++) {
                            Node field = children.item(k);
                            if (("Titulo".equals(field.getNodeName())) && ((newTitle != null && !newTitle.isBlank()))) {
                                field.getFirstChild().setNodeValue(newTitle);
                            } else if (("Autor".equals(field.getNodeName())) && (newAuthor != null) && (!newAuthor.isBlank())) {
                                field.getFirstChild().setNodeValue(newAuthor);
                            }
                        }
                        
                        if ((newYear != null) && (!newYear.isBlank())) {
                            ((Element) node).setAttribute("publicado_en", newYear);
                        }
                        return 0;
                    }
                }
            }
        }
    }
    return -1;
}
    /**
     * Verifica que un libro existe mediante una busqueda de su ISBN
     * @param search isbn a buscar
     * @return True si existe, False si no
     */
    public Boolean verifyExistence(String search){
        Node root = doc.getFirstChild();
        NodeList nodes = root.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE){
               NodeList children = node.getChildNodes();
                for (int j = 0; j < children.getLength(); j++) {
                    Node child = children.item(j);
                    if ("isbn".equals(child.getNodeName())) {
                        if (child.getFirstChild().getNodeValue().equals(search)) {
                            return true;
                        }
                    }
                }
            } 
        }
        
        return false;
    }
    
}