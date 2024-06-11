package moteurJeu;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Gère la configuration des touches
 */
public class KeyConfig {

   /**
    * Fichier de configuration des touches
    */
   public static String CONFIG_FILE = "configTouches.txt";

   /**
    * Map des touches
    */
   private final Map<String, Integer> keyMap;


   /**
    * Constructeur de KeyConfig
    */
   public KeyConfig() {
      keyMap = new HashMap<>();
      loadKeyConfig();
   }


   /**
    * Charge la configuration des touches
    */
   private void loadKeyConfig() {
      Path configPath = Paths.get(CONFIG_FILE);
      if (Files.exists(configPath)) {
         try (BufferedReader reader = Files.newBufferedReader(configPath)) {
            String line;
            while ((line = reader.readLine()) != null) {
               String[] parts = line.split("=");
               if (parts.length == 2) {
                  keyMap.put(parts[0].trim(), getKeyCode(parts[1].trim()));
               }
            }
         } catch (IOException e) {
            e.printStackTrace();
         }
      } else {
         setDefaultKeys();
      }
   }


   /**
    * Renvoie le code de la touche
    *
    * @param keyName nom de la touche
    * @return code de la touche
    */
   private int getKeyCode(String keyName) {
      switch (keyName.toUpperCase()) {
         case "UP":
            return KeyEvent.VK_UP;
         case "DOWN":
            return KeyEvent.VK_DOWN;
         case "LEFT":
            return KeyEvent.VK_LEFT;
         case "RIGHT":
            return KeyEvent.VK_RIGHT;
         case "SPACE":
            return KeyEvent.VK_SPACE;
         default:
            return KeyEvent.getExtendedKeyCodeForChar(keyName.charAt(0));
      }
   }


   /**
    * Règle les touches par défaut
    */
   private void setDefaultKeys() {
      keyMap.put("UP", KeyEvent.VK_UP);
      keyMap.put("DOWN", KeyEvent.VK_DOWN);
      keyMap.put("LEFT", KeyEvent.VK_LEFT);
      keyMap.put("RIGHT", KeyEvent.VK_RIGHT);
      keyMap.put("SHOOT", KeyEvent.VK_SPACE);
   }


   /**
    * Renvoie le code de la touche associée à l'action
    *
    * @param action action
    * @return code de la touche
    */
   public int getKey(String action) {
      return keyMap.getOrDefault(action, -1);
   }
}