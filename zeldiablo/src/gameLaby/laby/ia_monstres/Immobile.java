package gameLaby.laby.ia_monstres;

public class Immobile implements IA {
    @Override
    public int nextDirection(int case_monstre, int case_joueur) {
        return case_monstre;
    }
}
