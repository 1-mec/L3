package utours.l3.dp.tp2.plugin;

import utours.l3.dp.tp2.resolution.Solveur;

/**
 * Contrat qu'un greffon doit remplir pour fournir un solveur à l'application.
 *
 * <p>C'est une <em>fabrique paramétrée par le catalogue</em> : le greffon dit
 * comment il s'appelle, sous quel identifiant on le demande, et sait produire
 * son solveur. L'application peut donc construire son menu à partir de ce que
 * les greffons présents lui déclarent, sans connaître aucun d'eux.</p>
 *
 * <p>Ce contrat — et lui seul — est partagé entre l'application et les
 * greffons. Ni l'un ni l'autre ne connaît le code de son partenaire.</p>
 */
public interface FournisseurDeSolveur {

    /** Identifiant stable, utilisé en configuration. Ex. : {@code "dancing-links"}. */
    String identifiant();

    /** Libellé destiné à l'utilisateur. Ex. : {@code "Résoudre avec Dancing Links"}. */
    String libelle();

    /** @return une nouvelle instance du solveur fourni */
    Solveur creer();
}
