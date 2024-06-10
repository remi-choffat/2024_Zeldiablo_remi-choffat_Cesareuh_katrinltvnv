# Version 5

## Objectif

## Fonctionnalités

### - 11.2 Configuration du clavier

Il est possible de reconfigurer dans un fichier texte les touches pour contrôler le
personnage.

#### Critères de validation :

* Les touches sont décrites dans un fichier texte nomme "configTouches.txt"
* Si le fichier n'existe pas, ce sont les touches par défaut qui sont utilisées.
* le fichier de configuration est chargé une seule fois au lancement de l'application.

### - 2.5 Monstres avec intelligence variable

En plus d'être de type différents (orc, troll, fantôme), les monstres peuvent avoir
des degrés d'intelligence différents. En fonction de ce degré d'intelligence, ils se déplacent
différemment.

#### Critères de validation :

* Un monstre d'intelligence nulle reste immobile.
* Un monstre d'intelligence faible se déplace au hasard.
* Un monstre d'intelligence moyenne cherche à se rapprocher du héros sans prendre
  en compte les obstacles.
* Un monstre d'intelligence forte se rapproche du héros en évitant les obstacles.
* Plusieurs monstres avec des intelligences différentes peuvent être présents dans le
  même labyrinthe.
* Chaque type de monstre (Orc, Fantome, Troll) peut avoir n'importe quel type
  d'intelligence. On peut avoir dans le même labyrinthe des Orc d'intelligence nulle,
  faible, moyenne et forte. Idem pour les trolls et les fantômes.

### - Améliorations diverses

- [ ] Vitesse des objets `Deplacable` : certaines entités attendent plus longtemps avant de bouger 🏎️
- [ ] Mouvements fluides 🐇
- [ ] Zone de vision diminuée : Plus c'est loin, plus c'est sombre : Intensité selon niveau
  🙋🏻‍♂️🙋🏼‍♂️🙋🏽‍♂️🙋🏾‍♂️🙋🏿‍♂️
- [ ] Ajout des règles du jeu par un bouton sur le menu (descriptif des conditions de passage de niveau, des points, des
  points de vie, des flèches...) 📜
- [ ] Taille du labyrinthe qui grandit en fonction du niveau 🏗️

## Répartition des tâches

Rémi :  Règles du jeu, Taille du labyrinthe  
Kateryna :  
Mathieu :  Intelligence des monstres

## Diagrammes

### Diagrammes de classe

#### Diagramme initial

<img src="https://github.com/remi-choffat/2024_Zeldiablo_remi-choffat_Cesareuh_katrinltvnv/blob/main/documents/version_4/diag_classe_v5_initial.png" alt="Diagramme de classe initial"></img>

#### Diagramme de conception

<img src="https://github.com/remi-choffat/2024_Zeldiablo_remi-choffat_Cesareuh_katrinltvnv/blob/main/documents/version_4/diag_classe_v5.png" alt="Diagramme de classe conception"></img>

#### Diagramme final

<img src="https://github.com/remi-choffat/2024_Zeldiablo_remi-choffat_Cesareuh_katrinltvnv/blob/main/documents/version_4/diag_classe_v5_final.png" alt="Diagramme de classe final"></img>

### Diagrammes de séquence
