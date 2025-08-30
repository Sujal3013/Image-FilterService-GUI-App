package com.dev.imageprocessingimpl.utils;

import com.dev.imageprocessingimpl.filter.*;

import java.util.Scanner;

public class HelperUtility {

    public static IFilter filter=null;
    private static Scanner in=new Scanner(System.in);

    public static IFilter filterSelection(int filterNum){
        switch (filterNum){
            case 1:
                filter=new GreyScaleFilter();
                break;
            case 2:
                filter=new SepiaFilter();
                break;
            case 3:
                filter=new InvertFilter();
                break;
            case 4:
                System.out.println("Provide value for brightness level : +ve value for brighter, -ve value for darker");

                int brightnessLevel=in.nextInt();
                filter=new BrightnessFilter(brightnessLevel);
                break;
            default:
                System.out.println("Inappropriate filter Preference.");
                System.exit(-1);
        }
        return filter;
    }

}
