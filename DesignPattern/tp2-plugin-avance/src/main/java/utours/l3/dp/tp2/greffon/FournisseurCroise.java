package utours.l3.dp.tp2.greffon;

import utours.l3.dp.tp2.plugin.FournisseurDeSolveur;
import utours.l3.dp.tp2.resolution.Solveur;

public class FournisseurCroise implements FournisseurDeSolveur {

    @Override
    public String identifiant() {
        return "croise";
    }

    @Override
    public String libelle() {
        return "Résoudre par retour arrière guidé";
    }

    @Override
    public Solveur creer() {
        return new SolveurCroise();
    }
}
