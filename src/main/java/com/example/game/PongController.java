package com.example.game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class PongController {

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
    private Timeline animation;
    private int bolaX = 2;
    private int bolaY = 2;
    private int palaLeft = 7;
    private int palaRight = 7;
    private int scoreLeft = 0;
    private int scoreRight = 0;

    public PongController(Rectangle rightWall, Rectangle leftWall, Rectangle northWall, Rectangle southWall, Rectangle red,
                          Rectangle stickRight, Rectangle stickLeft,
                          Circle circleBall, StackPane ground, Label pointsLeft, Label pointsRight) {
        this.rightWall = rightWall;
        this.leftWall = leftWall;
        this.northWall = northWall;
        this.southWall = southWall;
        this.red = red;
        this.stickRight = stickRight;
        this.stickLeft = stickLeft;
        this.circleBall = circleBall;
        this.ground = ground;
        this.pointsLeft = pointsLeft;
        this.pointsRight = pointsRight;
        this.stickRight = stickRight;

        initializeGame();
        sticksControls();
    }

    private void sticksControls() {
        //switch con teclas
        ground.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W:
                    stickLeft.setTranslateY(stickLeft.getTranslateY() - palaLeft);
                    break;
                case S:
                    stickLeft.setTranslateY(stickLeft.getTranslateY() + palaLeft);
                    break;
                case UP:
                    stickRight.setTranslateY(stickRight.getTranslateY() - palaRight);
                    break;
                case DOWN:
                    stickRight.setTranslateY(stickRight.getTranslateY() + palaRight);
                    break;
                case SPACE:
                    animation.play();
                    break;
                case P:
                    animation.stop();
                    break;
            }
        });
        ground.setFocusTraversable(true);
    }

    //Inicializador del juego
    private void initializeGame() {
        this.animation = new Timeline(new KeyFrame(Duration.millis(17), t -> {
            moveCircle();
            detectPoints();
            detectSticks();
        }));
        animation.setCycleCount(Animation.INDEFINITE);
    }

    //Método que detecta las colisiones y la puntuación
    private void detectPoints() {
        if (circleBall.getBoundsInParent().intersects(southWall.getBoundsInParent())) {
            --bolaY;
        } else if (circleBall.getBoundsInParent().intersects(northWall.getBoundsInParent())) {
            ++bolaY;
        } else if (circleBall.getBoundsInParent().intersects(rightWall.getBoundsInParent())) {
            circleBall.setTranslateX(0);
            circleBall.setTranslateY(0);
            ++scoreLeft;
            pointsLeft.setText(scoreLeft + " puntos");
            animation.stop();
        } else if (circleBall.getBoundsInParent().intersects(leftWall.getBoundsInParent())) {
            circleBall.setTranslateX(0);
            circleBall.setTranslateY(0);
            ++scoreRight;
            pointsRight.setText(scoreRight + " puntos");
            animation.stop();
        }
    }

    //Método que detecta las colisiones en los sticks
    private void detectSticks() {
        if (circleBall.getBoundsInParent().intersects(stickLeft.getBoundsInParent())) {
            ++bolaX;
        } else if (circleBall.getBoundsInParent().intersects(stickRight.getBoundsInParent())) {
            --bolaX;
        } else if (stickLeft.getBoundsInParent().intersects(northWall.getBoundsInParent())) {
            stickLeft.setTranslateY(-167);
        } else if (stickLeft.getBoundsInParent().intersects(southWall.getBoundsInParent())) {
            stickLeft.setTranslateY(167);
        } else if (stickRight.getBoundsInParent().intersects(northWall.getBoundsInParent())) {
            stickRight.setTranslateY(-167);
        } else if (stickRight.getBoundsInParent().intersects(southWall.getBoundsInParent())) {
            stickRight.setTranslateY(167);
        }
    }

    //Método que mueve la pelota
    private void moveCircle() {
        circleBall.setTranslateX(circleBall.getTranslateX() + bolaX);
        circleBall.setTranslateY(circleBall.getTranslateY() + bolaY);
    }
}