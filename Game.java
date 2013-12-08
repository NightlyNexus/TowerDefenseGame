import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This is the main logic for the Tower Defense Game.
 *
 * @author Eric Cochran
 * @version 1.0
 */
public class Game extends JPanel {
    static final int WIDTH = 1000, HEIGHT = 600;
    private static final int DEFAULT_STEPS = 1000;
    private static final int DEFAULT_STARTING_MONEY = 10 * 1;
    private static final int SUPER_FREQUENCY = 10;
    private int stepsToEnd;
    private int money;
    private int health;
    private int round;
    private Timer timer;
    private List<BadGuy> guysToDraw;
    private List<BadGuy> fireDotsToDraw;
    private List<Tower> towersToDraw;
    private ControlPanel cp;

    /**
     * Creates the default game.
     *
     * @param cp the control panel that the game alters
     */
    public Game(ControlPanel cp) {
        this(cp, DEFAULT_STEPS, DEFAULT_STARTING_MONEY);
    }

    /**
     * Creates the game with a custom x value to the end
     * and a custom starting amount of money.
     *
     * @param cp the control panel that the game alters
     * @param stepsToEnd the custom x value to the end
     * @param startingMoney the custom amount of money
     *        the player starts with
     */
    public Game(ControlPanel cp, int stepsToEnd, int startingMoney) {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.cp = cp;
        this.stepsToEnd = stepsToEnd;
        money = startingMoney;
        health = 20;
        round = 0;
        cp.setHealth(health);
        cp.setMoney(money);
        towersToDraw = new ArrayList<Tower>();
        fireDotsToDraw = new ArrayList<BadGuy>();
        addMouseListener(new ClickListener());
    }

    /**
     * Gets the steps to the end (x value).
     *
     * @return the steps to the end (x value)
     */
    public int getStepsToEnd() {
        return stepsToEnd;
    }

    /**
     * Adds money to the player's spending money.
     * This happens typically when defeating a bad guy.
     *
     * @param moreMoney the amount of money to add
     */
    public void addMoney(int moreMoney) {
        money += moreMoney;
        cp.setMoney(money);
    }

    /**
     * Subtracts money from the player's spending money.
     * This happens typically when a tower is built.
     *
     * @param lessMoney the amount of money spent
     */
    public void spendMoney(int lessMoney) {
        money -= lessMoney;
        cp.setMoney(money);
    }

    /**
     * Subtracts from the player's health one point.
     * Also, sets the updated health on the control panel
     * and checks for losing.
     */
    public void takeAHit() {
        health--;
        cp.setHealth(health);
        if (health <= 0) {
            lose();
        }
    }

    private void lose() {
        timer.stop();
        repaint();
    }

    /**
     * Sends a new round of bad guys.
     */
    public void sendRound() {
        cp.disableButton();
        List<BadGuy> list;
        if ((round + 1) % SUPER_FREQUENCY == 0 && round != 0) {
            list = sendSuperRound();
        } else {
            switch (round) {
                case 0: list = sendRound0(); break;
                case 1: list = sendRound1(); break;
                case 2: list = sendRound2(); break;
                case 3: list = sendRound3(); break;
                case 4: list = sendRound4(); break;
                default: list = sendDefaultRound(); break;
            }
        }
        timer = new Timer(500, new TimerListener(list));
        timer.start();
        round++;
    }

    private List<BadGuy> sendRound0() {
        int numGreenGuys = 10;
        List<BadGuy> list = new ArrayList<BadGuy>(numGreenGuys);
        for (int i = 0; i < numGreenGuys; i++) {
            list.add(new GreenBadGuy(this));
        }
        return list;
    }

    private List<BadGuy> sendRound1() {
        int numGreenGuys = 5;
        int numBlueGuys = 10;
        List<BadGuy> list = new ArrayList<BadGuy>(numGreenGuys + numBlueGuys);
        for (int i = 0; i < numGreenGuys; i++) {
            list.add(new GreenBadGuy(this));
        }
        for (int i = 0; i < numBlueGuys; i++) {
            list.add(new BlueBadGuy(this));
        }
        return list;
    }

    private List<BadGuy> sendRound2() {
        int numGreenGuys = 5;
        int numRedGuys = 10;
        List<BadGuy> list = new ArrayList<BadGuy>(numGreenGuys + numRedGuys);
        for (int i = 0; i < numGreenGuys; i++) {
            list.add(new GreenBadGuy(this));
        }
        for (int i = 0; i < numRedGuys; i++) {
            list.add(new RedBadGuy(this));
        }
        return list;
    }

