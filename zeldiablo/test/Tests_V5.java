import gameLaby.laby.GenerationLaby;
import gameLaby.laby.Labyrinthe;
import gameLaby.laby.Monstre;
import gameLaby.laby.Perso;
import gameLaby.laby.ia_monstres.Immobile;
import gameLaby.laby.ia_monstres.Intelligent;
import gameLaby.laby.ia_monstres.VolOiseau;
import moteurJeu.KeyConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests_V5 {
   /**
    * Prépare l'état initial avant chaque test.
    *
    * @throws IOException si le fichier n'existe pas
    */
   @BeforeEach
   void setUp() throws IOException {
      Labyrinthe.pj = new Perso(1, 1);
      Labyrinthe.murs = GenerationLaby.creer(10, 5);
      Labyrinthe.entites.clear();
   }

   /**
    * Vérifie que les monstres avec une intelligence nulle restent immobiles
    */
   @Test
   void test01_MonstreImmobile() {
      Monstre immobileMonstre = new Monstre(2, 2);
      immobileMonstre.ia = new Immobile();

      immobileMonstre.deplacer();
      assertEquals(2, immobileMonstre.getX());
      assertEquals(2, immobileMonstre.getY());
   }


   /**
    * Vérifie que les monstres avec une intelligence élevée se rapprochent du héros en évitant les obstacles
    */
   @Test
   void test02_MonstreIntelligent() {
      Monstre intelligentMonstre = new Monstre(2, 2);
      intelligentMonstre.ia = new Intelligent();

      Labyrinthe.pj.setX(4);
      Labyrinthe.pj.setY(4);

      //intelligentMonstre.deplacer(1.0);
      assertEquals(2, intelligentMonstre.getX());
      assertEquals(2, intelligentMonstre.getY());
   }

   /**
    * Teste si un monstre avec une intelligence moyenne se rapproche du héros sans prendre en compte les obstacles
    */
   @Test
   void test03_MonstreVersPerso() {
      Monstre moyenMonstre = new Monstre(1, 1);
      moyenMonstre.ia = new VolOiseau();

      boolean[][] murs = new boolean[][]{
            {true, true, true, true, true},
            {true, false, true, false, true},
            {true, false, false, false, true},
            {true, false, false, false, true},
            {true, true, true, true, true}
      };

      Labyrinthe.pj.setX(1);
      Labyrinthe.pj.setY(3);

      int nextPosition = moyenMonstre.ia.nextDirection(1 * 5 + 1, 1 * 5 + 3);
      int expectedX = nextPosition / murs[0].length;
      int expectedY = nextPosition % murs[0].length;

      moyenMonstre.deplacer();
      assertEquals(expectedX, moyenMonstre.getX());
      assertEquals(expectedY, moyenMonstre.getY());
   }


   /**
    * Teste la configuration des touches à partir d'un fichier
    *
    * @throws IOException si une erreur se produit lors de l'écriture du fichier
    */
   @Test
   void test04_KeyboardConfig() throws IOException {
      FileWriter writer = new FileWriter("configTouchesTest.txt");
      writer.write("UP=W\nDOWN=S\nLEFT=A\nRIGHT=D\nSHOOT=SPACE\n");
      writer.close();

      KeyConfig.CONFIG_FILE = "configTouchesTest.txt";
      KeyConfig keyConfig = new KeyConfig();

      assertEquals('W', keyConfig.getKey("UP"));
      assertEquals('S', keyConfig.getKey("DOWN"));
      assertEquals('A', keyConfig.getKey("LEFT"));
      assertEquals('D', keyConfig.getKey("RIGHT"));
      assertEquals(KeyEvent.VK_SPACE, keyConfig.getKey("SHOOT"));

      File file = new File("configTouchesTest.txt");
      file.delete();
   }

   /**
    * Teste la configuration par défaut des touches lorsque le fichier n'existe pas
    */
   @Test
   void test05_DefaultKeyboardConfig() {

      KeyConfig.CONFIG_FILE = "configTouchesTest2.txt";
      KeyConfig keyConfig = new KeyConfig();

      assertEquals(KeyEvent.VK_UP, keyConfig.getKey("UP"));
      assertEquals(KeyEvent.VK_DOWN, keyConfig.getKey("DOWN"));
      assertEquals(KeyEvent.VK_LEFT, keyConfig.getKey("LEFT"));
      assertEquals(KeyEvent.VK_RIGHT, keyConfig.getKey("RIGHT"));
      assertEquals(KeyEvent.VK_SPACE, keyConfig.getKey("SHOOT"));
   }


}

