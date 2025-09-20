package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.interfaces.EnchantTableContainer;

/**
 * @author daoge_cmd
 */
public class EnchantTableContainerImpl extends BlockContainerImpl implements EnchantTableContainer {
    public EnchantTableContainerImpl() {
        super(ContainerType.ENCHANT_TABLE);
    }
}
