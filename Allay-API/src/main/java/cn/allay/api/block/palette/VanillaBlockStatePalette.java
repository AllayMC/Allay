package cn.allay.api.block.palette;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.registry.MappedRegistry;

import java.util.Map;

/**
 * @author daoge_cmd <br>
 * @date 2023/4/8 <br>
 * Allay Project <br>
 * <p>
 * TODO: Replaced by blockStateHash, the block runtime id is not used anymore.
 */
@Deprecated
public interface VanillaBlockStatePalette extends MappedRegistry<BlockState, Integer, Map<BlockState, Integer>> {
    ApiInstanceHolder<VanillaBlockStatePalette> REGISTRY = ApiInstanceHolder.of();

    static VanillaBlockStatePalette getRegistry() {
        return REGISTRY.get();
    }
}
