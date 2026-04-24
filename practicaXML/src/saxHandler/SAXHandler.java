/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saxHandler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author LABORATORIOS
 */

public class SAXHandler extends DefaultHandler {
    public String file_content = "";
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        for(int i =start; i<length+start;i++){
            file_content += file_content + ch[i];
        }
        file_content = file_content.trim() + "\n";
    }
    

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("Libro")){
            file_content += "--------------------------- \n";
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
       if(qName.equals("Libro")){
           file_content += file_content + "Publicado en: " + 
                attributes.getValue(attributes.getQName(0).trim());
       }else if(qName.equals("Titulo")){
           file_content += file_content + "El titulo es : ".trim();
       }else if(qName.equals("Autor")){
           file_content += file_content + "El autor es : ".trim();
       }
    }
    
}
