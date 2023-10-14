package cn.allay.api.world.storage;

import cn.allay.api.entity.interfaces.player.EntityPlayer;

/**
 * Allay Project 2023/9/16
 *
 * @author daoge_cmd
 */
public interface PlayerStorage {

    void readPlayerData(EntityPlayer player);

    void writePlayerData(EntityPlayer player);
}
