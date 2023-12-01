package org.allaymc.api.container.impl;

import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.item.ItemStack;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;

import static org.allaymc.api.container.FullContainerType.CREATED_OUTPUT;

/**
 * Allay Project 2023/7/28
 *
 * @author daoge_cmd
 */
public class PlayerCreatedOutputContainer extends BaseContainer {
    public PlayerCreatedOutputContainer() {
        super(CREATED_OUTPUT);
    }
}
