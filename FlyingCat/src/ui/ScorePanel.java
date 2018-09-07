package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ScorePanel extends JPanel implements Observer {

    private static final String HUDDLE_TXT = "Huddles passed: ";
    private static final String CURRENT_LEVEL = "Current level: ";
    private static final int LABEL_WIDTH = 200;
    private static final int LABEL_HEIGHT = 30;

    private JLabel huddleL;
    private JLabel levelL;

    public ScorePanel() {
        setBackground(new Color(180, 180, 180));
        huddleL = new JLabel(HUDDLE_TXT + 0);
        huddleL.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        levelL = new JLabel((CURRENT_LEVEL + 0));
        levelL.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        add(huddleL);
        add(Box.createHorizontalStrut(10));
        add(levelL);
    }

    @Override
    public void update(Observable o, Object arg) {
        Game game = (Game) o;
        huddleL.setText(HUDDLE_TXT + game.getHuddlesPassed() / 2);
        levelL.setText(CURRENT_LEVEL + game.getLevel());
        repaint();
    }
}
