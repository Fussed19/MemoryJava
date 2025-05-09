/**
 *CLASE TABLERO
 * 
 * @author Diego Palencia, Celia Puga, Jose Manuel Gomez
 */
package memory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import memory.interfaz.TableroInterfaz;

public class Tablero implements TableroInterfaz{
    
    private int columnas;
    private int filas;
    private Carta[][] tablero;
    
    //CONTRUCTOR
    
    public Tablero(int columnas, int filas){
        this.columnas = columnas;
        this.filas= filas;
        
        tablero = new Carta[this.filas][this.columnas];
    }
    
    //GETTER Y SETTER
    
    public int getColumnas(){
        return this.columnas;
    }
    public int getFilas(){
        return this.filas;
    }
    public Carta[][] getTablero(){
        return this.tablero;
    }
    
    public void setTablero(Carta[][] nuevoTablero){
        this.tablero = nuevoTablero;
    }
    
    public void setColumnas(int columnas){
        this.columnas = columnas;
    }
    
    public void setFilas(int filas){
        this.filas = filas;
    }
    
    //OTROS
    
    //METODO PARA INICIAR EL TABLERO
    public void inicializarTablero(Baraja baraja){
        
        int numCartas = (filas*columnas)/2;
        
        //Creamos una lista para meter las cartas y sus parejas
        List<Carta> cartasAnadidas = new ArrayList<>();
        
        
        //DOS METODOS A USAR -- getCarta() (casos generales) -- getCartaOpt() optimizado para este caso
        for(int i = 0; i<numCartas;i++){
            Carta carta = baraja.getCartaOpt();
            cartasAnadidas.add(carta);
            cartasAnadidas.add(carta.getPareja());
        }
        //Barajamos
        Collections.shuffle(cartasAnadidas);
        
        //LLenamos el tablero con la lista barajada
        int x=0;
        
        for(int i = 0; i<filas;i++){
            for(int j = 0; j<columnas;j++){
                tablero[i][j] = cartasAnadidas.get(x);
                x++;
            }
        }

    }
    
    public void revelarCarta(char c){
        
        //O(n) en el array
        for(int i = 0; i<filas;i++){
            for(int j = 0; j<columnas;j++){
                if(tablero[i][j].getNombre() == c){
                    tablero[i][j].setRevelada(true);
                }
            }
        }
        
    }
    
    public void mostrarTableroRevelado(){
        
        for(int i = 0; i<filas;i++){
            for(int j = 0; j<columnas;j++){
                if(tablero[i][j].getRevelada() == true){
                    System.out.print("[" + tablero[i][j].getNombre() + "]");
                } else {
                    System.out.print("[ ]");
                }
            }
            System.out.println("");
        }
    }
    
    public boolean todoRevelado(){
        for(int i = 0; i<filas;i++){
            for(int j = 0; j<columnas;j++){
                if(tablero[i][j].getRevelada() == false){
                    return false;
                }
            }
        }
        return true;
    }   
}
