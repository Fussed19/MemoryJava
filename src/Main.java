
/**
 *
 * @author Diego P, Celia P, Josema
 */
import memory.*;
import java.util.Scanner;


public class Main {
    
    static int cursorX = 0, cursorY = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        int columnas;
        int filas;
        int vidas;
        
        boolean exit = false;
        do{
            do{ 

                System.out.print("\nINTRODUCE EL NUMERO DE FILAS: \t");
                columnas = scanner.nextInt();

                System.out.print("\nINTRODUCE EL NUMERO DE COLUMNAS: ");
                filas = scanner.nextInt();

                System.out.print("\nINTRODUCE EL NUMERO DE VIDAS: ");
                vidas = scanner.nextInt();

                if((columnas*filas)%2 != 0){
                    System.out.println("\nUno de los dos números debe ser par.");
                } else if((columnas*filas) > 72){
                    System.out.println("\nTablero demasiado grande, prueba uno mas pequeño");
                } else if(vidas <= 0 || vidas >= 101){
                    System.out.println("\nNúmero de vidas inválido. Tiene que estar entre 1 y 100");
                }  
            } while ((columnas*filas)%2 != 0 || ((columnas*filas) > 72));

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


            boolean game = true; 
            while (game && tablero.getVidas() > 0) {

                for (int i = 0; i < 50; ++i) System.out.println();           
                tablero.mostrarTableroRevelado(cursorY,cursorX, select1, select2);

                System.out.print("\n Pulse INTRO después de cada selección de tecla ");
                System.out.println("WASD para moverse entre celdas, SPACE para seleccionar, E para salir: ");

                if(tablero.todoRevelado()){
                    System.out.println("\n\n***************************************************"
                                     +     "------------------¡¡¡HAS GANADO!!!-----------------"
                                     +     "***************************************************");

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
                            if(select1[0] == -1 && select1[1] == -1){

                                select1[0] = cursorY;
                                select1[1] = cursorX;
                            } else if (select2[0] == -1 && select2[1] == -1 && !(cursorY == select1[0] && cursorX == select1[1])){
                                select2[0] = cursorY;
                                select2[1] = cursorX;  
                            }
                            break;
                        case 'e': //Tecla ESC
                            exit = true;
                            break;                        
                    }
                }            
            }

            if(tablero.getVidas() <= 0){
                System.out.println(    "\n\n***************************************************"
                                     +     "-----------------¡NO QUEDAN VIDAS!-----------------"
                                     +     "***************************************************");
            }
            System.out.println("\n\n-------------------FIN DEL JUEGO------------------------");
            
            String answer;
            do{
                System.out.println("\n\n---------------VOLVER A JUGAR??? S/N--------------------");
                answer = scanner.nextLine().trim().toLowerCase();
            
                if(answer.equals("n")){
                    exit = true;
                }
            } while (!answer.equals("s") && !answer.equals("n"));
            
            
        } while(!exit);    
    }
    
}
