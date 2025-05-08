package HashSet;

import memory.Carta;
import Iterator.*;

public class HashSetCartaTest {

    public static void main(String[] args) {
        // Creamos el HashSetCarta
        HashSetCarta set = new HashSetCarta();

        // Creamos algunas cartas
        Carta a = new Carta('A');
        Carta b = new Carta('B');
        Carta c = new Carta('C');
        Carta d = new Carta('D');
        Carta e = new Carta('E');
        Carta a2 = a.getPareja(); // A' que es la pareja de A

        // Test 1: Añadir un elemento
        System.out.println("Añadir carta A: " + test(set.add(a), true));
        System.out.println("Añadir carta B: " + test(set.add(b), true));
        System.out.println("Añadir carta A (duplicado): " + test(set.add(a), false));
        System.out.println("Añadir carta C: " + test(set.add(c), true));

        // Test 2: Contiene
        System.out.println("Contiene A: " + test(set.contains(a), true));
        System.out.println("Contiene A': " + test(set.contains(a2), false));
        System.out.println("Contiene B: " + test(set.contains(b), true));
        System.out.println("Contiene C: " + test(set.contains(c), true));
        System.out.println("Contiene D: " + test(set.contains(new Carta('D')), false));

        // Test 3: Eliminar
        System.out.println("Eliminar A: " + test(set.remove(a), true));
        System.out.println("Contiene A tras eliminar: " + test(set.contains(a), false));

        // Test 4: Iterador tras eliminar
        System.out.println("Contenido actual (debe mostrar B y C):");
        Iterator<Carta> iterador = set.iterator();
        while (iterador.hasNext()) {
            System.out.println("  " + iterador.next().getNombre());
        }

        // Test 5: rehash (forzar al llenar muchos elementos)
        System.out.println("\nForzando rehash:");
        for (char ch = 'D'; ch <= 'Z'; ch++) {
            set.add(new Carta(ch));
        }
        System.out.println("Tamaño tras añadir muchas cartas (debería ser >= 24): " + test(set.size() >= 24, true));

        // Test 6: addAll
        HashSetCarta otro = new HashSetCarta();
        Carta n1 = new Carta('1');
        Carta n2 = new Carta('2');
        Carta n3 = new Carta('3');
        otro.add(n1);
        otro.add(n2);
        otro.add(n3);

        set.addAll(otro);
        System.out.println("\nProbando addAll con cartas 1, 2 y 3:");
        System.out.println("Contiene 1: " + test(set.contains(n1), true));
        System.out.println("Contiene 2: " + test(set.contains(n2), true));
        System.out.println("Contiene 3: " + test(set.contains(n3), true));

        // Test 7: Clear
        set.clear();
        System.out.println("\nTras clear()");
        System.out.println("Está vacío: " + test(set.isEmpty(), true));
        System.out.println("Tamaño tras clear: " + test(set.size(), 0));

        // Final: iterador vacío
        System.out.println("Iterador tras clear:");
        iterador = set.iterator();
        System.out.println(iterador.hasNext() ? "❌ Error: el conjunto debería estar vacío" : "✅ OK: iterador vacío");
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
