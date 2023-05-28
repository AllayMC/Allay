package cn.allay.server.item.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeRegistry;
import cn.allay.api.registry.SimpleMappedRegistry;
import cn.allay.server.utils.PackageClassLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/19 <br>
 * Allay Project <br>
 */
public class AllayItemTypeRegistry extends SimpleMappedRegistry<Identifier, ItemType<?>, Map<Identifier, ItemType<?>>> implements ItemTypeRegistry {
    public AllayItemTypeRegistry() {
        super(null, input -> new HashMap<>());
    }

    public void init() {
        PackageClassLoader.loadClasses("cn.allay.item.impl");
    }
}
