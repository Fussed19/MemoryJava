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


public class HashSetBaraja implements Set<Carta>{
    
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
    
    public HashSetBaraja(){
        this.buckets = new List[CAPACIDAD_INICIAL];
        
        for(int i = 0; i<CAPACIDAD_INICIAL;i++){
            buckets[i]= new LinkedList<>();
        }
        this.numeroElementos = 0;
    }
    
    /*
    *FUNCIONES HASH Y HASHCODE PARA SACAR CLAVES DE ACCESO
    */
    
    //hashCode se implementa en carta
    
    //hash nos saca el indice de acceso del elemento con su clave
    private int hash(char elem){
        return elem % buckets.length;
    }
    
    //rehash aumenta el numero de buckets al llenarse mucho la tabla
    private void rehash(int newCapacity){
        
        //Guardamos la antigua y actualizamos tamaño
        List<Carta> [] oldBuckets = buckets;
        buckets = new List[newCapacity];
        
        for(int i = 0; i<newCapacity;i++){
            this.buckets[i]= new LinkedList<>();
        }
        
        //Iniciamos un Iterador
        Iterator<Carta> iterador = new IteradorBaraja(oldBuckets, numeroElementos);
        
        //Ponemos el numero de elementos a 0 (ya que add va sumando elementos)
        this.numeroElementos = 0;
        
        //rellenamos con el iterador(igual que en addAll)
        while(iterador.hasNext()){
            Carta carta = iterador.next();
            this.add(carta);
        }
    }
    
    //Para hacer rehash, necesitamos crear un método que nos encuentre el próximo primo más cercano
    //Esto se debe a que es mejor que el hash tenga un numero primo de buckets para evitar colisiones
    //Metodo que busca el proximo primo de un numero
    private int nextPrime(int n){
        while(!isPrime(n)){
            n++;
        }
        
        return n;
    }
    
    //Este metodo complementa al de arriba para saber si el numero es primo o no
    //Quiero evitar usar un O(n) como es el caso de dividir todos los numeros anteriores al que compruebo
    //Utilizaremos un algoritmo que comprueba solo hasta la raiz cuadrada del elemento O(sqrt(n)) que es mas rapido con numero grandes
    private boolean isPrime(int n){
        
       if(n<=1) {return false;} //Caso base
       
       for(int i = 2; i<= (int)Math.sqrt(n);i++){
           if(n%i == 0) {return false;}
           break;
       }
        
       return true;
    }
    
    /*
    *IMPLEMENTACION DE LA INTERFAZ SET
    */
    
    @Override
    public boolean add(Carta elem) {
        if (elem == null) {
            return false;
        }
        
        int indice = hash(elem.getNombre());

        if((double)(numeroElementos/buckets.length) > FACTOR_CARGA){
            rehash(nextPrime(numeroElementos * 2));
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
        
        if (elem == null) {
            return false;
        }
        
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
        
        if (elem == null) {
            return false;
        }
        
        int indice = hash(elem.getNombre());
        //Comparamos si esta en el bucket        
        return buckets[indice].contains(elem);   
    }

    @Override
    public void addAll(Set<Carta> conjunto) {
        //Comprobacion de si necesitamos un rehash por si no hay capacidad
        if((numeroElementos + conjunto.size())/buckets.length > FACTOR_CARGA){
            rehash(nextPrime((numeroElementos + conjunto.size())*2));
        }
        
        //Iteramos en el nuevo conjunto y vamos añadiendo
        Iterator<Carta> iterador = conjunto.iterator();
        
        while (iterador.hasNext()) {
            Carta carta = iterador.next();
            this.add(carta); // Usa el método add() existente
        }   
    
    }

    @Override
    public void retainAll(Set<Carta> conjunto) {
        
        Iterator<Carta> iterador = this.iterator();
        
        //Simplemente iteramos como en los metodos anteriores, si el elemento
        //NO esta presente en ambos conjuntos, se elimina
        while(iterador.hasNext()){
            Carta carta = iterador.next();
            if(!conjunto.contains(carta)){
                this.remove(carta);
            }
        }
   
    }
    @Override
    public void removeAll(Set<Carta> conjunto) {
        
        //igual que en addAll pero eliminando
        Iterator<Carta> iterador = conjunto.iterator(); 
        
        while(iterador.hasNext()){
            Carta carta = iterador.next();
            if(this.contains(carta)){
                this.remove(carta);
            }
        }
    }

    @Override
    public boolean containsAll(Set<Carta> conjunto) {
         Iterator<Carta> iterador = conjunto.iterator(); 
         
        while(iterador.hasNext()){
            Carta carta = iterador.next();
            if(!this.contains(carta)){
                return false;                
            }
        }
        return true; 
    }

    @Override
    public int size() {
        return numeroElementos; 
    }

    @Override
    public boolean isEmpty() {
        return (numeroElementos == 0); 
    }

    @Override
    public void clear() {
        if(!isEmpty()){
            //Para borrar es como volver a llamar al constructor
            buckets = new List[CAPACIDAD_INICIAL];
        
            for(int i = 0; i<CAPACIDAD_INICIAL;i++){
                buckets[i]= new LinkedList<>();
            }
            numeroElementos = 0; 
        }
    }

    @Override
    public Carta randomElem() {
        //En vez de generar un indice random y poder caer en uno bucket vacio
        //Voy a generar un numero random de 0 a numeroElementos, y voy a iterar hasta llegar a ese elemento,
        //Asi la complejidad pasa a ser igual a la de una lista o un array O(n) en el peor caso al buscar un elemento.
        
        if(isEmpty()){
            throw new IllegalStateException("No hay nada en el conjunto para elegir");
        }
        //Creamos el iterador
        Iterator<Carta> iterador = this.iterator();
        //generamos un elemento random
        int randomElem = (int) (Math.random() * numeroElementos);
        
        for(int i = 0; i == randomElem; i++){
            iterador.next();
        }
       
        return iterador.next();
    }

    @Override
    public Iterator<Carta> iterator() {
        return new IteradorBaraja(this.buckets, this.numeroElementos); 
    }
 
}