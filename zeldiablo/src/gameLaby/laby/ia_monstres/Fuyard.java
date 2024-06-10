package gameLaby.laby.ia_monstres;

import gameLaby.laby.Labyrinthe;

public class Fuyard implements IA{
    @Override
    public int nextDirection(int case_monstre, int case_joueur) {
        int res_x = Integer.MAX_VALUE;
        int res_y = Integer.MAX_VALUE;
        int m_x = case_monstre/ Labyrinthe.murs[0].length;
        int m_y = case_monstre% Labyrinthe.murs[0].length;
        int p_x = case_joueur/ Labyrinthe.murs[0].length;
        int p_y = case_joueur% Labyrinthe.murs[0].length;
        if(m_x > p_x){
            res_x = case_monstre + Labyrinthe.murs[0].length;
        }
        if(m_x < p_x){
            res_x = case_monstre - Labyrinthe.murs[0].length;
        }

        if(m_y > p_y){
            res_y = case_monstre + 1;
        }
        if(m_y < p_y){
            res_y = case_monstre - 1;
        }
        return Math.min(res_x, res_y);
    }
}
