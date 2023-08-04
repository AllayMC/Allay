package cn.allay.server;

import cn.allay.api.MissingImplementationException;
import cn.allay.server.Allay;

public class InitApiSpeedTest {
    public static void main(String[] args) throws MissingImplementationException {
        long start = System.currentTimeMillis();
        Allay.initAllayAPI();
        System.out.println("time: " + ((System.currentTimeMillis() - start) / 1000d) + "s");
    }
}
