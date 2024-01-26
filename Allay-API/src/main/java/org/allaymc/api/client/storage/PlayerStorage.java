package org.allaymc.api.client.storage;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.nbt.NbtMap;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.allaymc.api.container.FullContainerType.*;

/**
 * Allay Project 2023/9/16
 *
 * @author daoge_cmd
 */
public interface PlayerStorage {
    default void tick(long currentTick) {}

    default void readPlayerData(EntityPlayer player) {
        var uuid = player.getUUID();
        player.getContainer(PLAYER_INVENTORY).loadNBT(readPlayerInventory(uuid, PLAYER_INVENTORY));
        player.getContainer(ARMOR).loadNBT(readPlayerInventory(uuid, ARMOR));
        player.getContainer(OFFHAND).loadNBT(readPlayerInventory(uuid, OFFHAND));
    }

    default void writePlayerData(EntityPlayer player) {
    }

    List<NbtMap> readPlayerInventory(UUID uuid, FullContainerType<?> containerType);

    void writePlayerInventory(UUID uuid, Container container);



    boolean hasPlayerData(UUID uuid);

    default boolean hasPlayerData(EntityPlayer player) {
        return hasPlayerData(player.getUUID());
    }
}
