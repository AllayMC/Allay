package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemCrossbowBaseComponent;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.interfaces.ItemCrossbowStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemCrossbowStackImpl} represents a server item stack with delegated
 * crossbow load access and repair material checks. {@code ItemStackImpl}
 * supplies the shared stack state and item operations, while the local delegates
 * expose their component contracts through the same typed stack object.
 */
public class ItemCrossbowStackImpl extends ItemStackImpl implements ItemCrossbowStack {

    // crossbowBaseComponent and repairableComponent receive components created
    // for this stack. ComponentClass populates both fields during construction
    // before delegated use and rejects missing or ambiguous compatible provider results.
    /**
     * Loaded crossbow projectile delegate.
     */
    @Delegate
    private ItemCrossbowBaseComponent crossbowBaseComponent;
    /**
     * Repair material compatibility delegate.
     */
    @Delegate
    private ItemRepairableComponent repairableComponent;

    /**
     * Creates the typed stack using the supplied initialization data and component
     * providers. {@code ComponentClass} requires exactly one compatible provider
     * result for each delegate type, including the inherited base component;
     * missing or ambiguous matches fail construction.
     */
    public ItemCrossbowStackImpl(ItemStackInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
