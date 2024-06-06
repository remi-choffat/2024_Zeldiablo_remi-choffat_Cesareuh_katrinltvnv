import gameLaby.laby.Labyrinthe;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class Tests_V2 {
    @Test
    public void test_creation_laby(){
        try {
            Labyrinthe laby = new Labyrinthe("./labySimple/labyTest.txt");
        }catch(IOException e){
            fail();
        }

        boolean[][] murs = {
                {true, true, true, true},
                {true, false, false, true},
                {true, false, false, true},
                {true, true, true, true},
        };

        assertEquals(Arrays.deepToString(murs), Arrays.deepToString(Labyrinthe.murs));
    }

    @Test
    public void test_getSuivant(){

            assertEquals(Arrays.toString(new int[]{1, 1}), Arrays.toString(Labyrinthe.getSuivant(1, 2, Labyrinthe.HAUT)));
            assertEquals(Arrays.toString(new int[]{1, 3}), Arrays.toString(Labyrinthe.getSuivant(1, 2, Labyrinthe.BAS)));
            assertEquals(Arrays.toString(new int[]{0, 2}), Arrays.toString(Labyrinthe.getSuivant(1, 2, Labyrinthe.GAUCHE)));
            assertEquals(Arrays.toString(new int[]{2, 2}), Arrays.toString(Labyrinthe.getSuivant(1, 2, Labyrinthe.DROITE)));

    }

    @Test
    public void test_deplacerPerso(){
        try {
            Labyrinthe laby = new Labyrinthe("./labySimple/labyTest.txt");
            laby.deplacerPerso(Labyrinthe.HAUT);
            assertEquals(1, Labyrinthe.pj.getX());
            assertEquals(1, Labyrinthe.pj.getY());
            laby.deplacerPerso(Labyrinthe.BAS);
            assertEquals(1, Labyrinthe.pj.getX());
            assertEquals(2, Labyrinthe.pj.getY());
            laby.deplacerPerso(Labyrinthe.GAUCHE);
            assertEquals(1, Labyrinthe.pj.getX());
            assertEquals(2, Labyrinthe.pj.getY());
            laby.deplacerPerso(Labyrinthe.DROITE);
            assertEquals(2, Labyrinthe.pj.getX());
            assertEquals(2, Labyrinthe.pj.getY());
        }catch(IOException e){
            fail();
        }
    }

    @Test
    public void lanceFleche(){
        try {
            Labyrinthe laby = new Labyrinthe("./labySimple/labyBase.txt");
            laby.pj.lancerFleche();
            assertEquals(4, laby.deplacables.size());
            laby.pj.setDirection(Labyrinthe.HAUT);
            laby.pj.lancerFleche();
            assertEquals(5, laby.deplacables.size());
            laby.deplacerPerso(Labyrinthe.HAUT);
            assertEquals(4, laby.deplacables.size());
        }catch(IOException e){
            fail();
        }
    }

}
