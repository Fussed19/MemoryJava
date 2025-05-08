/**
 *CLASE CARTA
 * 
 * @author Diego Palencia, Celia Puga, Jose Manuel Gomez
 */
package memory;

import memory.interfaz.CartaInterfaz;

public class Carta implements CartaInterfaz<Carta> {
    
    //Atributos
    
    private char nombre;
    private Carta pareja;
    private boolean revelada;
    
    
    //CONSTRUCTOR
    
    //constructor default
    public Carta(char nombre,Carta pareja){
        this.nombre = nombre;
        this.pareja = pareja;
        revelada = false;
    }
    //ESTE CONSTRUCTOR ES EL UTIL, CREA UNA CARTA Y SU PAREJA INSTANCIANDOSE MUTUAMENTE
    //Es decir --> A <----> A'
    public Carta(char nombre){
        
        this.nombre = nombre;
        pareja = new Carta(nombre, this);
        revelada = false;
    }
    
    
    //GETTERS
    @Override
    public char getNombre(){
        return nombre;
    }
    @Override
    public Carta getPareja(){
        return pareja;
    }
    @Override
    public boolean getRevelada(){
        return revelada;
    }
    
    //SETTER
    
    @Override
    public void setNombre(char nombre){
        //Con el if nos ahorramos un bucle infinito de cambios de nombre de una 
        //carta a otra de la pareja.
        if(this.nombre != nombre){
            this.nombre = nombre;
            //Cambiamos el nombre de la pareja
            pareja.setNombre(nombre);
        }            
    }
    
    @Override
    //ESTE METODO NO ES ESTRICTAMENTE NECESARIO CON ESTA IMPLEMENTACION
    public void setPareja(Carta pareja){
        this.pareja = pareja;
    }
    
    @Override
    public void setRevelada(boolean revelada){
        //Igual que con el método anterior
        if(this.revelada != revelada){
            this.revelada = revelada;
            //Cambiamos el nombre de la pareja
            pareja.setRevelada(revelada);
        }  
    }
    
    //OTROS
    
    //ESTOS METODOS SOBREESCRIBEN JAVA.OBJECT(objetos de java)
    //Es importante porque si no java no tiene forma de saber si dos objetos son iguales en las cartas
    //Aunque tengan todos los atributos iguales
    
    @Override
    public int hashCode() {
        return Character.hashCode(nombre);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carta other = (Carta) obj;
        return this.nombre == other.nombre;
    }
}


