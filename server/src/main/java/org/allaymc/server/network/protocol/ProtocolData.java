package org.allaymc.server.network.protocol;

import org.cloudburstmc.protocol.bedrock.data.BlockPropertyData;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemGroup;

import java.util.List;
import java.util.Objects;

/**
 * Immutable protocol-specific data produced during protocol initialization.
 */
public record ProtocolData(
        List<ItemDefinition> itemDefinitions,
        List<BlockDefinition> blockDefinitions,
        List<CreativeItemGroup> creativeGroups,
        List<CreativeItemData> creativeItems,
        List<BlockPropertyData> customBlockProperties,
        RecipeTable recipeTable
) {
    public ProtocolData {
        itemDefinitions = List.copyOf(Objects.requireNonNull(itemDefinitions, "itemDefinitions"));
        blockDefinitions = List.copyOf(Objects.requireNonNull(blockDefinitions, "blockDefinitions"));
        creativeGroups = List.copyOf(Objects.requireNonNull(creativeGroups, "creativeGroups"));
        creativeItems = List.copyOf(Objects.requireNonNull(creativeItems, "creativeItems"));
        customBlockProperties = List.copyOf(Objects.requireNonNull(customBlockProperties, "customBlockProperties"));
        recipeTable = Objects.requireNonNull(recipeTable, "recipeTable");
    }
}
