package utours.l3.dp.tp5.composite;

/** Un objet indivisible, avec un nom et une masse en grammes. */
public class Article {

    private final String nom;
    private final long masseEnGrammes;

    public Article(String nom, long masseEnGrammes) {
        this.nom = nom;
        this.masseEnGrammes = masseEnGrammes;
    }

    public String getNom() {
        return nom;
    }

    public long getMasseEnGrammes() {
        return masseEnGrammes;
    }
}
