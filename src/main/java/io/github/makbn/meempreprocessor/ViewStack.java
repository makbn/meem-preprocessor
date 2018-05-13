package io.github.makbn.meempreprocessor;

import javax.swing.*;
import java.util.HashMap;

public class ViewStack {
    private static HashMap<String,JFrame> views;

    public static void init(){
        views=new HashMap<>();
    }


    public static void addView(String tag,JFrame frame){
        views.put(tag,frame);
    }

    public static JFrame getViewByTag(String tag){
        return views.get(tag);
    }
}
