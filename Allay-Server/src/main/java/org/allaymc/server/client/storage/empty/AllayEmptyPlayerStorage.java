package org.allaymc.server.client.storage.empty;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.client.storage.PlayerStorage;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Allay Project 2023/9/16
 *
 * @author daoge_cmd
 */
public class AllayEmptyPlayerStorage implements PlayerStorage {

    @Override
    public void readPlayerData(EntityPlayer player) {
        // Empty
    }

    @Override
    public void writePlayerData(EntityPlayer player) {
        // Empty
    }

    @Override
    public List<NbtMap> readPlayerInventory(UUID uuid, FullContainerType<?> containerType) {
        return Collections.emptyList();
    }

    @Override
    public void writePlayerInventory(UUID uuid, Container container) {

    }

    @Override
    public boolean hasPlayerData(UUID uuid) {
        return false;
    }
}
