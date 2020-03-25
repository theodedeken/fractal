// SPDX-FileCopyrightText: 2020 Theo Dedeken
//
// SPDX-License-Identifier: MIT

package code;

import javafx.beans.property.IntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * Created by theo on 18-6-2016.
 */
public class FractalController {
    public PropertyControl size;
    public PropertyControl rotation;
    public PropertyControl leftOffset;
    public PropertyControl rightOffset;
    public PropertyControl leftMult;
    public PropertyControl rightMult;
    public Button itMin;
    public Button itPlus;
    public TextField it;
    public TextField xValue;
    public TextField yValue;
    public FractalModel model;
    public Pane view;

    public void setModel(FractalModel model) {
        this.model = model;
        size.setModel(model.getSizeProperty());
        rotation.setModel(model.getRotationProperty());
        leftOffset.setModel(model.getLeftOffsetProperty());
        rightOffset.setModel(model.getRightOffsetProperty());
        leftMult.setModel(model.getLeftMultProperty());
        rightMult.setModel(model.getRightMultProperty());
        view.getChildren().addAll(model.getRoot().getChildren());
        itMin.setOnMouseClicked(e -> {
            model.getIterationsProperty().setValue(model.getIterationsProperty().getValue() - 1);
            refresh();
        });
        itPlus.setOnMouseClicked(e -> {
            model.getIterationsProperty().setValue(model.getIterationsProperty().getValue() + 1);
            refresh();
        });
        it.setText(String.valueOf(model.getIterationsProperty().getValue()));
        it.setOnKeyPressed(new InputHandler(model.getIterationsProperty(), it));
        model.getIterationsProperty().addListener((InvalidationListener) -> {
            it.setText(String.valueOf(model.getIterationsProperty().getValue()));
        });
        xValue.setText(String.valueOf(model.getStartXProperty().getValue()));
        yValue.setText(String.valueOf(model.getStartYProperty().getValue()));
        xValue.setOnKeyPressed(new InputHandler(model.getStartXProperty(), xValue));
        yValue.setOnKeyPressed(new InputHandler(model.getStartYProperty(), yValue));
        model.getStartXProperty().addListener((InvalidationListener) -> {
            xValue.setText(String.valueOf(model.getStartXProperty().getValue()));
        });
        model.getStartYProperty().addListener((InvalidationListener) -> {
            yValue.setText(String.valueOf(model.getStartYProperty().getValue()));
        });
    }

    public void refresh() {
        view.getChildren().clear();
        view.getChildren().addAll(model.getRoot().getChildren());
    }

    public class InputHandler implements EventHandler<KeyEvent> {
        private IntegerProperty property;
        private TextField field;

        public InputHandler(IntegerProperty property, TextField field) {
            this.property = property;
            this.field = field;
        }

        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.ENTER) {
                double val;
                try {
                    val = Integer.parseInt(field.getText());
                    property.setValue(val);
                } catch (Exception e) {
                    field.setText(String.valueOf(property.getValue()));
                }
            }
        }
    }

}
