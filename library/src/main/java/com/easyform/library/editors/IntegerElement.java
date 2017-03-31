package com.easyform.library.editors;

import android.content.Context;
import android.text.InputFilter;

import com.easyform.library.editors.filters.IntegerFilter;
import com.easyform.library.util.ObjectUtil;

public class IntegerElement extends android.support.v7.widget.AppCompatEditText implements Editor<Integer> {

    private static final InputFilter[] inputFilters = new InputFilter[]{new IntegerFilter()};

    public IntegerElement(Context context) {
        super(context);
        setTextSize(16);
        this.setFilters(inputFilters);
    }


    public IntegerElement(Context context, Object value) {
        this(context);
        setValue((Integer) value);
    }

    @Override
    public Integer getValue() {
        try {
            return Integer.valueOf(getText().toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public void setValue(Integer value) {
        if (ObjectUtil.isNotNull(value))
            setText(String.valueOf(value));

    }

}
