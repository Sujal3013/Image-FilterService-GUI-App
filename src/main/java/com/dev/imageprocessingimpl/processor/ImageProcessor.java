package com.dev.imageprocessingimpl.processor;

import com.dev.imageprocessingimpl.filter.IFilter;
import com.dev.imageprocessingimpl.image.DrawMultipleImagesOnCanvas;
import com.dev.imageprocessingimpl.image.ImageData;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ImageProcessor {
    private ExecutorService executorService;
    private DrawMultipleImagesOnCanvas drawFn;

    public ImageProcessor() {
        executorService= Executors.newFixedThreadPool(100);
    }

    public void processImage(BufferedImage image, int num, IFilter imageFilter,DrawMultipleImagesOnCanvas drawingCanvas){
        int numHorizontalImages=image.getWidth()/num;
        int numVerticalImages=image.getHeight()/num;

        List<Future<ImageData>> futuresList=new ArrayList<>();

        for(int i=0;i<numHorizontalImages;i++){
            for(int j=0;j<numVerticalImages;j++){
                BufferedImage subImage=image.getSubimage(i*num,j*num,num,num);
                int finalI=i;
                int finalJ=j;
                Future<ImageData> future=executorService.submit(new Callable<ImageData>() {
                    @Override
                    public ImageData call(){
                        BufferedImage result=imageFilter.filter(subImage);
                        ImageData imageData=new ImageData(result,finalI*num,finalJ*num,num,num);
                        drawingCanvas.addImageToQueue(imageData);
                        return imageData;
                    }
                });
                futuresList.add(future);
            }
        }
        for (Future<ImageData> future : futuresList) {
            try {
                future.get();
            } catch (Exception ex) {
                System.err.println("Error processing image: " + ex.getMessage());
            }

        }
    }
}
