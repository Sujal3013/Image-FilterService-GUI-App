package com.dev.imageprocessingimpl;

import com.dev.imageprocessingimpl.filter.*;
import com.dev.imageprocessingimpl.image.DrawMultipleImagesOnCanvas;
import com.dev.imageprocessingimpl.io.FileImageIO;
import com.dev.imageprocessingimpl.io.ImageIOInterface;
import com.dev.imageprocessingimpl.processor.ImageProcessor;
import com.dev.imageprocessingimpl.utils.HelperUtility;
import javafx.application.Application;
import javafx.application.Platform;

import javafx.stage.Stage;

import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.Scanner;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        DrawMultipleImagesOnCanvas drawMultipleImagesOnCanvas=DrawMultipleImagesOnCanvas.getInstance();
        drawMultipleImagesOnCanvas.initialize(stage);
        ImageIOInterface imageIO=new FileImageIO();
        BufferedImage image=imageIO.readImage("C:\\Users\\gk-lenovo\\OneDrive\\Desktop\\Backend_Dev\\ImageProcessingImpl\\src\\main\\java\\com\\dev\\imageprocessingimpl\\io\\test.jpg");

        ImageProcessor processor=new ImageProcessor();
        Scanner in=new Scanner(System.in);
        System.out.println("Please Provide your filter preference");
        System.out.println(" Select 1 -> Gray Scale Filter");
        System.out.println(" Select 2 -> Sepia Filter");
        System.out.println(" Select 3 -> Invert Image Filter");
        System.out.println(" Select 4 -> Brightness Filter");
        System.out.print("Provide Input in numbers here : ");

        Integer filterNum=in.nextInt();
        IFilter filter=HelperUtility.filterSelection(filterNum);

        processor.processImage(image,120,filter,drawMultipleImagesOnCanvas);

        Platform.setImplicitExit(true);
    }

    public static void main(String[] args) {
        launch();
    }
}