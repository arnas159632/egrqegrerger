package com.example.jprojektinis;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;

import java.util.Stack;

public class ChainController {
    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    private Stack<Double[]> drawnLinesStack;

    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        drawnLinesStack = new Stack<>();
        canvas.setOnMousePressed(event -> {
            drawnLinesStack.push(new Double[]{event.getX(), event.getY()});
        });
        canvas.setOnMouseDragged(event -> {
            double endX = event.getX();
            double endY = event.getY();
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(2);
            gc.strokeLine(drawnLinesStack.peek()[0], drawnLinesStack.peek()[1], endX, endY);
            drawnLinesStack.push(new Double[]{endX, endY});
        });
    }

    public void clearCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawnLinesStack.clear();
    }
}
