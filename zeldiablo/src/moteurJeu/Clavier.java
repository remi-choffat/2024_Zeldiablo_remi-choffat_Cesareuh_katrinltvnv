package moteurJeu;

import javafx.scene.input.KeyEvent;

public class Clavier {

   /**
    * controle appuyes
    */
   public boolean haut, bas, gauche, droite, shoot;

   /**
    * Configuration des touches
    */
   private KeyConfig keyConfig;


   /**
    * Constructeur
    */
   public Clavier() {
      keyConfig = new KeyConfig();
   }

   /**
    * stocke les commandes
    *
    * @param event evenement clavier
    */
   public void appuyerTouche(KeyEvent event) {
      int code = event.getCode().getCode();

      if (code == keyConfig.getKey("DOWN")) {
         this.bas = true;
      } else if (code == keyConfig.getKey("UP")) {
         this.haut = true;
      } else if (code == keyConfig.getKey("LEFT")) {
         this.gauche = true;
      } else if (code == keyConfig.getKey("RIGHT")) {
         this.droite = true;
      } else if (code == keyConfig.getKey("SHOOT")) {
         this.shoot = true;
      }
   }

   /**
    * stocke les commandes
    *
    * @param event evenement clavier
    */
   public void relacherTouche(KeyEvent event) {
      int code = event.getCode().getCode();

      if (code == keyConfig.getKey("DOWN")) {
         this.bas = false;
      } else if (code == keyConfig.getKey("UP")) {
         this.haut = false;
      } else if (code == keyConfig.getKey("LEFT")) {
         this.gauche = false;
      } else if (code == keyConfig.getKey("RIGHT")) {
         this.droite = false;
      } else if (code == keyConfig.getKey("SHOOT")) {
         this.shoot = false;
      }
   }
}