package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.EnchantTableContainer;

/**
 * {@code EnchantTableContainerImpl} supplies 2-slot item storage for the enchanting
 * table container layout. The enchanting interface supplies named accessors for the
 * input item and enchantment material. Its constructor selects the shared {@code
 * ENCHANT_TABLE} type, and the base implementation manages item assignment and container
 * observers.
 *
 * @author daoge_cmd
 */
public class EnchantTableContainerImpl extends BlockContainerImpl implements EnchantTableContainer {
    /**
     * Creates empty enchanting table storage using the shared {@code ENCHANT_TABLE}
     * container type.
     */
    public EnchantTableContainerImpl() {
        super(ContainerTypes.ENCHANT_TABLE);
    }
}
