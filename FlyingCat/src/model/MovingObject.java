package model;

import java.awt.*;
import java.awt.image.ImageObserver;

public abstract class MovingObject {
    // location
    protected int x;
    protected int y;

    // size
    protected int width;
    protected int height;

    public boolean added;

    public MovingObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        added = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public abstract void move(int direction);

    public abstract void draw(Graphics g, ImageObserver o);

    public boolean collidedWith(Cat cat) {
        Rectangle thisBoundingRect = new Rectangle(getX(), getY(), getWidth(), getHeight());
        Rectangle otherBoundingRect = new Rectangle(cat.getX() - cat.getWidth() / 2 + 108, cat.getY() - cat.getHeight() / 2,
                cat.getWidth() - 108, cat.getHeight());
        return thisBoundingRect.intersects(otherBoundingRect);
    }
}
