/**
 *CLASE BARAJA
 * 
 * @author Diego Palencia, Celia Puga, Jose Manuel Gomez
 */

package memory;

import HashSet.HashSetBaraja;
import Iterator.Iterator;

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
    //Metodo que coge una carta random y la elimina (O(n)) similar a un array
    public Carta getCarta(){
        
        Carta carta = baraja.randomElem();
        
        baraja.remove(carta);
        
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
