/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jaxbHandler;

import java.io.File;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.JAXBContext;
import jaxb.Libro;

/**
 *
 * @author LABORATORIOS
 */
public class Handler {
    Libro book;
   public int openXML(File fichero){
    try{
        JAXBContext context = JAXBContext.newInstance(Libro.class);
        Unmarshaller u = context.createUnmarshaller();
        book = (Libro) u.unmarshal(fichero);
        return 0;
    }catch(Exception ex){
        return -1;
    }
   }
   
    
}
}
