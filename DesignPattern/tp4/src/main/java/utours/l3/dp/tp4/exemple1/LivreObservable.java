package utours.l3.dp.tp4.exemple1;

import java.util.ArrayList;
import java.util.List;

/**
 * La greffe : un {@link Livre} adapté pour être observé.
 *
 * <p>Adaptation <strong>par héritage</strong>, et c'est ici le bon choix : il
 * faut <em>intercepter</em> un comportement existant tout en le conservant, ce
 * que {@code super.setTitre(t)} exprime en une ligne. Une adaptation par
 * délégation obligerait à réécrire tous les accesseurs.</p>
 *
 * <p>Le code de {@link Livre} n'est pas touché : il reste utilisable seul,
 * sans traîner le mécanisme d'observation derrière lui.</p>
 */
public class LivreObservable extends Livre implements Sujet {

    private final List<Observateur> observateurs = new ArrayList<>();

    public LivreObservable(String titre) {
        super(titre);
    }

    @Override
    public List<Observateur> observateurs() {
        return observateurs;
    }

    @Override
    public void setTitre(String titre) {
        super.setTitre(titre);   // 1. le comportement d'origine : le stockage
        notifier();              // 2. le comportement ajouté : le signalement
    }
}
