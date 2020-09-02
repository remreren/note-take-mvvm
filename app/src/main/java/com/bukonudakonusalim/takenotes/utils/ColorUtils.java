package com.bukonudakonusalim.takenotes.utils;

import android.content.Context;
import android.graphics.Color;

import com.bukonudakonusalim.takenotes.R;

public class ColorUtils {

    public static int fromHSV(float hue, float saturation, float value) {
        return Color.HSVToColor(new float[]{hue, saturation, value});
    }

    public static float[] RGBStringToHSV(String colorString) {
        int color = Color.parseColor(colorString);
        int red = (color >> 16) & 0xff;
        int green = (color >> 8) & 0xff;
        int blue = color & 0xff;
        float[] hsv = new float[3];
        Color.RGBToHSV(red, green, blue, hsv);
        return hsv;
    }

    public static float[] RGBStringToHSV(int color) {
        int red = (color >> 16) & 0xff;
        int green = (color >> 8) & 0xff;
        int blue = color & 0xff;
        float[] hsv = new float[3];
        Color.RGBToHSV(red, green, blue, hsv);
        return hsv;
    }

    public static int[] hsvToColorTwist(float[] hsv) {
        final float[] hsv2 = new float[]{hsv[0], hsv[1], hsv[2] + 0.2f};
        return new int[]{Color.HSVToColor(hsv2), Color.HSVToColor(hsv)};
    }

    public static int[] getColorTwist(Context context, String colorName) {
        switch (colorName) {
            case "purple":
                return context.getResources().getIntArray(R.array.purple);

            case "deep_purple":
                return context.getResources().getIntArray(R.array.deep_purple);

            case "red":
                return context.getResources().getIntArray(R.array.red);

            case "indigo":
                return context.getResources().getIntArray(R.array.indigo);

            case "blue":
                return context.getResources().getIntArray(R.array.blue);

            case "light_blue":
                return context.getResources().getIntArray(R.array.light_blue);

            case "teal":
                return context.getResources().getIntArray(R.array.teal);

            case "lime":
                return context.getResources().getIntArray(R.array.lime);

            case "amber":
                return context.getResources().getIntArray(R.array.amber);

            case "orange":
                return context.getResources().getIntArray(R.array.orange);

            case "deep_orange":
                return context.getResources().getIntArray(R.array.deep_orange);

            default:
                return new int[]{0, 0};
        }
    }
}
