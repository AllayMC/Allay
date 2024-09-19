package org.allaymc.server;

import org.allaymc.api.MissingImplementationException;

public class InitApiSpeedTest {
    public static void main(String[] args) throws MissingImplementationException {
        long start = System.currentTimeMillis();
        Allay.initAllay();
        System.out.println("time: " + ((System.currentTimeMillis() - start) / 1000d) + "s");
    }
}
