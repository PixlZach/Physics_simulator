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
    private static final double gravity = 1764;

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
        Body b = new Body(400,200,180,0,50, 20,Color.AQUA);
        body.add(b);
        Body c = new Body(100,300,300,0,30, 10, Color.BLUE);
        body.add(c);
        Body d = new Body(700,100,500,0,30, 10, Color.CHARTREUSE);
        body.add(d);

        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = 0;
            @Override
            public void handle(long now) {
                if (lastTime == 0) {
                    lastTime = now;
                    return;
                }
                double dt = (now - lastTime) / 1_000_000_000.0;
                lastTime = now;

                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, width, height);
                gc.setFill(Color.WHITE);
                gc.fillRect(0, height-wall_length, width, wall_length);
                gc.fillRect(0, 0, wall_length, height);
                gc.fillRect(width-wall_length, 0, wall_length, height);
                for (Body bod : body) {
                    bod.update(gravity, dt);
                    bod.handleWalls(width, height, wall_length);
                }
                for (Body bod : body) {
                    Body.handleCollisions(bod, body);
                }
                for (Body bod : body) {
                    bod.draw(gc);
                }
            }
        };
        timer.start();

    }
}