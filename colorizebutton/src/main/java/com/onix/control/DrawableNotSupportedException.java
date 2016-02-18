package com.onix.control;


public class DrawableNotSupportedException extends Exception {
    private String mMessage;

    public DrawableNotSupportedException(String message) {
        mMessage = message;
    }

    public String toString() {
        return DrawableNotSupportedException.class.getCanonicalName() + "[" + mMessage + "]";
    }

    public String getMessage() {
        return mMessage;
    }
}
