package cn.allay.block.palette;

import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.type.BlockPropertyType;

import java.util.List;
import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
public record VanillaBlockPaletteDataEntry(
        VanillaBlockId blockId,
        List<BlockPropertyType<?>> propertyTypes,
        Map<List<BlockPropertyType.BlockPropertyValue<?, ?>>, Integer> runtimeIdMap,
        int version) {

}
