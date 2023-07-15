package cn.allay.server.inventory.impl;

import cn.allay.api.entity.impl.EntityPlayer;
import cn.allay.server.inventory.BaseInventory;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public class PlayerCursorInventory extends BaseInventory {
    public PlayerCursorInventory(EntityPlayer holder) {
        super(holder, 1);
    }

    @Override
    public ContainerType getType() {
        return ContainerType.INVENTORY;
    }
}
