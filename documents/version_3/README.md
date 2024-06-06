# Version 3

## Objectif
### Résumé

## Fonctionnalités
 - [ ] 1.8 Gestion d'un labyrinthe multi-étages
Le labyrinthe est constitué de plusieurs étages. Chaque étage correspond à un niveau
particulier. Des escaliers permettent de passer d'un étage à un autre.
Critères de validation
* Les escaliers sont des cases traversables par le héros et les monstres.
* Lorsque le héros se trouve sur un escalier, il peut l'activer et il change alors de
niveau.
* Les monstres du niveau où le héros se trouvait avant l'escalier n'apparaissent pas
dans l'étage supérieur/inférieur. Ils restent à leur position et conservent leur points
de vie.
* De nouveaux monstres peuvent être présents à l'étage supérieur en fonction du
descriptif du niveau.
* Le Héros arrive aux mêmes coordonnées dans l'étage supérieur/inférieur après
avoir emprunté les escaliers.
* ~~Les escaliers peuvent être de deux types : un escalier qui monte ou un escalier qui descend.~~ 
* Les escaliers envoient vers le niveau suivant. 
* ~~Les escaliers sont empruntables dans les deux sens : un escalier qui monte vers un étage possède un escalier qui descend à la même position dans l'étage supérieur (et inversement).~~
* Les escaliers sont empruntables dans un sens : un escalier qui monte vers un étage possède un escalier qui descend à la même position dans l'étage supérieur (et inversement).

 - [ ] 1.9 Génération automatique de labyrinthe
Les niveaux ne sont pas stockés en mémoire, mais le labyrinthe est généré automatiquement par le jeu (ce qui permet d'avoir des parties à chaque fois différentes)
Critères de validation
* Les monstres, les objets et le héros sont bien placés sur des cases vides
* Vous pourrez vous inspirer de la page suivante [Création labyrinthe](http://www.encyclopedie-incomplete.com/?Modelisation-et-Creation-d-un)
* les critères de validation sont à détailler en fonction de la méthode de génération suivie.

### Améliorations diverses
 - [ ] Déplacement en temps réel
 - [ ] Changement de l'aspect des flèches
 - [ ] Équilibrage des dégats et des points de vie

## Répartition des tâches
Rémi :     
Kateryna :     
Mathieu : Génération automatique de labyrinthe    

## Diagrammes
### Diagrammes de classe
#### Diagramme initial
<img src="https://github.com/remi-choffat/2024_Zeldiablo_remi-choffat_Cesareuh_katrinltvnv/blob/main/documents/version_2/diag_classe_v2.png" alt="Diagramme de classe initial"></img>
<img src="https://github.com/remi-choffat/2024_Zeldiablo_remi-choffat_Cesareuh_katrinltvnv/blob/main/documents/version_3/diag_classe_v3.png" alt="Diagramme de classe conception"></img>
<img src="https://github.com/remi-choffat/2024_Zeldiablo_remi-choffat_Cesareuh_katrinltvnv/blob/main/documents/version_3/diag_classe_final_v3.png" alt="Diagramme de classe final"></img>
