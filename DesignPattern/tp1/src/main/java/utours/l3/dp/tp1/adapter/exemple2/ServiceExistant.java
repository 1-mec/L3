package utours.l3.dp.tp1.adapter.exemple2;

public class ServiceExistant {

    public String traiter(String texte) {
        return "[" + texte.toUpperCase() + "]";
    }

    /**
     * Membre protégé : inaccessible par délégation, accessible par héritage.
     * C'est le seul argument sérieux en faveur de l'adaptation par héritage.
     */
    protected String prefixe() {
        return ">> ";
    }
}
