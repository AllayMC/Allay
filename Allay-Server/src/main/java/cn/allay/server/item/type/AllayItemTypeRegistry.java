package cn.allay.server.item.type;

import cn.allay.api.data.VanillaItemTypes;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeRegistry;
import cn.allay.api.registry.SimpleMappedRegistry;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayItemTypeRegistry extends SimpleMappedRegistry<Identifier, ItemType<?>, Map<Identifier, ItemType<?>>> implements ItemTypeRegistry {
    public AllayItemTypeRegistry() {
        super(null, input -> new HashMap<>());
    }

    @SneakyThrows
    public void init() {
        var fields = VanillaItemTypes.class.getDeclaredFields();
        log.info("Loading Item Types...");
        fields[0].get(null);
        log.info("Loaded " + fields.length + " Item Types");
        rebuildDefinitionList();
    }

    private final List<ItemDefinition> itemDefinitions = new ArrayList<>();

    @Override
    public List<ItemDefinition> getItemDefinitions() {
        return itemDefinitions;
    }

    @Override
    public void rebuildDefinitionList() {
        itemDefinitions.clear();
        for (var itemType : this.getContent().values()) {
            itemDefinitions.add(itemType.toNetworkDefinition());
        }
    }
}
