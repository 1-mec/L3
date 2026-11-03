package utours.l3.dp.tp2.greffon;

import utours.l3.dp.tp2.plugin.FournisseurDeSolveur;
import utours.l3.dp.tp2.resolution.Solveur;

/**
 * Point d'entrée du greffon : c'est cette classe que {@code ServiceLoader}
 * instancie après l'avoir lue dans {@code META-INF/services}.
 *
 * <p>Elle doit offrir un constructeur public sans argument — le chargeur de
 * services n'en connaît pas d'autre.</p>
 */
public class FournisseurComptage implements FournisseurDeSolveur {

    @Override
    public String identifiant() {
        return "comptage";
    }

    @Override
    public String libelle() {
        return "Résoudre par comptage (sans retour arrière)";
    }

    @Override
    public Solveur creer() {
        return new SolveurComptage();
    }
}
