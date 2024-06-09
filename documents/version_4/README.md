# Version 4

## Objectif

L'objectif est d'implémenter une gestion des niveaux en générant des labyrinthes aléatoires, et de rendre les déplacements des monstres intelligents.
De plus, une interface graphique est mise en place pour permettre au joueur de lancer le jeu.

## Fonctionnalités

### - 1.9 Génération automatique de labyrinthe

Les niveaux ne sont pas stockés en mémoire, mais le labyrinthe est généré automatiquement par le jeu (ce qui permet
d'avoir des parties à chaque fois différentes).   
Pour générer ce labyrinthe, nous avons utilisé l'algorithme de Prims.

#### Critères de validation :

* Les monstres, les objets et le héros sont bien placés sur des cases vides
* Vous pourrez vous inspirer de la page
  suivante [Création labyrinthe](http://www.encyclopedie-incomplete.com/?Modelisation-et-Creation-d-un)
* les critères de validation sont à détailler en fonction de la méthode de génération suivie.

### - 2.4 Monstres au comportement intelligent

A chaque fois que les monstres se déplacent, ils se rapprochent dans la direction du
héros en prenant en compte la présence des murs.

#### Critères de validation :

* Avant son déplacement, le monstre détermine le meilleur chemin pour atteindre
le héros.
* Le monstre prend en compte les obstacles qui le concernent pour trouver le meilleur
chemin.
* Lorsqu'il se déplace, le monstre suit effectivement le meilleur chemin et parvient
jusqu'au héros même s'il y a des obstacles.

### - 11.3 Gestion d'un menu
Plutôt que de lancer directement le jeu, le joueur lance un menu qui lui permet de
lancer le jeu ou de quitter l'application. Ce menu ré-apparait lorsque le joueur gagne
ou perd.

#### Critères de validation
* Le menu possède une image spécifique.
* Le menu propose deux options : Jouer ou Quitter.
* Le menu s'affiche dans la même JFrame que le rendu du jeu.
* Pendant le menu, le jeu n'est pas créé et ne tourne pas.

### - Améliorations diverses

- [X] Placement aléatoire des monstres et de la sortie 🤪
- [ ] Vitesse des objets `Deplacable` : certaines entités attendent plus longtemps avant de bouger 🏎️
- [ ] Mouvements fluides 🐇
- [ ] Ajout d'un système de points (score) 💯 :  
      - Augmentent beaucoup si le perso tue un monstre (+10)  
      - Augmentent un peu si le perso atteint la sortie (+5)  
      - Diminuent quand un monstre est tué par une autre entité que le perso (-2)  
- [X] Système de points de vie ❤️ :  
      - Le perso gagne des points de vie quand il termine le niveau (+4)  
- [ ] Gestion du stock de flèches 🏹 :   
      - À chaque niveau, on donne au perso autant de flèches que de monstres dans le niveau
- [X] Images pour toutes les entités 🖼️
- [ ] Zone de vision diminuée : Plus c'est loin, plus c'est sombre : Intensité selon niveau 🙋🏻‍♂️🙋🏼‍♂️🙋🏽‍♂️🙋🏾‍♂️🙋🏿‍♂️
- [ ] **Fonctionnalité 11.2 - Configuration du clavier ⌨️️**
- [X] **Fonctionnalité 11.3 - Gestion d'un menu 📋**

## Répartition des tâches

Rémi :  Placement des entités sur le labyrinthe, Système de points  
Kateryna :  Gestion du menu  
Mathieu :  Génération du labyrinthe, Intelligence des monstres  

## Diagrammes

### Diagrammes de classe

#### Diagramme initial

<img src="https://github.com/remi-choffat/2024_Zeldiablo_remi-choffat_Cesareuh_katrinltvnv/blob/main/documents/version_4/diag_classe_v4_initial.png" alt="Diagramme de classe initial"></img>

#### Diagramme de conception

<img src="https://github.com/remi-choffat/2024_Zeldiablo_remi-choffat_Cesareuh_katrinltvnv/blob/main/documents/version_4/diag_classe_v4.png" alt="Diagramme de classe conception"></img>

#### Diagramme final

<img src="https://github.com/remi-choffat/2024_Zeldiablo_remi-choffat_Cesareuh_katrinltvnv/blob/main/documents/version_4/diag_classe_v4_final.png" alt="Diagramme de classe final"></img>

### Diagrammes de séquence
