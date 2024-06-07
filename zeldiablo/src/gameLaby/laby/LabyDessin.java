package gameLaby.laby;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

public class LabyDessin implements DessinJeu {

   /**
    * Dessine l'etat du jeu
    *
    * @param jeu    jeu a afficher
    * @param canvas canvas dans lequel dessiner l'etat du jeu
    */
   @Override
   public void dessinerJeu(Jeu jeu, Canvas canvas) {

      double x;
      double y;
      double w;
      double h;

      Labyrinthe laby = ((LabyJeu) jeu).laby;
      GraphicsContext gc = canvas.getGraphicsContext2D();

      gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
      for (int l = 0; l < laby.getLengthY(); l++) {
         for (int c = 0; c < laby.getLengthX(); c++) {
            w = Math.ceil(canvas.getWidth() / laby.getLengthX());
            h = w;
            x = Math.ceil(w * c);
            y = Math.ceil(h * l);

            // Dessine les murs
            if (laby.getMur(c, l)) {
               gc.setFill(Color.BLACK);
               gc.fillRect(x, y, w, h);
            }

            // Dessine chaque entité
            if (Labyrinthe.entites != null) {
               for (Entite entite : Labyrinthe.entites) {
                  if (entite.etrePresent(c, l)) {
                     ImageView iv = getImageView(entite);
                     SnapshotParameters params = new SnapshotParameters();
                     params.setFill(Color.TRANSPARENT);
                     Image rotatedImage = iv.snapshot(params, null);
                     gc.drawImage(rotatedImage, x, y, w, h); // Dessine l'image
                     // Affiche la barre de vie
                     if (entite instanceof Vivant v) {
                        int healthSize = 7;
                        gc.setFill(Color.RED);
                        gc.fillRect(x, y - healthSize, w, healthSize);
                        gc.setFill(Color.GREEN);
                        gc.fillRect(x, y - healthSize, w * v.getPv() / v.getInitialPv(), healthSize);
                     }
                  }
               }
            }
         }
      }
   }

   /**
    * Renvoie l'ImageView correspondant à l'entité
    *
    * @param entite entité à afficher
    * @return ImageView correspondant à l'entité
    */
   private static ImageView getImageView(Entite entite) {

      // Si l'ImageView n'est pas encore initialisée, on la crée à partir de l'image correspondante
      if (entite.getImageView() == null) {
         Image image = new Image("file:images/" + entite.getClass().getSimpleName() + ".png");
         ImageView iv = new ImageView(image);
         entite.setImageView(iv);
      }

      // Récupère l'ImageView de l'entité
      ImageView iv = entite.getImageView();

      // Applique les transformations en fonction de la direction de l'entité
      if (entite instanceof Deplacable) {
         String direction = ((Deplacable) entite).getDirection();
         if (entite instanceof Fleche) {
            double rotate = switch (direction) {
               case Labyrinthe.DROITE -> 90;
               case Labyrinthe.GAUCHE -> 270;
               case Labyrinthe.BAS -> 180;
               default -> 0;
            };
            iv.setRotate(rotate); // Applique la rotation
         } else {
            if (Labyrinthe.GAUCHE.equals(direction)) {
               iv.setScaleX(-1); // Applique l'effet miroir
            } else if (Labyrinthe.DROITE.equals(direction)) {
               iv.setScaleX(1); // Réinitialise l'effet miroir
            }
         }
      }

      if (entite instanceof Escalier) {
         if (!((Escalier) entite).debloque) {
            iv.setOpacity(0.3);
         } else {
            iv.setOpacity(1);
         }
      }

      return iv;
   }
}
