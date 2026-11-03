package utours.l3.dp.tp1.adapter.exemple1;

/**
 * Adaptateur par délégation : il IMPLÉMENTE la cible et CONTIENT le service.
 *
 * <p>C'est la forme la plus courante, et celle qu'il faut préférer : elle
 * n'impose rien au service adapté, elle en accepte plusieurs, et le service
 * adapté peut être remplacé à l'exécution.</p>
 */
public class AdaptateurParDelegation implements InterfaceCible {

    private final ServiceExistant service;

    public AdaptateurParDelegation(ServiceExistant service) {
        this.service = service;
    }

    @Override
    public String operation(String donnee) {
        // Tout le travail de l'adaptateur est ici : traduire l'appel, et
        // éventuellement convertir les données dans les deux sens.
        return service.traiter(donnee);
    }
}
