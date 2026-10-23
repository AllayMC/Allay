package org.allaymc.api.blockentity.component;

import org.allaymc.api.container.Container;

/**
 * {@code BlockEntityChestContainerHolderComponent} exposes a chest's own
 * container and the combined view used while paired. The combined container
 * delegates item storage to both chests, so callers must obtain it for the
 * current pairing instead of treating it as independent inventory storage.
 *
 * @author daoge_cmd
 */
public interface BlockEntityChestContainerHolderComponent extends BlockEntityContainerHolderComponent {
    /**
     * Returns the shared combined container configured for the current chest
     * pair. Callers must use this view only while the chest is paired; an
     * unpaired call can expose unconfigured or previously configured underlying
     * containers.
     */
    Container getDoubleChestContainer();
}
