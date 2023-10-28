package cn.allay.api.item.registry;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.registry.MappedRegistry;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;

import java.util.List;
import java.util.Map;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public interface ItemTypeRegistry extends MappedRegistry<Identifier, ItemType<?>, Map<Identifier, ItemType<?>>> {

    ApiInstanceHolder<ItemTypeRegistry> REGISTRY = ApiInstanceHolder.of();

    static ItemTypeRegistry getRegistry() {
        return REGISTRY.get();
    }

    List<ItemDefinition> getItemDefinitions();

    void rebuildDefinitionList();
}
