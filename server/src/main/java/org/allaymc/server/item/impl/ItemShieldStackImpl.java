package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.component.ItemShieldBaseComponent;
import org.allaymc.api.item.interfaces.ItemShieldStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemShieldStackImpl} represents a server item stack with delegated
 * shield blocking rules and repair material checks. {@code ItemStackImpl}
 * supplies the shared stack state and item operations, while the local delegates
 * expose their component contracts through the same typed stack object.
 */
public class ItemShieldStackImpl extends ItemStackImpl implements ItemShieldStack {
    // shieldBaseComponent and repairableComponent receive components created
    // for this stack. ComponentClass populates these fields during
    // construction and rejects missing or ambiguous provider results before
    // the stack becomes usable.
    /**
     * Shield blocking rule delegate.
     */
    @Delegate
    private ItemShieldBaseComponent shieldBaseComponent;

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
    public ItemShieldStackImpl(ItemStackInitInfo initInfo,
                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
