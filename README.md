# TurtleSoccer
Projet du module d'ISI3 consacré aux Design Patterns

* Proposer un jeu de balles
* ~~Proposer un jeu de foot~~

## TODO (par niveau de complexité)

Simple :
- Déclencher un timer lorsqu'une tortue a la balle
- Conception des tests unitaires
- Commenter le code : en-tête de chaque méthode
- Générer la javadoc
- Modifier le diagramme UML (rapport)
- Expliquer les choix de conception (design patterns, MVC) (rapport)
- Détailler les tests (rapport)
- Générer le .jar
- Vérifer que l'évènement keyboard soit compatible pour tous les ordinateur
- Observer/observable sur le terrain
- Empécher la tortue de quitter le terrain quand elle est dirigée à la souris
- Lorsque la tortue courante attrape la balle, elle ne doit plus être tortue courante (manipulable avec le clavier)
---
Moyen :
- Implémenter Code couleur tortues (au pif) :
  - Rouge : tortue courante
  - Bleue : tortue détentrice de la balle
  - Noire : balle
  - Jaune : tortues intelligentes
  - Vert : tortues aléatoires
- Permettre le changement de stratégie sur l'interface graphique
- Implémenter les tests unitaires
- Gérer les états du Jeu avec un enum (EN_COURS, EN_PAUSE, ...) et bloquer les actions utilisateurs en fonction de cet état
- Reset du controller, jeu, etc.
---
Complexe :
- Implémenter un temps minimum de possession de la balle par la tortue
- Implémenter la stratégie "Intelligente" : se rapprocher de la balle
---
Optionnel :
- Peaufiner l'interface graphique (centrer, etc.)
- Liste Enum pour mettre des noms de tortue rigolos : Turtule, torture ...

