package cn.allay.api.inventory.impl;

import cn.allay.api.inventory.BaseInventory;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public class PlayerCursorInventory extends BaseInventory {
    public PlayerCursorInventory() {
        super(1);
    }

    @Override
    public ContainerType getType() {
        //TODO: ContainerType.HAND?
        return ContainerType.INVENTORY;
    }
}
