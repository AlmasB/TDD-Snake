package com.almasb.snake;

import javafx.geometry.Point2D;

import java.util.Random;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class Game {

    private final int size;

    private Snake snake;
    private Food food;

    private boolean isOver = false;

    private Random random = new Random(123);

    public Game(int size) {
        this.size = size;

        snake = new Snake(new Point2D(size / 2, size / 2));
        food = new Food(getRandomPosition());
    }

    public void update() {
        snake.update();

        if (snake.isCollidingWith(food)) {
            snake.grow();
            food.setPosition(getRandomPosition());
        }

        if (snake.isDead() || snake.isOutOfBounds(size)) {
            isOver = true;
        }
    }

    public boolean isOver() {
        return isOver;
    }

    private Point2D getRandomPosition() {
        return new Point2D(random.nextInt(size), random.nextInt(size));
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

    public void setDirection(Direction direction) {
        snake.setDirection(direction);
    }
}
