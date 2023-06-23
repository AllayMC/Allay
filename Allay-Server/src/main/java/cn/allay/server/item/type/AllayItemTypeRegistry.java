package cn.allay.server.item.type;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeRegistry;
import cn.allay.api.item.type.VanillaItemTypes;
import cn.allay.api.registry.SimpleMappedRegistry;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

/**
 * @author daoge_cmd <br>
 * @date 2023/5/19 <br>
 * Allay Project <br>
 */
public class AllayItemTypeRegistry extends SimpleMappedRegistry<Identifier, ItemType<?>, Map<Identifier, ItemType<?>>> implements ItemTypeRegistry {
    public AllayItemTypeRegistry() {
        super(null, input -> new HashMap<>());
    }

    @SneakyThrows
    public void init() {
        for (var field : VanillaItemTypes.class.getDeclaredFields()) {
            var loaded = field.get(null);
        }
    }
}
