package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemEdibleComponent;
import org.allaymc.api.item.interfaces.ItemDriedKelpStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemDriedKelpStackImpl} implements the dried kelp item API through
 * supplied component delegates. {@link ItemStackImpl} supplies ordinary stack
 * behavior, while {@code edibleComponent} exposes eating eligibility, eating
 * time, nutrition values, drink behavior, and consumption callbacks. Callers
 * access these contracts through this stack instance.
 */
public class ItemDriedKelpStackImpl extends ItemStackImpl implements ItemDriedKelpStack {
    /**
     * {@code edibleComponent} holds the injected {@link ItemEdibleComponent}
     * implementation. Lombok-generated forwarding methods use this instance
     * after superclass component initialization completes.
     */
    @Delegate
    private ItemEdibleComponent edibleComponent;

    /**
     * Creates the dried kelp stack using {@code initInfo} and
     * {@code componentProviders}. {@code componentProviders} must produce
     * exactly one assignable implementation for each component-typed field,
     * including inherited fields; superclass construction injects these
     * instances before this constructor returns.
     */
    public ItemDriedKelpStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
