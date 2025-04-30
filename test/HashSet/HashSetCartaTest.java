package HashSet;

import memory.Carta;
import java.util.Iterator;

public class HashSetCartaTest {

    public static void main(String[] args) {
        // Creamos el HashSetCarta
        HashSetCarta set = new HashSetCarta();

        // Creamos algunas cartas
        Carta a = new Carta('A');
        Carta b = new Carta('B');
        Carta c = new Carta('C');
        Carta a2 = a.getPareja(); // A' que es la pareja de A

        // Test 1: Añadir un elemento
        System.out.println("Añadir carta A: " + test(set.add(a), true));  // Debería ser true
        System.out.println("Añadir carta B: " + test(set.add(b), true));  // Debería ser true
        System.out.println("Añadir carta A (debería ser duplicado): " + test(set.add(a), false));  // Debería ser false (duplicado)
        System.out.println("Añadir carta C: " + test(set.add(c), true));  // Debería ser true

        // Test 2: Verificar si contiene elementos
        System.out.println("Contiene A: " + test(set.contains(a), true));  // Debería ser true
        System.out.println("Contiene A': " + test(set.contains(a2), false));  // Debería ser false
        System.out.println("Contiene B: " + test(set.contains(b), true));  // Debería ser true
        System.out.println("Contiene C: " + test(set.contains(c), true));  // Debería ser true
        System.out.println("Contiene D (no existe): " + test(set.contains(new Carta('D')), false));  // Debería ser false

        // Test 3: Eliminar elementos
        System.out.println("Eliminar carta A: " + test(set.remove(a), true));  // Debería ser true
        System.out.println("Eliminar carta A después de eliminar: " + test(set.contains(a), false));  // Debería ser false

        // Test 4: Verificar que el iterador recorra correctamente
        Iterator<Carta> iterador = set.iterator();
        System.out.println("Iterador siguiente elemento: ");
        while (iterador.hasNext()) {
            Carta carta = iterador.next();
            System.out.println("  " + carta.getNombre());  // Debería imprimir B y C, ya que A fue eliminada
        }

        // Test 5: Añadir más elementos y comprobar tamaño
        Carta d = new Carta('D');
        System.out.println("Añadir carta D: " + test(set.add(d), true));  // Debería ser true
        System.out.println("Contiene D: " + test(set.contains(d), true));  // Debería ser true

        // Verificación final del iterador después de añadir D
        iterador = set.iterator();
        System.out.println("Iterador después de añadir D:");
        while (iterador.hasNext()) {
            Carta carta = iterador.next();
            System.out.println("  " + carta.getNombre());  // Debería imprimir B, C y D
        }
    }

    // Método auxiliar para comparar valores esperados y reales
    private static String test(boolean actual, boolean esperado) {
        return actual == esperado ? "✅ OK" : "❌ Error (esperado " + esperado + ", obtenido " + actual + ")";
    }
}
