package utours.l3.dp.tp5.decorator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Le composant concret : il écrit et relit les octets tels quels.
 *
 * <p>C'est le bout de la chaîne. Un décorateur ne sait pas le construire et
 * ne le remplace pas : il l'enveloppe.</p>
 */
public class ArchiveFichier implements Archive {

    @Override
    public void ecrire(byte[] contenu, Path fichier) throws IOException {
        Files.write(fichier, contenu);
    }

    @Override
    public byte[] lire(Path fichier) throws IOException {
        return Files.readAllBytes(fichier);
    }
}
