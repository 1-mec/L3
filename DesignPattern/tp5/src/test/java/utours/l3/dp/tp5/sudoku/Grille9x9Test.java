package utours.l3.dp.tp5.sudoku;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests d'invariant du domaine. Ils ne parlent d'aucun pattern : ils décrivent
 * ce que la grille doit faire, et rien de ce que vous ferez ne doit les casser.
 */
@DisplayName("Grille 9x9 — invariants du domaine")
class Grille9x9Test {

    @Test
    @DisplayName("une case renseignée se relit à l'identique")
    void relectureIdentique() {
        Grille9x9 grille = new Grille9x9();
        grille.set(3, 7, 5);
        assertEquals(5, grille.get(3, 7));
    }

    @Test
    @DisplayName("une grille neuve est entièrement vide")
    void grilleNeuveVide() {
        Grille9x9 grille = new Grille9x9();
        assertFalse(grille.estComplete());
        assertEquals(Grille9x9.VIDE, grille.get(1, 1));
    }

    @Test
    @DisplayName("une grille entièrement remplie est complète")
    void grilleRemplie() {
        Grille9x9 grille = new Grille9x9();
        for (int c = 1; c <= 9; c++) {
            for (int l = 1; l <= 9; l++) {
                grille.set(c, l, 1);
            }
        }
        assertTrue(grille.estComplete());
    }

    @Test
    @DisplayName("les coordonnées hors grille sont refusées")
    void coordonneesRefusees() {
        Grille9x9 grille = new Grille9x9();
        assertThrows(IllegalArgumentException.class, () -> grille.set(0, 1, 1));
        assertThrows(IllegalArgumentException.class, () -> grille.set(10, 1, 1));
        assertThrows(IllegalArgumentException.class, () -> grille.get(1, 0));
    }

    @Test
    @DisplayName("toString produit 9 lignes de 9 chiffres")
    void representationTextuelle() {
        String[] lignes = new Grille9x9().toString().split("\\R");
        assertEquals(9, lignes.length);
        for (String ligne : lignes) {
            assertEquals(9, ligne.length());
        }
    }
}
