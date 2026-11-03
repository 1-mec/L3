package utours.l3.dp.tp5.decorator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import utours.l3.dp.tp5.outils.Refactoring;
import utours.l3.dp.tp5.sudoku.Grille9x9;

@DisplayName("Partie A — archivage décoré")
class ArchivageTest {

    private static final String P = "utours.l3.dp.tp5.decorator.";

    /** Un contenu volumineux et répétitif : il se compresse très bien. */
    private static final byte[] CONTENU_REPETITIF =
            "530070000600195000098000060\n".repeat(200).getBytes(StandardCharsets.UTF_8);

    private static Grille9x9 grilleTemoin() {
        Grille9x9 g = new Grille9x9();
        for (int c = 1; c <= 9; c++) {
            for (int l = 1; l <= 9; l++) {
                g.set(c, l, (c + l) % 9 + 1);
            }
        }
        return g;
    }

    @Nested
    @DisplayName("Invariants — doivent passer AVANT et APRÈS")
    class Invariants {

        @Test
        @DisplayName("une grille encodée puis décodée est identique à l'originale")
        void allerRetourFormat() {
            Grille9x9 origine = grilleTemoin();
            assertEquals(origine.toString(),
                         FormatTexte.decoder(FormatTexte.encoder(origine)).toString());
        }

        @Test
        @DisplayName("l'archive fichier relit exactement ce qu'elle a écrit")
        void allerRetourFichier(@TempDir Path dossier) throws IOException {
            Archive archive = new ArchiveFichier();
            Path f = dossier.resolve("brut.dat");
            archive.ecrire(CONTENU_REPETITIF, f);
            assertTrue(java.util.Arrays.equals(CONTENU_REPETITIF, archive.lire(f)));
        }
    }

    @Nested
    @DisplayName("Objectifs — ignorés tant que les décorateurs n'existent pas")
    class Objectifs {

        private Optional<Class<?>> classe(String nom) {
            return Refactoring.classe(P + nom);
        }

        private Archive envelopper(String nom, Archive suivant) {
            return (Archive) Refactoring.instancier(classe(nom).orElseThrow(), suivant);
        }

        private boolean pretes(String... noms) {
            for (String n : noms) {
                if (classe(n).isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        @Test
        @DisplayName("le décorateur abstrait est un Archive ET contient un Archive")
        void doubleRelation() throws Exception {
            assumeTrue(pretes("DecorateurArchive"), "DecorateurArchive absent : test ignoré");
            Class<?> d = classe("DecorateurArchive").orElseThrow();

            assertTrue(Archive.class.isAssignableFrom(d),
                    "le décorateur doit ÊTRE un Archive");
            assertTrue(Modifier.isAbstract(d.getModifiers()),
                    "DecorateurArchive n'a pas vocation à être instancié seul");
            boolean contient = java.util.Arrays.stream(d.getDeclaredFields())
                    .anyMatch(f -> f.getType() == Archive.class);
            assertTrue(contient,
                    "le décorateur doit CONTENIR un Archive — et non un ArchiveFichier : "
                    + "c'est la déclaration au type abstrait qui rend le chaînage possible");
        }

        @Test
        @DisplayName("le journal n'altère pas le contenu")
        void journalTransparent(@TempDir Path dossier) throws Exception {
            assumeTrue(pretes("DecorateurJournal"), "DecorateurJournal absent : test ignoré");
            Archive a = envelopper("DecorateurJournal", new ArchiveFichier());
            Path f = dossier.resolve("j.dat");
            a.ecrire(CONTENU_REPETITIF, f);
            assertTrue(java.util.Arrays.equals(CONTENU_REPETITIF, a.lire(f)),
                    "un décorateur qui n'ajoute qu'une trace ne doit rien changer aux données");
        }

        @Test
        @DisplayName("chaque pile de décorateurs relit ce qu'elle a écrit")
        void symetrieDeLaPile(@TempDir Path dossier) throws Exception {
            assumeTrue(pretes("DecorateurCompression", "DecorateurChiffrement", "DecorateurJournal"),
                    "décorateurs incomplets : test ignoré");

            Archive[] piles = {
                envelopper("DecorateurCompression", new ArchiveFichier()),
                envelopper("DecorateurChiffrement", new ArchiveFichier()),
                envelopper("DecorateurCompression",
                        envelopper("DecorateurChiffrement", new ArchiveFichier())),
                envelopper("DecorateurJournal",
                        envelopper("DecorateurCompression",
                                envelopper("DecorateurChiffrement", new ArchiveFichier()))),
            };
            for (int i = 0; i < piles.length; i++) {
                Path f = dossier.resolve("pile" + i + ".dat");
                piles[i].ecrire(CONTENU_REPETITIF, f);
                assertTrue(java.util.Arrays.equals(CONTENU_REPETITIF, piles[i].lire(f)),
                        "la pile n°" + i + " ne relit pas ce qu'elle a écrit : "
                        + "chaque décorateur doit défaire à la lecture ce qu'il fait à l'écriture");
            }
        }

        @Test
        @DisplayName("l'ordre des décorateurs change le résultat : compresser AVANT de chiffrer")
        void lOrdreCompte(@TempDir Path dossier) throws Exception {
            assumeTrue(pretes("DecorateurCompression", "DecorateurChiffrement"),
                    "décorateurs incomplets : test ignoré");

            // Le décorateur le plus extérieur agit le premier à l'écriture.
            Archive bonOrdre = envelopper("DecorateurCompression",
                    envelopper("DecorateurChiffrement", new ArchiveFichier()));
            Archive mauvaisOrdre = envelopper("DecorateurChiffrement",
                    envelopper("DecorateurCompression", new ArchiveFichier()));

            Path bon = dossier.resolve("bon.dat");
            Path mauvais = dossier.resolve("mauvais.dat");
            bonOrdre.ecrire(CONTENU_REPETITIF, bon);
            mauvaisOrdre.ecrire(CONTENU_REPETITIF, mauvais);

            long tailleBonne = Files.size(bon);
            long tailleMauvaise = Files.size(mauvais);

            assertTrue(tailleBonne * 4 < tailleMauvaise,
                    "compresser puis chiffrer doit produire un fichier bien plus petit "
                    + "(" + tailleBonne + " octets) que chiffrer puis compresser ("
                    + tailleMauvaise + " octets) : des données chiffrées ne se "
                    + "compressent pas. Si les deux tailles se valent, votre chiffrement "
                    + "laisse passer la structure des données.");
            assertTrue(tailleBonne < CONTENU_REPETITIF.length / 2,
                    "la compression doit effectivement réduire la taille");
        }
    }
}
