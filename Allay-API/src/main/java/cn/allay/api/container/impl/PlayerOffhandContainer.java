package cn.allay.api.container.impl;

import cn.allay.api.container.BaseContainer;
import cn.allay.api.container.FullContainerType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType;

/**
 * Allay Project 2023/7/22
 *
 * @author daoge_cmd
 */
public class PlayerOffhandContainer extends BaseContainer {
    public PlayerOffhandContainer() {
        super(1);
    }

    @Override
    public ContainerSlotType getSlotType(int slot) {
        return ContainerSlotType.OFFHAND;
    }

    @Override
    public FullContainerType getContainerType() {
        return FullContainerType.OFFHAND;
    }
}
