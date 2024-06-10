package gameLaby.laby;

import gameLaby.laby.ia_monstres.*;

import java.util.ArrayList;

/**
 * Représente un monstre
 */
public class Monstre extends Vivant {

   /**
    * Tableau des différentes IA possibles
    */
   IA[] ias = {new Immobile(), new Fuyard(), new Aleatoire(), new VolOiseau(), new Intelligent()};

   /**
    * IA du monstre
    */
   IA ia;


   /**
    * constructeur
    *
    * @param dx position selon x
    * @param dy position selon y
    * @param speed vitesse de deplacement
    */
   public Monstre(int dx, int dy, double speed) {
      super(dx, dy, 1, 4, speed);

      // Choisit aléatoirement un niveau d'intelligence en fonction du niveau (plus le niveau est élevé, plus l'IA est intelligente)
      int index_ia = (int)(Math.random()*((ias.length-1)*Labyrinthe.prochainNiveau*.2));

      if (index_ia >= ias.length - 1) {
         ia = ias[ias.length - 1];
      } else if (index_ia <= 0) {
         ia = ias[0];
      } else {
         ia = ias[index_ia];
      }
   }


   /**
    * deplace le monstre
    * le monstre se déplace aléatoirement
    *
    * @param deltaTime temps écoulé depuis la dernière mise à jour
    */
   public void deplacer(double deltaTime) {
      this.setDirection(vers_joueur());
      super.deplacer(deltaTime);
   }


   /**
    * inflige des dégats à un vivant
    *
    * @param v vivant à attaquer
    */
   public void infligerDegats(Vivant v) {
      v.subirDegats(1, this);
   }


   /**
    * Subit des dégâts
    *
    * @param degats    dégâts subits
    * @param attaquant entité attaquante
    */
   @Override
   public void subirDegats(int degats, Entite attaquant) {
      // Si le monstre est déjà mort, ne fait rien
      if (this.getPv() <= 0) {
         return;
      }
      // Fait subir des dégâts au monstre
      super.subirDegats(degats);
      // Si le monstre est mort après avoir subi des dégâts
      if (this.getPv() <= 0) {
         // Si l'attaquant est le personnage ou une flèche lancée par le personnage
         if (attaquant instanceof Perso) {
            // Ajoute 10 points au personnage
            Labyrinthe.pj.addPoints(5, "Monstre tué par attaque directe");
         } else if (attaquant instanceof Fleche) {
            // Ajoute 10 points au personnage
            Labyrinthe.pj.addPoints(5, "Monstre tué par attaque à distance");
         } else {
            // Soustrait 2 points au personnage
            Labyrinthe.pj.addPoints(-2, "Monstre tué par un autre monstre");
         }
      }
   }

   /**
    * Retourne la direction vers le joueur
    *
    * @return direction vers le joueur
    */
   public String vers_joueur() {
      // Trouver le numéro de la case du monstre et du perso
      int case_monstre = Labyrinthe.murs[0].length * getX() + getY();
      int case_perso = Labyrinthe.murs[0].length * Labyrinthe.pj.getX() + Labyrinthe.pj.getY();

      ArrayList<Integer> prochaines_positions = A_star.path(case_monstre, case_perso);

      // verifier qu'il y a suffisamment d'elements dans la liste
      if (prochaines_positions == null || prochaines_positions.size() < 2) {
         return "";
      }

      int next_pos = prochaines_positions.get(prochaines_positions.size() - 2);

      if (next_pos == case_monstre + 1) {
         return Labyrinthe.BAS;
      }
      if (next_pos == case_monstre - 1) {
         return Labyrinthe.HAUT;
      }
      if (next_pos > case_monstre) {
         return Labyrinthe.DROITE;
      }
      if (next_pos < case_monstre) {
         return Labyrinthe.GAUCHE;
      }

      return "";
   }

}
