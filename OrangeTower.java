import java.awt.Color;

/**
 * Represents an orange Tower that shoots at Bad Guys.
 * @author Eric Cochran
 */
public class OrangeTower extends Tower {
    private static final int ORANGE_ATTACK_POWER = 10 * 5;
    private static final int ORANGE_RADIUS_RANGE = 10;
    private static final Color ORANGE_COLOR = new Color(212, 83, 0, 255);
    public static final int ORANGE_COST = 5 * 1;
    protected int cost;

    /**
     * Creates the orange tower.
     *
     * @param game the game that the orange tower exists in
     * @param x the x position of the orange tower
     * @param y the y position of the orange tower
     */
    public OrangeTower(Game game, int x, int y) {
        super(game, x, y);
        attackPower = ORANGE_ATTACK_POWER;
        radiusRange = ORANGE_RADIUS_RANGE;
        color = ORANGE_COLOR;
        cost = ORANGE_COST;
        game.spendMoney(cost);
    }
}
