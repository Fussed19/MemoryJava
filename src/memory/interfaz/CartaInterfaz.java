/*
INTERFAZ PARA CARTAS

@author Diego Palencia, Celia Puga, Jose Manuel Gomez
*/
package memory.interfaz;


public interface CartaInterfaz<TipoGenerico>{
    
    /*
    DEFINICION DE METODOS DE ACCESO A DATOS
    */
    
    /**
     * Acceso a la variable de nombre de la carta
     *
     * @return Devuelve el nombre.
     */
    public char getNombre();
    
    /**
     * Acceso a la pareja (otra carta identica en nombre)
     *
     * @return Devuelve un identificador de carta.
     */
    public TipoGenerico getPareja();
    
    /**
     * Acceso a la variable de revelado
     *
     * @return Devuelve true si las cartas han sido reveladas en el juego.
     */
    public boolean getRevelada();
    
    
    /*
    DEFINICION DE METODOS DE MODIFICACION DE DATOS
    */
    
    /**
     * Modificacion del nombre de la carta
     * @param elem se introduce el nuevo nombre
     */
    public void setNombre(char elem);
    
    /**
     * Modificacion de pareja (otra carta identica en nombre)
     * @param elem se introduce cuál va a ser la carta enparejada, 
     * ya sea con un identificador o una propia carta..
     */
    public void setPareja(TipoGenerico elem);
    
    /**
     * Modificacion de revelado
     * @param estado simboliza si esta hacia arriba en el juego o no.
     */
    public void setRevelada(boolean estado);
    
    /*
    DEFINICION DE OTROS METODOS
    */
    
    
}
