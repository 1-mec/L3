package utours.l3.dp.tp5.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Un colis contient des articles… et éventuellement d'autres colis.
 *
 * <p>État initial : le contenu est une liste d'{@code Object}, faute d'un type
 * commun aux deux sortes de contenus. C'est le symptôme à traiter.</p>
 */
public class Colis {

    private final String nom;
    private final long tareEnGrammes;
    private final List<Object> contenu = new ArrayList<>();

    public Colis(String nom, long tareEnGrammes) {
        this.nom = nom;
        this.tareEnGrammes = tareEnGrammes;
    }

    public String getNom() {
        return nom;
    }

    public long getTareEnGrammes() {
        return tareEnGrammes;
    }

    public void ajouter(Object element) {
        contenu.add(element);
    }

    public List<Object> getContenu() {
        return contenu;
    }
}
