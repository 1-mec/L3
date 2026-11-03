package utours.l3.dp.tp1.outils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

/**
 * Capture ce qu'un traitement écrit sur la sortie standard.
 *
 * <p>Cette classe n'est pas un exemple à suivre : c'est un pis-aller. Si l'on
 * doit détourner {@code System.out} pour tester un affichage, c'est que le
 * code écrit en dur dans la console — autrement dit qu'il n'a pas séparé le
 * <em>calcul</em> de la <em>présentation</em>. C'est précisément le défaut que
 * le TP corrige.</p>
 *
 * <p>Une fois le pattern Strategy en place, on peut écrire un afficheur qui
 * produit une chaîne, et le tester directement : plus besoin de cet outil.
 * Le fait qu'il devienne inutile est une mesure du progrès accompli.</p>
 */
public final class SortieConsole {

    private SortieConsole() { }

    /** Exécute le traitement et renvoie ce qu'il a écrit sur System.out. */
    public static String capturer(Runnable traitement) {
        PrintStream original = System.out;
        ByteArrayOutputStream tampon = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(tampon, true, StandardCharsets.UTF_8));
            traitement.run();
        } finally {
            System.setOut(original);
        }
        return tampon.toString(StandardCharsets.UTF_8);
    }

    /** Variante recevant le texte capturé. */
    public static void surCapture(Runnable traitement, Consumer<String> verification) {
        verification.accept(capturer(traitement));
    }
}
