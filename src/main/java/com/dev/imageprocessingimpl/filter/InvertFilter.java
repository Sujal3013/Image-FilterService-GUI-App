package com.dev.imageprocessingimpl.filter;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Invert Colors Filter
 */
public class InvertFilter implements IFilter {

    @Override
    public BufferedImage filter(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage invertedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalImage.getRGB(x, y);

                int r = 255 - ((rgb >> 16) & 0xFF);
                int g = 255 - ((rgb >> 8) & 0xFF);
                int b = 255 - (rgb & 0xFF);

                int newRgb = new Color(r, g, b).getRGB();
                invertedImage.setRGB(x, y, newRgb);
            }
        }
        return invertedImage;
    }
}
