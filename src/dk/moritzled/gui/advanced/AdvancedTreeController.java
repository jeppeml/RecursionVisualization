package dk.moritzled.gui.advanced;

import dk.moritzled.TreeProperties;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class AdvancedTreeController implements Initializable {
    @FXML private  Button btnAnimation;
    @FXML private Slider sliderHeight;
    @FXML private Slider sliderStartAngle;
    @FXML private Slider sliderIterations;
    @FXML private Slider sliderLength;
    @FXML private Slider sliderAsymLow;
    @FXML private Slider sliderAsymHigh;
    @FXML private Slider sliderRandom;
    @FXML private Slider sliderAnimationLoops;
    @FXML private Slider sliderBranchAngle;
    @FXML private Slider sliderAnimationTime;
    @FXML private CheckBox tickInfinite;
    @FXML private CheckBox tickReverse;

    @FXML
    private Canvas canvas;

    private GraphicsContext gc;
    private Timeline timeline;
    private AnimationTimer timer;
    private boolean animationStarted = false;
    private final static String animationStartText = "Start tree animation";
    private final static String animationStopText = "Stop tree animation";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();

        tickInfinite.selectedProperty().addListener((observableValue, before, after) -> {
            sliderAnimationLoops.setDisable(after);
        });

        sliderHeight.valueProperty().addListener((obs) -> {
            drawTree();
        });
        sliderStartAngle.valueProperty().addListener((obs) -> {
            drawTree();
        });
        sliderIterations.valueProperty().addListener((obs) -> {
            drawTree();
        });
        sliderLength.valueProperty().addListener((obs) -> {
            drawTree();
        });
        sliderAsymLow.valueProperty().addListener((obs) -> {
            drawTree();
        });
        sliderAsymHigh.valueProperty().addListener((obs) -> {
            drawTree();
        });
        sliderRandom.valueProperty().addListener((obs) -> {
            drawTree();
        });
        sliderBranchAngle.valueProperty().addListener((obs) -> {
            drawTree();
        });

    }
    public void drawTree(ActionEvent actionEvent) {
        drawTree();
    }

    private void drawTree() {
        TreeProperties props = getPropsFromControls();
        props.setStartX(600);
        props.setStartY(1150);
        startIterations(props);
    }
    public void drawTreeAnimation(ActionEvent actionEvent) {
        toggleAnimationButtonText();
        if(!animationStarted) {
            stopAnimation();
        }
        else{
            TreeProperties props = getPropsFromControls();
            props.setStartX(600);
            props.setStartY(1150);
            startAnimation(props, (long)sliderAnimationTime.getValue(), (int)sliderAnimationLoops.getValue(), tickInfinite.isSelected(), tickReverse.isSelected());
        }
    }

    private TreeProperties getPropsFromControls(){
        TreeProperties props = new TreeProperties();
        // This binding makes the slider move while animating angles
        props.angleBetweenProperty().bindBidirectional(sliderBranchAngle.valueProperty());
        props.setStartLength((int)sliderHeight.getValue());
        props.setStartAngle((int)sliderStartAngle.getValue());
        props.setNumberOfIterations((int)sliderIterations.getValue());
        props.setAsymmetryLow(sliderAsymLow.getValue());
        props.setAsymmetryHigh(sliderAsymHigh.getValue());
        props.setRandomness(sliderRandom.getValue());
        props.setLengthFactor(sliderLength.getValue());


        return props;
    }

    private void toggleAnimationButtonText(){
        if(!animationStarted)
            btnAnimation.setText(animationStopText);
        else
            btnAnimation.setText(animationStartText);

        animationStarted=!animationStarted;
    }

    private void startAnimation(TreeProperties props, long animationSeconds, int numberOfLoops, boolean runInfinite, boolean autoReverse){
        props.angleBetweenProperty().setValue(0);

        timeline = new Timeline();
        if(runInfinite)
            timeline.setCycleCount(Timeline.INDEFINITE);
        else
            timeline.setCycleCount(numberOfLoops);
        timeline.setAutoReverse(autoReverse);

        KeyValue kv = new KeyValue(props.angleBetweenProperty(), 360);
        KeyFrame kf = new KeyFrame(Duration.seconds(animationSeconds), kv);
        timeline.getKeyFrames().add(kf);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                props.setAngleBetween(props.angleBetweenProperty().get());
                startIterations(props);
            }
        };

        timer.start();
        timeline.play();
    }

    private void stopAnimation(){
        timer.stop();
        timeline.stop();
    }

    private void startIterations(TreeProperties props){
        gc.clearRect(0,0,canvas.getHeight(),canvas.getWidth());
        drawLineLAngle(props.getStartX(), props.getStartY(), props.getStartAngle(), props.getStartLength(), props.getNumberOfIterations(), props);
    }

    private void drawLineLAngle(double x1, double y1, double angle, double length, int stopIt, TreeProperties props)
    {
        double x2 = x1 + length * Math.sin((angle) * (Math.PI/180));
        double y2 = y1 + length * Math.cos((angle) * (Math.PI/180));
        gc.strokeLine(x1, y1, x2, y2);

        if (stopIt>0)
        {
            stopIt -= 1;
            // Branch to the left
            double thisRand = Math.floor(Math.random()*(props.getRandomness()+1));
            if (Math.random()>0.5) thisRand*=-1;
            if (Math.random()>0.5) thisRand=0;
            drawLineLAngle(x2, y2, angle-(props.getAngleBetween()/2)- props.getAsymmetryHigh()+thisRand, length/props.getLengthFactor(), stopIt, props);

            // This third middle branch can be disabled
            thisRand = Math.floor(Math.random()*(props.getRandomness()+1));
            if (Math.random()>0.5) thisRand*=-1;
            if (Math.random()>0.5) thisRand=0;
            drawLineLAngle(x2, y2, angle+props.getAsymmetryLow()+thisRand, length/props.getLengthFactor(), stopIt, props);

            // Branch to the right
            thisRand = Math.floor(Math.random()*(props.getRandomness()+1));
            if (Math.random()>0.5) thisRand*=-1;
            if (Math.random()>0.5) thisRand=0;
            drawLineLAngle(x2, y2, angle+(props.getAngleBetween()/2)+props.getAsymmetryLow()+thisRand, length/props.getLengthFactor(), stopIt, props);

        }
    }

}
