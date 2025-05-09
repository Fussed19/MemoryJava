/**
 *CLASE BARAJA
 * 
 * @author Diego Palencia, Celia Puga, Jose Manuel Gomez
 */

package memory;

import HashSet.HashSetBaraja;
import Iterator.Iterator;
import java.util.Random;

/**
 *
 * @author fussed
 */
public class Baraja {
    
    private HashSetBaraja baraja ;
    
    //CONSTRUCTOR
    public Baraja(){
        baraja = new HashSetBaraja();
    }
    
    //GETTER Y SETTER
    
    public void setBaraja(HashSetBaraja nuevaBaraja){
        this.baraja = nuevaBaraja;
    }
    
    public HashSetBaraja getBaraja(){
        return this.baraja;
    }
    
    //OTROS
    
    public void inicializarBaraja(){
        //AÑADIMOS A LA BARAJA ELEMENTOS DE LA A A LA Z Y DEL 0 AL 9
        for(char c = 'A'; c <= 'Z'; c++){
            baraja.add(new Carta(c));
        }
        
        for(char c = '0';c  <= '9'; c++){
            baraja.add(new Carta(c));
        }
        
    }
    //Metodo que coge una carta random y la elimina (O(m))
    public Carta getCarta(){
        
        Carta carta = baraja.randomElem();
        
        baraja.remove(carta);
        
        return carta;
    }
    
    //Este metodo coge una carta entre el conjunto SOLO de la A a la Z y 0 al 9
    //Acambio su coste es O(1)
    public Carta getCartaOpt(){
       
        Random random = new Random();
        
        //Esta es la lista de caracteres posibles cuando se inicia la baraja, por lo tanto
        //este metodo solo sirve si la baraja no se ha modificado
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        char randomCartaNombre = caracteres.charAt(random.nextInt(caracteres.length()));
        
        Carta carta = baraja.getElem(randomCartaNombre); 
        
        return carta;
    }
    
    //Metodo para añadir una carta
    public void setCarta(Carta carta){
        baraja.add(carta);
    }
    
    //Limpia la baraja
    public void clearBaraja(){
        baraja.clear();
    }
    
    //METODO DE PRUEBA
    public void imprimirBaraja() {
        System.out.println("Cartas en la baraja:");
        Iterator<Carta> iterador = baraja.iterator();
        while (iterador.hasNext()) {
            System.out.println(iterador.next().getNombre());
        }
    }
    
}
