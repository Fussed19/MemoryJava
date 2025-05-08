package HashSet;

import memory.Carta;
import Iterator.*;

import java.util.HashSet;
import java.util.Set;

public class HashSetCartaTest2 {

    public static void main(String[] args) {
        // Crear un conjunto HashSetBaraja
        HashSetBaraja set = new HashSetBaraja();
        
        // Añadir cartas aleatorias y verificar duplicados
        System.out.println("Probando duplicados y aleatoriedad:");

        Carta cartaA = new Carta('A');
        
        set.add(new Carta('a'));
        set.add(new Carta('a'));
        set.add(new Carta('a'));
        set.add(new Carta('A'));
        set.add(cartaA);

        // Mostrar el contenido del conjunto
        System.out.println("\nContenido del conjunto (debe mostrar cartas únicas):");
        Iterator<Carta> iterador = set.iterator();
        while (iterador.hasNext()) {
            System.out.println(iterador.next().getNombre());
        }
    }
}

