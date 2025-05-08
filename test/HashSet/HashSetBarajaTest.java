package HashSet;

import memory.Carta;
import Iterator.*;
import java.util.Random;

public class HashSetBarajaTest {

    public static void main(String[] args) {
        // Creamos el HashSetBaraja
        HashSetBaraja set = new HashSetBaraja();
        Random random = new Random();

        // Creamos algunas cartas
        Carta a = new Carta('A');
        Carta b = new Carta('B');
        Carta c = new Carta('C');
        Carta d = new Carta('D');
        Carta e = new Carta('E');
        Carta a2 = a.getPareja(); // A' que es la pareja de A

        // Test 1: Añadir un elemento
        System.out.println("Test 1: Añadir elementos");
        System.out.println("Añadir carta A: " + test(set.add(a), true)); // True, debería añadirse
        System.out.println("Añadir carta B: " + test(set.add(b), true)); // True, debería añadirse
        System.out.println("Añadir carta A (duplicado): " + test(set.add(a), false)); // False, A ya existe
        System.out.println("Añadir carta C: " + test(set.add(c), true)); // True, debería añadirse

        // Test 2: Comprobar si contiene los elementos
        System.out.println("\nTest 2: Comprobar contiene");
        System.out.println("Contiene A: " + test(set.contains(a), true)); // True, A está
        System.out.println("Contiene A' (pareja de A): " + test(set.contains(a2), false)); // False, A' no está
        System.out.println("Contiene B: " + test(set.contains(b), true)); // True, B está
        System.out.println("Contiene C: " + test(set.contains(c), true)); // True, C está
        System.out.println("Contiene D: " + test(set.contains(new Carta('D')), false)); // False, D no está

        // Test 3: Eliminar un elemento
        System.out.println("\nTest 3: Eliminar un elemento");
        System.out.println("Eliminar A: " + test(set.remove(a), true)); // True, A debería eliminarse
        System.out.println("Contiene A tras eliminar: " + test(set.contains(a), false)); // False, A ya no está

        // Test 4: Iterador tras eliminar un elemento
        System.out.println("\nTest 4: Iterador tras eliminar A");
        System.out.println("Contenido actual (debe mostrar B y C):");
        Iterator<Carta> iterador = set.iterator();
        while (iterador.hasNext()) {
            System.out.println("  " + iterador.next().getNombre()); // Debería imprimir B y C
        }

        // Test 5: Rehashing (añadir muchos elementos para forzar un rehash)
        System.out.println("\nTest 5: Forzar Rehash:");
        for (char ch = 'D'; ch <= 'Z'; ch++) {
            set.add(new Carta(ch));
        }
        System.out.println("Tamaño tras añadir muchas cartas (debería ser >= 24): " + test(set.size() >= 24, true)); // True, debe haber más de 24 cartas

        // Test 6: Método addAll con otro conjunto
        System.out.println("\nTest 6: Método addAll");
        HashSetBaraja otro = new HashSetBaraja();
        Carta n1 = new Carta('1');
        Carta n2 = new Carta('2');
        Carta n3 = new Carta('3');
        otro.add(n1);
        otro.add(n2);
        otro.add(n3);

        set.addAll(otro);
        System.out.println("Contiene 1: " + test(set.contains(n1), true)); // True, 1 está
        System.out.println("Contiene 2: " + test(set.contains(n2), true)); // True, 2 está
        System.out.println("Contiene 3: " + test(set.contains(n3), true)); // True, 3 está

        // Test 7: retainAll (retener solo los elementos que están en ambos conjuntos)
        System.out.println("\nTest 7: retainAll");
        HashSetBaraja conjuntoRetener = new HashSetBaraja();
        conjuntoRetener.add(b);
        conjuntoRetener.add(c);

        set.retainAll(conjuntoRetener);
        System.out.println("Contiene A (debería ser false): " + test(set.contains(a), false)); // False, A ya no está
        System.out.println("Contiene B (debería ser true): " + test(set.contains(b), true)); // True, B está
        System.out.println("Contiene C (debería ser true): " + test(set.contains(c), true)); // True, C está
        System.out.println("Contiene D (debería ser false): " + test(set.contains(d), false)); // False, D no está

        // Test 8: removeAll (eliminar elementos de un conjunto)
        System.out.println("\nTest 8: removeAll");
        HashSetBaraja conjuntoEliminar = new HashSetBaraja();
        conjuntoEliminar.add(b);
        conjuntoEliminar.add(c);

        set.removeAll(conjuntoEliminar);
        System.out.println("Contiene B (debería ser false): " + test(set.contains(b), false)); // False, B debería eliminarse
        System.out.println("Contiene C (debería ser false): " + test(set.contains(c), false)); // False, C debería eliminarse

        // Test 9: Limpiar el conjunto
        System.out.println("\nTest 9: clear()");
        set.clear();
        System.out.println("Está vacío: " + test(set.isEmpty(), true)); // True, debe estar vacío
        System.out.println("Tamaño tras clear: " + test(set.size(), 0)); // 0, no debe haber elementos

        // Test 10: Iterador tras clear
        System.out.println("\nTest 10: Iterador tras clear");
        iterador = set.iterator();
        System.out.println(iterador.hasNext() ? "❌ Error: el conjunto debería estar vacío" : "✅ OK: iterador vacío");

        // Test 11: Prueba de aleatoriedad
        System.out.println("\nTest 11: Prueba de aleatoriedad");
        // Añadir cartas aleatorias
        for (int i = 0; i < 100; i++) {
            char randomChar = (char) ('A' + random.nextInt(26)); // Generar letra aleatoria entre A-Z
            Carta randomCarta = new Carta(randomChar);
            set.add(randomCarta);
            System.out.println("Añadir carta: " + randomCarta.getNombre());
        }

        // Verificar si un elemento aleatorio está en el conjunto
        char randomChar = (char) ('A' + random.nextInt(26)); // Generar nueva carta aleatoria
        Carta randomCarta = new Carta(randomChar);
        System.out.println("Contiene carta aleatoria " + randomCarta.getNombre() + ": " + test(set.contains(randomCarta), true));

        // Eliminar una carta aleatoria
        System.out.println("Eliminar carta aleatoria " + randomCarta.getNombre() + ": " + test(set.remove(randomCarta), true));

        // Verificar que la carta ha sido eliminada
        System.out.println("Contiene carta aleatoria " + randomCarta.getNombre() + " tras eliminación: " + test(set.contains(randomCarta), false));

        // Iterar sobre el conjunto aleatorio
        System.out.println("Contenido del conjunto tras pruebas aleatorias:");
        iterador = set.iterator();
        while (iterador.hasNext()) {
            System.out.println("  " + iterador.next().getNombre());
        }
    }

    // Método auxiliar para comparar valores esperados y reales (boolean)
    private static String test(boolean actual, boolean esperado) {
        return actual == esperado ? "✅ OK" : "❌ Error (esperado " + esperado + ", obtenido " + actual + ")";
    }

    // Método auxiliar para comparar enteros
    private static String test(int actual, int esperado) {
        return actual == esperado ? "✅ OK" : "❌ Error (esperado " + esperado + ", obtenido " + actual + ")";
    }
}
