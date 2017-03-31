package com.easyform.library.editors;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.view.View;

import com.easyform.library.R;
import com.easyform.library.editors.model.DateTimeDialogFragment;
import com.easyform.library.util.ObjectUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DateTimeElement extends android.support.v7.widget.AppCompatTextView implements DateTimeDialogFragment.OnDateTimeChangeListener, Editor<Date>, View.OnClickListener {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);
    private DateTimeDialogFragment dateTimePickerDialog;
    private Calendar currentCal = Calendar.getInstance();
    private Object value;

    public DateTimeElement(Context context) {
        super(context);
        setTextSize(16);
        setTextColor(Color.BLACK);
        setBackgroundResource(R.drawable.bottom_line);
        setPadding(10, 5, 0, 0);
        setOnClickListener(this);
    }

    public DateTimeElement(Context context, Object value) {
        this(context);
        this.value = value;

        if (ObjectUtil.isNotNull(value)) {
            setText(DATE_FORMAT.format((Date) value));
        }
    }

    public DateTimeDialogFragment getDateTimePickerDialog() {
        if (dateTimePickerDialog == null) {
            dateTimePickerDialog = new DateTimeDialogFragment(getActivity());
            dateTimePickerDialog.setListener(this);
        }
        return dateTimePickerDialog;
    }

    @Override
    public Date getValue() {
        return ObjectUtil.isNull(value) ? null : ((Date) value);
    }

    @Override
    public void setValue(Date value) {

        this.value = value;

        setText(DATE_FORMAT.format(value));
        currentCal.setTime(value);
        getDateTimePickerDialog().setDateTime(
                currentCal.get(Calendar.YEAR),
                currentCal.get(Calendar.MONTH),
                currentCal.get(Calendar.DAY_OF_MONTH),
                currentCal.get(Calendar.HOUR),
                currentCal.get(Calendar.MINUTE)
        );
    }

    @Override
    public void onClick(View view) {
        getDateTimePickerDialog().getDialog().show();
    }


    @Override
    public void onDateTimeSet(int year, int month, int dayOfMonth, int hour, int minute) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date dateTime = calendar.getTime();
        setValue(dateTime);
        this.value = dateTime;
    }

    private Activity getActivity() {
        Context context = getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

}
