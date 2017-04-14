package com.mrhabibi.logman;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.util.Log;

import static android.util.Log.DEBUG;
import static android.util.Log.ERROR;
import static android.util.Log.INFO;
import static android.util.Log.VERBOSE;
import static android.util.Log.WARN;

/**
 * Created by mrhabibi on 4/14/17.
 */

public class Logman {

    private static Logman instance;

    private final String defaultTag;
    private final boolean debugOnly;
    private final boolean debugMode;

    private Logman(Context context, boolean debugOnly) {
        this.debugOnly = debugOnly;
        this.debugMode = (0 != (context.getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE));
        this.defaultTag = context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
    }

    private boolean isActive() {
        return !debugOnly || debugMode;
    }

    public static void init(Context context) {
        init(context, true);
    }

    public static void init(Context context, boolean logInDebugOnly) {
        instance = new Logman(context, logInDebugOnly);
    }

    // VERBOSE

    public static void v(@Size(min = 1, max = 23) String tag, MessageHolder holder) {
        proceedLog(VERBOSE, tag, holder);
    }

    public static void v(MessageHolder holder) {
        proceedLog(VERBOSE, null, holder);
    }

    public static void v(@Size(min = 1, max = 23) String tag, String message) {
        proceedLog(VERBOSE, tag, message);
    }

    public static void v(String message) {
        proceedLog(VERBOSE, null, message);
    }

    // DEBUG

    public static void d(@Size(min = 1, max = 23) String tag, MessageHolder holder) {
        proceedLog(DEBUG, tag, holder);
    }

    public static void d(MessageHolder holder) {
        proceedLog(DEBUG, null, holder);
    }

    public static void d(@Size(min = 1, max = 23) String tag, String message) {
        proceedLog(DEBUG, tag, message);
    }

    public static void d(String message) {
        proceedLog(DEBUG, null, message);
    }

    // INFO

    public static void i(@Size(min = 1, max = 23) String tag, MessageHolder holder) {
        proceedLog(INFO, tag, holder);
    }

    public static void i(MessageHolder holder) {
        proceedLog(INFO, null, holder);
    }

    public static void i(@Size(min = 1, max = 23) String tag, String message) {
        proceedLog(INFO, tag, message);
    }

    public static void i(String message) {
        proceedLog(INFO, null, message);
    }

    // WARN

    public static void w(@Size(min = 1, max = 23) String tag, MessageHolder holder) {
        proceedLog(WARN, tag, holder);
    }

    public static void w(MessageHolder holder) {
        proceedLog(WARN, null, holder);
    }

    public static void w(@Size(min = 1, max = 23) String tag, String message) {
        proceedLog(WARN, tag, message);
    }

    public static void w(String message) {
        proceedLog(WARN, null, message);
    }

    // ERROR

    public static void e(@Size(min = 1, max = 23) String tag, MessageHolder holder) {
        proceedLog(ERROR, tag, holder);
    }

    public static void e(MessageHolder holder) {
        proceedLog(ERROR, null, holder);
    }

    public static void e(@Size(min = 1, max = 23) String tag, String message) {
        proceedLog(ERROR, tag, message);
    }

    public static void e(String message) {
        proceedLog(ERROR, null, message);
    }

    private static void proceedLog(int logType, @Nullable String tag, final Object message) {
        proceedLog(logType, tag, new MessageHolder() {
            @Override
            public Object getMessage() {
                return message;
            }
        });
    }

    private static void proceedLog(int logType, @Nullable String tag, MessageHolder holder) {
        if (instance == null) {
            throw new IllegalStateException("Logman hasn't initialized yet, have you called Logman.init(context)?");
        }
        if (instance.isActive()) {
            if (tag == null) tag = instance.defaultTag;
            String message = String.valueOf(holder.getMessage());
            switch (logType) {
                case VERBOSE:
                    Log.v(tag, message);
                    break;
                case DEBUG:
                    Log.d(tag, message);
                    break;
                case INFO:
                    Log.i(tag, message);
                    break;
                case WARN:
                    Log.w(tag, message);
                    break;
                case ERROR:
                    Log.e(tag, message);
                    break;
            }
        }
    }
}
