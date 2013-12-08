import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents a Super Bad Guy who is trying to enter the
 * player's castle.
 * @author Eric Cochran
 */
public class SuperBadGuy extends BadGuy {
    private static final int SUPER_HEALTH = (int) (10000f * 1.5f);
    private static final int SUPER_SPEED = 1 * 5;
    private static final int SUPER_VALUE = 150 / 10;
    private static final Color SUPER_FULL_COLOR = new Color(241, 196, 15, 255);
    private static final Color SUPER_COLOR_0 = new Color(241, 137, 15, 255);
    private static final Color SUPER_COLOR_1 = new Color(75, 137, 220, 255);
    private static final Color SUPER_COLOR_2 = new Color(75, 137, 15, 255);
    private static final Color SUPER_COLOR_DYING = new Color(45, 82, 49, 255);

    /**
     * Creates the super bad guy.
     *
     * @param game the game that the super bad guy exists in
     */
    public SuperBadGuy(Game game) {
        super(game);
        health = SUPER_HEALTH;
        speed = SUPER_SPEED;
        value = SUPER_VALUE;
        color = SUPER_FULL_COLOR;
    }

    /**
     * Draws the super bad guy.
     * He changes color based on his health.
     *
     * @param g the Graphics object
     */
    @Override
    public void draw(Graphics g) {
        if (health >= SUPER_HEALTH * 9 / 10) {
            color = SUPER_FULL_COLOR;
        } else if (health >= SUPER_HEALTH * 7.5 / 10) {
            color = SUPER_COLOR_0;
        } else if (health >= SUPER_HEALTH * 5 / 10) {
            color = SUPER_COLOR_1;
        } else if (health >= SUPER_HEALTH * 2 / 10) {
            color = SUPER_COLOR_2;
        } else {
            color = SUPER_COLOR_DYING;
        }
        super.draw(g);
    }
}
