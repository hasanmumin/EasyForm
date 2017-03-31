package com.easyform.library;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;

public class ButtonElement extends AppCompatButton {
    public ButtonElement(Context context) {
        super(context);
        setTextSize(16);
        setBackgroundResource(R.drawable.button_success);
        setTextColor(Color.WHITE);
        setTypeface(null, Typeface.BOLD);
    }

    public ButtonElement(Context context, String text) {
        this(context);
        setText(text);
    }

    public ButtonElement(Context context, String text, OnClickListener listener) {
        this(context, text);
        setOnClickListener(listener);
    }

}

