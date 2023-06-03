package cn.allay.server.world.anvil;

import cn.allay.api.math.vector.Vec3;
import cn.allay.api.world.WorldData;
import cn.allay.api.world.WorldFormat;
import cn.allay.api.world.dimension.DimensionData;
import cn.allay.api.world.gamerule.GameRules;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Author: Cool_Loong <br>
 * Date: 5/31/2023 <br>
 * Allay Project
 */
/*
@Slf4j
public class AllayAnvilWorldData implements WorldData {
    private final File file;
    private NbtMap data;

    public AllayAnvilWorldData(File levelDat) {
        this.file = levelDat;
        try (var input = NbtUtils.createGZIPReader(new FileInputStream(levelDat))) {
            this.data = ((NbtMap) input.readTag()).getCompound("Data");
        } catch (FileNotFoundException e) {
            log.error("The level.dat file does not exist!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
*/
