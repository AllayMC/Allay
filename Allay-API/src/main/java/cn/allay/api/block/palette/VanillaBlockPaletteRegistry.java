package cn.allay.api.block.palette;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.registry.MappedRegistry;

import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 * <p>
 * TODO: Replaced by blockStateHash, the block runtime id is not used anymore.
 */
@Deprecated
public interface VanillaBlockPaletteRegistry extends MappedRegistry<VanillaBlockId, VanillaBlockPaletteDataEntry, Map<VanillaBlockId, VanillaBlockPaletteDataEntry>> {
    ApiInstanceHolder<VanillaBlockPaletteRegistry> REGISTRY = ApiInstanceHolder.of();

    static VanillaBlockPaletteRegistry getRegistry() {
        return REGISTRY.get();
    }
}
