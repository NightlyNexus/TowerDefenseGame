import java.awt.Color;

/**
 * Represents a Dark Master Tower that shoots at Bad Guys.
 * @author Eric Cochran
 */
public class DarkMasterTower extends Tower {
    private static final int DARK_MASTER_ATTACK_POWER = 15 * 5;
    private static final int DARK_MASTER_RADIUS_RANGE = 20;
    private static final Color DARK_MASTER_COLOR = new Color(45, 62, 80, 255);
    public static final int DARK_MASTER_COST = 10 * 1;
    protected int cost;

    /**
     * Creates the dark master tower.
     *
     * @param game the game that the dark master tower exists in
     * @param x the x position of the dark master tower
     * @param y the y position of the dark master tower
     */
    public DarkMasterTower(Game game, int x, int y) {
        super(game, x, y);
        attackPower = DARK_MASTER_ATTACK_POWER;
        radiusRange = DARK_MASTER_RADIUS_RANGE;
        color = DARK_MASTER_COLOR;
        cost = DARK_MASTER_COST;
        game.spendMoney(cost);
    }
}
