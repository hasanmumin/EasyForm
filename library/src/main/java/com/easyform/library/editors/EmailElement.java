package com.easyform.library.editors;

import android.content.Context;
import android.text.InputType;

public class EmailElement extends TextElement {
    public EmailElement(Context context) {
        super(context);
        setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
    }

    public EmailElement(Context context, Object value) {
        super(context, value);
        setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
    }
}
