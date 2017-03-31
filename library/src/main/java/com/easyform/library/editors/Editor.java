package com.easyform.library.editors;

public interface Editor<T> {
    T getValue();

    void setValue(T value);

    CharSequence getText();

    enum EditorTypes {
        TEXT,
        INTEGER,
        ONLY_INTEGER_STRING,
        DOUBLE,
        EMAIL,
        DATE,
        DROPDOWN,
        BOOLEAN,
        DATETIME,
        MULTISELECT,
        PHOTO,
        TEXT_UPPERCASE,
        TIME,
        BIG_DECIMAL,
    }
}
