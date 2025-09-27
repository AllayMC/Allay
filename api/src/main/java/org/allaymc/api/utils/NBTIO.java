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
 * Responsible for reading and converting NBT data into corresponding in-memory game objects.
 * This interface includes methods for deserializing block states, item stacks, entities,
 * and block entities from their respective NBT representations. Updating will also be applied
 * if the nbt data is outdated.
 *
 * @author daoge_cmd
 */
public interface NBTIO {

    AllayAPI.APIInstanceHolder<NBTIO> API = AllayAPI.APIInstanceHolder.create();

    /**
     * Get the API instance.
     *
     * @return the API instance
     */
    static NBTIO getAPI() {
        return API.get();
    }

    /**
     * Get the block state from the block state NBT.
     *
     * @param nbt the block state NBT
     * @return the block state, or the default state of {@link BlockTypes#UNKNOWN}
     * if the block state is not found, or the block state version is too new.
     */
    BlockState fromBlockStateNBT(NbtMap nbt);

    /**
     * Create an {@link ItemStack} from an NBT tag.
     * <p>
     * This method will also update the item state to the latest version.
     *
     * @param nbt The NBT tag
     * @return The {@link ItemStack} object, or {@link ItemAirStack#AIR_STACK} if failed to deserialize the nbt
     */
    ItemStack fromItemStackNBT(NbtMap nbt);

    /**
     * Creates a new entity from a NBT map.
     *
     * @param dimension The dimension the entity is in
     * @param nbt       The NBT map to create the entity from
     * @return The new entity, or {@code null} if the provided entity type is unknown
     */
    Entity fromEntityNBT(Dimension dimension, NbtMap nbt);

    /**
     * Creates a new block entity from a NBT map.
     *
     * @param dimension the dimension the block entity is in
     * @param nbt       the NBT map to create the block entity from
     * @return the new block entity, or {@code null} if the provided block entity type is unknown
     */
    BlockEntity fromBlockEntityNBT(Dimension dimension, NbtMap nbt);
}
