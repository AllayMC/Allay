package cn.allay.server.world.leveldb;

import cn.allay.api.math.vector.Vec3;
import cn.allay.api.world.WorldData;
import cn.allay.api.world.WorldFormat;
import cn.allay.api.world.dimension.DimensionData;
import cn.allay.api.world.gamerule.GameRules;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.*;

/**
 * Author: Cool_Loong <br>
 * Date: 6/3/2023 <br>
 * Allay Project
 */
/*@Slf4j
public class AllayLevelDBWorldData{
    private NbtMap data;

    public AllayLevelDBWorldData(File levelDat) {
        try (var input = new FileInputStream(levelDat)) {
            //The first 8 bytes are magic number
            input.skip(8);
            NBTInputStream readerLE = NbtUtils.createReaderLE(new ByteArrayInputStream(input.readAllBytes()));
            this.data = (NbtMap) readerLE.readTag();
            readerLE.close();
        } catch (FileNotFoundException e) {
            log.error("The level.dat file does not exist!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}*/
