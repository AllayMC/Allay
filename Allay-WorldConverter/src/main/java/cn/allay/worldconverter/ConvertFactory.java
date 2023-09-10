package cn.allay.worldconverter;

import cn.allay.server.world.storage.rocksdb.RocksDBWorldStorage;
import cn.allay.worldconverter.tasks.VanillaRegionConvertTask;
import lombok.extern.slf4j.Slf4j;
import org.jglrxavpok.hephaistos.mca.AnvilException;
import org.jglrxavpok.hephaistos.mca.RegionFile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ConvertFactory {
    private static final ForkJoinPool THREAD_POOL = (ForkJoinPool) Executors.newWorkStealingPool();

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
        var s = new RocksDBWorldStorage(output);
        for (var f : Objects.requireNonNull(files.listFiles())) {
            try {
                String name = f.getName();
                String[] split = name.split("\\.");
                RegionFile srcRegion = new RegionFile(new RandomAccessFile(f, "r"), Integer.parseInt(split[1]), Integer.parseInt(split[2]), dimension.getDimensionInfo().minHeight(), dimension.getDimensionInfo().maxHeight());
                THREAD_POOL.execute(new VanillaRegionConvertTask(srcRegion, s, dimension.getDimensionInfo()));
            } catch (AnvilException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        THREAD_POOL.awaitQuiescence(1, TimeUnit.DAYS);
    }
}
