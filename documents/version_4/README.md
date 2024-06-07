# Version 4

## Objectif

L'objectif est d'implémenter une gestion des niveaux en générant des labyrinthes aléatoires.

## Fonctionnalités

### - 1.9 Génération automatique de labyrinthe

Les niveaux ne sont pas stockés en mémoire, mais le labyrinthe est généré automatiquement par le jeu (ce qui permet
d'avoir des parties à chaque fois différentes)   
Pour générer ce labyrinthe, nous avons utilisé l'algorithme de Prims

#### Critères de validation :

##### Base

* Les monstres, les objets et le héros sont bien placés sur des cases vides
* Vous pourrez vous inspirer de la page
  suivante [Création labyrinthe](http://www.encyclopedie-incomplete.com/?Modelisation-et-Creation-d-un)
* les critères de validation sont à détailler en fonction de la méthode de génération suivie.

### - Améliorations diverses

- [ ] Vitesse des objets `Deplacable`
- [ ] Mouvements fluides
- [ ] Images pour toutes les entités

## Répartition des tâches

Rémi :  
Kateryna :  
Mathieu :  

## Diagrammes

### Diagrammes de classe

#### Diagramme initial

<img src="https://github.com/remi-choffat/2024_Zeldiablo_remi-choffat_Cesareuh_katrinltvnv/blob/main/documents/version_4/diag_classe_v4_initial.png" alt="Diagramme de classe initial"></img>

#### Diagramme de conception

<img src="https://github.com/remi-choffat/2024_Zeldiablo_remi-choffat_Cesareuh_katrinltvnv/blob/main/documents/version_4/diag_classe_v4.png" alt="Diagramme de classe conception"></img>

#### Diagramme final

<img src="https://github.com/remi-choffat/2024_Zeldiablo_remi-choffat_Cesareuh_katrinltvnv/blob/main/documents/version_4/diag_classe_v4_final.png" alt="Diagramme de classe final"></img>

### Diagrammes de séquence