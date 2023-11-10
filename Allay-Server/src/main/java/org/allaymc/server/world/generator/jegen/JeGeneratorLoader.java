package org.allaymc.server.world.generator.jegen;

import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.server.Allay;
import io.papermc.paperclip.Paperclip;

import java.io.File;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.nio.file.Path;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public final class JeGeneratorLoader {
    public static final String WORK_PATH = "jegenerator";
    private static MethodHandle overworld;
    private static MethodHandle nether;
    private static MethodHandle end;

    public static void setup() {
        File file = Path.of(WORK_PATH).toFile();
        if (!file.exists()) {
            file.mkdirs();
        }
        Paperclip.setup(Allay.EXTRA_RESOURCE_CLASS_LOADER, new String[]{WORK_PATH, "--noconsole", "--nogui", "--universe=jegenerator"});
        try {
            final Class<?> JeGeneratorMain = Class.forName("org.allaymc.jegenerator.JeGeneratorMain", true, Allay.EXTRA_RESOURCE_CLASS_LOADER);
            final MethodType methodType = MethodType.methodType(WorldGenerator.class);
            overworld = MethodHandles.lookup()
                    .findStatic(JeGeneratorMain, "overworld", methodType)
                    .asFixedArity();
            nether = MethodHandles.lookup()
                    .findStatic(JeGeneratorMain, "nether", methodType)
                    .asFixedArity();
            end = MethodHandles.lookup()
                    .findStatic(JeGeneratorMain, "end", methodType)
                    .asFixedArity();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static void waitStart() {
        Utils.spinUntil(() -> !System.getProperties().getOrDefault("complete_start", false).equals("true"), Duration.of(20, ChronoUnit.MILLIS));
    }

    //todo add safe stop

    public static WorldGenerator getJeGenerator(DimensionInfo info) {
        try {
            if (info == DimensionInfo.NETHER) {
                return (WorldGenerator) nether.invokeExact();
            } else if (info == DimensionInfo.THE_END) {
                return (WorldGenerator) end.invokeExact();
            } else {
                return (WorldGenerator) overworld.invokeExact();
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
