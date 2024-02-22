import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
 
public class Ubicacion extends Thread
{
   public static void main(String args[]) throws InterruptedException, AWTException
   {
      Point p;
      Robot robot = new Robot();
      while (true)
      {
         //Para no consumir toda la CPU
         sleep(1); 
         //Recupera la posición del ratón
         p = MouseInfo.getPointerInfo().getLocation(); 
         if (p.x > Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 + 100)
            //Mueve el ratón a la posición deseada
            robot.mouseMove((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 + 100, p.y); 
 
         if (p.y > Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 + 100)
            robot.mouseMove(p.x, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 + 100);
 
         if (p.x < Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 100)
            robot.mouseMove((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 100, p.y);
 
         if (p.y < Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - 100)
            robot.mouseMove(p.x, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - 100);
      }
   }
}