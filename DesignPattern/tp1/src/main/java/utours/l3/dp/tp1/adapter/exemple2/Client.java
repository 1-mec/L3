package utours.l3.dp.tp1.adapter.exemple2;

public final class Client {

    private Client() { }

    public static void main(String[] args) {
        InterfaceCible cible = new AdaptateurParHeritage();
        System.out.println(cible.operation("bonjour"));
    }
}
