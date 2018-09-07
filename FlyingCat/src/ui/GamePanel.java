package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private static final String SELECT = "Select Difficulty!";
    private static final String LEVEL = "press: 1 | 2 | 3 | 4";
    private static final String OVER = "Game Over!";
    private static final String REPLAY = "Select level to replay";
    private Game game;
    private Color stringColor = new Color( 180, 180, 180);
    private Font font = new Font("Arial", 20, 20);

    public GamePanel(Game g) {
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setBackground(new Color(0, 72, 144));
        this.game = g;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!game.isDifficultySelect()) {
            selectDifficulty(g);
        } else {
            drawGame(g);
        }
        if (game.isOver()) {
            gameOver(g);
        }
    }

    private void drawGame(Graphics g) {
        game.draw(g, this);
    }

    private void selectDifficulty(Graphics g) {
        Color saved = g.getColor();
        g.setColor(stringColor);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        centreString(SELECT, g, fm, Game.HEIGHT / 2);
        centreString(LEVEL, g, fm, Game.HEIGHT / 2 + 50);
        g.setColor(saved);
    }

    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(stringColor);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        centreString(OVER, g, fm, Game.HEIGHT / 2);
        centreString(REPLAY, g, fm, Game.HEIGHT / 2 + 20);
        centreString(LEVEL, g, fm, Game.HEIGHT / 2 + 40);
        g.setColor(saved);
    }

    private void centreString(String str, Graphics g, FontMetrics fm, int yPos) {
        int width = fm.stringWidth(str);
        g.drawString(str, (Game.WIDTH - width) / 2, yPos);
    }

}
