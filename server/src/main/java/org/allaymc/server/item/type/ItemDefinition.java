package org.allaymc.server.item.type;

import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemVersion;

/**
 * Item definition sent to the client for custom item registration.
 * <p>
 * The NBT structure (when component-based) contains:
 * <ul>
 *   <li>{@code components} - Item components and properties
 *     <ul>
 *       <li>{@code item_properties} - Legacy properties (max_stack_size, allow_off_hand, etc.)</li>
 *       <li>{@code minecraft:display_name} - Display name with {@code value} field</li>
 *       <li>{@code minecraft:icon} - Icon with {@code textures} map</li>
 *       <li>{@code minecraft:durability} - Durability with {@code max_durability}</li>
 *       <li>{@code minecraft:tags} - Item tags array</li>
 *       <li>Other components like armor, wearable, food, cooldown, etc.</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * @param componentBased whether this item uses the component-based (data-driven) format
 * @param components     the NBT data containing item components and properties
 * @param version        the item version for protocol compatibility
 *
 * @author daoge_cmd
 * @see CustomItemDefinitionGenerator
 * @see <a href="https://wiki.bedrock.dev/items/item-components">Bedrock Item Components</a>
 */
public record ItemDefinition(boolean componentBased, NbtMap components, ItemVersion version) {
    public static final ItemDefinition DEFAULT = new ItemDefinition(false, NbtMap.EMPTY, ItemVersion.NONE);
}
