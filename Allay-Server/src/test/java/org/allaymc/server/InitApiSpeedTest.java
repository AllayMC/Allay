package org.allaymc.server;

import org.allaymc.api.common.exception.MissingImplementationException;

public class InitApiSpeedTest {
    public static void main(String[] args) throws MissingImplementationException {
        long start = System.currentTimeMillis();
        Allay.initAllayAPI();
        System.out.println("time: " + ((System.currentTimeMillis() - start) / 1000d) + "s");
    }
}
