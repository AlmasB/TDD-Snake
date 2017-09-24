package com.almasb.snake;

import javafx.geometry.Point2D;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class SnakeTest {

    @Test
    public void testSnakeMoves() {
        Snake snake = new Snake(new Point2D(0, 0));

        for (Direction direction : Direction.values()) {
            Point2D oldPosition = snake.getPosition();

            snake.setDirection(direction);

            snake.update();

            assertThat(snake.getPosition(), is(oldPosition.add(direction.vector)));
        }
    }

    @Test
    public void testSnakeFoodCollision() {
        Snake snake = new Snake(new Point2D(10, 5));

        Food food = new Food(new Point2D(10, 5));

        assertTrue(snake.isCollidingWith(food));
    }

    @Test
    public void testSnakeGrows() {
        Snake snake = new Snake(new Point2D(0, 0));

        snake.setDirection(Direction.RIGHT);
        snake.update();
        snake.grow();

        assertThat(snake.getLength(), is(2));
        assertThat(snake.getBody(), hasItem(new Point2D(0, 0)));
    }

    @Test
    public void testSnakeOutOfBounds() {
        Snake snake = new Snake(new Point2D(25, 0));

        assertTrue(snake.isOutOfBounds(24));
        assertFalse(snake.isOutOfBounds(25));
    }

    @Test
    public void testSnakeDies() {
        Snake snake = new Snake(new Point2D(0, 0));

        for (int i = 0; i < 5; i++) {
            snake.setDirection(Direction.RIGHT);
            snake.update();
            snake.grow();
        }

        snake.setDirection(Direction.UP);
        snake.update();

        snake.setDirection(Direction.LEFT);
        snake.update();

        snake.setDirection(Direction.DOWN);
        snake.update();

        assertTrue(snake.isDead());
    }

    @Test
    public void testHeadIsInFront() {
        Snake snake = new Snake(new Point2D(0, 0));

        snake.setDirection(Direction.RIGHT);
        snake.update();
        snake.grow();

        snake.update();

        assertThat(snake.getBody().get(0), is(snake.getPosition()));
    }
}
