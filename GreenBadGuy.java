import java.awt.Color;

/**
 * Represents a green Bad Guy who is trying to enter the
 * player's castle.
 * @author Eric Cochran
 */
public class GreenBadGuy extends BadGuy {
    private static final int GREEN_HEALTH = (int) (100f * 1.5f);
    private static final int GREEN_SPEED = 1 * 5;
    private static final int GREEN_VALUE = 10 / 10;
    private static final Color GREEN_COLOR = new Color(39, 174, 97, 255);

    /**
     * Creates the green bad guy.
     *
     * @param game the game that the green bad guy exists in
     */
    public GreenBadGuy(Game game) {
        super(game);
        health = GREEN_HEALTH;
        speed = GREEN_SPEED;
        value = GREEN_VALUE;
        color = GREEN_COLOR;
    }
}
