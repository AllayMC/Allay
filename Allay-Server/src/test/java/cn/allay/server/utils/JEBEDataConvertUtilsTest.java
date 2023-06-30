package cn.allay.server.utils;

import cn.allay.api.utils.JEBEDataConvertUtils;
import org.cloudburstmc.nbt.NbtMap;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

/**
 * Allay Project 2023/6/7
 *
 * @author Cool_Loong
 */
public class JEBEDataConvertUtilsTest {
    @Test
    void testConvertJEBlockState() {
        var states = new TreeMap<String, Object>();
        states.put("age_bit", true);
        states.put("sapling_type", "acacia");
        NbtMap beState = NbtMap.builder().putString("name", "minecraft:sapling")
                .putCompound("states", NbtMap.fromMap(states)).build();
        NbtMap nbtMap = JEBEDataConvertUtils.convertToJEBlockState(beState);
        System.out.println(beState);
        System.out.println(nbtMap);
    }
}
