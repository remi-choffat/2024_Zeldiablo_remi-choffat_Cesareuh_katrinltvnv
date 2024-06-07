import gameLaby.laby.Escalier;
import gameLaby.laby.Labyrinthe;
import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class Tests_V3 {

    /**
     * Teste la méthode changerNiveau
     * @throws IOException si probleme a la lecture du fichier
     */
    @Test
    public void test01_ChangerNiveau() throws IOException {

        Labyrinthe.allLevels.clear();
        Labyrinthe labyrinthe = new Labyrinthe("./labySimple/laby0.txt");
        Labyrinthe.allLevels.add(labyrinthe);
        Labyrinthe.currentLabyrinthe = labyrinthe;

        assertThrows(IllegalArgumentException.class, () -> Labyrinthe.changerNiveau(1));
        assertThrows(IllegalArgumentException.class, () -> Labyrinthe.changerNiveau(2));

        Labyrinthe.changerNiveau(0);
        assertEquals(Labyrinthe.allLevels.get(0), Labyrinthe.currentLabyrinthe);
    }


    /**
     *  Teste la méthode updateLaby
     * @throws IOException si probleme a la lecture du fichier
     */
    @Test
    public void test02_Update() throws IOException {
        Labyrinthe.allLevels.clear();
        Labyrinthe labyrinthe = new Labyrinthe("./labySimple/laby0.txt");
        Labyrinthe.allLevels.add(labyrinthe);
        Labyrinthe.currentLabyrinthe = labyrinthe;

        Labyrinthe.updateLaby();
        assertEquals(1, Labyrinthe.allLevels.size());
    }


    /**
     * Teste la méthode changerNiveau avec un niveau incorrect
     * @throws IOException si probleme a la lecture du fichier
     */
    @Test
    public void test03_ChangerNiveau_Negatif() throws IOException {
        Labyrinthe.allLevels.clear();
        Labyrinthe labyrinthe = new Labyrinthe("./labySimple/laby0.txt");
        Labyrinthe.allLevels.add(labyrinthe);
        Labyrinthe.currentLabyrinthe = labyrinthe;

        assertThrows(IllegalArgumentException.class, () -> Labyrinthe.changerNiveau(1));
    }

    /**
     * Teste la méthode collision de Escalier
     * @throws IOException si probleme a la lecture du fichier
     */
    @Test
    public void test04_CollisionEscalier() throws IOException {
        Labyrinthe labyrinthe = new Labyrinthe("./labySimple/laby0.txt");
        Labyrinthe.allLevels.add(labyrinthe);
        Labyrinthe.currentLabyrinthe = labyrinthe;

        Escalier escalier = new Escalier(1, 1, 0);
        escalier.collision(Labyrinthe.pj);
        assertEquals(2, Labyrinthe.allLevels.size());
    }

    /**
     * Teste la méthode changerNiveau avec un niveau incorrect
     * @throws IOException si probleme a la lecture du fichier
     */
    @Test
    public void test05_InvalideLevelChange() throws IOException {
        Labyrinthe labyrinthe = new Labyrinthe("./labySimple/laby0.txt");
        Labyrinthe.allLevels.add(labyrinthe);
        Labyrinthe.currentLabyrinthe = labyrinthe;

        assertThrows(IllegalArgumentException.class, () -> Labyrinthe.changerNiveau(1));
    }
}