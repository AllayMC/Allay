package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.FurnaceContainer;

/**
 * {@code FurnaceContainerImpl} supplies the three processing slots and block location
 * for furnace-family inventories. The furnace interface supplies named ingredient, fuel,
 * and result accessors plus directional automation rules, while subclasses select
 * distinct container identities for blast furnaces and smokers.
 *
 * @author daoge_cmd
 */
public class FurnaceContainerImpl extends BlockContainerImpl implements FurnaceContainer {
    /**
     * Creates empty processing storage using the standard furnace container identity.
     */
    public FurnaceContainerImpl() {
        super(ContainerTypes.FURNACE);
    }

    /**
     * Creates processing storage with the supplied furnace-family type identity. The
     * caller must select a layout containing the ingredient, fuel, and result indices
     * required by the inherited furnace accessors.
     */
    public FurnaceContainerImpl(ContainerType<?> containerType) {
        super(containerType);
    }
}
