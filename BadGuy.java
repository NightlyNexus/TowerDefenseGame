import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents a Bad Guy who is trying to enter the
 * player's castle.
 * @author Eric Cochran
 */
public abstract class BadGuy {
    public static final int WIDTH = 10, HEIGHT = 10;
    protected int health;
    protected Color color;
    protected int value;
    protected int speed;
    private Game game;
    private int position;
    private boolean isAlive;
    private int x;
    private int y;

    /**
     * Creates the bad guy.
     *
     * @param game the game that the bad guy exists in
     */
    public BadGuy(Game game) {
        this.game = game;
        position = 0;
        x = Game.WIDTH - WIDTH;
        y = Game.HEIGHT / 2;
        isAlive = true;
    }

    /**
     * Moves the bad guy forward,
     * taking in to account the bad guy's speed.
     */
    public void moveForward() {
        position += speed;
        /*if (position >= game.getStepsToEnd()) {
            enterCastle();
        }*/
        changePosition();
    }

    private void enterCastle() {
        game.takeAHit();
        isAlive = false;
    }

    /**
     * Decreases the health of the bad guy.
     * This happens typically when a tower is shooting
     * at the bad guy.
     *
     * @param attackPower the amount of health to lose
     */
    public void takeFire(int attackPower) {
        health -= attackPower;
        if (health <= 0) {
            die();
        }
    }

    private void die() {
        isAlive = false;
        game.addMoney(value);
    }

    /**
     * Gets whether or not the bad guy is alive.
     *
     * @return true if the bad guy is alive, false if not
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Gets whether or not the bad guy is at the
     * starting position.
     *
     * @return true if the bad is at the start, false if not
     */
    public boolean isAtStart() {
        return position == 0;
    }

    private void changePosition() {
        int stepsToEnd = game.getStepsToEnd();
        if (position <= stepsToEnd / 20) {
            x -= speed;
        } else if (position <= stepsToEnd * 7 / 20) {
            x -= speed;
            y -= speed;
        } else if (position <= stepsToEnd * 7 / 20 + 400) {
            y += speed;
        } else if (position <= stepsToEnd * 12 / 20 + 400) {
            x -= speed;
        } else if (position <= stepsToEnd * 12 / 20 + 500) {
            y -= speed;
        } else if (position <= stepsToEnd * 15 / 20 + 500) {
            x -= speed;
        } else if (position <= stepsToEnd * 15 / 20 + 800) {
            y += speed;
        } else if (position <= stepsToEnd * 20 / 20 + 800) {
            x -= speed;
        }
        if (x <= Game.WIDTH - stepsToEnd) {
            enterCastle();
        }
    }

    /**
     * Gets the x position of the bad guy.
     *
     * @return the x position of the bad guy
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y position of the bad guy.
     *
     * @return the y position of the bad guy
     */
    public int getY() {
        return y;
    }

    /**
     * Draws the bad guy.
     *
     * @param g the Graphics object
     */
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, WIDTH, HEIGHT);
    }
}
//////if health == 0, we need to increase game's money and undraw the badguy
