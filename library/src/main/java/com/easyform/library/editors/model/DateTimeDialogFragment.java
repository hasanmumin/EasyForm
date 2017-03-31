package com.easyform.library.editors.model;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.easyform.library.R;

public class DateTimeDialogFragment {

    private OnDateTimeChangeListener listener;

    private DatePicker datePicker;
    private TimePicker timePicker;
    private Dialog dialog;
    private View view;

    public DateTimeDialogFragment(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_datetime_picker, null);
        builder.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        fireEvents();
                        DateTimeDialogFragment.this.getDialog().cancel();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DateTimeDialogFragment.this.getDialog().cancel();
                    }
                });
        dialog = builder.create();
    }


    private DatePicker getDatePicker() {
        if (datePicker == null) {
            datePicker = (DatePicker) getView().findViewById(R.id.dtDatePicker);
        }
        return datePicker;
    }

    private TimePicker getTimePicker() {
        if (timePicker == null)
            timePicker = (TimePicker) getView().findViewById(R.id.dtTimePicker);
        return timePicker;
    }

    public int[] getDateTime() {
        int year = getDatePicker().getYear();
        int month = getDatePicker().getMonth();
        int dayOfMonth = getDatePicker().getDayOfMonth();
        int hour = getTimePicker().getCurrentHour();
        int minute = getTimePicker().getCurrentMinute();

        return new int[]{year, month, dayOfMonth, hour, minute};
    }

    public void setDateTime(int year, int month, int dayOfMonth, int hour, int minute) {
        getDatePicker().updateDate(year, month, dayOfMonth);
        getTimePicker().setCurrentHour(hour);
        getTimePicker().setCurrentMinute(minute);
    }


    private void fireEvents() {
        if (listener == null)
            return;
        int[] values = getDateTime();
        listener.onDateTimeSet(values[0], values[1], values[2], values[3], values[4]);
    }

    public OnDateTimeChangeListener getListener() {
        return listener;
    }

    public void setListener(OnDateTimeChangeListener listener) {
        this.listener = listener;
    }

    public Dialog getDialog() {
        return dialog;
    }

    private View getView() {
        return view;
    }


    public interface OnDateTimeChangeListener {
        void onDateTimeSet(int year, int month, int dayOfMonth, int hour, int minute);
    }


}
