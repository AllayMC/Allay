package org.allaymc.api.utils;

import org.allaymc.api.AllayAPI;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;

/**
 * {@code NBTIO} reconstructs registered game objects from persistent NBT and exposes the
 * server-installed implementation to API callers. Block and item decoding update older data,
 * while each object family defines its own unknown-type or malformed-data result instead of
 * sharing one universal failure sentinel.
 *
 * @author daoge_cmd
 */
public interface NBTIO {

    /**
     * {@code API} holds the server-installed NBT decoder implementation. {@code getAPI} resolves
     * this holder, so callers require API initialization before requesting deserialization
     * services.
     */
    AllayAPI.APIInstanceHolder<NBTIO> API = AllayAPI.APIInstanceHolder.create();

    /**
     * Returns the NBT decoder implementation installed in the API holder.
     */
    static NBTIO getAPI() {
        return API.get();
    }

    /**
     * Decodes a block state after updating older serialized versions and supplying missing
     * property defaults. Unsupported future versions, unknown types, or rejected state selection
     * use {@link BlockTypes#UNKNOWN}; other malformed property representations can still throw.
     */
    BlockState fromBlockStateNBT(NbtMap nbt);

    /**
     * Updates stored item data and creates its registered stack. A missing count defaults to one,
     * and failed deserialization returns {@link ItemAirStack#AIR_STACK} after the implementation
     * reports the failure rather than propagating that conversion error.
     */
    ItemStack fromItemStackNBT(NbtMap nbt);

    /**
     * Creates an entity in the supplied dimension using its stored registered identifier. Unknown
     * entity types return {@code null}; malformed identifiers or failures constructing a resolved
     * type can still propagate to the caller.
     */
    Entity fromEntityNBT(Dimension dimension, NbtMap nbt);

    /**
     * Creates a block entity in the supplied dimension using its stored type ID. Unknown IDs
     * return {@code null}, while failures constructing a resolved block-entity type can still
     * propagate to the caller.
     */
    BlockEntity fromBlockEntityNBT(Dimension dimension, NbtMap nbt);
}
