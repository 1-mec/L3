# Design Patterns — TP de L3 Informatique

Code des travaux pratiques de l'EP 5.1.2 *Design Patterns* (L3 Informatique,
Université de Tours, année 2026–2027).

Ce dépôt contient, pour chaque TP, le **code de départ** et **les tests** qui
l'accompagnent. Les sujets (PDF) sont déposés sur Celene. Les corrigés seront
publiés plus tard dans ce même dépôt.

Les TP prolongent les TD : ils font **écrire** ce que les TD font
**dessiner**. Ils travaillent trois choses :

* **comprendre par l'implémentation** : un pattern qu'on a dessiné en TD, on
  l'écrit, on le compile, on le teste ;
* **intervenir dans du code existant** : la plupart des exercices sont des
  *refactorings*, pas des écritures à partir de zéro ;
* **comparer des variantes** : le même problème résolu de deux ou trois
  façons, pour mesurer ce que chacune coûte.

Le **sudoku** sert de fil rouge au code, comme le `Livre` sert de fil rouge
aux TD.

---

## Les TP

| TP | Sujet | Prérequis | Séance |
|---|---|---|---|
| 1 | `Strategy` et `Adapter` : séparer ce qui varie, réutiliser sans modifier | TD 1, TD 2 | 3 nov. |
| 2 | *Mais qu'est-ce que tu fabriques !* — `Factory Method`, `Abstract Factory`, greffons | TD 4 | 10 nov. |
| 3 | Quelques compléments : `Iterator` et `Singleton` | TP 2 | 17 nov. |
| 4 | `Observer` : trois mises en œuvre, et ce qui les sépare | TD 3, TP 1 | 24 nov. |
| 5 | `Decorator` et `Composite` : empiler, composer, mesurer ce que cela change | TD 5, TP 3 | autonomie |

Le TP 5 n'a pas de séance encadrée : c'est un travail d'approfondissement et
de révision avant le contrôle du 3 décembre. Les dates sont celles du
syllabus et peuvent évoluer.

---

## Ce qu'il vous faut

* un **JDK 17** ou plus récent ;
* **Eclipse** (« Eclipse IDE for Java Developers », version 2021-12 ou plus
  récente) : c'est l'environnement utilisé en salle machine. Il intègre
  Maven, inutile de l'installer à part ;
* **Git**.

Maven en ligne de commande (3.8 ou plus récent) est facultatif : il sert à
lancer les tests hors d'Eclipse, et c'est lui que vous utiliserez si vous
travaillez avec un autre éditeur.

## Récupérer le code

```sh
git clone https://scm.univ-tours.fr/brouard/l3info-dp.git
cd l3info-dp
```

Le dépôt sera complété au fil du semestre (corrigés, éventuelles
corrections). Pour récupérer ces mises à jour sans perdre votre travail :

```sh
git switch -c mon-travail      # une fois, au début : votre propre branche
# ... vous travaillez, vous validez régulièrement (git add, git commit) ...
git fetch origin               # quand une mise à jour est annoncée
git merge origin/main
```

Validez (`git commit`) votre travail **avant** de récupérer une mise à jour.

## Travailler sous Eclipse

Le projet est un projet **Maven** : c'est le fichier `pom.xml` qui décrit
les modules, la version de Java et les bibliothèques (JUnit). Eclipse le lit
et configure tout seul. **Ne créez pas de projet Java à la main** et
n'ajoutez rien au *build path* : passez par l'import Maven.

### Importer le code (une seule fois)

1. Clonez le dépôt (voir ci-dessus).
2. Dans Eclipse : *File → Import… → Maven → Existing Maven Projects*, puis
   *Next*.
3. *Root Directory* : choisissez le dossier `l3info-dp` que vous venez de
   cloner. Eclipse liste le projet parent et les modules (`tp1`, `tp2`,
   `tp2-plugin-basique`…) : laissez-les tous cochés, puis *Finish*.
4. Patientez : au premier import, Eclipse télécharge JUnit et les outils
   Maven depuis Internet (barre de progression en bas à droite). Chaque
   module apparaît ensuite comme un projet distinct dans le *Package
   Explorer*.

> Vous pouvez aussi cloner depuis Eclipse : vue *Git Repositories* (*Window →
> Show View → Other… → Git*), bouton *Clone a Git repository*, avec l'adresse
> du dépôt. Faites ensuite l'import Maven des étapes 2 à 4 sur le dossier
> cloné.

### Lancer les tests

* Clic droit sur un module (par exemple `tp1`) ou sur une classe de test →
  *Run As → JUnit Test*. La vue JUnit affiche les tests réussis, en échec,
  et **ignorés** : ce sont les tests « Objectifs » encore en attente de
  votre travail (voir plus bas).
* Pour obtenir exactement la sortie de la ligne de commande : clic droit sur
  le module → *Run As → Maven test*.

### En cas de problème

