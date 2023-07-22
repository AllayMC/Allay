package cn.allay.api.inventory.impl;

import cn.allay.api.inventory.BaseInventory;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType;

/**
 * Allay Project 2023/7/22
 *
 * @author daoge_cmd
 */
public class PlayerOffhandInventory extends BaseInventory {
    public PlayerOffhandInventory() {
        super(1);
    }

    @Override
    public ContainerType getType() {
        return ContainerType.INVENTORY;
    }
}
