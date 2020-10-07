package cegepst.engine;

import java.awt.*;
import java.util.Random;

public class Ball {

    private final int radius;
    private int x;
    private int y;
    private int velocityX;
    private int velocityY;

    public Ball(int radius) {
        this.radius = radius;
        x = getRandom(radius * 2, 800 - radius * 2);
        y = getRandom(radius * 2, 600 - radius * 2);
        velocityX = getRandom(0, 1) == 0 ? 2 : -2;
        velocityY = getRandom(0, 1) == 0 ? 2 : -2;
    }

    public void update() {
        x += velocityX;
        y += velocityY;
        if (hasTouchVerticalBound()) {
            velocityY *= -1;
        }
        if (hasTouchHorizontalBound()) {
            velocityX *= -1;
        }
    }

    public void draw(Graphics2D buffer) {
        buffer.setPaint(Color.red);
        buffer.fillOval(x, y, radius * 2, radius * 2);
    }

    public boolean hasTouchBound() {
        return hasTouchHorizontalBound() || hasTouchVerticalBound();
    }

    public int getRadius() {
        return radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private boolean hasTouchHorizontalBound() {
        return x <= radius || x >= 800 - radius;
    }

    private boolean hasTouchVerticalBound() {
        return y <= radius || y >= 600 - radius;
    }

    private int getRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
