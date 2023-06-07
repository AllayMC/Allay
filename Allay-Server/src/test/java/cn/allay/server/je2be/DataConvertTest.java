package cn.allay.server.je2be;

import cn.allay.api.je2be.DataConvert;
import org.cloudburstmc.nbt.NbtMap;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

/**
 * Author: Cool_Loong <br>
 * Date: 6/7/2023 <br>
 * Allay Project
 */
public class DataConvertTest {
    @Test
    void testConvertJEBlockState() {
        var states = new TreeMap<String, Object>();
        states.put("age_bit", true);
        states.put("sapling_type", "acacia");
        NbtMap beState = NbtMap.builder().putString("name", "minecraft:sapling")
                .putCompound("states", NbtMap.fromMap(states)).build();
        NbtMap nbtMap = DataConvert.convertJEBlockState(beState);
        System.out.println(nbtMap);
    }
}
