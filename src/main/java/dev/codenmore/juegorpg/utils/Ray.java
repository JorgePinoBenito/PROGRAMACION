package main.java.dev.codenmore.juegorpg.utils;

import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.Segment;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Optional;

public class Ray {
    public Vector2D direction;
    public Vector2D origin;
    private final double tolerance;

    public Ray(Vector2D origin, Vector2D direction, double tolerance) {
        this.direction = direction;
        this.origin = origin;
        this.tolerance = tolerance;
    }

    public Ray(Vector2D origin, Vector2D direction) {
        this.direction = direction;
        this.origin = origin;
        this.tolerance = 0.01d;
    }

    public boolean contains(Vector2D v) {
        boolean containsY = (direction.getY() > 0 && v.getY() >= origin.getY()) ||
            (direction.getY() < 0 && v.getY() <= origin.getY()) ||
            (direction.getY() == 0 && v.getY() == origin.getY());
        boolean containsX = (direction.getX() > 0 && v.getX() >= origin.getX()) ||
            (direction.getX() < 0 && v.getX() <= origin.getX()) ||
            (direction.getX() == 0 && v.getX() == origin.getX());
        return containsX && containsY;
    }

    public Optional<Vector2D> intersectionWith(Line2D line) {
        Line rayLine = new Line(origin, origin.add(direction), tolerance);
        Vector2D v1 = new Vector2D(line.getX1(),line.getY1());
        Vector2D v2 = new Vector2D(line.getX2(), line.getY2());
        Segment segment = new Segment(v1, v2, new Line(v1,v2,tolerance));
        Vector2D intersection = rayLine.intersection(segment.getLine());
        if (intersection == null) return Optional.empty();

        double minX = Math.min(segment.getStart().getX(), segment.getEnd().getX());
        double minY = Math.min(segment.getStart().getY(), segment.getEnd().getY());
        double maxX = Math.max(segment.getStart().getX(), segment.getEnd().getX());
        double maxY = Math.max(segment.getStart().getY(), segment.getEnd().getY());

        boolean containedInSegment = intersection.getX() + tolerance >= minX &&
            intersection.getX() - tolerance <= maxX &&
            intersection.getY() - tolerance <= maxY &&
            intersection.getY() + tolerance >= minY;
        boolean containedInRay = contains(intersection);

        if (containedInRay && containedInSegment) return Optional.of(intersection);
        else return Optional.empty();
    }

    public Optional<Vector2D> intersectionWith(Rectangle rectangle) {
        Line2D.Double[] lines = new Line2D.Double[]{
            new Line2D.Double(rectangle.x, rectangle.y, rectangle.x, rectangle.y + rectangle.height),
            new Line2D.Double(rectangle.x, rectangle.y, rectangle.x + rectangle.width, rectangle.y),
            new Line2D.Double(rectangle.x, rectangle.y + rectangle.height, rectangle.x + rectangle.width, rectangle.y + rectangle.height),
            new Line2D.Double(rectangle.x + rectangle.width, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height),
        };
        Optional<Vector2D> result = Optional.empty();
        for (Line2D line : lines) {
            result = intersectionWith(line);
            if (result.isPresent()) return result;
        }
        return result;
    }
}
