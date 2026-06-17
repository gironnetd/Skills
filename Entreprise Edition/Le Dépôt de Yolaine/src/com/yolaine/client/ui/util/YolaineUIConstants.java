package com.yolaine.client.ui.util;


import java.awt.Color;


public interface YolaineUIConstants {
    
    public final static Color DEFAULT_BG_COLOR = Color.lightGray;
    
    public static final Color TABLE_ROW_COLOR_1 = Color.white;
    public static final Color TABLE_ROW_COLOR_2 = new Color(202,225,255);
    public static final Color TABLE_ROW_SELECTED_COLOR_1 = new Color(0, 0, 128);
    public static final Color TABLE_ROW_SELECTED_COLOR_2 = new Color(32, 0, 64);
    
    /**
     * A simple date pattern.
     */
    public static final String DATE_PATTERN = "MM/dd/yyyy";
    /**
     * A group of date patterns.
     */
    public static final String[] DATE_PATTERNS = new String[] {
        DATE_PATTERN
    };
    
}