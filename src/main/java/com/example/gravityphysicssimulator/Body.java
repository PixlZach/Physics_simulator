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
public void update(double grav, double sec){
    vy += grav * sec;
    x += vx * sec;
    y += vy * sec;
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

    public double getX() {
    return x;
    }
    public double getY() {
    return y;
    }
    public double getDiameter() {
    return diameter;
    }
    public void setColor(Color color) {
        this.color = color;
    }


    public static void handleCollisions(Body current, ArrayList<Body> B) {
        for (Body body : B) {
            if (current != body) {
                double dx = body.x - current.x;
                double dy = body.y - current.y;
                double distance = Math.sqrt(dx * dx + dy * dy);
                double touchingDist = current.diameter/2.0 + body.diameter/2.0;

                if (distance < touchingDist && distance > 0) {
                    double nx = dx / distance;
                    double ny = dy / distance;

                    double overlap = touchingDist - distance;
                    current.x -= nx * overlap / 2.0;
                    current.y -= ny * overlap / 2.0;
                    body.x    += nx * overlap / 2.0;
                    body.y    += ny * overlap / 2.0;


                    double v1n = current.vx * nx + current.vy * ny;
                    double v2n = body.vx    * nx + body.vy    * ny;


                    if (v1n - v2n <= 0) {
                        continue;
                    }

                    double m1 = current.mass;
                    double m2 = body.mass;
                    double v1n_new = ((m1 - m2) * v1n + 2 * m2 * v2n) / (m1 + m2);
                    double v2n_new = ((m2 - m1) * v2n + 2 * m1 * v1n) / (m1 + m2);

                    current.vx += (v1n_new - v1n) * nx;
                    current.vy += (v1n_new - v1n) * ny;
                    body.vx    += (v2n_new - v2n) * nx;
                    body.vy    += (v2n_new - v2n) * ny;
                }
            }
        }
    }
}
