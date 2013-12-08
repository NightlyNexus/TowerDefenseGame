import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

/**
 * This is the ControlPanel for the Tower Defense Game. It allows the
 * user to start the next round, shows the score,
 * and allows the user to pick which towers he would like to add.
 *
 * @author Eric Cochran
 * @version 1.0
 */
public class ControlPanel extends JPanel {
    private static final int TYPES_OF_TOWERS = 3;
    public static final int TURQUOISE_TOWER = 0;
    public static final int ORANGE_TOWER = 1;
    public static final int DARK_MASTER_TOWER = 2;
    private static final String[] CHOICE_ITEMS = {"TURQUOISE", "ORANGE",
            "DARK MASTER"};
    private static final int TIME_BETWEEN_ROUNDS = 15;
    private static final int TIME_BETWEEN_ROUNDS_TICK = 1 * 1000;
    private JLabel tickTick;
    private JLabel currentMoney;
    private JLabel currentHealth;
    private JButton sendWave;
    private ButtonGroup group;
    private JRadioButton turquoise;
    private JRadioButton orange;
    private JRadioButton darkMaster;
    private Timer gameTimer;
    private int time;
    private boolean gameStarted;
    private Game game;
    private Sound s;

    /**
     * Creates the control panel.
     */
    public ControlPanel() {
        turquoise = new JRadioButton(CHOICE_ITEMS[0]
                + " ($" + TurquoiseTower.TURQUOISE_COST + ")");
        orange = new JRadioButton(CHOICE_ITEMS[1]
                + " ($" + OrangeTower.ORANGE_COST + ")");
        darkMaster = new JRadioButton(CHOICE_ITEMS[2]
                + " ($" + DarkMasterTower.DARK_MASTER_COST + ")");
        JRadioButton[] choiceButtons = {turquoise, orange, darkMaster};
        choiceButtons[0].setSelected(true);

        JPanel radioButtonMenuPanel = new JPanel();
        radioButtonMenuPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        group = new ButtonGroup();

        sendWave = new JButton("Send a New Wave");
        sendWave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.sendRound();
            }
        });
        radioButtonMenuPanel.add(sendWave, gbc);

        //timer
        gameStarted = false;
        tickTick = new JLabel();
        tickTick.setVisible(false);
        radioButtonMenuPanel.add(tickTick, gbc);

        currentMoney = new JLabel();
        currentHealth = new JLabel();
        radioButtonMenuPanel.add(currentHealth, gbc);
        radioButtonMenuPanel.add(currentMoney, gbc);

        for (JRadioButton radioButton : choiceButtons) {
            group.add(radioButton);
            radioButtonMenuPanel.add(radioButton, gbc);
        }

        //music button
        try {
            s = new Sound("music.wav");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        final JButton musicButton = new JButton("Play Music");
        musicButton.addActionListener(new ActionListener() {
            private boolean isPlaying = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (s == null) {
                    return;
                }
                if (isPlaying) {
                    s.stop();
                    musicButton.setText("Play Music");
                } else {
                    s.loop();
                    musicButton.setText("Stahp");
                }
                isPlaying = !isPlaying;
            }
        });
        radioButtonMenuPanel.add(musicButton, gbc);

        gameTimer = new Timer(TIME_BETWEEN_ROUNDS_TICK,
                new GameTimerListener());

        setBackground(new Color(243, 155, 19, 255));
        //add(currentHealth);
        //add(currentMoney);
        add(radioButtonMenuPanel);
        game = new Game(this);
    }

    /**
     * Sets the money shown in the control panel.
     *
     * @param money the money to be shown
     */
    public void setMoney(int money) {
        currentMoney.setText("$" + money);
    }

    /**
     * Sets the health shown in the control panel.
     *
     * @param health the health to be shown
     */
    public void setHealth(int health) {
        currentHealth.setText("Health: " + health);
    }

    /**
     * Disables the "Send Wave" button.
     */
    public void disableButton() {
        gameTimer.stop();
        time = 0;
        setTimerTime(time);
        sendWave.setEnabled(false);
    }

    /**
     * Enables the "Send Wave" button.
     */
    public void enableButton() {
        gameTimer.start();
        time = TIME_BETWEEN_ROUNDS;
        setTimerTime(time);
        sendWave.setEnabled(true);
        if (!gameStarted) {
            gameStarted = true;
            tickTick.setVisible(true);
        }
    }

    /**
     * Gets the tower that is selected in the radio buttons.
     *
     * @return the tower type selected
     */
    public int getSelectedTower() {
        Enumeration<AbstractButton> allRadioButton =
                group.getElements();
        while (allRadioButton.hasMoreElements()) {
            JRadioButton temp = (JRadioButton)
                    allRadioButton.nextElement();
            if (temp.isSelected()) {
                String name = temp.getText();
                return Arrays.asList(CHOICE_ITEMS).indexOf(
                        name.substring(0, name.indexOf(" (")));
            }
        }
        /*for (int i = 0; i < group.getButtonCount(); i++) {

        }*/
        return -1;
    }

    /**
     * Gets the game that is created in the control panel.
     *
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /*public class ButtonListener implements ActionListener {
        private String name;

        public ButtonListener(String className) {
            name = className;
        }

        public void actionPerformed(ActionEvent e) {
            fishType = name;
            current.setText(name);
        }
    }*/

    private class GameTimerListener implements ActionListener {
        public GameTimerListener() {
            time = TIME_BETWEEN_ROUNDS;
            setTimerTime(time);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            time--;
            setTimerTime(time);
            if (time <= 0) {
                game.sendRound();
            }
        }
    }

    private void setTimerTime(int i) {
        tickTick.setText("Time: " + i);
    }
}
