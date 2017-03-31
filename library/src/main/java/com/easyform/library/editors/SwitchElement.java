package com.easyform.library.editors;

import android.content.Context;
import android.widget.Switch;

import com.easyform.library.R;

import static com.easyform.library.util.ObjectUtil.isNull;

public class SwitchElement extends Switch implements Editor<Boolean> {

    public SwitchElement(Context context) {
        super(context);
        setBackgroundResource(R.drawable.bottom_line);
        setTextSize(16);
        setTextOff("Off");
        setTextOn("On");
        setGravity(TEXT_ALIGNMENT_CENTER);
    }

    public SwitchElement(Context context, Object value) {
        this(context);
        setValue((Boolean) value);
    }

    @Override
    public Boolean getValue() {
        return isChecked();
    }

    @Override
    public void setValue(Boolean value) {
        setChecked(isNull(value) ? false : value);
    }
}
