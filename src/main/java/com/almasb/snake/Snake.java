package com.almasb.snake;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class Snake {

    private Direction direction = Direction.RIGHT;
    private Point2D head;
    private Point2D previousTail;

    private List<Point2D> body = new ArrayList<>();

    public Snake(Point2D head) {
        this.head = head;
        this.previousTail = head;
        body.add(head);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void update() {
        head = head.add(direction.vector);

        previousTail = body.remove(body.size() - 1);
        body.add(0, head);
    }

    public Point2D getPosition() {
        return head;
    }

    public boolean isCollidingWith(Food food) {
        return head.equals(food.getPosition());
    }

    public void grow() {
        body.add(previousTail);
    }

    public int getLength() {
        return body.size();
    }

    public List<Point2D> getBody() {
        return body;
    }

    public boolean isOutOfBounds(int size) {
        return head.getX() < 0 || head.getY() < 0
                || head.getX() > size || head.getY() > size;
    }

    public boolean isDead() {
        return body.lastIndexOf(head) > 0;
    }
}
