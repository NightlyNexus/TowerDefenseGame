import java.awt.Color;

/**
 * Represents a purple Bad Guy who is trying to enter the
 * player's castle.
 * @author Eric Cochran
 */
public class PurpleBadGuy extends BadGuy {
    private static final int PURPLE_HEALTH = (int) (350f * 1.5f);
    private static final int PURPLE_SPEED = 5 * 5;
    private static final int PURPLE_VALUE = 50 / 10;
    private static final Color PURPLE_COLOR = new Color(141, 68, 175, 255);

    /**
     * Creates the purple bad guy.
     *
     * @param game the game that the purple bad guy exists in
     */
    public PurpleBadGuy(Game game) {
        super(game);
        health = PURPLE_HEALTH;
        speed = PURPLE_SPEED;
        value = PURPLE_VALUE;
        color = PURPLE_COLOR;
    }
}
