package cn.allay.mapping.generator;

import cn.allay.api.MissingImplementationException;
import cn.allay.server.Allay;
import net.minecraft.SharedConstants;
import net.minecraft.server.Bootstrap;

import java.io.PrintStream;

public class Main {
    public static void init() {
        try {
            Allay.initAllayAPI();
        } catch (MissingImplementationException e) {
            throw new RuntimeException(e);
        }
        PrintStream err = System.err;
        PrintStream out = System.out;
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();

        // Revert this stupid thing that the Bootstrap process does
        System.setErr(err);
        System.setOut(out);
    }

    public static void main(String[] args) {
        init();
        MappingsGenerator generator = new MappingsGenerator();
        generator.generateBlocks();
    }
}
