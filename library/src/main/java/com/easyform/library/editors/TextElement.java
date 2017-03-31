package com.easyform.library.editors;

import android.content.Context;
import android.text.InputFilter;

import static com.easyform.library.util.ObjectUtil.isNotNull;

public class TextElement extends android.support.v7.widget.AppCompatEditText implements Editor<String> {

    public TextElement(Context context) {
        super(context);
        setTextSize(16);
    }

    public TextElement(Context context, Object value) {
        this(context);
        if (isNotNull(value))
            setText(value.toString());
    }

    public TextElement(Context context, Object value, boolean forceUpperCase) {
        this(context, value);
        if (forceUpperCase)
            this.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
    }

    @Override
    public String getValue() {
        return getText().toString();
    }

    @Override
    public void setValue(String value) {
        if (isNotNull(value))
            setText(value);
    }
}
