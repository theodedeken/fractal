package code;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theo on 17-6-2016.
 */
public class FractalNode extends Circle {
    private FractalNode leftChild;
    private FractalNode rightChild;
    private double leftOffset;
    private double rightOffset;
    private double leftMult;
    private double rightMult;
    private int n;

    public FractalNode(double x, double y, double radius, double rot, double leftOffset, double rightOffset,
            double leftMult, double rightMult, int n) {
        super(x, y, radius);
        setLayoutY(640 - 2 * getCenterY());
        this.leftOffset = leftOffset;
        this.rightOffset = rightOffset;
        this.leftMult = leftMult;
        this.rightMult = rightMult;
        this.n = n;
        setRotate(rot);
        setFill(Color.BLUE);
        setStroke(Color.BLACK);
        build();
    }

    public void setProperties(double x, double y, double radius, double rot, double leftOffset, double rightOffset,
            double leftMult, double rightMult, int n) {
        setCenterX(x);
        setCenterY(y);
        setLayoutY(640 - 2 * getCenterY());
        setRadius(radius);
        setRotate(rot);
        this.leftOffset = leftOffset;
        this.rightOffset = rightOffset;
        this.leftMult = leftMult;
        this.rightMult = rightMult;
        this.n = n;
        rebuild();
    }

    public void build() {
        if (n > 0) {
            double dx1 = Math.cos(degreeToRad(getRotate() + leftOffset)) * getRadius() * leftMult;
            double dy1 = Math.sin(degreeToRad(getRotate() + leftOffset)) * getRadius() * leftMult;
            double dx2 = Math.cos(degreeToRad(getRotate() - rightOffset)) * getRadius() * rightMult;
            double dy2 = Math.sin(degreeToRad(getRotate() - rightOffset)) * getRadius() * rightMult;
            leftChild = new FractalNode(getCenterX() + dx1, getCenterY() + dy1, getRadius() * 3 / 5,
                    getRotate() + leftOffset, leftOffset, rightOffset, leftMult, rightMult, n - 1);
            rightChild = new FractalNode(getCenterX() + dx2, getCenterY() + dy2, getRadius() * 4 / 5,
                    getRotate() - rightOffset, leftOffset, rightOffset, leftMult, rightMult, n - 1);
        }
    }

    public void rebuild() {
        double dx1 = Math.cos(degreeToRad(getRotate() + leftOffset)) * getRadius() * leftMult;
        double dy1 = Math.sin(degreeToRad(getRotate() + leftOffset)) * getRadius() * leftMult;
        double dx2 = Math.cos(degreeToRad(getRotate() - rightOffset)) * getRadius() * rightMult;
        double dy2 = Math.sin(degreeToRad(getRotate() - rightOffset)) * getRadius() * rightMult;
        if (n == 0) {
            leftChild = null;
            rightChild = null;
        }
        if (leftChild != null) {
            leftChild.setProperties(getCenterX() + dx1, getCenterY() + dy1, getRadius() * 3 / 5,
                    getRotate() + leftOffset, leftOffset, rightOffset, leftMult, rightMult, n - 1);
            rightChild.setProperties(getCenterX() + dx2, getCenterY() + dy2, getRadius() * 4 / 5,
                    getRotate() - rightOffset, leftOffset, rightOffset, leftMult, rightMult, n - 1);
        } else if (n > 0) {
            leftChild = new FractalNode(getCenterX() + dx1, getCenterY() + dy1, getRadius() * 3 / 5,
                    getRotate() + leftOffset, leftOffset, rightOffset, leftMult, rightMult, n - 1);
            rightChild = new FractalNode(getCenterX() + dx2, getCenterY() + dy2, getRadius() * 4 / 5,
                    getRotate() - rightOffset, leftOffset, rightOffset, leftMult, rightMult, n - 1);
        }
    }

    public double degreeToRad(double deg) {
        return deg / 180 * Math.PI;
    }

    public List<FractalNode> getChildren() {
        List<FractalNode> children = new ArrayList<>();
        if (leftChild != null) {
            children.addAll(leftChild.getChildren());
            children.addAll(rightChild.getChildren());
        }
        children.add(this);
        return children;
    }

}