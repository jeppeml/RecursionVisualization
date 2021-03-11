package dk.moritzled;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class TreeProperties {
    private int startLength = 250;
    private int startAngle = 180;
    private int startX = 400;
    private int startY = 800;
    private int numberOfIterations = 12;
    private double lengthFactor = 1.5;
    private DoubleProperty angleBetween = new SimpleDoubleProperty(40);
    private double asymmetryLow = 0;
    private double asymmetryHigh = 0;
    private double randomness = 0;

    public TreeProperties() {
    }

    public TreeProperties(int startLength, int startAngle, int startX, int startY, int numberOfIterations, double lengthFactor, double angleBetween, double asymmetryLow, double asymmetryHigh, double randomness) {
        this.startLength = startLength;
        this.startAngle = startAngle;
        this.startX = startX;
        this.startY = startY;
        this.numberOfIterations = numberOfIterations;
        this.lengthFactor = lengthFactor;
        this.angleBetween.setValue(angleBetween);
        this.asymmetryLow = asymmetryLow;
        this.asymmetryHigh = asymmetryHigh;
        this.randomness = randomness;
    }

    public int getStartLength() {
        return startLength;
    }

    public void setStartLength(int startLength) {
        this.startLength = startLength;
    }

    public int getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(int startAngle) {
        this.startAngle = startAngle;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getNumberOfIterations() {
        return numberOfIterations;
    }

    public void setNumberOfIterations(int numberOfIterations) {
        this.numberOfIterations = numberOfIterations;
    }

    public double getLengthFactor() {
        return lengthFactor;
    }

    public void setLengthFactor(double lengthFactor) {
        this.lengthFactor = lengthFactor;
    }

    public double getAngleBetween() {
        return angleBetween.get();
    }

    public void setAngleBetween(double angleBetween) {
        this.angleBetween.setValue(angleBetween);

    }

    public DoubleProperty angleBetweenProperty(){
        return angleBetween;
    }

    public double getAsymmetryLow() {
        return asymmetryLow;
    }

    public void setAsymmetryLow(double asymmetryLow) {
        this.asymmetryLow = asymmetryLow;
    }

    public double getAsymmetryHigh() {
        return asymmetryHigh;
    }

    public void setAsymmetryHigh(double asymmetryHigh) {
        this.asymmetryHigh = asymmetryHigh;
    }

    public double getRandomness() {
        return randomness;
    }

    public void setRandomness(double randomness) {
        this.randomness = randomness;
    }
}
