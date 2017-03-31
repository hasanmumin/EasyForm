package com.easyform.library.editors.filters;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Pattern;

public class DoubleFilter implements InputFilter {

    private static final Pattern doublePattern = Pattern.compile("([0-9]+[.][0-9]+)|([0-9]+[.])|([.][0-9]+)|([0-9]+)");

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dStart, int dEnd) {

        if (end > start && !isDouble(source, dest)) {
            return dest.subSequence(dStart, dEnd);
        }
        return null;
    }

    private boolean isDouble(CharSequence cs, Spanned dest) {
        String allSeq = dest.toString();
        if ("".equals(allSeq)) {
            return doublePattern.matcher(cs).matches();
        } else {
            return doublePattern.matcher(allSeq + cs).matches();
        }
    }
}