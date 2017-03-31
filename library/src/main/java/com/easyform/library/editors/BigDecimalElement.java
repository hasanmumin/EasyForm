package com.easyform.library.editors;

import android.content.Context;
import android.text.InputFilter;
import android.util.Log;

import com.easyform.library.editors.filters.DoubleFilter;

import java.math.BigDecimal;

import static com.easyform.library.util.ObjectUtil.isNotNullAndEmpty;


public class BigDecimalElement extends android.support.v7.widget.AppCompatEditText implements Editor<BigDecimal> {

    private static final InputFilter[] doubleFilters = new InputFilter[]{new DoubleFilter()};
    private String TAG = BigDecimalElement.class.getName();

    public BigDecimalElement(Context context) {
        super(context);
        this.setTextSize(16);
        this.setFilters(doubleFilters);
    }

    public BigDecimalElement(Context context, Object value) {
        this(context);
        if (value instanceof Double) {
            value = BigDecimal.valueOf((Double) value);
        }
        setValue((BigDecimal) value);
    }

    @Override
    public BigDecimal getValue() {
        try {
            String value = getText().toString();
            if (isNotNullAndEmpty(value)) {
                return BigDecimal.valueOf(Double.valueOf(value));
            }
            return null;
        } catch (NumberFormatException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    @Override
    public void setValue(BigDecimal value) {
        if (isNotNullAndEmpty(value)) {
            setText(String.valueOf(value));
        }
    }

}
