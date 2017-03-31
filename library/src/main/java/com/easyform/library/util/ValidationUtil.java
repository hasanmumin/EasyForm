package com.easyform.library.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * ValidationUtil is a helper class which implements most used validation rules used in application.
 */
public class ValidationUtil {

    public static final String PLEASE_SELECT = "<Lütfen Seçiniz>";
    public static final String NO_SELECT_ERROR = "Lütfen Seçim Yapınız";

    private static final ConcurrentHashMap<TYPES, Pattern> PATTERNS = new ConcurrentHashMap<>(TYPES.values().length);

    /**
     * This method validates given text with the given
     *
     * @param type
     * @param value
     * @return
     */
    public static boolean isValid(TYPES type, String value) {
        if (!PATTERNS.containsKey(type))
            PATTERNS.put(type, Pattern.compile(type.pattern));
        return PATTERNS.get(type).matcher(value).matches();
    }

    public enum TYPES {
        EMAIL_VALID_AND_REQURID("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"),
        EMAIL_VALID_OR_EMPTY("^(|[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}))$"),
        IDENTITY_NUMBER_VALID_OR_EMPTY("^(|[1-9]{1}[0-9]{9}[0,2,4,6,8]{1})$"),
        IDENTITY_NUMBER_VALID_AND_REQUIRED("^[1-9]{1}[0-9]{9}[0,2,4,6,8]{1}$"),
        TAX_NUMBER_VALID_AND_REQUIRED("^[0-9]{10,10}$"),
        TAX_NUMBER_VALID_OR_EMPTY("^(|[0-9]{10,10})$"),
        PLATE_VALID_OR_EMPTY("^(|[0-9]{2}\\s[A-Z]{1,3}\\s[0-9]{1,3})$");

        private final String pattern;

        TYPES(String pattern) {
            this.pattern = pattern;
        }

        public String pattern() {
            return pattern;
        }
    }
}
