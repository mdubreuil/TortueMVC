# TurtleSoccer
Projet du module d'ISI3 consacré aux Design Patterns

* Proposer un jeu de balles
* ~~Proposer un jeu de foot~~

## TODO (par niveau de complexité)

Simple :
- Conception des tests unitaires
- Commenter le code : en-tête de chaque méthode
- Générer la javadoc
- Modifier le diagramme UML (rapport)
- Expliquer les choix de conception (design patterns, MVC) (rapport)
- Détailler les tests (rapport)
- Générer le .jar
- Empécher la tortue de quitter le terrain quand elle est dirigée au clavier
- Lorsque la tortue courante attrape la balle, elle ne doit plus être tortue courante (manipulable avec le clavier)
---
Moyen :
- Permettre le changement de stratégie sur l'interface graphique
- Implémenter les tests unitaires
- Debugguer l'arrêt du jeu (Ophélie)
---
Complexe :
- Implémenter un temps minimum de possession de la balle par la tortue
- Important: Implémenter le pattern "Stategie" pour gérer l'état du jeu
---
Optionnel :
- Peaufiner l'interface graphique (centrer, etc.)
- Dessiner le rayon de visibilité des tortues intelligentes
- Liste Enum pour mettre des noms de tortue rigolos : Turtule, torture ...
- Implémenter Code couleur tortues (au pif) :
  - Rouge : tortue courante
  - Bleue : tortue détentrice de la balle
  - Noire : balle
  - Jaune : tortues intelligentes
  - Vert : tortues aléatoires

