/**
 *INTERFAZ DE ITERADOR PARA LA PRACTICA
 * 
 * @author Diego Palencia
 */

package Iterator;

public interface Iterator<TipoGenerico>{
    
    /**
     * Verificar si hay siguiente elemento
     *
     * 
     * @return True si hay mas elementos en la lista/conjunto
     */
    boolean hasNext();
    
    /**
     * Coger el siguiente elemento
     *
     * 
     * @return Devuelve el elemento que sigue en la lista/conjunto.
     */
    TipoGenerico next();
    
    
}
