package cn.allay.api.container.impl;

import cn.allay.api.container.BaseContainer;
import cn.allay.api.container.FullContainerType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;

/**
 * Allay Project 2023/7/22
 *
 * @author daoge_cmd
 */
public class PlayerArmorContainer extends BaseContainer {

    public PlayerArmorContainer() {
        super(4);
    }

    @Override
    public ContainerSlotType getSlotType(int slot) {
        return ContainerSlotType.ARMOR;
    }

    @Override
    public FullContainerType getContainerType() {
        return FullContainerType.ARMOR;
    }
}
