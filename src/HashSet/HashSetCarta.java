/**
 *IMPLEMENTACION CONJUNTOS CON TABLAS DE HASH(SET)
 * 
 * **NOTA: Se utiliza un iterador propio en vez del de java.utils
 * @author Diego Palencia
 */

package HashSet;

//Paquetes propios
import Iterator.*;
import memory.Carta;
//Paquetes de java
import java.util.LinkedList;
import java.util.List;


public class HashSetCarta implements Set<Carta>{
    
    /*
    * Declaración de variables de la tabla de Hash
    */
    
    //Como el abecedario tiene 26 letras (sin ñ) + 10 números, tenemos 36 elementos
    //Como M>N y M debe ser primo, el numero primo más cercano es el 73 para evitar colisiones
    //La carga lambda = N/M queda de 0,49
    private static final int CAPACIDAD_INICIAL = 73;
    private static final double FACTOR_CARGA = 0.7; //Numero a partir del cual se aumenta el numero de buckets para evitar colisiones.
    
    private List<Carta>[] buckets; //Array con los buckets(mis cajones donde guardas cosas)
    
    private int numeroElementos; 
    
    /*
    *CONSTRUCTOR
    */
    
    public HashSetCarta(){
        this.buckets = new List[CAPACIDAD_INICIAL];
        
        for(int i = 0; i<CAPACIDAD_INICIAL;i++){
            buckets[i]= new LinkedList<>();
        }
        this.numeroElementos = 0;
    }
    
    /*
    *FUNCIONES HASH Y HASHCODE PARA SACAR CLAVES DE ACCESO
    */
    
    /*
        La funcion F(h1) o hashCode no es necesaria ya que trabajamos con char que representan numeros ASCII directamente
        private int hashCode(char elem){   
        }
    
    */
    
    //hash nos saca el indice de acceso del elemento con su clave
    private int hash(char elem){
        return elem % buckets.length;
    }
    
    //rehash aumenta el numero de buckets al llenarse mucho la tabla
    private void rehash(){
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
    /*
    *IMPLEMENTACION DE LA INTERFAZ SET
    */
    
    @Override
    public boolean add(Carta elem) {
        int indice = hash(elem.getNombre());
        
        if(numeroElementos/buckets.length > FACTOR_CARGA){
            rehash();
        }
        
        if( buckets[indice].contains(elem)){ 
           return false; //elemento repetido
        }
        
        buckets[indice].add(elem);
        numeroElementos++;
        return true; //elemento añadido con exito
    }

    @Override
    public boolean remove(Carta elem) {
        int indice = hash(elem.getNombre());
        
        if( buckets[indice].contains(elem)){ 
           buckets[indice].remove(elem);
           numeroElementos--;
           return true; //Elemento eliminado
        }
        
        return false; //No existe el elemento
    }

    @Override
    public boolean contains(Carta elem) {
        int indice = hash(elem.getNombre());
        
        return buckets[indice].contains(elem); //true si el elem esta en el cajon
    }

    @Override
    public void addAll(Set<Carta> conjunto) {
        
        Iterator<Carta> iterador = conjunto.iterator();
        
        while (iterador.hasNext()) {
            Carta carta = iterador.next();
            this.add(carta); // Usa el método add() existente
        }   
    
    }

    @Override
    public void retainAll(Set<Carta> conjunto) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    @Override
    public void removeAll(Set<Carta> conjunto) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean containsAll(Set<Carta> conjunto) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Carta randomElem() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Iterator<Carta> iterator() {
        return new IteradorBaraja(this.buckets, this.numeroElementos); 
    }
 
}

