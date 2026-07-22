package org.allaymc.server.item.type;

import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemVersion;

/**
 * Raw client registration data loaded for a vanilla item.
 * <p>
 * This data remains in the internal vanilla registry. It is consumed by protocol initialization and is not stored
 * on {@link AllayItemType}, keeping item types independent of encoded protocol payloads.
 * <p>
 * Server-side gameplay behavior such as actual stack handling, durability consumption, and
 * item logic is still driven by {@link org.allaymc.api.item.type.ItemType} and its components;
 * this definition only describes what the client needs to register and render the item.
 *
 * @param componentBased whether the protocol should treat this item as a data-driven/component-based item
 * @param components     the raw NBT payload sent for this item definition, usually containing a root {@code components} compound
 * @param version        the Bedrock item definition version used for protocol compatibility
 * @author daoge_cmd
 */
public record VanillaItemDefinitionData(boolean componentBased, NbtMap components, ItemVersion version) {
}
