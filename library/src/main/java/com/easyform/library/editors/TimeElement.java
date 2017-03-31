package com.easyform.library.editors;

import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TimePicker;

import com.easyform.library.R;
import com.easyform.library.util.ObjectUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class TimeElement extends android.support.v7.widget.AppCompatTextView implements TimePickerDialog.OnTimeSetListener, View.OnClickListener, Editor<Date> {

    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
    private static final Calendar calendar = Calendar.getInstance();
    private TimePickerDialog dialog;

    public TimeElement(Context context) {
        super(context);
        setTextSize(16);
        setTextColor(Color.BLACK);
        setBackgroundResource(R.drawable.bottom_line);
        setPadding(10, 5, 0, 0);
        setOnClickListener(this);
    }

    public TimeElement(Context context, Object value) {
        this(context);
        if (ObjectUtil.isNotNull(value)) {
            if (value instanceof String) {
                try {
                    value = TIME_FORMAT.parse(value.toString());
                    setText(TIME_FORMAT.format(value));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                setText(TIME_FORMAT.format((Date) value));
            }
        }
    }

    public TimePickerDialog getTimePickerDialog() {
        if (dialog == null)
            createTimePickerDialog();
        return dialog;
    }

    private void createTimePickerDialog() {
        dialog = new TimePickerDialog(getContext(), this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MONTH), true);
    }


    @Override
    public Date getValue() {
        try {
            return TIME_FORMAT.parse(getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void setValue(Date value) {
        setText(TIME_FORMAT.format(value));
        getTimePickerDialog().updateTime(value.getHours(), value.getMinutes());
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        setValue(calendar.getTime());
    }

    @Override
    public void onClick(View view) {
        getTimePickerDialog().show();
    }

}