    private List<BadGuy> sendRound3() {
        int numPurpleGuys = 10;
        List<BadGuy> list = new ArrayList<BadGuy>(numPurpleGuys);
        for (int i = 0; i < numPurpleGuys; i++) {
            list.add(new PurpleBadGuy(this));
        }
        return list;
    }

    private List<BadGuy> sendRound4() {
        int numGreenGuys = 10;
        int numBlueGuys = 10;
        int numRedGuys = 10;
        List<BadGuy> list = new ArrayList<BadGuy>(
                numGreenGuys + numBlueGuys + numRedGuys);
        for (int i = 0; i < numGreenGuys; i++) {
            list.add(new GreenBadGuy(this));
        }
        for (int i = 0; i < numBlueGuys; i++) {
            list.add(new BlueBadGuy(this));
        }
        for (int i = 0; i < numRedGuys; i++) {
            list.add(new RedBadGuy(this));
        }
        return list;
    }

    private List<BadGuy> sendDefaultRound() {
        int numGreenGuys = 10;
        int numBlueGuys = 10;
        int numRedGuys = 10;
        int numPurpleGuys = 10 + (round - 5) * 5;
        List<BadGuy> list = new ArrayList<BadGuy>(
                numGreenGuys + numBlueGuys + numRedGuys + numPurpleGuys);
        for (int i = 0; i < numGreenGuys; i++) {
            list.add(new GreenBadGuy(this));
        }
        for (int i = 0; i < numPurpleGuys; i++) {
            list.add(new PurpleBadGuy(this));
        }
        for (int i = 0; i < numBlueGuys; i++) {
            list.add(new BlueBadGuy(this));
        }
        for (int i = 0; i < numRedGuys; i++) {
            list.add(new RedBadGuy(this));
        }
        return list;
    }

    private List<BadGuy> sendSuperRound() {
        int numSuperGuys = (round + 1) / SUPER_FREQUENCY;
        List<BadGuy> list = new ArrayList<BadGuy>(numSuperGuys);
        for (int i = 0; i < numSuperGuys; i++) {
            list.add(new SuperBadGuy(this));
        }
        return list;
    }

    private class TimerListener implements ActionListener {
        private List<BadGuy> list;

        public TimerListener(List<BadGuy> list) {
            this.list = list;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int size = list.size();
            int i = 0;
            boolean found0 = false;
            Set<BadGuy> removeThese = new HashSet<BadGuy>();
            while (i < size && !found0) {
                BadGuy bg = list.get(i);
                found0 = bg.isAtStart();
                if (!bg.isAlive()) {
                    removeThese.add(bg);
                } else {
                    //we move forward just the ones who are
                    //not on start +1 (who is on start)
                    //don't move forward if not alive
                    bg.moveForward();
                }
                //TODONE: check for FIRE
                /*if (!bg.isAlive()) {
                    removeThese.add(bg);
                }*/
                i++;
            }
            list.removeAll(removeThese);
            /*Set<BadGuy> removeThese = new HashSet<BadGuy>;
            for (BadGuy bg : list) {
                bg.moveForward();
                if (!bg.isAlive) {
                    removeThese.add(bg);
                }
            }
            list.removeAll(removeThese);*/
            guysToDraw = list;
            fireAttack();
            repaint();
            if (list.isEmpty()) {
                timer.stop();
                cp.enableButton();
            }
        }
    }

    private void fireAttack() {
        for (Tower t : towersToDraw) {
            for (BadGuy bg : guysToDraw) {
                if (t.tryToAttack(bg) && bg.isAlive()) {
                    fireDotsToDraw.add(bg);
                    break;
                } else {
                    fireDotsToDraw.remove(bg);
                }
            }
        }
    }

