package com.example.gravityphysicssimulator;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;




public class HelloApplication extends Application {
    public static final int width = 800;
    public static final int height = 600;
    public static final int wall_length = 1;
    private double grav = gravity/scale;
    private static final double gravity = 9.8;
    private static final double scale = 20.0;
    public ArrayList<Body> body = new ArrayList<>();



    @Override
    public void start(Stage stage)  {

        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gravity Simulator");
        stage.show();
        Body b = new Body(100,300,3,0,50, 10,Color.AQUA);
        body.add(b);
        Body c = new Body(100,300,5,10,30, 10, Color.BLUE);
        body.add(c);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, width, height);
                gc.setFill(Color.WHITE);
                gc.fillRect(0, height-wall_length, width, wall_length);
                gc.fillRect(0, 0, wall_length, height);
                gc.fillRect(width-wall_length, 0, wall_length, height);
                for(Body bod : body) {
                    bod.update(grav);
                    bod.handleWalls(width ,height ,wall_length);
                    bod.draw(gc);
                }
            }
        };
        timer.start();

    }
}