* **Erreur sur la version de Java** (« release version 17 not supported »,
  « unbound JRE ») : Eclipse n'a pas de JDK 17. Déclarez-en un dans
  *Window → Preferences → Java → Installed JREs* (*Add…*, puis le
  répertoire du JDK), cochez-le, puis faites l'étape suivante.
* **Croix rouges alors que le code n'a pas changé**, ou après une mise à
  jour du dépôt : sélectionnez tous les projets, clic droit → *Maven →
  Update Project…* (cochez *Force Update*), puis *OK*.
* **Le téléchargement échoue** au premier import : vérifiez votre accès à
  Internet, puis refaites *Maven → Update Project…*.

### Avec un autre éditeur

IntelliJ IDEA (*File → Open* sur le `pom.xml` racine) et VS Code (extension
*Extension Pack for Java*, puis ouverture du dossier) savent aussi importer
un projet Maven. Ils ne sont pas utilisés en salle machine : vous êtes
autonomes pour leur configuration.

## Organisation du dépôt

```
l3info-dp/
  pom.xml                 projet parent : version de Java, JUnit, surefire
  tp1/                    TP 1 : code de départ + tests
  tp2/                    TP 2 : l'application de résolution
  tp2-plugin-basique/     TP 2 : greffons (seconde partie)
  tp2-plugin-avance/
  tp3/  tp4/  tp5/        TP 3 à 5
```

Chaque module `tpN` suit la même organisation Maven :

```
tpN/src/main/java/utours/l3/dp/tpN/<domaine>/   le code
tpN/src/test/java/utours/l3/dp/tpN/<domaine>/   les tests
```

## Compiler et lancer les tests

Sous Eclipse : voir ci-dessus (*Run As → JUnit Test* ou *Run As → Maven
test*).

En ligne de commande, depuis la racine du dépôt :

```sh
mvn test             # compile et teste tous les TP
mvn -pl tp1 test     # un seul TP
```

---

## Les tests : votre filet, et votre énoncé

Les tests fournis sont de deux sortes, et le nom du groupe (`@Nested`) le dit :

* **Invariants** : ils passent *avant* et *après* votre travail. Ils décrivent
  ce que le programme doit continuer à faire. S'ils cassent, votre
  transformation a changé le comportement : ce n'est plus un refactoring,
  c'est une réécriture.
* **Objectifs** : ils portent sur des classes que vous n'avez pas encore
  écrites. Ils sont **ignorés** tant que ces classes n'existent pas — Maven
  les compte comme *skipped* — et doivent passer à la fin. Ils vous disent
  exactement ce qui est attendu, y compris les noms des classes.

> Le but n'est donc pas « 0 échec », mais **« 0 échec et 0 test ignoré »**.

Au départ, avant toute modification, Maven doit afficher, pour chaque
module, la ligne de résultats suivante. Attention : le nombre de tests
lancés (*Tests run*) **inclut** les tests ignorés (*Skipped*).

| Module | Au départ (sortie de Maven) | À la fin |
|---|---|---|
| `tp1` | `Tests run: 23, Failures: 0, Errors: 0, Skipped: 10` | `Skipped: 0` |
| `tp2` | `Tests run: 9, Failures: 0, Errors: 0, Skipped: 4` | `Skipped: 0` |
| `tp3` | `Tests run: 9, Failures: 0, Errors: 0, Skipped: 7` | `Skipped: 0` |
| `tp4` | `Tests run: 10, Failures: 0, Errors: 0, Skipped: 4` | `Skipped: 0` |
| `tp5` | `Tests run: 18, Failures: 0, Errors: 0, Skipped: 8` | `Skipped: 0` |

Les lignes `Tests run: 0` qui apparaissent au passage sont normales : elles
correspondent aux classes de test qui ne font que regrouper les groupes
`Invariants` et `Objectifs`.

Si vous n'obtenez pas ces chiffres avant d'avoir commencé, votre
installation pose problème : réglez-le d'abord.

**Ne modifiez pas les tests.** Un refactoring ne renomme pas les classes
attendues et ne change pas le comportement observable : ce sont les mêmes
tests qui valideront les corrigés.

### Travaillez par petits pas

Lancez les tests **avant** de commencer, pour partir d'un état connu. Puis :
une transformation, les tests ; une transformation, les tests. Si vous
enchaînez dix modifications avant de relancer, vous ne saurez plus laquelle
a cassé quoi — et c'est exactement ce que le refactoring cherche à éviter.
Validez (`git commit`) à chaque étape qui fonctionne : vous pourrez toujours
y revenir.

---

## Le TP 2 et les greffons

La seconde partie du TP 2 charge, **à l'exécution**, des solveurs qui
n'existaient pas à la compilation de l'application.

* `tp2-plugin-basique` et `tp2-plugin-avance` déclarent leur dépendance vers
  `tp2` en portée **`provided`** : un greffon *compile* contre les interfaces
  de l'application, mais ne les *embarque* pas.
* Chaque greffon déclare son fournisseur dans
  `src/main/resources/META-INF/services/`, où `ServiceLoader` le découvre.
