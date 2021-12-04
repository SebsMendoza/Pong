package com.example.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Pong extends BorderPane {

    private Rectangle rightWall;
    private Rectangle leftWall;
    private Rectangle northWall;
    private Rectangle southWall;
    private Rectangle red;
    private Label pointsLeft;
    private Label pointsRight;
    private Rectangle stickRight;
    private Rectangle stickLeft;
    private Circle circleBall;
    private StackPane ground;
    private Label controllers;

    private PongController controller;

    public Pong() {
        //Inicialización de variables
        rightWall = new Rectangle();
        leftWall = new Rectangle();
        northWall = new Rectangle();
        southWall = new Rectangle();
        red = new Rectangle();
        pointsLeft = new Label("0 puntos");
        pointsRight = new Label("0 puntos");
        stickRight = new Rectangle();
        stickLeft = new Rectangle();
        circleBall = new Circle();
        ground = new StackPane();
        controllers = new Label();

        //Setteo de los Label
        controllers.setText("CONTROLS: SPACE - Start | P - Pause\nPLAYER 1: W - Up; S - down | PLAYER 2: ↑ - Up; ↓ - Down");
        controllers.setFont(new Font("Arial",12));
        controllers.setTextFill(Color.WHITE);
        pointsLeft.setTextFill(Color.WHITE);
        pointsLeft.setFont(new Font("Arial", 25));
        pointsRight.setTextFill(Color.WHITE);
        pointsRight.setFont(new Font("Arial", 25));

        //Dando estilos a la pista de juego (paredes, sticks y red)
        rightWall.setFill(Color.BLACK);
        leftWall.setFill(Color.BLACK);
        northWall.setFill(Color.BLACK);
        southWall.setFill(Color.BLACK);
        red.setFill(Color.WHITE);
        stickRight.setFill(Color.WHITE);
        stickLeft.setFill(Color.WHITE);
        ground.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        //Setteando la pelota (color y tamaño)
        circleBall.setFill(Color.WHITE);
        circleBall.setRadius(10);

        //Bindeando las paredes, la red y los sticks
        rightWall.heightProperty().bind(ground.heightProperty());
        rightWall.widthProperty().bind(ground.widthProperty().divide(200));
        leftWall.heightProperty().bind(ground.heightProperty());
        leftWall.widthProperty().bind(ground.widthProperty().divide(200));
        northWall.heightProperty().bind(ground.heightProperty().divide(15));
        northWall.widthProperty().bind(ground.widthProperty());
        southWall.heightProperty().bind(ground.heightProperty().divide(15));
        southWall.widthProperty().bind(ground.widthProperty());
        red.heightProperty().bind(ground.heightProperty());
        red.widthProperty().bind(ground.widthProperty().divide(300));
        stickRight.heightProperty().bind(ground.heightProperty().divide(5));
        stickRight.widthProperty().bind(ground.widthProperty().divide(30));
        stickLeft.heightProperty().bind(ground.heightProperty().divide(5));
        stickLeft.widthProperty().bind(ground.widthProperty().divide(30));

        //Agregando y posicionando los elementos en la pista
        ground.getChildren().addAll(circleBall, southWall, northWall, leftWall, rightWall, red, stickRight, stickLeft, controllers, pointsLeft, pointsRight);
        ground.setAlignment(rightWall, Pos.CENTER_RIGHT);
        ground.setAlignment(southWall, Pos.BOTTOM_CENTER);
        ground.setAlignment(northWall, Pos.TOP_CENTER);
        ground.setAlignment(leftWall, Pos.CENTER_LEFT);
        ground.setAlignment(red, Pos.CENTER);
        ground.setAlignment(stickRight, Pos.CENTER_RIGHT);
        ground.setAlignment(stickLeft, Pos.CENTER_LEFT);
        ground.setAlignment(circleBall, Pos.CENTER);
        ground.setAlignment(controllers, Pos.TOP_LEFT);
        ground.setAlignment(pointsLeft, Pos.BOTTOM_LEFT);
        ground.setAlignment(pointsRight, Pos.BOTTOM_RIGHT);

        this.setCenter(ground);

        controller = new PongController(rightWall, leftWall, northWall, southWall, red, stickRight, stickLeft, circleBall, ground, pointsLeft, pointsRight);
    }
}