package code;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by theo on 18-6-2016.
 */
public class FractalModel {
    private DoubleProperty sizeProperty;
    private DoubleProperty rotationProperty;
    private DoubleProperty leftOffsetProperty;
    private DoubleProperty rightOffsetProperty;
    private DoubleProperty leftMultProperty;
    private DoubleProperty rightMultProperty;
    private IntegerProperty startXProperty;
    private IntegerProperty startYProperty;
    private IntegerProperty iterationsProperty;
    private FractalNode root;

    public FractalModel() {
        sizeProperty = new SimpleDoubleProperty(100);
        rotationProperty = new SimpleDoubleProperty(90);
        leftOffsetProperty = new SimpleDoubleProperty(60);
        rightOffsetProperty = new SimpleDoubleProperty(30);
        leftMultProperty = new SimpleDoubleProperty(1.25);
        rightMultProperty = new SimpleDoubleProperty(1.25);
        startXProperty = new SimpleIntegerProperty(320);
        startYProperty = new SimpleIntegerProperty(150);
        iterationsProperty = new SimpleIntegerProperty(10);

        sizeProperty.addListener((InvalidationListener) -> updateRoot());
        rotationProperty.addListener((InvalidationListener) -> updateRoot());
        leftOffsetProperty.addListener((InvalidationListener) -> updateRoot());
        rightOffsetProperty.addListener((InvalidationListener) -> updateRoot());
        leftMultProperty.addListener((InvalidationListener) -> updateRoot());
        rightMultProperty.addListener((InvalidationListener) -> updateRoot());
        startXProperty.addListener((InvalidationListener) -> updateRoot());
        startYProperty.addListener((InvalidationListener) -> updateRoot());
        iterationsProperty.addListener((InvalidationListener) -> updateRoot());
        root = new FractalNode(startXProperty.getValue(), startYProperty.getValue(), sizeProperty.getValue(),
                rotationProperty.getValue(), leftOffsetProperty.getValue(), rightOffsetProperty.getValue(),
                leftMultProperty.getValue(), rightMultProperty.getValue(), iterationsProperty.getValue());
    }

    public void updateRoot() {
        root.setProperties(startXProperty.getValue(), startYProperty.getValue(), sizeProperty.getValue(),
                rotationProperty.getValue(), leftOffsetProperty.getValue(), rightOffsetProperty.getValue(),
                leftMultProperty.getValue(), rightMultProperty.getValue(), iterationsProperty.getValue());
    }

    public FractalNode getRoot() {
        return root;
    }

    public DoubleProperty getSizeProperty() {
        return sizeProperty;
    }

    public DoubleProperty getRotationProperty() {
        return rotationProperty;
    }

    public DoubleProperty getLeftOffsetProperty() {
        return leftOffsetProperty;
    }

    public DoubleProperty getRightOffsetProperty() {
        return rightOffsetProperty;
    }

    public DoubleProperty getLeftMultProperty() {
        return leftMultProperty;
    }

    public DoubleProperty getRightMultProperty() {
        return rightMultProperty;
    }

    public IntegerProperty getStartXProperty() {
        return startXProperty;
    }

    public IntegerProperty getStartYProperty() {
        return startYProperty;
    }

    public IntegerProperty getIterationsProperty() {
        return iterationsProperty;
    }

    public void setSizeProperty(DoubleProperty sizeProperty) {
        this.sizeProperty = sizeProperty;
    }

    public void setRotationProperty(DoubleProperty rotationProperty) {
        this.rotationProperty = rotationProperty;
    }

    public void setLeftOffsetProperty(DoubleProperty leftOffsetProperty) {
        this.leftOffsetProperty = leftOffsetProperty;
    }

    public void setRightOffsetProperty(DoubleProperty rightOffsetProperty) {
        this.rightOffsetProperty = rightOffsetProperty;
    }

    public void setLeftMultProperty(DoubleProperty leftMultProperty) {
        this.leftMultProperty = leftMultProperty;
    }

    public void setRightMultProperty(DoubleProperty rightMultProperty) {
        this.rightMultProperty = rightMultProperty;
    }

}
