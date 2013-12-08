import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents a Tower that shoots at Bad Guys.
 * @author Eric Cochran
 */
public abstract class Tower {
    public static final int WIDTH = 10, HEIGHT = 10;
    protected int attackPower;
    protected int radiusRange;
    protected Color color;
    protected int cost;
    private int x;
    private int y;
    private int cornerX;
    private int cornerY;

    /**
     * Creates the tower.
     *
     * @param game the game that the tower exists in
     * @param x the x position of the tower
     * @param y the y position of the tower
     */
    public Tower(Game game, int x, int y) {
        this.x = x;
        this.y = y;
        //set the square's top left corner
        cornerX = x - WIDTH / 2;
        cornerY = y - HEIGHT / 2;
        //when overriding this, don't forget to call
        //super AFTER setting the cost!!
        //game.spendMoney(cost);
    }

    /**
     * Attempts to shoot at the bad guys.
     * The tower will hit a bad guy that is in range.
     * Note that a tower can only shoot at one bad guy
     * at a time.
     *
     * @param bg the bad guy to attampt to shoot at
     * @return true if a bad guy was shot, false if the
     *         tower had to sit idly by
     */
    public boolean tryToAttack(BadGuy bg) {
        double distance = Math.sqrt(Math.pow(bg.getX() - x, 2)
                + Math.pow(bg.getY() - y, 2));
        if (distance <= radiusRange) {
            bg.takeFire(attackPower);
            return true;
        }
        return false;
    }

    /**
     * Gets the x position of the tower.
     *
     * @return the x position of the tower
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y position of the tower.
     *
     * @return the y position of the tower
     */
    public int getY() {
        return y;
    }

    /**
     * Draws the tower.
     *
     * @param g the Graphics object
     */
    public void draw(Graphics g) {
        //draw square
        g.setColor(color);
        g.fillRect(cornerX, cornerY, WIDTH, HEIGHT);
        //draw radius of attack
        g.setColor(new Color(126, 140, 140, 122));
        g.fillOval(x - radiusRange, y - radiusRange,
                radiusRange * 2, radiusRange * 2);
    }
}
