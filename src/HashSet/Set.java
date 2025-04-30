/**
 *INTERFAZ PARA IMPLEMENTAR CONJUNTOS (SET)
 * 
 * @author Diego Palencia
 */

package HashSet;

import java.util.Iterator;

public interface Set<TipoGenerico> extends Iterable<TipoGenerico>{
    
    /**
     * Añadir elemento NO repetido en el conjunto
     *
     * @param elem a añadir
     * @return True si la operacion se ha realizado(no esta repetido el elemento)
     */
    boolean add (TipoGenerico elem);
    
    
    /**
     * Borrar elemento en el conjunto
     *
     * @param elem a borrar
     * @return True si se ha realizado la operacion
     */
    boolean remove(TipoGenerico elem);
    
    
    /**
     * Comprobacion de si el elemento esta en el conjunto
     *
     * @param elem a comprobar si esta
     * @return True si está.
     */
    boolean contains(TipoGenerico elem);
    
    
    /**
     * Iterador para saltar de elemento en elemento
     *
     * @return Devuelve un objeto del iterador
     */
    @Override
    Iterator iterator();
    
    
    /**
     * Añadir un conjunto (B) al que ya está (A), suma de conjuntos
     *
     * @param conjunto es el conjunto a sumar
     */
    void addAll(Set<TipoGenerico> conjunto);
    
    
    /**
     * Intersecciona A y B para hallar los elementos presentes en ambos conjuntos
     *
     * @param conjunto a intersecar
     */
    void retainAll(Set<TipoGenerico> conjunto);
    
    
    /**
     * Elimina los elementos de A presentes en B (A-B)
     *
     * @param conjunto a restar
     */
    void removeAll(Set<TipoGenerico> conjunto);
    
    
    /**
     * Comprueba si todos los elementos de B estan presentes en A.
     *
     * @param conjunto a comprobar
     * @return True si B pertenece a A
     */
    boolean containsAll(Set<TipoGenerico> conjunto);
    
    
    /**
     * Halla el número de elementos en un conjunto
     *
     * @return número de elementos en A
     */
    int size();
    
    
    /**
     * Comprobacion de si el conjunto es un conjunto vacio
     *
     * @return True si no hay elementos.
     */
    boolean isEmpty();
    
    
    /**
     * Borra todos los elementos del conjunto
     *
     */
    void clear();
    
    
    /**
     * Da y borra un elemento del conjunto dado de forma aleatoria
     *
     * @return devuelve un elemento aleatorio del conjunto.
     */
    TipoGenerico randomElem();
    
}
