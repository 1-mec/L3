package utours.l3.dp.tp1.adapter.exemple2;

/**
 * Adaptateur par héritage : il EST un service, et se conforme à la cible.
 *
 * <p>Possible en Java uniquement parce que la cible est une interface : on
 * n'hérite que d'une seule classe. Le service adapté doit être dérivable, et
 * l'adaptateur est lié à un seul type, fixé à la compilation.</p>
 */
public class AdaptateurParHeritage extends ServiceExistant implements InterfaceCible {

    @Override
    public String operation(String donnee) {
        // prefixe() est protégé : ce code ne serait pas écrivable avec une
        // adaptation par délégation.
        return prefixe() + traiter(donnee);
    }
}
