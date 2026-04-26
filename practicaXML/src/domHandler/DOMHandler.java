/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domHandler;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        path = file.getAbsolutePath();
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
                output += "\n Publicado en :" + datum[0];
                output += "\n El titulo es :" + datum[1];
                output += "\n El autor es :" + datum[2];
                output += "\n --------------";
            }
        }
        System.out.println(output);
        return output;
    }
    
    private String[] processNodes(Node n){
        String [] data = new String[3];
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
    
    public int addToDOM(String title, String author, String year){
        try{
            Node ntitle = doc.createElement("Titulo");
            Node ntitleText = doc.createTextNode(title);
            ntitle.appendChild(ntitleText);
            
            Node nauthor = doc.createElement("Autor");
            Node nauthorText = doc.createTextNode(author);
            nauthor.appendChild(nauthorText);
            
            Node nbook = doc.createElement("Libro");
            ((Element)nbook).setAttribute("publicado_en",year);
            
            nbook.appendChild(ntitle);
            nbook.appendChild(nauthor);
            
            Node root = doc.getFirstChild();
            
            root.appendChild(nbook);
            return 0;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public int saveDOMtoXML(){    
        //TODO: Implement this with a JFileChooser
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);
        }catch (TransformerException ex) {
            Logger.getLogger(DOMHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
