package cn.allay.worldconverter;

import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.gamerule.GameRule;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.jglrxavpok.hephaistos.nbt.*;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "",
        mixinStandardHelpOptions = true,
        description = "Usage examples: \n" + "java -jar pnxworldconvert.jar -t D:mc\\.minecraft\\save\\new world -d OVERWORLD"
)
@Slf4j
public class WorldConverter implements Callable<Integer> {
    @CommandLine.Option(names = {"-t", "--target"}, paramLabel = "path", description = "The path of convert target world", defaultValue = "./world", required = true)
    private Path path = Path.of("./world");

    @CommandLine.Option(names = {"-d", "--dimension"}, paramLabel = "dimension", description = "Valid values: ${COMPLETION-CANDIDATES}", required = true)
    private Dimension dimension;

    @CommandLine.Option(names = {"-n", "--name"}, paramLabel = "levelName", description = "The name of World")
    private String levelName;

    public static void main(String[] args) {
        var mainClass = new WorldConverter();
        if (args.length == 0) {
            String s = new CommandLine(mainClass).getUsageMessage();
            System.out.println(s);
            close(1);
        }
        close(new CommandLine(mainClass).execute(args));
    }

    public static void close(int status) {
        System.exit(status);
    }

    public Path createOutputFolder(String levelName) {
        Path output = Path.of("output").resolve(levelName);
        File file = output.toFile();
        if (!file.exists()) {
            file.mkdirs();
        }
        Path region = output.resolve("region");
        File regionFile = region.toFile();
        if (!regionFile.exists()) {
            regionFile.mkdirs();
        }
        return output;
    }

    public Path convertLevelDat() {
        Path levelDat = path.resolve("level.dat");
        Preconditions.checkArgument(path.toFile().isDirectory());
        Preconditions.checkArgument(levelDat.toFile().exists());
        NBTCompound result = null;
        try (NBTReader reader = new NBTReader(levelDat, CompressedProcesser.GZIP)) {
            NBTCompound tag = ((NBTCompound) reader.read()).getCompound("Data");
            NBTCompound gameRulesNbt = tag.getCompound("GameRules");
            var gameRules = NBT.Compound(data -> {
                for (var entry : gameRulesNbt.asMapView().entrySet()) {
                    GameRule gameRule = GameRule.fromName(entry.getKey());
                    if (gameRule != null) {
                        data.set(entry.getKey(), entry.getValue());
                    }
                }
            });
            DimensionInfo dim = dimension.getDimensionInfo();
            var dimensionInfo = NBT.Compound((root) -> root.setInt("dimensionId", dim.dimensionId())
                    .setInt("minHeight", dim.minHeight())
                    .setInt("maxHeight", dim.maxHeight())
                    .setInt("chunkSectionSize", dim.chunkSectionSize()));
            if (levelName == null) {
                levelName = tag.getString("LevelName");
            }
            final String copyLevelName = levelName;
            result = NBT.Compound((root) -> root.set("GameRules", gameRules)
                    .set("DimensionInfo", dimensionInfo)
                    .setInt("SpawnX", tag.getAsInt("SpawnX"))
                    .setInt("SpawnY", tag.getAsInt("SpawnY"))
                    .setInt("SpawnZ", tag.getAsInt("SpawnZ"))
                    .setInt("Difficulty", tag.getAsInt("Difficulty"))
                    .setInt("version", tag.getAsInt("version"))
                    .setInt("GameType", tag.getAsInt("GameType"))
                    .setLong("thunderTime", tag.getAsLong("thunderTime"))
                    .setLong("rainTime", tag.getAsLong("rainTime"))
                    .setLong("Time", tag.getLong("Time"))
                    .setInt("tickingRadius", 8)
                    .setInt("viewDistance", 8)
                    .setString("LevelName", copyLevelName)
                    .setString("generatorOptions", "")
                    .setLong("seed", tag.getCompound("WorldGenSettings").getLong("seed"))
                    .setByte("allay", (byte) 1));

        } catch (IOException | NBTException e) {
            log.error(e.getCause().getMessage());
            close(1);
        }
        Path output = createOutputFolder(levelName);
        try (NBTWriter writer = new NBTWriter(output.resolve("level.dat"), CompressedProcesser.GZIP)) {
            writer.writeNamed("", result);
        } catch (IOException e) {
            log.error(e.getCause().getMessage());
            close(1);
        }
        return output;
    }

    //Zero often means success, a non-zero exit code is often used for errors
    @Override
    public Integer call() {
        if (!path.toFile().exists()) {
            return 1;
        }
        convertLevelDat();
        ConvertFactory.of(path, convertLevelDat(), dimension);
        return 0;
    }
}
