package model;

import ui.FlyingCat;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.*;
import java.util.List;

public class Game extends Observable {

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 900;
    public static final Random RANDOM = new Random();

    public static int huddlesPassed = 0;
    private static int level = 0;

    private List<MovingObject> objects;
    private Cat cat;
    private boolean isGameOver;
    private boolean difficultySelect;
    private int direction = 1;

    private static final int NORMAL_CAT = 4;
    private static final int NORMAL_HUDDLE = 7;
    private static int gap = 250;
    private boolean stop = false;

    public Game() {
        objects = new ArrayList<>();

        reset();
        initiateObject();
    }

    /**
     *clears objects and add a cat
     */
    private void initiateObject() {
        objects.clear();
        cat = new Cat(HEIGHT/2);
        objects.add(cat);
        huddlesPassed = 0;
    }

    /**
     * resets huddle passed.
     * game is not over.
     */
    private void reset() {
        isGameOver = false;
        difficultySelect = false;
    }

    public boolean isOver() {
        return isGameOver;
    }

    public boolean isDifficultySelect() {
        return difficultySelect;
    }

    public void draw(Graphics g, ImageObserver o) {
        for (MovingObject movingObject: objects) {
            movingObject.draw(g, o);
        }
    }

    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_KP_UP || keyCode == KeyEvent.VK_UP) {
            direction = -1;
        } else if (keyCode == KeyEvent.VK_KP_DOWN || keyCode == KeyEvent.VK_DOWN) {
            direction = 1;
        } else if (keyCode == KeyEvent.VK_ESCAPE || keyCode == KeyEvent.VK_X) {
            System.exit(0);
        } else if (keyCode == KeyEvent.VK_1 || keyCode == KeyEvent.VK_NUMPAD1) {
            currentLevelSetting(1);
            reInitiate();
        } else if (keyCode == KeyEvent.VK_2 || keyCode == KeyEvent.VK_NUMPAD2) {
            currentLevelSetting(2);
            reInitiate();
        } else if (keyCode == KeyEvent.VK_3 || keyCode == KeyEvent.VK_NUMPAD3) {
            currentLevelSetting(3);
            reInitiate();
        } else if (keyCode == KeyEvent.VK_4 || keyCode == KeyEvent.VK_NUMPAD4) {
            currentLevelSetting(4);
            reInitiate();
        } else if (keyCode == KeyEvent.VK_SPACE) {
            if (!stop) {
                stop = true;
                Cat.speed = 0;
                Huddle.speed = 0;
            } else {
                stop = false;
                currentLevelSetting(level);
            }
        }
        setChanged();
        notifyObservers();
    }

    private void currentLevelSetting(int l) {
        if (l == 1) {
            Cat.speed = NORMAL_CAT;
            Huddle.speed = NORMAL_HUDDLE - 2;
            level = 1;
        } else if (l == 2) {
            Cat.speed = NORMAL_CAT;
            Huddle.speed = NORMAL_HUDDLE;
            level= 2;
        } else if (l == 3) {
            Cat.speed = NORMAL_CAT + 1;
            Huddle.speed = NORMAL_HUDDLE + 2;
            level = 3;
        } else if (l == 4) {
            Cat.speed = NORMAL_CAT + 2;
            Huddle.speed = NORMAL_HUDDLE + 3;
            level = 4;
        }
    }

    private void reInitiate() {
        reset();
        initiateObject();
        difficultySelect = true;
    }

    private void moveObjects() {
        Iterator<MovingObject> iterator = objects.iterator();

        while (iterator.hasNext()) {
            MovingObject mo = iterator.next();
            mo.move(direction);

            // optimize
            if (mo.getX() < -50) {
                iterator.remove();
            }
        }
    }

    private void displayObjects() {
        if (FlyingCat.coolTime > 400) { // minimum to avoid objects
            incomingObjects();
            FlyingCat.coolTime = 0;
        }
    }

    private void incomingObjects() {
        int heigh = 600;
        int temp = RANDOM.nextInt(heigh - gap);
        int anchor = RANDOM.nextInt(heigh - temp - gap);

        Huddle huddle_upper = new Huddle(0, temp);
        Huddle huddle_lower = new Huddle(600 - anchor, anchor);

        objects.add(huddle_upper);
        objects.add(huddle_lower);
    }

    private void checkCollisions() {
        for (MovingObject mo: objects) {
            if (mo instanceof Huddle) {
                if (mo.collidedWith(cat)) {
                    isGameOver = true;
                } else if (!mo.added && mo.getX() <= Cat.FIXED_X) {
                    huddlesPassed += 1;
                    mo.added = true;
                }
                setChanged();
                notifyObservers();
            }
        }
    }

    private void checkGameOver() {
        if (cat.getY() <= 0 || cat.getY() + cat.getHeight() >= HEIGHT) {
            isGameOver = true;
        }

        if (isGameOver) {
            initiateObject();
            reset();
        }
    }

    public int getLevel() {
        return level;
    }

    public int getHuddlesPassed() {
        return huddlesPassed;
    }

    public void update() {
        moveObjects();
        displayObjects();
        checkCollisions();
        checkGameOver();
    }
}
