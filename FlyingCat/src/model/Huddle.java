package model;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Huddle extends MovingObject {

    public static final int HUDDLE_WIDTH = 40;
    public static int speed = 7;

    private static final Color COLOR = new Color(255, 255, 255);

    public Huddle(int y, int height) {
        super(Game.WIDTH + HUDDLE_WIDTH, y, HUDDLE_WIDTH, height);
    }

    @Override
    public void move(int direction) {
        x -= speed;
    }

    @Override
    public void draw(Graphics g, ImageObserver o) {
        Color savedCol = g.getColor();
        g.setColor(COLOR);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        g.setColor(savedCol);
    }

}
