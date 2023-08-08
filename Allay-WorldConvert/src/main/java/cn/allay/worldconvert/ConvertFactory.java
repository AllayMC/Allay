package cn.allay.worldconvert;

import cn.allay.worldconvert.tasks.VanillaRegionConvertTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.jglrxavpok.hephaistos.mca.AnvilException;
import org.jglrxavpok.hephaistos.mca.RegionFile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;

@Slf4j
public class ConvertFactory {
    private static final ForkJoinPool THREAD_POOL = new ForkJoinPool();

    public static void of(Path input, Path output, Dimension dimension) {
        input = switch (dimension) {
            case OVERWORLD -> input.resolve("region");
            case NETHER -> input.resolve("DIM1").resolve("region");
            case THE_END -> input.resolve("DIM-1").resolve("region");
        };
        var files = input.toFile();
        if (!files.exists()) {
            log.error("The region folder does not exist!");
            WorldConverter.close(1);
        }
        try {
            File target = output.resolve("region").toFile();
            FileUtils.copyDirectory(files, target);
            for (var file : Objects.requireNonNull(target.listFiles())) {
                try {
                    String name = file.getName();
                    String[] split = name.split("\\.");
                    RegionFile region = new RegionFile(new RandomAccessFile(file, "rw"), Integer.parseInt(split[1]), Integer.parseInt(split[2]), dimension.getDimensionInfo().minHeight(), dimension.getDimensionInfo().maxHeight());
                    THREAD_POOL.submit(new VanillaRegionConvertTask(region, 1023));
                } catch (AnvilException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
