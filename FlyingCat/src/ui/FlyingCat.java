package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FlyingCat extends JFrame {

    public static final int INTERVAL = 9;
    public static int coolTime = 0;
    private Game game;
    private GamePanel gp;
    public static ScorePanel sp;
    private Timer t;

    public FlyingCat() {
        super("Flying Cat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        game = new Game();
        gp = new GamePanel(game);
        sp = new ScorePanel();

        add(gp);
        add(sp, BorderLayout.NORTH);
        addKeyListener(new KeyHandler());
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
        t.start();
        game.addObserver(sp);
    }

    private void addTimer() {
        t = new Timer(INTERVAL, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                game.update();
                gp.repaint();
                coolTime += INTERVAL;
            }
        });
    }

    private void centreOnScreen() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width - getWidth()) / 2, (dim.height - getHeight()) / 2);
    }


    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            game.keyPressed(e.getKeyCode());
        }
    }

    public static void main(String[] args) {
        new FlyingCat();
    }
}
