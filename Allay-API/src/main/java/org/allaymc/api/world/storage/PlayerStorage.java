package org.allaymc.api.world.storage;

import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Allay Project 2023/9/16
 *
 * @author daoge_cmd
 */
public interface PlayerStorage {

    void readPlayerData(EntityPlayer player);

    void writePlayerData(EntityPlayer player);
}
