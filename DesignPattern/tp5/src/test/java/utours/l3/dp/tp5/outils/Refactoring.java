package utours.l3.dp.tp5.outils;

import java.lang.reflect.Constructor;
import java.util.Optional;

/**
 * Petite boîte à outils pour les tests dits « objectifs ».
 *
 * <p>Les tests fournis avec un TP sont de deux natures :</p>
 * <ul>
 *   <li>les tests <em>d'invariant</em>, qui passent avant ET après le
 *       refactoring : ils constituent le filet de sécurité, et prouvent que la
 *       transformation n'a pas changé le comportement observable ;</li>
 *   <li>les tests <em>objectifs</em>, qui portent sur des classes que vous
 *       n'avez pas encore écrites. Ils sont <em>ignorés</em> tant que ces
 *       classes n'existent pas, et doivent passer une fois le travail fait.</li>
 * </ul>
 *
 * <p>Cette classe permet aux seconds de se compiler alors que la classe visée
 * n'existe pas encore : on la désigne par son nom, pas par son type.</p>
 */
public final class Refactoring {

    private Refactoring() { }

    /** @return la classe portant ce nom complet, si elle existe déjà */
    public static Optional<Class<?>> classe(String nomComplet) {
        try {
            return Optional.of(Class.forName(nomComplet));
        } catch (ClassNotFoundException absente) {
            return Optional.empty();
        }
    }

    /** Instancie une classe par son constructeur sans argument. */
    public static Object instancier(Class<?> type) {
        try {
            Constructor<?> ctor = type.getDeclaredConstructor();
            ctor.setAccessible(true);
            return ctor.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "La classe " + type.getName()
                    + " doit offrir un constructeur sans argument.", e);
        }
    }

    /**
     * Instancie une classe en cherchant un constructeur à un argument dont le
     * type accepte la valeur donnée ; à défaut, le constructeur sans argument
     * suivi d'aucune configuration.
     */
    public static Object instancier(Class<?> type, Object argument) {
        for (Constructor<?> ctor : type.getDeclaredConstructors()) {
            if (ctor.getParameterCount() == 1
                    && ctor.getParameterTypes()[0].isInstance(argument)) {
                try {
                    ctor.setAccessible(true);
                    return ctor.newInstance(argument);
                } catch (ReflectiveOperationException e) {
                    throw new AssertionError("Instanciation impossible : " + type, e);
                }
            }
        }
        return instancier(type);
    }
}
