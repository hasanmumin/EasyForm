package com.easyform.library.editors;

import android.content.Context;
import android.text.InputFilter;

import com.easyform.library.editors.filters.IntegerFilter;
import com.easyform.library.util.ObjectUtil;

public class OnlyIntegerStringElement extends android.support.v7.widget.AppCompatEditText implements Editor<String> {

    private static final InputFilter[] inputFilters = new InputFilter[]{new IntegerFilter()};

    public OnlyIntegerStringElement(Context context) {
        super(context);
        setTextSize(16);
        this.setFilters(inputFilters);
    }

    public OnlyIntegerStringElement(Context context, Object value) {
        this(context);
        if (ObjectUtil.isNotNull(value))
            setText(value.toString());
    }

    @Override
    public String getValue() {
        return getText().toString();
    }

    @Override
    public void setValue(String value) {
        if (ObjectUtil.isNotNull(value))
            setText(value);
    }
}
