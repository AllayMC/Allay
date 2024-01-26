package org.allaymc.server.client.storage.empty;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.client.storage.PlayerStorage;

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
}
