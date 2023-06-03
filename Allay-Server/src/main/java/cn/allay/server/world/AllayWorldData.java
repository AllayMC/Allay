package cn.allay.server.world;

import cn.allay.api.math.vector.Vec3;
import cn.allay.api.world.WorldData;
import cn.allay.api.world.WorldFormat;
import cn.allay.api.world.dimension.DimensionData;
import cn.allay.api.world.gamerule.GameRules;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.*;
import java.util.Arrays;

/**
 * Author: Cool_Loong <br>
 * Date: 5/31/2023 <br>
 * Allay Project
 */
@Slf4j
public class AllayWorldData implements WorldData {
    private final File file;
    private NbtMap data;

    public AllayWorldData(File levelDat, WorldFormat format) {
        this.file = levelDat;
        if (format == WorldFormat.ANVIL) {
            try (var input = NbtUtils.createGZIPReader(new FileInputStream(levelDat))) {
                this.data = ((NbtMap) input.readTag()).getCompound("Data");
            } catch (FileNotFoundException e) {
                log.error("The level.dat file does not exist!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (format == WorldFormat.LEVELDB) {
            try (var input = new FileInputStream(levelDat)) {
                //The first 8 bytes are magic number
                input.skip(8);
                NBTInputStream readerLE = NbtUtils.createReaderLE(new ByteArrayInputStream(input.readAllBytes()));
                this.data =  (NbtMap) readerLE.readTag();
                readerLE.close();
            } catch (FileNotFoundException e) {
                log.error("The level.dat file does not exist!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public NbtMap getNBT() {
        return data;
    }

    @Override
    public long getRandomSeed() {
        return 0;
    }

    @Override
    public Vec3<Integer> getSpawnPoint() {
        return null;
    }

    @Override
    public int getRainTime() {
        return 0;
    }

    @Override
    public int getThunderTime() {
        return 0;
    }

    @Override
    public String getWorldName() {
        return null;
    }

    @Override
    public long getTime() {
        return 0;
    }

    @Override
    public int getDifficulty() {
        return 0;
    }

    @Override
    public int getGameType() {
        return 0;
    }

    @Override
    public GameRules getGameRules() {
        return null;
    }

    @Override
    public DimensionData getDimensionData() {
        return null;
    }
}
