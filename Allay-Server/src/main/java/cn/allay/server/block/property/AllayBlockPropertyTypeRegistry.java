package cn.allay.server.block.property;

import cn.allay.api.block.property.BlockPropertyTypeRegistry;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.data.VanillaBlockPropertyTypes;
import cn.allay.api.registry.SimpleMappedRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
public final class AllayBlockPropertyTypeRegistry extends SimpleMappedRegistry<String, BlockPropertyType<?>, Map<String, BlockPropertyType<?>>> implements BlockPropertyTypeRegistry {
    public AllayBlockPropertyTypeRegistry() {
        super(null, input -> new HashMap<>());
        //Load vanilla property types
        VanillaBlockPropertyTypes.values().forEach(property -> property.registerTo(this));
    }
}
