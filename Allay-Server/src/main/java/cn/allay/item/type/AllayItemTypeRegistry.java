package cn.allay.item.type;

import cn.allay.identifier.Identifier;
import cn.allay.registry.SimpleMappedRegistry;
import lombok.SneakyThrows;

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

    @SneakyThrows
    public void init() {
        //TODO: load vanilla item types
    }
}
