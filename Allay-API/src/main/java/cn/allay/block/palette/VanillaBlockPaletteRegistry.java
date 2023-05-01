package cn.allay.block.palette;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.registry.MappedRegistry;

import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 *
 * TODO: Replaced by blockStateHash, the block runtime id is not used anymore.
 */
public interface VanillaBlockPaletteRegistry extends MappedRegistry<VanillaBlockId, VanillaBlockPaletteDataEntry, Map<VanillaBlockId, VanillaBlockPaletteDataEntry>> {
    ApiInstanceHolder<VanillaBlockPaletteRegistry> REGISTRY = ApiInstanceHolder.create();

    static VanillaBlockPaletteRegistry getRegistry() {
        return REGISTRY.get();
    }
}
