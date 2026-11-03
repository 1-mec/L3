package utours.l3.dp.tp1.strategy.exercice1;

/**
 * Un ouvrage du catalogue.
 *
 * <p>État initial de l'exercice : cette classe détient les données ET sait
 * les afficher de deux manières. C'est ce mélange qu'il s'agit de défaire.</p>
 */
public class Livre {

    private final String titre;
    private final String auteur;
    private final String editeur;
    private final int annee;
    private final String resume;
    private final String isbn;

    public Livre(String titre, String auteur, String editeur, int annee,
                 String resume, String isbn) {
        this.titre = titre;
        this.auteur = auteur;
        this.editeur = editeur;
        this.annee = annee;
        this.resume = resume;
        this.isbn = isbn;
    }

    public String getTitre()   { return titre; }
    public String getAuteur()  { return auteur; }
    public String getEditeur() { return editeur; }
    public int    getAnnee()   { return annee; }
    public String getResume()  { return resume; }
    public String getIsbn()    { return isbn; }

    /** Affichage résumé : une ligne. */
    public void afficherSimple() {
        System.out.println(titre + " (" + editeur + "), " + annee);
        System.out.println("----------------------------------------");
    }

    /** Affichage détaillé : un bloc de plusieurs lignes. */
    public void afficherDetail() {
        System.out.println("Titre     : " + titre);
        System.out.println("Auteur(s) : " + auteur);
        System.out.println("Annee     : " + annee);
        System.out.println("Editeur   : " + editeur);
        System.out.println("ISBN      : " + isbn);
        System.out.println("Resume    : " + resume);
        System.out.println("----------------------------------------");
    }
}
