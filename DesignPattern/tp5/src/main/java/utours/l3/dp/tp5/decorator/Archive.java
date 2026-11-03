package utours.l3.dp.tp5.decorator;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Service d'archivage : écrire un contenu quelque part, et le relire.
 *
 * <p>C'est le « composant » du pattern Decorator. Deux propriétés en font une
 * bonne cible de décoration :</p>
 * <ul>
 *   <li>l'interface est <strong>étroite</strong> — deux opérations —, donc un
 *       décorateur est court à écrire ;</li>
 *   <li>les deux opérations sont <strong>symétriques</strong> : tout ce qu'un
 *       décorateur fait à l'écriture, il doit le défaire à la lecture. Cette
 *       symétrie est le contrat, et c'est elle que les tests vérifient.</li>
 * </ul>
 */
public interface Archive {

    /** Écrit le contenu dans le fichier indiqué. */
    void ecrire(byte[] contenu, Path fichier) throws IOException;

    /** Relit le contenu du fichier indiqué. */
    byte[] lire(Path fichier) throws IOException;
}
