// SPDX-FileCopyrightText: 2020 Theo Dedeken
//
// SPDX-License-Identifier: MIT

package code;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

/**
 * Created by theo on 18-6-2016.
 */
public class PropertyControl extends HBox {
    private Button left;
    private Button right;
    private Slider slider;
    private TextField value;
    private DoubleProperty model;
    private double min;
    private double max;
    private double increment;

    public PropertyControl() {
        left = new Button("<");
        right = new Button(">");
        slider = new Slider();
        value = new TextField();
        value.setPrefWidth(48);
        slider.setPrefWidth(128);
        getChildren().addAll(left, slider, right, value);
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
        slider.setMin(min);
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
        slider.setMax(max);
    }

    public double getIncrement() {
        return increment;
    }

    public void setIncrement(double increment) {
        this.increment = increment;
    }

    public void setModel(DoubleProperty model) {
        this.model = model;
        value.setOnKeyPressed(new KeyHandler());
        model.addListener(new PropertyListener());
        slider.valueProperty().bindBidirectional(model);
        value.setText(String.valueOf(model.getValue()));
        slider.setMajorTickUnit(increment * 10);
        slider.setMinorTickCount(0);
        slider.setSnapToTicks(true);
        slider.setShowTickMarks(true);
        left.setOnMouseClicked(e -> slider.setValue(model.getValue() - increment));
        right.setOnMouseClicked(e -> slider.setValue(model.getValue() + increment));
    }

    public class KeyHandler implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.ENTER) {
                double val;
                try {
                    val = Double.parseDouble(value.getText());
                    if (val < min) {
                        value.setText(String.valueOf(min));
                        model.setValue(min);
                    } else if (val > max) {
                        value.setText(String.valueOf(max));
                        model.setValue(max);
                    } else {
                        model.setValue(val);
                    }
                } catch (Exception e) {
                    value.setText(String.valueOf(model.getValue()));
                }
            }
        }
    }

    public class PropertyListener implements InvalidationListener {

        @Override
        public void invalidated(Observable observable) {
            if (model.getValue() == min) {
                left.setDisable(true);
            } else if (model.getValue() == max) {
                right.setDisable(true);
            } else {
                left.setDisable(false);
                right.setDisable(false);
            }

            value.setText(String.valueOf(model.getValue()));
        }
    }
}
