package gameLaby.laby;

import java.util.ArrayList;
import java.util.HashMap;

public class A_star {

   private static boolean[][] murs;

   public static ArrayList<Integer> path(int start, int goal) {
      murs = Labyrinthe.murs;
      ArrayList<Integer> openSet = new ArrayList<>();
      HashMap<Integer, Integer> cameFrom = new HashMap<>();
      HashMap<Integer, Integer> gScore = new HashMap<>();
      HashMap<Integer, Integer> fScore = new HashMap<>();

      // Initialiser le gScore et fScore à +infini pour chaque case
      for (int x = 0; x < murs.length; x++) {
         for (int y = 0; y < murs[x].length; y++) {
            gScore.put(murs[x].length * x + y, Integer.MAX_VALUE);
            fScore.put(murs[x].length * x + y, Integer.MAX_VALUE);
         }
      }

      openSet.add(start);
      gScore.put(start, 0);
      fScore.put(start, h(start, goal));

      while (!openSet.isEmpty()) {

         int current = openSet.get(0);

         for (int cell : openSet) {
            if (fScore.get(cell) < fScore.get(current)) {
               current = cell;
            }
         }

         if (current == goal) {
            return reconstruct_path(cameFrom, current);
         }

         openSet.remove(openSet.indexOf(current));

         ArrayList<Integer> neighbors = new ArrayList<>();
         int x;
         int y;
         for (int i = -1; i <= 1; i += 1) {
            x = current / murs[0].length + i;
            y = current % murs[0].length;
            if (i == 0) {
               for (int j = -1; j <= 1; j += 2) {
                  y = current % murs[0].length + j;
                  if (!murs[x][y]) {
                     neighbors.add(murs[0].length * x + y);
                  }
               }
            } else {
               if (!murs[x][y]) {
                  neighbors.add(murs[0].length * x + y);
               }
            }
         }

         for (int neighbor : neighbors) {
            int tentative_gScore = gScore.get(current) + 1;
            if (tentative_gScore < gScore.get(neighbor)) {
               cameFrom.put(neighbor, current);
               gScore.put(neighbor, tentative_gScore);
               fScore.put(neighbor, tentative_gScore + h(neighbor, goal));
               if (!openSet.contains(neighbor)) {
                  openSet.add(neighbor);
               }
            }
         }
      }


      return null;
   }

   // Reconstruit le chemin le plus court trouvé
   private static ArrayList<Integer> reconstruct_path(HashMap<Integer, Integer> cameFrom, int current) {
      ArrayList<Integer> total_path = new ArrayList<>();
      total_path.add(current);

      while (cameFrom.containsKey(current)) {
         current = cameFrom.get(current);
         total_path.add(current);
      }

      return total_path;
   }

   // Indice de distance entre le départ et l'arrivée
   private static int h(int pos, int goal) {
      int[] start = new int[]{pos % murs[0].length, pos / murs[0].length};
      int[] end = new int[]{goal % murs[0].length, goal / murs[0].length};
      return Math.abs(start[0] - end[0]) + Math.abs(start[1] - end[1]);
   }

}
