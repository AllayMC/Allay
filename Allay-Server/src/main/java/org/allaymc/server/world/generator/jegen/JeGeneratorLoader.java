package org.allaymc.server.world.generator.jegen;

import io.papermc.paperclip.Paperclip;
import me.sunlan.fastreflection.FastMethod;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.generator.Generator;
import org.allaymc.server.Allay;

import java.io.File;
import java.nio.file.Path;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public final class JeGeneratorLoader {
    public static final String WORK_PATH = "jegenerator";
    private static FastMethod overworld;
    private static FastMethod nether;
    private static FastMethod end;
    private static Class<?> JeGeneratorMain;

    public static void setup() {
        File file = Path.of(WORK_PATH).toFile();
        if (!file.exists()) {
            file.mkdirs();
        }
        Paperclip.setup(Allay.EXTRA_RESOURCE_CLASS_LOADER, new String[]{WORK_PATH, "--noconsole", "--nogui", "--universe=jegenerator"});
        try {
            JeGeneratorMain = Class.forName("org.allaymc.jegenerator.JeGeneratorMain", true, Allay.EXTRA_RESOURCE_CLASS_LOADER);
            overworld = FastMethod.create(JeGeneratorMain.getMethod("overworld", Generator.class));
            nether = FastMethod.create(JeGeneratorMain.getMethod("nether", Generator.class));
            end = FastMethod.create(JeGeneratorMain.getMethod("end", Generator.class));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static void waitStart() {
        Utils.spinUntil(() -> !System.getProperties().getOrDefault("complete_start", false).equals("true"), Duration.of(20, ChronoUnit.MILLIS));
    }

    //todo add safe stop
    public static Generator getJeGenerator(DimensionInfo info) {
        try {
            if (info == DimensionInfo.NETHER) {
                return (Generator) nether.invoke(JeGeneratorMain);
            } else if (info == DimensionInfo.THE_END) {
                return (Generator) end.invoke(JeGeneratorMain);
            } else {
                return (Generator) overworld.invoke(JeGeneratorMain);
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
