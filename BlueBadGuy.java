import java.awt.Color;

/**
 * Represents a blue Bad Guy who is trying to enter the
 * player's castle.
 * @author Eric Cochran
 */
public class BlueBadGuy extends BadGuy {
    private static final int BLUE_HEALTH = (int) (150f * 1.5f);
    private static final int BLUE_SPEED = 4 * 5;
    private static final int BLUE_VALUE = 20 / 10;
    private static final Color BLUE_COLOR = new Color(41, 127, 184, 255);

    /**
     * Creates the blue bad guy.
     *
     * @param game the game that the blue bad guy exists in
     */
    public BlueBadGuy(Game game) {
        super(game);
        health = BLUE_HEALTH;
        speed = BLUE_SPEED;
        value = BLUE_VALUE;
        color = BLUE_COLOR;
    }
}
