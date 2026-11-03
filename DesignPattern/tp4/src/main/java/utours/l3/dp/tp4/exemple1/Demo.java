package utours.l3.dp.tp4.exemple1;

/** Deux observateurs sur le même livre, abonnés puis résiliés. */
public final class Demo {

    private Demo() { }

    public static void main(String[] args) {
        LivreObservable livre = new LivreObservable("Sans titre");

        Observateur trace = sujet ->
                System.out.println("[trace] titre -> " + ((Livre) sujet).getTitre());
        Observateur majuscules = sujet ->
                System.out.println("[maj]   " + ((Livre) sujet).getTitre().toUpperCase());

        livre.attacher(trace);
        livre.attacher(majuscules);
        livre.setTitre("Design Patterns");

        livre.detacher(majuscules);
        livre.setTitre("Refactoring");
    }
}
