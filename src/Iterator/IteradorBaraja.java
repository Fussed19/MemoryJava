/**
 *IMPLEMENTACION DEL ITERADOR USANDO LA INTERFAZ PROPIA ADAPTADA A LA BARAJA
 * 
 * @author Diego Palencia
 */
package Iterator;

import java.util.List;
import memory.Carta;

public class IteradorBaraja implements Iterator<Carta>{
        
        /*
        * CLASE DE ITERADOR
        */
        private int bucketActual = 0;
        private int elementoActual = 0;
        private int elementosRestantes;
        private final List<Carta>[] buckets;
        
        /*
        CONSTRUCTOR DEL METODO 
        */
        public IteradorBaraja(List<Carta>[] buckets, int numeroElementos){
            this.buckets = buckets;
            elementosRestantes = numeroElementos;
        }
        
        @Override
        public boolean hasNext(){
            return (elementosRestantes > 0);
        }
        
        @Override
        public Carta next(){
            
            //Comprobamos si hay siguiente
            if(!hasNext()){
                throw new IndexOutOfBoundsException("No hay mas elementos");
            }
            //Avanzamos a un bucket con elementos IMPORTANTE
            //ESTA LINEA SIEMPRE hay que llamarla antes de cojer el objeto
            //puesto que en la primera llamada si el primer bucket esta vacio
            //salta error
            nextBucket();
            
            //Cojemos el elemento
            Carta carta = buckets[bucketActual].get(elementoActual);
            
            //Actualizamos variables
            elementoActual++;
            elementosRestantes--;
            //Si no quedan mas elementos pasamos de bucket
            if (elementoActual >= buckets[bucketActual].size()) {
            bucketActual++;
            elementoActual = 0;
            }
            //Devolvemos la carta
            return carta;
        }
         
        //METODO PARA AVANZAR POR TODOS LOS BUCKETS VACIOS
        private void nextBucket(){
            while((bucketActual < buckets.length) && buckets[bucketActual].isEmpty()){
                bucketActual++;
                elementoActual=0;
            }
        }
}
