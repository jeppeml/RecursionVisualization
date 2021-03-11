package dk.moritzled.gui.simple;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class SierpinskyController implements Initializable {
    @FXML
    private Slider sliderIterations;
    @FXML
    private Canvas canvas;

    private GraphicsContext gc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        sliderIterations.valueProperty().addListener((observable, prev, after) ->
            drawExample(after.intValue()));
        drawExample(1);
    }

    private void drawExample(int iterations){
        gc.clearRect(0,0,canvas.getHeight(),canvas.getWidth()); // Clears the Canvas
        drawTriangles(0, 800, 1150, 800, 575, 0, iterations);
    }

    private void drawTriangles(double x1, double y1, double x2, double y2, double x3, double y3, int stopIt){
        // Draw a triangle
        gc.strokeLine(x1, y1, x2, y2);
        gc.strokeLine(x2, y2, x3, y3);
        gc.strokeLine(x1, y1, x3, y3);
        if (stopIt>0)
        {
            stopIt -= 1;

            var tilfDiv = 2;
            double x1Small = (x1 + x2)/tilfDiv;
            double x2Small = (x2 + x3)/tilfDiv;
            double x3Small = (x1 + x3)/tilfDiv;
            double y1Small = (y1 + y2)/tilfDiv;
            double y2Small = (y2 + y3)/tilfDiv;
            double y3Small = (y1 + y3)/tilfDiv;

            // Ask to draw 3 triangles
            drawTriangles(x1, y1, x1Small, y1Small, x3Small, y3Small, stopIt);
            drawTriangles(x2, y2, x1Small, y1Small, x2Small, y2Small, stopIt);
            drawTriangles(x3, y3, x3Small, y3Small, x2Small, y2Small, stopIt);
        }
    }
}
