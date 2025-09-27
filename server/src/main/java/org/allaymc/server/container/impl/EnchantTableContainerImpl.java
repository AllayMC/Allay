package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.EnchantTableContainer;

/**
 * @author daoge_cmd
 */
public class EnchantTableContainerImpl extends BlockContainerImpl implements EnchantTableContainer {
    public EnchantTableContainerImpl() {
        super(ContainerTypes.ENCHANT_TABLE);
    }
}
