package com.easyform.library.editors.filters;

import android.text.InputFilter;
import android.text.Spanned;

public class IntegerFilter implements InputFilter {
    @Override
    public CharSequence filter(CharSequence src, int sStart, int sEnd, Spanned dest, int dStart, int dEnd) {
        if (sEnd > sStart) {
            if (!validInteger(src))
                return dest.subSequence(dStart, dEnd);
            return null;
        } else
            return null;
    }

    private boolean validInteger(CharSequence cs) {
        for (char c : cs.toString().toCharArray()) {
            if (c < 48 || c > 57)
                return false;

        }
        return true;
    }
}
