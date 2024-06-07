package gameLaby.laby;

/**
 * Représente un élément du labyrinthe pouvant lancer une attaque
 */
public interface Attaque {

   void infligerDegats(Vivant v);

   void collision(Entite d);
}
