package cn.allay.block.data;

import cn.allay.block.component.BlockDataComponent;
import cn.allay.identifier.Identifier;
import cn.allay.registry.RegistryLoader;
import cn.allay.registry.SimpleMappedRegistry;

import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/26 <br>
 * Allay Project <br>
 */
public final class VanillaBlockPalette extends SimpleMappedRegistry<Identifier, BlockDataComponent, Map<Identifier, BlockDataComponent>> {

    private VanillaBlockPalette(Object o, RegistryLoader registryLoader) {
        super(o, registryLoader);
    }
}
