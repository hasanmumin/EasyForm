package com.easyform.library.editors;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.DatePicker;

import com.easyform.library.R;
import com.easyform.library.util.ObjectUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateElement extends android.support.v7.widget.AppCompatTextView implements DatePickerDialog.OnDateSetListener, View.OnClickListener, Editor<Date> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    DatePickerDialog datePickerDialog;
    private Calendar currentCal = Calendar.getInstance();
    private Date value = null;

    public DateElement(Context context) {
        super(context);
        setTextSize(16);
        setTextColor(Color.BLACK);
        setBackgroundResource(R.drawable.bottom_line);
        setPadding(10, 5, 0, 0);
        setOnClickListener(this);
    }

    public DateElement(Context context, Object value) {
        this(context);
        if (ObjectUtil.isNotNull(value)) {
            this.value = (Date) value;
            setText(DATE_FORMAT.format(this.value));
            currentCal.setTime(this.value);
        }


    }

    public DatePickerDialog getDatePickerDialog() {
        if (datePickerDialog == null)
            createDatePickerDialog();
        return datePickerDialog;
    }

    private void createDatePickerDialog() {
        datePickerDialog = new DatePickerDialog(getContext(), this, currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH), currentCal.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        setValue(calendar.getTime());

    }

    @Override
    public Date getValue() {
        return this.value;

    }

    @Override
    public void setValue(Date value) {
        if (ObjectUtil.isNotNullAndEmpty(value)) {
            setText(DATE_FORMAT.format(value));
            currentCal.setTime(value);
            getDatePickerDialog().updateDate(currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH), currentCal.get(Calendar.DAY_OF_MONTH));
        }

        this.value = value;
    }

    @Override
    public void onClick(View view) {
        getDatePickerDialog().show();
    }
}
