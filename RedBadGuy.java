import java.awt.Color;

/**
 * Represents a red Bad Guy who is trying to enter the
 * player's castle.
 * @author Eric Cochran
 */
public class RedBadGuy extends BadGuy {
    //private static final int RED_HEALTH = 225 * 1.5;
    private static final int RED_HEALTH = (int) (250f * 1.5f);
    private static final int RED_SPEED = 2 * 5;
    //private static final int RED_VALUE = 25;
    private static final int RED_VALUE = 30 / 10;
    private static final Color RED_COLOR = new Color(193, 57, 45, 255);

    /**
     * Creates the red bad guy.
     *
     * @param game the game that the red bad guy exists in
     */
    public RedBadGuy(Game game) {
        super(game);
        health = RED_HEALTH;
        speed = RED_SPEED;
        value = RED_VALUE;
        color = RED_COLOR;
    }
}
