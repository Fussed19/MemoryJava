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
    
    //Vidas
    int vidas = 5;
    
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
    public int getVidas(){
        return this.vidas;
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
    public void setVidas(int vidas){
        this.vidas = vidas;
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
    
    public void mostrarTableroRevelado(int x, int y, int[] select1, int[] select2){
        
        System.out.println("VIDAS: " + "*".repeat(vidas));
        
        for(int i = 0; i<filas;i++){
            for(int j = 0; j<columnas;j++){
                if(tablero[i][j].getRevelada() == true || i == select1[0] && j == select1[1] ||  
                   i == select2[0] && j == select2[1] ){

                    if(i == x && j == y){
                        System.out.print("> [" + tablero[i][j].getNombre() + "]  "); 
                    } else {
                        System.out.print("  [" + tablero[i][j].getNombre() + "]  ");
                    }    
                } else {
                    if(i == x && j == y){
                        System.out.print("> [ ]  "); 
                    } else {
                        System.out.print("  [ ]  ");
                    }                  
                }
            }
            System.out.println();
        }
        
        //Comprobacion si al seleccionar dos son pareja
        if(inRange(select2)){
            if(!parejaRevelada(select1, select2)){
                vidas--;
            }
        }
        
        
    }
    
    //Ver si son pareja
    public boolean parejaRevelada(int[] select1, int[] select2){
        if( inRange(select1) && inRange(select2) &&
            tablero[select1[0]][select1[1]].equals(tablero[select2[0]][select2[1]])){
            
            tablero[select1[0]][select1[1]].setRevelada(true);
            return true;
        }     
        return false;
    }
    
    //Comprobacion de si el puntero esta fuera del rango
    private boolean inRange(int[] s){
        return s[0] >= 0 && s[1] >= 0 && s[0] < filas && s[1] < columnas;
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
