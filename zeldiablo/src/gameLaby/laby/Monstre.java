package gameLaby.laby;

import gameLaby.laby.ia_monstres.*;

/**
 * Représente un monstre
 */
public class Monstre extends Vivant {

   IA[] ias = {new Immobile(), new Fuyard(), new Aleatoire(), new VolOiseau(),  new Intelligent()};
   IA ia;
   /**
    * constructeur
    *
    * @param dx position selon x
    * @param dy position selon y
    */
   public Monstre(int dx, int dy) {
      super(dx, dy, 1, 4);

      // Choisi aléatoirement un niveau d'intelligence en fonction du niveau
      int index_ia = (int)(Math.random()*((ias.length-1)*Labyrinthe.prochainNiveau*.15));

      if(index_ia >= ias.length-1){
         ia = ias[ias.length-1];
      }else if (index_ia <= 0){
         ia = ias[0];
      }else{
         ia = ias[index_ia];
      }
      System.out.println(ia.getClass().getSimpleName());
   }


   /**
    * deplace le monstre
    * le monstre se déplace aléatoirement
    */
   public void deplacer() {
      this.setDirection(vers_joueur());
      super.deplacer();
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

   public String vers_joueur(){
      // Trouver le numéro de la case du monstre et du perso
      int case_monstre = Labyrinthe.murs[0].length*getX()+getY();
      int case_perso = Labyrinthe.murs[0].length*Labyrinthe.pj.getX()+Labyrinthe.pj.getY();

      int next_pos = ia.nextDirection(case_monstre, case_perso);

      if(next_pos == case_monstre + 1){
         return Labyrinthe.BAS;
      }
      if(next_pos == case_monstre - 1){
         return Labyrinthe.HAUT;
      }
      if(next_pos > case_monstre){
         return Labyrinthe.DROITE;
      }
      if(next_pos < case_monstre){
         return Labyrinthe.GAUCHE;
      }

      return "";
   }

}
