package com.example.odotracker.config;



import android.app.Activity;

public class Config { 
   
   
    /**
    * Screenw Width and Screenw Hight
    */
    public static int SCREENWIDTH = 800, SCREENHIEGHT = 1280;
 
    
    public static int TabSize=22;


 

    public static int getDisplayScreen(Activity mActivity) {
        SCREENWIDTH = mActivity.getWindowManager().getDefaultDisplay().getWidth();
        SCREENHIEGHT = mActivity.getWindowManager().getDefaultDisplay().getHeight();
 
     
        if (SCREENWIDTH<=480) TabSize=10;
        else if (SCREENWIDTH<=720) TabSize=12;
        System.out.println(TabSize);

        return TabSize;
    }
    //---------------------------------------------------------------
    /**
    * 
    * @return
    */
}