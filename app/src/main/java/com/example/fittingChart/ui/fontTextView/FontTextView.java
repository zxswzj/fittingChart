package com.example.fittingChart.ui.fontTextView;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class FontTextView extends AppCompatTextView {
    private final static String FONT_GTW = "fonts/mangobrother.ttf";

    public FontTextView(Context context){
        this(context, null);
    }

    public FontTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FontTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        replaceCustomFont();
    }

    private void replaceCustomFont(){
        Typeface typeface = getTypeface();
        int style = Typeface.NORMAL;
        if(typeface == null){
            style = typeface.getStyle();
        }
        Typeface newTypeface = Typeface.createFromAsset(getContext().getAssets(), FONT_GTW);
        setTypeface(newTypeface,style);
    }
}
