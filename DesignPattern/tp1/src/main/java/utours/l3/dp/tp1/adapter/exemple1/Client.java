package utours.l3.dp.tp1.adapter.exemple1;

public final class Client {

    private Client() { }

    public static void main(String[] args) {
        InterfaceCible cible = new AdaptateurParDelegation(new ServiceExistant());
        System.out.println(cible.operation("bonjour"));
    }
}
