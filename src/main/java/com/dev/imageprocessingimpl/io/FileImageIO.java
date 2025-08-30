package com.dev.imageprocessingimpl.io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class FileImageIO implements ImageIOInterface {

    @Override
    public void sendImage(BufferedImage image) {
        // TODO : Implement this func to save the image
    }

    @Override
    public <T> BufferedImage readImage(T src) {
        try{
            String path=(String)src;
            File imageFile=new File(path);
            return ImageIO.read(imageFile);
        }catch (IOException e) {
            System.err.println("Not able to read this image.");
            return null;
        }
    }
}
