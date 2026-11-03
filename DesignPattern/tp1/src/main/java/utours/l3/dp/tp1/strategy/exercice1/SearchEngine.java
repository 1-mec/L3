package utours.l3.dp.tp1.strategy.exercice1;

/**
 * Moteur de recherche du catalogue.
 *
 * <p>Simulation : le résultat est constitué en dur, ce qui suffit à
 * l'exercice et évite d'avoir à monter une base de données.</p>
 */
public class SearchEngine {

    private final ListeLivres resultat = new ListeLivres();

    public SearchEngine() {
        resultat.addLivre(new Livre(
                "Design Patterns: Elements of Reusable Object-Oriented Software",
                "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides",
                "Addison-Wesley", 1994,
                "Capturing a wealth of experience about the design of "
                        + "object-oriented software, four top-notch designers present "
                        + "a catalog of simple and succinct solutions to commonly "
                        + "occurring design problems.",
                "978-0201633610"));
        resultat.addLivre(new Livre(
                "Design patterns. Catalogue des modeles de conception reutilisables",
                "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides",
                "Vuibert Informatique", 1999,
                "Traduction francaise de l'ouvrage de reference.",
                "978-2711786442"));
        resultat.addLivre(new Livre(
                "Refactoring: Improving the Design of Existing Code",
                "Martin Fowler",
                "Addison-Wesley Professional", 1999,
                "Catalogue de transformations de code a comportement constant.",
                "978-0201485677"));
        resultat.addLivre(new Livre(
                "Domain-Driven Design: Tackling Complexity in the Heart of Software",
                "Eric Evans",
                "Addison-Wesley", 2003,
                "Modeliser le domaine metier au centre de la conception.",
                "978-0321125217"));
    }

    public ListeLivres getResult() {
        return resultat;
    }
}
