package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

class Cat extends MovingObject {
    public static BufferedImage image;

    public static final int FIXED_X = 200;
    public static final int CAT_WIDTH = 261;
    public static final int CAT_HEIGHT = 100;

    public static int speed = 5;
    private static final Color COLOR = new Color(223, 222, 110);

    public Cat(int y) {
        super(FIXED_X, y, CAT_WIDTH, CAT_HEIGHT);
        try {
            image = ImageIO.read(new File("data/flyingcat.jpg"));
        } catch (IOException e) {
            //
        }
    }

    @Override
    public void move(int direction) {
        y += direction * speed;
    }

    @Override
    public void draw(Graphics g, ImageObserver o) {
        Color savedCol = g.getColor();
        g.setColor(COLOR);
        g.fillRect(getX() - CAT_WIDTH / 2, getY() - CAT_HEIGHT / 2, CAT_WIDTH, CAT_HEIGHT);
        g.setColor(savedCol);
        g.drawImage(image, getX() - CAT_WIDTH / 2, getY() - CAT_HEIGHT / 2, o);
    }
}