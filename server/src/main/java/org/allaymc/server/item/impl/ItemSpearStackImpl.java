package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.interfaces.ItemSpearStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemSpearStackImpl} represents a server item stack with delegated
 * repair material checks. {@code ItemStackImpl} supplies the shared stack state
 * and item operations, while the local delegates expose their component
 * contracts through the same typed stack object.
 */
public class ItemSpearStackImpl extends ItemStackImpl implements ItemSpearStack {
    /**
     * {@code repairableComponent} holds the injected delegate for repair material checks.
     * {@code ComponentClass} assigns this stack's unique compatible provider result
     * during construction before delegated operations can be used.
     */
    @Delegate
    private ItemRepairableComponent repairableComponent;

    /**
     * Creates the typed stack using the supplied initialization data and component
     * providers. {@code ComponentClass} requires exactly one compatible provider
     * result for each delegate type, including the inherited base component;
     * missing or ambiguous matches fail construction.
     */
    public ItemSpearStackImpl(ItemStackInitInfo initInfo,
                              List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
