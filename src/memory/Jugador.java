
package memory;

import memory.interfaz.JugadorInterfaz;

/**
 *
 * @author fussed
 */
public class Jugador implements JugadorInterfaz{
    
    private String nombre;
    private int cartasGanadas;
    
    //Constructor
    
    public Jugador(){
        nombre = "Jugador";
        cartasGanadas = 0;    
    }
     
    public Jugador(String nombre){
        this.nombre = nombre;
        cartasGanadas = 0;
    }
    
    //Getters
    
    @Override
    public String getNombre(){
        return nombre;
    }
    @Override
    public int getCartasGanadas(){
        return cartasGanadas;
    }
    
    //Setter
    @Override
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    @Override
    public void setCartasGanadas(int cartasGanadas){
        this.cartasGanadas = cartasGanadas;
    }
    
    //Otros
    
    //Metodo para sumar al contador uno si encuentra pareja.
    //Ahorra usar un get y un set. Actualizando directamente el número.
    public void parejaEncontrada(){
        cartasGanadas++;
    }
    
}
