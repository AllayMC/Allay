package cn.allay.entity.type;

import cn.allay.identifier.Identifier;
import cn.allay.registry.SimpleMappedRegistry;
import cn.allay.utils.PackageClassLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/26 <br>
 * Allay Project <br>
 */
public class AllayEntityTypeRegistry extends SimpleMappedRegistry<Identifier, EntityType<?>, Map<Identifier, EntityType<?>>> implements EntityTypeRegistry {
    public <INPUT> AllayEntityTypeRegistry() {
        super(null, input -> new HashMap<>());
    }

    public void init() {
        PackageClassLoader.loadClasses("cn.allay.entity.impl");
    }
}
