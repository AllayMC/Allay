package cn.allay.api.container.impl;

import cn.allay.api.container.BaseContainer;
import cn.allay.api.container.FullContainerType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public class PlayerCursorContainer extends BaseContainer {
    public PlayerCursorContainer() {
        super(1);
    }

    @Override
    public ContainerSlotType getSlotType(int slot) {
        return ContainerSlotType.CURSOR;
    }

    @Override
    public FullContainerType getContainerType() {
        return FullContainerType.CURSOR;
    }
}
