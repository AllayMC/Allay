package cn.allay.server;

import cn.allay.api.MissingImplementationException;
import org.junit.jupiter.api.Test;

public class InitApiSpeedTest {
    @Test
    void start() throws MissingImplementationException {
        long start = System.currentTimeMillis();
        Allay.initAllayAPI();
        System.out.println("time: " + ((System.currentTimeMillis() - start) / 1000d) + "s");
    }
}
