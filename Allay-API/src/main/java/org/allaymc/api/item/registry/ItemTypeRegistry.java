package org.allaymc.api.item.registry;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.registry.MappedRegistry;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;

import java.util.List;
import java.util.Map;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public interface ItemTypeRegistry extends MappedRegistry<Identifier, ItemType<?>, Map<Identifier, ItemType<?>>> {

    ApiInstanceHolder<ItemTypeRegistry> REGISTRY = ApiInstanceHolder.create();

    static ItemTypeRegistry getRegistry() {
        return REGISTRY.get();
    }

    List<ItemDefinition> getItemDefinitions();

    void rebuildDefinitionList();
}
