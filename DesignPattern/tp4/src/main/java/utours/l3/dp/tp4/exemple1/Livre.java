package utours.l3.dp.tp4.exemple1;

/**
 * Objet métier ordinaire. Il ne sait rien de l'observation, et c'est voulu :
 * on veut pouvoir le réutiliser tel quel.
 */
public class Livre {

    private String titre;

    public Livre(String titre) {
        this.titre = titre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
