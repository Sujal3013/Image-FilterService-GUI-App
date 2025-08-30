package com.dev.imageprocessingimpl.io;

import java.awt.image.BufferedImage;
import java.util.Optional;

public interface ImageIOInterface {
    <T> BufferedImage readImage(T src);
    void sendImage(BufferedImage image);


}
