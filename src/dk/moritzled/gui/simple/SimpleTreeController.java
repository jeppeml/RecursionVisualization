package dk.moritzled.gui.simple;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.net.URL;
import java.util.ResourceBundle;

public class SimpleTreeController implements Initializable {

    @FXML
    private Canvas canvas;
    private GraphicsContext gc;

    private int randomness;
    private double angleBetween;
    private double lengthFactor;
    private double asymmetryLow;
    private double asymmetryHigh;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        angleBetween=40;
        lengthFactor=1.5;
        asymmetryLow=0;
        asymmetryHigh=0;
        randomness=0;
    }
    public void drawTree(ActionEvent actionEvent) {
        drawTree();
    }

    private void drawTree() {
        startIterations(600,1150, 180, 250, 11);
    }

    private void startIterations(int startX, int startY, double startAngle, double startLength, int iterations){

        gc.clearRect(0,0,canvas.getHeight(),canvas.getWidth());
        drawLineLAngle(startX, startY, startAngle, startLength, iterations);
        gc.stroke();
    }

    private void drawLineLAngle(double x1, double y1, double angle, double length, int stopIt)
    {
        double x2 = x1 + length * Math.sin((angle) * (Math.PI/180));
        double y2 = y1 + length * Math.cos((angle) * (Math.PI/180));
        gc.strokeLine(x1, y1, x2, y2);

        if (stopIt>0)
        {
            stopIt -= 1;
            double thisRand = Math.floor(Math.random()*(randomness+1));
            if (Math.random()>0.5) thisRand*=-1;
            if (Math.random()>0.5) thisRand=0;
            drawLineLAngle(x2, y2, angle-(angleBetween/2)- asymmetryHigh+thisRand, length/lengthFactor, stopIt);


            thisRand = Math.floor(Math.random()*(randomness+1));
            if (Math.random()>0.5) thisRand*=-1;
            if (Math.random()>0.5) thisRand=0;
            drawLineLAngle(x2, y2, angle+(angleBetween/2)+asymmetryLow+thisRand, length/lengthFactor, stopIt);
        }
    }

}
