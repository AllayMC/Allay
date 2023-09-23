package cn.allay.dataupdater;

import cn.allay.api.block.interfaces.air.BlockAirBehavior;
import cn.allay.api.block.palette.BlockStateHashPalette;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.mapping.JeBlockState;
import cn.allay.api.mapping.Mapping;
import cn.allay.server.Allay;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

/**
 * Allay Project 2023/9/8
 *
 * @author Cool_Loong
 */
public class MappingValidator {

    @SneakyThrows
    public static void main(String[] args) {
        Allay.initAllayAPI();
        Mapping.getJeBiomeName(1);
        Field je2BeBlocksMapping = Mapping.class.getDeclaredField("JE_2_BE_BLOCKS_MAPPING");
        je2BeBlocksMapping.setAccessible(true);
        Object2IntOpenHashMap<JeBlockState> o = (Object2IntOpenHashMap<JeBlockState>) je2BeBlocksMapping.get(Mapping.class);
        for (var entry : o.object2IntEntrySet()) {
            BlockState blockState = BlockStateHashPalette.getRegistry().get(entry.getIntValue());
            if (blockState == null) {
                JeBlockState key = entry.getKey();
                System.out.println(key);
            }
        }
        System.out.println(BlockAirBehavior.AIR_TYPE.getDefaultState().blockStateHash());
    }
}