import gameLaby.laby.Entite;
import gameLaby.laby.Escalier;
import gameLaby.laby.Labyrinthe;
import gameLaby.laby.Perso;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class Tests_V4 {

   private Labyrinthe labyrinthe;
   private Perso perso;
   private Escalier escalier;

   /**
    * Crée un labyrinthe et un personnage avant chaque test
    *
    * @throws IOException si le fichier n'existe pas
    */
   @Before
   public void setUp() throws IOException {
      labyrinthe = new Labyrinthe("./labySimple/laby0.txt");
      perso = Labyrinthe.pj;
      for (Entite entite : Labyrinthe.entites) {
         if (entite instanceof Escalier) {
            escalier = (Escalier) entite;
            break;
         }
      }
   }

   /**
    * Vérifie que le labyrinthe est généré correctement
    */
   @Test
   public void test01_GenerationAutomatiqueLabyrinthe() {
      assertNotNull(labyrinthe);
      assertTrue(labyrinthe.getLengthX() > 0);
      assertTrue(labyrinthe.getLengthY() > 0);
   }

   /**
    * Vérifie que le labyrinthe contient des murs
    */
   @Test
   public void test02_GenerationAutomatiqueLabyrinthe_Murs() {
      // Check that the maze contains walls
      boolean hasWalls = false;
      for (int i = 0; i < labyrinthe.getLengthX(); i++) {
         for (int j = 0; j < labyrinthe.getLengthY(); j++) {
            if (labyrinthe.getMur(i, j)) {
               hasWalls = true;
               break;
            }
         }
         if (hasWalls) break;
      }
      assertTrue(hasWalls);
   }

   /**
    * Vérifie que le personnage n'est pas placé sur un mur
    */
   @Test
   public void test03_PlacementAleatoireEntites_Perso() {
      assertFalse(labyrinthe.getMur(perso.getX(), perso.getY()));
   }

   /**
    * Vérifie que le personnage est bien placé sur le labyrinthe
    */
   @Test
   public void test04_PlacementAleatoireEntites_Perso_Position() {
      assertTrue(perso.getX() >= 0 && perso.getX() < labyrinthe.getLengthX());
      assertTrue(perso.getY() >= 0 && perso.getY() < labyrinthe.getLengthY());
   }

   /**
    * Vérifie que le personnage n'est pas placé sur l'escalier de sortie
    */
   @Test
   public void test05_Perso_Non_Sur_Sortie() {
      int distance = Math.abs(perso.getX() - escalier.getX()) + Math.abs(perso.getY() - escalier.getY());
      assertTrue(distance > 0);
   }

}
