import java.awt.Color;

/**
 * Represents a turquoise Tower that shoots at Bad Guys.
 * @author Eric Cochran
 */
public class TurquoiseTower extends Tower {
    private static final int TURQUOISE_ATTACK_POWER = 5 * 5;
    private static final int TURQUOISE_RADIUS_RANGE = 15;
    private static final Color TURQUOISE_COLOR = new Color(23, 160, 134, 255);
    public static final int TURQUOISE_COST = 2 * 1;
    protected int cost;

    /**
     * Creates the turquoise tower.
     *
     * @param game the game that the turquoise tower exists in
     * @param x the x position of the turquoise tower
     * @param y the y position of the turquoise tower
     */
    public TurquoiseTower(Game game, int x, int y) {
        super(game, x, y);
        attackPower = TURQUOISE_ATTACK_POWER;
        radiusRange = TURQUOISE_RADIUS_RANGE;
        color = TURQUOISE_COLOR;
        cost = TURQUOISE_COST;
        game.spendMoney(cost);
    }
}
