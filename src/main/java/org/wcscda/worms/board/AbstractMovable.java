package org.wcscda.worms.board;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.stream.Stream;

public abstract class AbstractMovable extends AbstractBoardElement implements IVisitable {
    /* Speed is in pixel by clock iteration */
    private double speed = 0.0;
    /* In radian */
    private double direction = 0.0;

    public static Stream<AbstractMovable> getAllMovable() {
        return AbstractDrawableElement.getAllDrawable().stream()
            .filter(ade -> ade instanceof AbstractMovable)
            .map(AbstractMovable.class::cast);
    }

    public double getSpeed() {
        return speed;
    }

    public double getSpeedX() {
        return speed * Math.cos(direction);
    }

    public double getSpeedY() {
        return speed * Math.sin(direction);
    }

    public void addSpeedXY(double speedXdelta, double speedYdelta) {
        double newSpeedX = speedXdelta + getSpeedX();
        double newSpeedY = speedYdelta + getSpeedY();
        setSpeedXY(newSpeedX, newSpeedY);
    }

    public void setSpeedXY(double speedX, double speedY) {
        double newSpeed = Math.sqrt(Math.pow(speedX, 2) + Math.pow(speedY, 2));
        if (newSpeed < 0.05) {
            setSpeed(0);
            return;
        }
        setSpeed(newSpeed);
        if (speedY >= 0) {
            direction = Math.acos(speedX / getSpeed());
        } else {
            direction = -Math.acos(speedX / getSpeed());
        }
    }

    public void setSpeedY(double speedY) {
        setSpeedXY(getSpeedX(), speedY);
    }

    public boolean isMoving() {
        return getSpeed() > 1e-6;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public abstract void rawMove(double x, double y);

    public void singleMove(IMovableVisitor visitor, double x, double y) {
        Point2D currentPosition = getPosition();
        rawMove(x, y);
        accept(currentPosition, visitor);
    }

    public void move(IMovableVisitor visitor) {
        singleMove(visitor, getSpeedX(), getSpeedY());
    }

    public Point2D getPosition() {
        return new Point2D.Double(getX(), getY());
    }

    public abstract int getX();

    public abstract int getY();

    public void setPosition(Point2D position) {
        rawMove(position.getX() - getX(), position.getY() - getY());
    }

    public void accept(Point2D prevPosition, IMovableVisitor visitor) {
        visitor.visit(this, prevPosition);
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean isSubjectToGravity() {
        return false;
    }

    /* NRO 2021-09-30 : Check if the movable is in collision with a Shape. The Shape is a very generic java.awt
     * object, so any physical element is represented by a check
     */
    public abstract boolean isCollidingWith(Shape s);

    public abstract void collideWith(AbstractBoardElement movable, Point2D prevPosition);

    public boolean isCollidingWith(AbstractBoardElement abe) {
        if (abe == this) return false;
        return isCollidingWith(abe.getShape());
    }

    /* By default do nothing, might be overloaded */
    public void takeDamage(int explosionDamage) { }
}
