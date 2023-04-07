package cn.allay.block.property;

import cn.allay.block.property.type.BlockPropertyType;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.registry.SimpleMappedRegistry;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
public final class BlockPropertyTypeRegistry extends SimpleMappedRegistry<String, BlockPropertyType<?>, Map<String, BlockPropertyType<?>>> {

    private static final BlockPropertyTypeRegistry INSTANCE = new BlockPropertyTypeRegistry();
    private BlockPropertyTypeRegistry() {
        super(null, input -> {
            Map<String, BlockPropertyType<?>> map = new HashMap<>();
            for (BlockPropertyType<?> property : VanillaBlockPropertyTypes.values()) {
                map.put(property.getName(), property);
            }
            return map;
        });
    }

    public static BlockPropertyTypeRegistry getInstance() {
        return INSTANCE;
    }
}
