package gameLaby.laby.ia_monstres;

import gameLaby.laby.Labyrinthe;

public class Aleatoire implements IA{
    @Override
    public int nextDirection(int case_monstre, int case_joueur) {

      int[] actions = {case_monstre-1, case_monstre+1, case_monstre - Labyrinthe.murs[0].length,case_monstre + Labyrinthe.murs[0].length};
      return actions[(int) (Math.random() * actions.length - .001)];


    }
}
