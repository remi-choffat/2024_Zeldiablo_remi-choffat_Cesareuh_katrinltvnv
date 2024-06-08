import gameLaby.laby.Escalier;
import gameLaby.laby.Labyrinthe;
import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Tests_V3 {

   /**
    * Teste la méthode changerNiveau
    *
    * @throws IOException si probleme a la lecture du fichier
    */
   @Test
   public void test01_ChangerNiveau() throws IOException {

      Labyrinthe.allLevels.clear();
      Labyrinthe labyrinthe = new Labyrinthe("./labySimple/laby0.txt");
      Labyrinthe.allLevels.add(labyrinthe);
      Labyrinthe.currentLabyrinthe = labyrinthe;

      Labyrinthe.changerNiveau(0);
      assertEquals(Labyrinthe.allLevels.get(0), Labyrinthe.currentLabyrinthe);
   }


   /**
    * Teste la méthode updateLaby
    *
    * @throws IOException si probleme a la lecture du fichier
    */
   @Test
   public void test02_Update() throws IOException {
      Labyrinthe.allLevels.clear();
      Labyrinthe labyrinthe = new Labyrinthe("./labySimple/laby0.txt");
      Labyrinthe.allLevels.add(labyrinthe);
      Labyrinthe.currentLabyrinthe = labyrinthe;

      Labyrinthe.updateLaby();
      assertEquals(2, Labyrinthe.allLevels.size());
   }


   /**
    * Teste la méthode supprimer de Escalier
    */
   @Test
   public void test03_SupprimerEscalier() {
      Escalier escalier = new Escalier(1, 1, 0);
      assertFalse(escalier.supprimer());
   }

   /**
    * Teste la méthode collision de Escalier
    *
    * @throws IOException si probleme a la lecture du fichier
    */
   @Test
   public void test04_CollisionEscalier() throws IOException {
      Labyrinthe.allLevels.clear();
      Labyrinthe labyrinthe = new Labyrinthe("./labySimple/laby0.txt");
      Labyrinthe.allLevels.add(labyrinthe);
      Escalier escalier = new Escalier(1, 1, 0);
      escalier.collision(Labyrinthe.pj);
      assertEquals(2, Labyrinthe.allLevels.size());
   }



}