package org.allaymc.api.container.impl;

import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.item.ItemStack;

import static org.allaymc.api.container.FullContainerType.CREATED_OUTPUT;

/**
 * @author daoge_cmd
 */
public class PlayerCreatedOutputContainer extends BaseContainer {
    public PlayerCreatedOutputContainer() {
        super(CREATED_OUTPUT);
    }

    public void setItemStack(ItemStack itemStack) {
        setItemStack(0, itemStack, false);
    }
}
