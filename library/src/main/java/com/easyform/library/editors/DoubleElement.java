package com.easyform.library.editors;

import android.content.Context;
import android.text.InputFilter;
import android.util.Log;
import android.widget.EditText;

import com.easyform.library.editors.filters.DoubleFilter;

import java.math.BigDecimal;

import static com.easyform.library.util.ObjectUtil.isNotNullAndEmpty;


public class DoubleElement extends android.support.v7.widget.AppCompatEditText implements Editor<Double> {

    private static final InputFilter[] doubleFilters = new InputFilter[]{new DoubleFilter()};
    private String TAG = DoubleElement.class.getName();

    public DoubleElement(Context context) {
        super(context);
        this.setTextSize(16);
        this.setFilters(doubleFilters);
    }

    public DoubleElement(Context context, Object value) {
        this(context);
        if (value instanceof BigDecimal) {
            value = ((BigDecimal) value).doubleValue();
        }
        setValue((Double) value);
    }

    @Override
    public Double getValue() {
        try {
            String value = getText().toString();
            if (isNotNullAndEmpty(value)) {
                return Double.valueOf(value);
            }
            return null;
        } catch (NumberFormatException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    @Override
    public void setValue(Double value) {
        if (isNotNullAndEmpty(value)) {
            setText(String.valueOf(value));
        }
    }

}
