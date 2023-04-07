package cn.allay.block.property;

import cn.allay.block.property.type.BlockProperty;
import cn.allay.block.property.vanilla.VanillaBlockProperties;
import cn.allay.registry.SimpleMappedRegistry;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
public final class BlockPropertyRegistry extends SimpleMappedRegistry<String, BlockProperty<?>, Map<String, BlockProperty<?>>> {

    private static final BlockPropertyRegistry INSTANCE = new BlockPropertyRegistry();
    private BlockPropertyRegistry() {
        super(null, input -> {
            Map<String, BlockProperty<?>> map = new HashMap<>();
            for (BlockProperty<?> property : VanillaBlockProperties.values()) {
                map.put(property.getName(), property);
            }
            return map;
        });
    }

    public static BlockPropertyRegistry getInstance() {
        return INSTANCE;
    }
}
