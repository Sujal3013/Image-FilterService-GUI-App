package com.dev.imageprocessingimpl.filter;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Brightness Adjustment Filter
 */
public class BrightnessFilter implements IFilter {

    private final int brightness; // +ve for brighter, -ve for darker

    public BrightnessFilter(int brightness) {
        this.brightness = brightness;
    }

    @Override
    public BufferedImage filter(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage brightImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalImage.getRGB(x, y);

                int r = ((rgb >> 16) & 0xFF) + brightness;
                int g = ((rgb >> 8) & 0xFF) + brightness;
                int b = (rgb & 0xFF) + brightness;

                r = Math.min(255, Math.max(0, r));
                g = Math.min(255, Math.max(0, g));
                b = Math.min(255, Math.max(0, b));

                int newRgb = new Color(r, g, b).getRGB();
                brightImage.setRGB(x, y, newRgb);
            }
        }
        return brightImage;
    }
}
