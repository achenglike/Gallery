package com.example.like.mylibrary;

import android.os.Build;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

public class VersionDiffUtils {
    
    public static void scaleX(View view, float f) {
        if (view == null)
            return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            view.setScaleX(f);
        } else {
            ScaleAnimation animation =new ScaleAnimation(f, f, f, f, 
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f); 
            animation.setDuration(0);
            animation.setFillAfter(true);
            view.startAnimation(animation);
        }
    }
    
    public static void scaleY(View view, float f) {
        if (view == null)
            return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            view.setScaleY(f);
        } else {
            ScaleAnimation animation =new ScaleAnimation(f, f, f, f, 
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f); 
            animation.setDuration(0);
            animation.setFillAfter(true);
            view.startAnimation(animation);
        }
    }
    
}
