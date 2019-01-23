package com.prestashop.utilities;

public class BrowserUtilities {


    public static final boolean ISWINDOWS;
    public static final boolean ISMAC;

    static {
        ISWINDOWS = System.getProperty("os.name").toLowerCase().contains("windows");
        ISMAC = System.getProperty("os.name").toLowerCase().contains("mac");
    }


    public static void wait(int secs) {
        try {
            Thread.sleep(1000 * secs);
        }
        catch (InterruptedException e) {

        }
    }



}
