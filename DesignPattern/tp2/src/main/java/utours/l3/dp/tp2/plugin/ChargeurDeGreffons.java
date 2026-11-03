package utours.l3.dp.tp2.plugin;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * Charge les greffons présents dans un répertoire, sans les connaître.
 *
 * <p>Deux mécanismes standard de Java sont combinés :</p>
 * <ol>
 *   <li>un {@link URLClassLoader} ouvert sur le répertoire des greffons :
 *       c'est lui qui rend possible le <em>late binding</em>, c'est-à-dire le
 *       fait de découvrir du code qui n'était pas là à la compilation ;</li>
 *   <li>{@link ServiceLoader}, qui lit dans chaque archive le fichier
 *       {@code META-INF/services/…FournisseurDeSolveur} et instancie les
 *       classes qui y sont déclarées.</li>
 * </ol>
 *
 * <p>Le chargeur du greffon a pour <strong>parent</strong> le chargeur de
 * l'application : le greffon voit donc l'interface {@code Solveur} de
 * l'application, et les deux parlent bien du même type. C'est la condition
 * pour que le transtypage fonctionne — et l'erreur la plus fréquente est
 * d'oublier ce parent.</p>
 *
 * <p>Remplacer un fichier {@code .jar} dans le répertoire suffit alors à
 * changer le comportement de l'application, sans la recompiler.</p>
 */
public final class ChargeurDeGreffons {

    private ChargeurDeGreffons() { }

    /**
     * @param repertoire dossier contenant les archives {@code .jar} des greffons
     * @return les fournisseurs déclarés par les greffons trouvés
     */
    public static List<FournisseurDeSolveur> charger(Path repertoire) throws IOException {
        if (!Files.isDirectory(repertoire)) {
            return List.of();
        }
        List<URL> archives = new ArrayList<>();
        try (var fichiers = Files.list(repertoire)) {
            for (Path jar : fichiers.filter(p -> p.toString().endsWith(".jar")).toList()) {
                archives.add(jar.toUri().toURL());
            }
        }
        // Le parent est le chargeur de CETTE classe : le greffon voit ainsi
        // les interfaces de l'application.
        ClassLoader chargeur = new URLClassLoader(
                archives.toArray(URL[]::new),
                ChargeurDeGreffons.class.getClassLoader());

        List<FournisseurDeSolveur> trouves = new ArrayList<>();
        for (FournisseurDeSolveur f : ServiceLoader.load(FournisseurDeSolveur.class, chargeur)) {
            trouves.add(f);
        }
        return trouves;
    }
}
