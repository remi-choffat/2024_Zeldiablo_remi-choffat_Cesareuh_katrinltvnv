package gameLaby.laby.ia_monstres;

import gameLaby.laby.A_star;

import java.util.ArrayList;

public class Intelligent implements IA {
    @Override
    public int nextDirection(int case_monstre, int case_perso) {
        ArrayList<Integer> prochaines_positions = A_star.path(case_monstre, case_perso);
        return prochaines_positions.get(prochaines_positions.size()-2);
    }
}
