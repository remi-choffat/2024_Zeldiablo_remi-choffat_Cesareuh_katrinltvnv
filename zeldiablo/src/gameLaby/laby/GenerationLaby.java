package gameLaby.laby;

import java.util.ArrayList;

public class GenerationLaby {
    private static ArrayList<ArrayList<Integer>> murs_a_ajouter;
    private static boolean[][] murs = Labyrinthe.murs;

    public static void creer(){
        murs_a_ajouter = new ArrayList<ArrayList<Integer>>();
        for(int x = 0; x < murs.length; x++){
            for(int y = 0; x < murs[x].length; y++){
                murs[x][y] = true;
            }
        }

        murs[Labyrinthe.pj.getX()][Labyrinthe.pj.getY()] = false;
        ajouterMurs(Labyrinthe.pj.getX(), Labyrinthe.pj.getY());

        while(murs_a_ajouter.size() > 0){
            int index = (int)(Math.random() * murs_a_ajouter.size()-1);
            ArrayList<Integer> cell = murs_a_ajouter.get(index);
            int x = cell.get(0);
            int y = cell.get(1);
            ajouter(x,y);
            murs_a_ajouter.remove(index);
        }

    }

    public static void ajouterMurs(int x, int y){
        int[][] a_ajouter = {{x-1, y}, {x+1, y}, {x, y-1}, {x, y+1}};

        for(int[] pos : a_ajouter){
            if(pos[0] >= 0 && pos[0] < murs.length && pos[1] >= 0 && pos[1] < murs[0].length && murs[pos[0]][pos[1]] == true){
                ArrayList<Integer> mur = new ArrayList<Integer>();
                mur.add(pos[0]);
                mur.add(pos[1]);
                murs_a_ajouter.add(mur);
            }
        }
    }

    public static void ajouter(int x, int y){
        int compteur = 0;
        int[][] a_ajouter = {{x-1, y}, {x+1, y}, {x, y-1}, {x, y+1}};
        ArrayList<int[]> base_pos = new ArrayList<>();
        ArrayList<int[]> deuxieme_pos = new ArrayList<>();

        for(int[] pos : a_ajouter){
            if(pos[0] > 0 && pos[0] < murs.length-1 && pos[1] > 0 && pos[1] < murs[0].length-1){
                if(!murs[pos[0]][pos[1]]){
                    compteur++;
                    base_pos.add(pos);
                }
                if(ajoutable(pos[0], pos[1])){
                    deuxieme_pos.add(pos);
                }
            }
        }

        while (deuxieme_pos.size() > 0 && compteur == 1) {
            int index = (int)(Math.random() * deuxieme_pos.size()-1);
            if(base_pos.get(0)[0] == deuxieme_pos.get(index)[0] || base_pos.get(0)[1] == deuxieme_pos.get(index)[1]){
                murs[x][y] = false;
                murs[deuxieme_pos.get(index)[0]][deuxieme_pos.get(index)[1]] = true;
                ajouterMurs(deuxieme_pos.get(index)[0], deuxieme_pos.get(index)[1]);
                compteur++;
            }
            deuxieme_pos.remove(index);
        }
    }

    public static boolean ajoutable(int x, int y){
        int compteur = 0;
        for(int i = x -1; i <= x+1; i++){
            for(int j = y -1; j <= y+1; j++){
                if(i > 0 && i < murs.length-1 && j > 0 && j < murs[0].length-1){
                    if(murs[i][j]){
                        compteur++;
                    }
                }
            }
        }
        return compteur == 0;
    }
}
