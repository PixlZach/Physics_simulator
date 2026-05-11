package com.example.gravityphysicssimulator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Body {

    private double diameter = 50;
    private double x = 0;
    private double y = 0;
    private double vx = 0;
    private double vy = 0;
    private double mass = 0;
    private Color color;


public Body(double x, double y, double vx, double vy, double diameter, double density, Color color){
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
    this.diameter = diameter;
    this.mass = diameter * diameter * density;
    this.color = color;
}
public void update(double grav){
    vy += grav;
    x += vx;
    y += vy;
}
public void handleWalls(double width, double height, double wall_length){
    if(y > height- diameter/2.0 -wall_length ){
        y=height-diameter/2.0 -wall_length;
        vy = vy * -.8;
        vx*=.9;
    }
    if (x > width-diameter/2.0 -wall_length){
        x = width-diameter/2.0 -wall_length;
        vx *= -.8;
    }
    else if(x < diameter/2.0 +wall_length){
        x = diameter/2.0+wall_length;
        vx *= -.8;
    }
}
public void draw(GraphicsContext gc){
    gc.setFill(color);
    gc.fillOval(x - diameter/2.0, y - diameter/2.0, diameter, diameter);
}

public static void handleCollisions(Body current,ArrayList<Body> B){
    for(Body body : B){
        if (current != body) {

        }


    }

}
}
