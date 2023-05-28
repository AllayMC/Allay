package cn.allay.block.type;

import cn.allay.identifier.Identifier;
import cn.allay.registry.SimpleMappedRegistry;
import cn.allay.utils.PackageClassLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/26 <br>
 * Allay Project <br>
 */
public final class AllayBlockTypeRegistry extends SimpleMappedRegistry<Identifier, BlockType<?>, Map<Identifier, BlockType<?>>> implements BlockTypeRegistry {
    public AllayBlockTypeRegistry() {
        super(null, input -> new HashMap<>());
    }

    public void init() {
        PackageClassLoader.loadClasses("cn.allay.block.impl");
    }
}
