package org.allaymc.server.world.generator.jegen;

import io.papermc.paperclip.Paperclip;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.server.Allay;

import java.io.File;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.nio.file.Path;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public final class JeGeneratorLoader {
    private static final AtomicBoolean loaded = new AtomicBoolean(false);
    public static final String WORK_PATH = "jegenerator";
    private static MethodHandle OVERWORLD;
    private static MethodHandle NETHER;
    private static MethodHandle THE_END;

    public static void setup() {
        File file = Path.of(WORK_PATH).toFile();
        if (!file.exists()) {
            file.mkdirs();
        }
        Paperclip.setup(Allay.EXTRA_RESOURCE_CLASS_LOADER, new String[]{WORK_PATH, "--noconsole", "--nogui", "--universe=jegenerator"});
        try {
            final Class<?> JeGeneratorMain = Class.forName("org.allaymc.jegenerator.JeGeneratorMain", true, Allay.EXTRA_RESOURCE_CLASS_LOADER);
            final MethodType methodType = MethodType.methodType(WorldGenerator.class);
            OVERWORLD = MethodHandles.lookup()
                    .findStatic(JeGeneratorMain, "overworld", methodType)
                    .asFixedArity();
            NETHER = MethodHandles.lookup()
                    .findStatic(JeGeneratorMain, "nether", methodType)
                    .asFixedArity();
            THE_END = MethodHandles.lookup()
                    .findStatic(JeGeneratorMain, "end", methodType)
                    .asFixedArity();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static void waitStart() {
        Utils.spinUntil(() -> !System.getProperties().getOrDefault("complete_start", false).equals("true"), Duration.of(20, ChronoUnit.MILLIS));
    }

    public static WorldGenerator getJeGenerator(DimensionInfo info) {
        if (loaded.compareAndSet(false, true)) {
            JeGeneratorLoader.setup();
            JeGeneratorLoader.waitStart();
        }
        try {
            if (info == DimensionInfo.NETHER) {
                return (WorldGenerator) NETHER.invokeExact();
            } else if (info == DimensionInfo.THE_END) {
                return (WorldGenerator) THE_END.invokeExact();
            } else {
                return (WorldGenerator) OVERWORLD.invokeExact();
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
