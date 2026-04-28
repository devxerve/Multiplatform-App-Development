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
    private String path = null;
    
    public DOMHandler(File file){
        this.file = file;
        
    };
    
    /**
     * Loads the doc pointer to the XML FILE (must have been initialized)
     * @return code regarding the creation of the pointer (0 states sucessful)
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
     * Process the nodes of the DOM tree 
     * @return output - String with the content of the DOM
     */
    public String processDOM(){
        String output = "";
        Node root = doc.getFirstChild();
        NodeList nodes = root.getChildNodes();
        
        for(int i = 0; i<nodes.getLength();i++){
            Node node = nodes.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){  
                String[] datum = processNodes(node);
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

    public int modifyInDOM(String search, String newTitle, String newAuthor, String newYear) {
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
                        
                        for (int k = 0; k < children.getLength(); k++) {
                            Node field = children.item(k);
                            if ("Titulo".equals(field.getNodeName()) && newTitle != null && !newTitle.isBlank()) {
                                field.getFirstChild().setNodeValue(newTitle);
                            } else if ("Autor".equals(field.getNodeName()) && newAuthor != null && !newAuthor.isBlank()) {
                                field.getFirstChild().setNodeValue(newAuthor);
                            }
                        }
                        
                        if (newYear != null && !newYear.isBlank()) {
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