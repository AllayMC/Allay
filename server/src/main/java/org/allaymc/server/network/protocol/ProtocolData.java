package org.allaymc.server.network.protocol;

import org.cloudburstmc.protocol.bedrock.data.BlockPropertyData;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemGroup;

import java.util.List;
import java.util.Objects;

/**
 * {@code ProtocolData} represents the definitions, creative inventory, custom
 * blocks, and recipe table prepared for one protocol. The constructor copies
 * {@code itemDefinitions}, {@code blockDefinitions}, {@code creativeGroups},
 * {@code creativeItems}, and {@code customBlockProperties} into unmodifiable
 * list containers. Their element objects and {@code recipeTable} remain shared;
 * this record does not provide a deep snapshot of mutable payloads.
 *
 * @param itemDefinitions item definitions in network order
 *
 * @param blockDefinitions block definitions in source registry order
 *
 * @param creativeGroups creative inventory groups in network order
 *
 * @param creativeItems creative inventory entries in network order
 *
 * @param customBlockProperties custom block properties in network order
 *
 * @param recipeTable encoded recipes and their server-side lookup index
 * @param blockPalette the block palette the protocol's clients know block states by
 * @param itemPalette the items the protocol's clients know
 */
public record ProtocolData(
        List<ItemDefinition> itemDefinitions,
        List<BlockDefinition> blockDefinitions,
        List<CreativeItemGroup> creativeGroups,
        List<CreativeItemData> creativeItems,
        List<BlockPropertyData> customBlockProperties,
        RecipeTable recipeTable,
        NetworkBlockPalette blockPalette,
        NetworkItemPalette itemPalette
) {
    /**
     * Creates a protocol payload bundle with copied, unmodifiable lists.
     * {@code null} lists, {@code null} list elements, and a {@code null} recipe
     * table are rejected; element objects and the recipe table retain their
     * supplied identities.
     */
    public ProtocolData {
        itemDefinitions = List.copyOf(Objects.requireNonNull(itemDefinitions, "itemDefinitions"));
        blockDefinitions = List.copyOf(Objects.requireNonNull(blockDefinitions, "blockDefinitions"));
        creativeGroups = List.copyOf(Objects.requireNonNull(creativeGroups, "creativeGroups"));
        creativeItems = List.copyOf(Objects.requireNonNull(creativeItems, "creativeItems"));
        customBlockProperties = List.copyOf(Objects.requireNonNull(customBlockProperties, "customBlockProperties"));
        recipeTable = Objects.requireNonNull(recipeTable, "recipeTable");
        blockPalette = Objects.requireNonNull(blockPalette, "blockPalette");
        itemPalette = Objects.requireNonNull(itemPalette, "itemPalette");
    }
}
