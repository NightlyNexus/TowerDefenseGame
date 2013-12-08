import javax.swing.JFrame;
import java.awt.BorderLayout;

/**
 * The main driver class for starting the Tower Defense Game.
 * @author Eric Cochran
 */
public class TowerDefenseGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tower Defense");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ControlPanel control = new ControlPanel();
        frame.add(control, BorderLayout.WEST);
        //Game game = new Game(control);
        Game game = control.getGame();
        frame.add(game); //defaults to CENTER
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        //game.sendRound();
    }
}
