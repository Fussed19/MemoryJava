/*
INTERFAZ PARA CARTAS

@author Diego Palencia, Celia Puga, Jose Manuel Gomez
*/
package memory.interfaz;


public interface JugadorInterfaz{
    
    /*
    DEFINICION DE METODOS DE ACCESO A DATOS
    */
    
    /**
     * Acceso a la variable de nombre del jugador
     *
     * @return Devuelve el nombre.
     */
    public String getNombre();
    
    /**
     * Acceso a la variable de cartas ganadas
     *
     * @return Devuelve el numero de parejas encontradas en el juego.
     */
    public int getCartasGanadas();
    
    
    /*
    DEFINICION DE METODOS DE MODIFICACION DE DATOS
    */
    
    /**
     * Modificacion del nombre del jugador
     * @param elem se introduce el nuevo nombre
     */
    public void setNombre(String elem);
    
    /**
     * Modificacion del numero de cartas ganadas
     * @param num es el numero de cartas al que se le han dado la vuelta.
     */
    public void setCartasGanadas(int num);
    
    /*
    DEFINICION DE OTROS METODOS
    */
    
}
