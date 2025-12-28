package org.allaymc.api.blockentity.component;

import org.allaymc.api.container.Container;

/**
 * @author daoge_cmd
 */
public interface BlockEntityChestContainerHolderComponent extends BlockEntityContainerHolderComponent {
    /**
     * Gets the container object to use when the chest is paired. Please note that the returned
     * container object should only be used when the chest is paired, otherwise undefined behavior
     * will occur.
     *
     * @return the container object to use when the chest is paired
     */
    Container getDoubleChestContainer();
}