    private class ClickListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            Point p = e.getPoint();
            if (!isTowerPlacementOkay(p.x, p.y)) {
                return;
            }
            switch (cp.getSelectedTower()) {
                case ControlPanel.TURQUOISE_TOWER:
                        addTurquoiseTower(p.x, p.y); break;
                case ControlPanel.ORANGE_TOWER:
                        addOrangeTower(p.x, p.y); break;
                case ControlPanel.DARK_MASTER_TOWER:
                        addDarkMasterTower(p.x, p.y); break;
                default: return;
            }
            repaint();
        }
    }

    private void addTurquoiseTower(int x, int y) {
        if (money < TurquoiseTower.TURQUOISE_COST) {
            return;
        }
        towersToDraw.add(new TurquoiseTower(this, x, y));
    }

    private void addOrangeTower(int x, int y) {
        if (money < OrangeTower.ORANGE_COST) {
            return;
        }
        towersToDraw.add(new OrangeTower(this, x, y));
    }

    private void addDarkMasterTower(int x, int y) {
        if (money < DarkMasterTower.DARK_MASTER_COST) {
            return;
        }
        towersToDraw.add(new DarkMasterTower(this, x, y));
    }

    private boolean isTowerPlacementOkay(int x, int y) {
        if (towersToDraw != null) {
            for (Tower t : towersToDraw) {
                if (Math.abs(x - t.getX()) <= 3 / 2
                        * Tower.WIDTH && Math.abs(y - t.getY())
                        <= 3 / 2 * Tower.HEIGHT) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Draws the game.
     *
     * @param g the Graphics object
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //Call to the super constructor to make sure
        //all of JPanel's paintComponent stuff is called first
        //if lost
        if (health <= 0) {
            g.setFont(new Font("Century Gothic", Font.PLAIN, 48));
            g.setColor(Color.RED);
            g.drawString("You Lose", Game.WIDTH / 2 - 100,
                    Game.HEIGHT / 2 - 100);
        }
        //draw round number
        g.setFont(new Font("Century Gothic", Font.PLAIN, 48));
        g.drawString("Round: " + round, Game.WIDTH / 2 - 100, 50);
        //draw lines
        g.drawLine(stepsToEnd, HEIGHT / 2, stepsToEnd
                - stepsToEnd / 20, HEIGHT / 2);
        g.drawLine(stepsToEnd - stepsToEnd / 20, HEIGHT / 2,
                stepsToEnd - stepsToEnd * 7 / 20,
                HEIGHT / 2 - stepsToEnd * 6 / 20);
        g.drawLine(stepsToEnd - stepsToEnd * 7 / 20,
                HEIGHT / 2 - stepsToEnd * 7 / 20,
                stepsToEnd - stepsToEnd * 7 / 20, HEIGHT
                / 2 - stepsToEnd * 6 / 20 + 400);
        g.drawLine(stepsToEnd - stepsToEnd * 7 / 20, HEIGHT / 2
                - stepsToEnd * 6 / 20 + 400, stepsToEnd - stepsToEnd
                * 12 / 20, HEIGHT / 2 - stepsToEnd * 6 / 20 + 400);
        g.drawLine(stepsToEnd - stepsToEnd * 12 / 20,
                HEIGHT / 2 - stepsToEnd * 6 / 20 + 400,
                stepsToEnd - stepsToEnd * 12 / 20, HEIGHT / 2
                - stepsToEnd * 6 / 20 + 300);
        g.drawLine(stepsToEnd - stepsToEnd * 12 / 20,
                HEIGHT / 2 - stepsToEnd * 6 / 20 + 300,
                stepsToEnd - stepsToEnd * 15 / 20, HEIGHT / 2
                - stepsToEnd * 6 / 20 + 300);
        g.drawLine(stepsToEnd - stepsToEnd * 15 / 20,
                HEIGHT / 2 - stepsToEnd * 6 / 20 + 300,
                stepsToEnd - stepsToEnd * 15 / 20, HEIGHT / 2
                - stepsToEnd * 6 / 20 + 600);
        g.drawLine(stepsToEnd - stepsToEnd * 15 / 20,
                HEIGHT / 2 - stepsToEnd * 6 / 20 + 600,
                0, HEIGHT / 2 - stepsToEnd * 6 / 20 + 600);

        if (towersToDraw != null) {
            for (Tower t : towersToDraw) {
                t.draw(g);
            }
        }
        if (guysToDraw != null) {
            for (BadGuy bg : guysToDraw) {
                bg.draw(g);
            }
        }

        if (fireDotsToDraw != null) {
            g.setColor(new Color(190, 195, 199, 191));
            for (BadGuy bg : fireDotsToDraw) {
                g.fillOval(bg.getX(), bg.getY(),
                BadGuy.WIDTH / 2, BadGuy.HEIGHT / 2);
            }
        }
    }
}
