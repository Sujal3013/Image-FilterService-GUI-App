package com.dev.imageprocessingimpl.image;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;

import javax.swing.*;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class DrawMultipleImagesOnCanvas {
    private static DrawMultipleImagesOnCanvas instance;

    private Queue<ImageData> imageDataQueue=new LinkedBlockingQueue<>();
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private boolean isDrawing=false;


    public static DrawMultipleImagesOnCanvas getInstance(){
        if(instance==null){
            return new DrawMultipleImagesOnCanvas();
        }
        return instance;
    }

    public void addImageToQueue(ImageData imageData){
        imageDataQueue.offer(imageData);
    }

    public void initialize(Stage primaryStage){
        this.canvas=new Canvas(1920,1080);
        this.graphicsContext=canvas.getGraphicsContext2D();
        this.graphicsContext.clearRect(0,0,1920,1080);

        new AnimationTimer(){
            @Override
            public void handle(long l) {
                // TODO :poll the image from queue, spin the thread and draw
                if(!isDrawing && !imageDataQueue.isEmpty()){
                    isDrawing=true;
                    new Thread(()->{
                        try{
                            drawNextImage();
                        }finally {
                            isDrawing=false;
                        }

                    }).start();
                }
            }
        }.start();
        Group root=new Group();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
//        StackPane stack = new StackPane(canvas);
//        Scene scene = new Scene(stack, 1920, 1080);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Canvas Example");
//        primaryStage.show();
    }

    private void drawNextImage() {
        ImageData imageData=imageDataQueue.poll();
        Platform.runLater(()->{
            if(imageData!=null){
                this.graphicsContext.drawImage(SwingFXUtils.toFXImage(imageData.getImage(),null),imageData.getI(), imageData.getJ(), imageData.getX(), imageData.getY());
                System.out.println(String.format("Drawing image at i: %s,j: %s",imageData.getI(),imageData.getJ()));
            }
        });
    }
}
