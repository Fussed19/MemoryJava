
/**
 *
 * @author Diego P, Celia P, Josema
 */
import memory.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.*;


public class Main {
    
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        boolean exit = false;
        
        do{
            System.out.println("\n\n***************************************************"
                             +     "\n--------------------MEMORY GAME--------------------"
                             +     "\n***************************************************");            
            int columnas;
            int filas;
            int vidas;
            String nombreJugador;
            
            //ESTO ASEGURA QUE EL NOMBRE SEA DE UNA PALABRA, Y SOLO CON CARACTERES NORMALES
            do {
                System.out.print("\nNOMBRE JUGADOR: ");
                nombreJugador = scanner.next();
                if(nombreJugador.isEmpty() || nombreJugador.contains(" ") || !nombreJugador.matches("[a-zA-Z]+")){
                    System.out.println("\n Nombre de jugador no válido, solo se admite una palabra y caracteres de la A a la Z");
                }
            } while(nombreJugador.isEmpty() || nombreJugador.contains(" ") || !nombreJugador.matches("[a-zA-Z]+"));
            
            Jugador jugador = new Jugador(nombreJugador);    
                
            do{ 
                System.out.print("\nINTRODUCE EL NUMERO DE FILAS:    ");
                columnas = scanner.nextInt();

                System.out.print("\nINTRODUCE EL NUMERO DE COLUMNAS: ");
                filas = scanner.nextInt();

                

                if((columnas*filas)%2 != 0){
                    System.out.println("\nUno de los dos números debe ser par.");
                } else if((columnas*filas) > 72){
                    System.out.println("\nTablero demasiado grande, prueba uno mas pequeño");
                } 
            } while ((columnas*filas)%2 != 0 || ((columnas*filas) > 72));
            
            do{
                System.out.print("\nINTRODUCE EL NUMERO DE VIDAS:    ");
                vidas = scanner.nextInt();
                if(vidas <= 0 || vidas >= 21){
                    System.out.println("\nNúmero de vidas inválido. Tiene que estar entre 1 y 20");
                } 
            } while(vidas <= 0 || vidas > 20);    

            Tablero tablero = new Tablero(columnas, filas);
            tablero.setVidas(vidas);
            Baraja baraja = new Baraja();

            baraja.inicializarBaraja();
            tablero.inicializarTablero(baraja);

            int[] select1 = new int[2];
            int[] select2 = new int[2];

            for(int i= 0;i<2;i++){
                select1[i] = -1;
                select2[i] = -1;  
            }

            int cursorX = 0, cursorY = 0;

            boolean game = true; 
            while (game && tablero.getVidas() > 0) {
                
                for (int i = 0; i < 50; ++i) System.out.println();           
                tablero.mostrarTableroRevelado(cursorY,cursorX, select1, select2, jugador);

                System.out.print("\nPulse INTRO después de cada selección de tecla ");
                System.out.print("\nWASD para moverse entre celdas, SPACE para seleccionar, E para salir: ");

                if(tablero.todoRevelado()){
                    game=false;
                } 


                if(select1[0] != -1 && select2[0] != -1){
                    try{
                        Thread.sleep(1000);

                        for(int i= 0;i<2;i++){
                            select1[i] = -1;
                            select2[i] = -1;  
                        }  

                    } catch (InterruptedException e) {
                        System.out.println("Error");
                    }
                } else {

                    String input = scanner.nextLine();
                    if (input.isEmpty()) {continue;}
                    char tecla = input.charAt(0);

                    switch (tecla) {
                        case 'w': 
                            cursorY = Math.max(0, cursorY - 1); 
                            break;
                        case 's': 
                            cursorY = Math.min(filas - 1, cursorY + 1); 
                            break;
                        case 'a': 
                            cursorX = Math.max(0, cursorX - 1); 
                            break;
                        case 'd': 
                            cursorX = Math.min(columnas - 1, cursorX + 1); 
                            break;
                        case ' ': 
                            if(select1[0] == -1 && select1[1] == -1
                                && !tablero.isRevelada(cursorY, cursorX)){

                                select1[0] = cursorY;
                                select1[1] = cursorX;
                            } else if (select2[0] == -1 && select2[1] == -1 && !(cursorY == select1[0] && cursorX == select1[1]) 
                                       && !tablero.isRevelada(cursorY, cursorX)){
                                select2[0] = cursorY;
                                select2[1] = cursorX;  
                            }
                            break;
                        case 'e': 
                            exit = false;
                            break;                        
                    }
                }            
            }
            System.out.println("\n\n-----------------FIN DEL JUEGO---------------------");
            
            if(tablero.todoRevelado()){
                    System.out.println("\n***************************************************"
                                     +     "\n------------------¡¡¡HAS GANADO!!!-----------------"
                                     +     "\n***************************************************");
            }        
            if(tablero.getVidas() <= 0){
                System.out.println(    "\n***************************************************"
                                     +     "\n-----------------¡NO QUEDAN VIDAS!-----------------"
                                     +     "\n***************************************************");
            }
 
            guardarJugador(jugador);
            topJugadores();
            
            String answer;
            do{
                System.out.println("\n\n---------------VOLVER A JUGAR??? S/N--------------------");
                answer = scanner.nextLine().trim().toLowerCase();
            
                if(answer.equals("n")){
                    System.out.println("\n-----------------GRACIAS POR JUGAR----------------------");
                    exit = true;
                }
            } while (!answer.equals("s") && !answer.equals("n"));
            
            
        } while(!exit);    
    }
    
    //Metodo para guardar jugadores en una base de datos
    public static void guardarJugador(Jugador jugador){
        
            List<Jugador> jugadores =  new ArrayList<>();
            File archivo = new File("jugadores.dat");
            if(archivo.exists()){
                try{
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo));
                    jugadores = (List<Jugador>) in.readObject();
                    
                } catch (IOException | ClassNotFoundException e){
                    System.out.println("Error al cargar jugadores anteriores");
                }
            }
            
            jugadores.add(jugador);
            
            try{
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo));
                out.writeObject(jugadores);
            } catch (IOException e){
                System.out.println("Error al guardar jugadores anteriores");
            }
          
    }
    
    //Metodo que lee info de jugadores e imprime un top
    public static void topJugadores(){
        
        Jugador top1 = null;
        Jugador top2 = null; 
        Jugador top3 = null;
        
        File archivo = new File("jugadores.dat");
        
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo));
       
            List<Jugador> jugadores = (List<Jugador>) in.readObject();
                
            jugadores.sort((j1, j2) -> Integer.compare(j2.getCartasGanadas(), j1.getCartasGanadas())); 
            
            top1 = jugadores.get(0);
            if(jugadores.size()>=2) top2 = jugadores.get(1);
            if(jugadores.size()>=3) top3 = jugadores.get(2);
            
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Error al leer jugadores");
        }
        
        System.out.println("\n\n***************************************************"
                              +"\n-------------------TOP JUGADORES-------------------"
                              +"\n***************************************************");
        
        System.out.print("\n\n1. ");
        if(top1 != null){
            System.out.print(top1.getNombre() + "   -   " + top1.getCartasGanadas());
        }
        System.out.print("\n2. ");
        if(top2 != null){
            System.out.print(top2.getNombre()+ "   -   " + top2.getCartasGanadas());
        }
        System.out.print("\n3. ");
        if(top3 != null){
            System.out.print(top3.getNombre()+ "   -   " + top3.getCartasGanadas());
        }
        
    }
    
}


