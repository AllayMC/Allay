package org.allaymc.server.item.type;

import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemVersion;

/**
 * {@code VanillaItemDefinitionData} holds the raw client-registration payload for
 * a vanilla item. The internal vanilla registry retains these values for protocol
 * initialization, while item types and their components supply server gameplay
 * behavior independently of the encoded client definition.
 *
 * @param componentBased client component-based registration flag
 * @param components raw item-definition NBT payload
 * @param version Bedrock item-definition compatibility version
 *
 * @author daoge_cmd
 */
public record VanillaItemDefinitionData(boolean componentBased, NbtMap components, ItemVersion version) {
}