* `ChargeurDeGreffons` ouvre un répertoire de fichiers `.jar` ; ajouter ou
  retirer un `.jar` ajoute ou retire des solveurs, sans recompiler
  l'application.

Pour produire les archives des greffons :

```sh
mvn -pl tp2-plugin-basique,tp2-plugin-avance -am package
# les .jar sont dans tp2-plugin-*/target/
```

Pour construire **votre propre greffon** (seconde partie du TP 2) dans un
nouveau module, sans modifier le `pom.xml` racine :

```sh
mvn -pl tp2 -am install                    # installe l'application et le projet parent
mvn -f tp2-plugin-region/pom.xml package   # construit votre module seul
```

Sous Eclipse, sans ligne de commande : clic droit sur le projet parent →
*Run As → Maven install* (installe tous les modules), puis clic droit sur
votre module → *Run As → Maven build…*, *Goals* : `package`. Les `.jar`
apparaissent dans le dossier `target/` de chaque module (faites *Refresh*,
touche F5, s'il n'est pas visible). Pour que votre nouveau module apparaisse
dans Eclipse, importez-le comme les autres (*Existing Maven Projects*).

---

## Conventions de code

Le code fourni suit ces conventions, et les sujets s'appuient dessus.
Respectez-les dans ce que vous écrivez :

* paquetages `utours.l3.dp.tpN.<domaine>`, en minuscules ;
* noms de classes sans préfixe (`Grille9x9`, et non `CGrille9x9`) ;
* collections **paramétrées**, variables déclarées avec le type de
  l'interface (`List<Livre>`, pas `ArrayList<Livre>`) ;
* `try-with-resources` pour tout ce qui se ferme ;
* dépendances **injectées** (flux de sortie, graine aléatoire, seuil
  d'alerte…) plutôt que codées en dur : c'est ce qui rend les classes
  testables ;
* Javadoc sur les classes et sur les décisions de conception, pas sur les
  accesseurs évidents.

---

## Se documenter

Ne pas comprendre une classe, un message d'erreur ou une option de Maven est
**normal** : cela arrive tous les jours aux développeurs confirmés. Ce qui
compte, c'est la démarche pour s'en sortir. Prenez l'habitude de chercher
par vous-même avant de demander :

1. **Lisez le message d'erreur en entier.** La cause réelle est souvent plus
   bas que la première ligne : cherchez le `Caused by:` et la première ligne
   de la pile d'appels qui désigne *votre* code.
2. **Consultez la documentation officielle** de la classe ou de l'outil en
   cause (liens ci-dessous) : la Javadoc dit ce qu'une méthode attend, ce
   qu'elle rend, et quelles exceptions elle lève.
3. **Cherchez le message d'erreur exact** sur un moteur de recherche, entre
   guillemets, sans ce qui est propre à votre machine (chemins, numéros de
   ligne).
4. **Réduisez le problème** : reproduisez-le dans un exemple de quelques
   lignes. Souvent, la solution apparaît en chemin.
5. Seulement ensuite, **posez la question** sur le forum, en disant ce que
   vous avez essayé.

### Sources fiables

Privilégiez les documentations officielles, et la version de Java du cours
(17).

| Sujet | Où chercher |
|---|---|
| L'API Java (Javadoc) | <https://docs.oracle.com/en/java/javase/17/docs/api/> |
| Apprendre et réviser Java | <https://dev.java/learn/> (site officiel d'Oracle) |
| Tutoriels Java (anciens, mais solides sur les bases) | <https://docs.oracle.com/javase/tutorial/> |
| Le langage, dans le détail | <https://docs.oracle.com/javase/specs/> |
| Maven | <https://maven.apache.org/guides/getting-started/> |
| JUnit 5 | <https://docs.junit.org/5.10.2/user-guide/> |
| Git (livre *Pro Git*, en français) | <https://git-scm.com/book/fr/v2> |
| Les design patterns (en français) | <https://refactoring.guru/fr/design-patterns> |
| Questions et réponses | <https://stackoverflow.com/questions/tagged/java> |

Quelques précautions :

* sur les forums (Stack Overflow ou autres), vérifiez **la date** et **la
  version de Java** de la réponse, et préférez les réponses expliquées à
  celles qui ne donnent que du code ;
* un code trouvé en ligne, ou produit par une IA générative, n'est pas une
  explication : **ne recopiez rien que vous ne sauriez pas expliquer**, et
  vérifiez-le avec les tests fournis ;
* la Javadoc d'une classe est presque toujours plus fiable — et plus courte
  à lire — qu'un tutoriel qui en parle.

## Aide

* Une question sur un sujet, une notion encore floue : le **forum Celene** du
  cours.
* Un problème avec ce dépôt (le code ne compile pas, un test échoue alors que
  vous n'avez rien modifié) : signalez-le sur le forum, en indiquant le module
  et la sortie de `mvn -pl tpN test`.

Enseignants : Thierry Brouard, Hélène Brouard — `prenom.nom@univ-tours.fr`.
