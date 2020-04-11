package org.vlcervera.version.utils;

import java.util.concurrent.TimeUnit;

public class TimeUtilities {

    public static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
