package org.allaymc.api.client.storage;

import org.allaymc.api.client.data.Abilities;
import org.allaymc.api.client.data.AdventureSettings;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3fc;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.GameType;

import java.util.List;
import java.util.UUID;

import static org.allaymc.api.container.FullContainerType.*;

/**
 * Allay Project 2023/9/16
 *
 * @author daoge_cmd
 */
public interface PlayerStorage {
    default void tick(long currentTick) {}

    PlayerData readPlayerData(UUID uuid);

    default PlayerData readPlayerData(EntityPlayer player) {
        return readPlayerData(player.getUUID());
    }

    void savePlayerData(UUID uuid, PlayerData playerData);

    default void savePlayerData(EntityPlayer player) {
        savePlayerData(player.getUUID(), player.savePlayerData());
    }

    boolean removePlayerData(UUID uuid);

    boolean hasPlayerData(UUID uuid);

    default boolean hasPlayerData(EntityPlayer player) {
        return hasPlayerData(player.getUUID());
    }
}
