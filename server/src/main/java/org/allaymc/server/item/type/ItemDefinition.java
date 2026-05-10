package org.allaymc.server.item.type;

import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemVersion;

/**
 * ItemDefinition holds the client-side registration data for a single item type.
 * <p>
 * This record is used as the return type of {@link ItemDefinitionGenerator} and is forwarded
 * to the Bedrock item registry during network encoding. For component-based items, the payload
 * typically contains a root {@code components} compound with entries such as
 * {@code item_properties}, {@code minecraft:display_name}, {@code minecraft:durability},
 * and other client-recognized item components.
 * <p>
 * Server-side gameplay behavior such as actual stack handling, durability consumption, and
 * item logic is still driven by {@link org.allaymc.api.item.type.ItemType} and its components;
 * this definition only describes what the client needs to register and render the item.
 *
 * @param componentBased whether the protocol should treat this item as a data-driven/component-based item
 * @param components     the raw NBT payload sent for this item definition, usually containing a root {@code components} compound
 * @param version        the Bedrock item definition version used for protocol compatibility
 * @author daoge_cmd
 * @see ItemDefinitionGenerator
 * @see CustomItemDefinitionGenerator
 * @see <a href="https://wiki.bedrock.dev/items/item-components">Bedrock Item Components</a>
 */
public record ItemDefinition(boolean componentBased, NbtMap components, ItemVersion version) {
    /**
     * Default definition for items that do not provide custom component data.
     */
    public static final ItemDefinition DEFAULT = new ItemDefinition(false, NbtMap.EMPTY, ItemVersion.NONE);
}
