package org.allaymc.server.world.storage.nonpersistent;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.world.storage.PlayerStorage;

/**
 * Allay Project 2023/9/16
 *
 * @author daoge_cmd
 */
public class AllayNullPlayerStorage implements PlayerStorage {

    @Override
    public void readPlayerData(EntityPlayer player) {
        // Empty
    }

    @Override
    public void writePlayerData(EntityPlayer player) {
        // Empty
    }
}
