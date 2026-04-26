/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evento;

/**
 * Este es el DataModel (o modelo de datos) de un visitante
 * Aqui se establece la logica para construir cada objeto visitante
 * @author xerve
 */
public class Visitante {
    private String nombre = null;
    private String ala = null;
    private String provincia = null;
    private String horaLlegada = null;
    private int horaSexagesimal = 0;
    private boolean esProfesor = false;
    
    public Visitante(String nombre, String provincia, String horaLlegada,boolean esProfesor){
        setNombre(nombre);
        
        setProvincia(provincia);
        
        setHoraLlegada(horaLlegada);
        
        if(esProfesor){
           setAla("norte");
           this.esProfesor = esProfesor;
        }
        
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAla() {
        return ala;
    }

    public void setAla(String ala) {
        this.ala = ala;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
    String[] partes = horaLlegada.split(":");
     int horas = Integer.parseInt(partes[0]);
     int minutos = Integer.parseInt(partes[1]);

     if (horas < 0 || horas > 23 || minutos < 0 || minutos > 59) {
         throw new IllegalArgumentException("Hora inválida: " + horaLlegada);
     }

     this.horaSexagesimal = horas * 60 + minutos;
     this.horaLlegada = horaLlegada;
    }
}
